package virtualhealth.webresources.uniprot;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MsaConservationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String MAFFT_STATUS_URL = "https://www.ebi.ac.uk/Tools/services/rest/mafft/status/";
    private static final String MAFFT_RESULT_URL = "https://www.ebi.ac.uk/Tools/services/rest/mafft/result/";

    /**
     * todo Get UniProt sequences by UniProtID (simplified)
     */
    public List<String> getUniProtSequences(String uniprotId) {
        System.out.println(uniprotId);
        String url = "https://rest.uniprot.org/uniprotkb/" + uniprotId + ".fasta";
        System.out.println(url);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String fasta = response.getBody();

            return List.of(fasta);
        } else {
            throw new RuntimeException("Error getting UniProt sequence " + uniprotId);
        }
    }

    public List<String> getSimilarSequencesByBlast(String sequence, int maxHits) throws InterruptedException {
        String runUrl = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast/run";
        String statusUrl = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast/status/";
        String resultUrl = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast/result/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sequence", sequence);
        params.add("stype", "protein");
        params.add("database", "uniprotkb_swissprot");
        params.add("program", "blastp");
        params.add("email", "kiraswav@gmail.com");
        params.forEach((k, v) -> System.out.println(k + ": " + v));
        // todo Send a BLAST request with parameters
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(runUrl, request, String.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("BLAST запуск не удался: " + response.getStatusCode());
        }
        String jobId = response.getBody();
        // todo We are waiting for the completion
        Integer retries = 30;
        String status;
        do {
            Thread.sleep(6000);
            status = restTemplate.getForObject(statusUrl + jobId, String.class);
        } while (!"FINISHED".equals(status) && retries-- > 0);

        if (!"FINISHED".equals(status)) {
            throw new RuntimeException("BLAST is not completed on time");
        }
        // todo Obtaining hit identifiers (UniProt ID)
        String idsText = restTemplate.getForObject(resultUrl + jobId + "/ids", String.class);

        List<String> ids = Arrays.stream(idsText.split("\n"))
                .filter(line -> line.startsWith("SP:"))
                .map(line -> line.substring(3))
                .distinct()
                .limit(maxHits)
                .collect(Collectors.toList());
        System.out.println("Similar sequences found: " + ids.size());

        // todo We load the sequences themselves for each ID
        List<String> sequences = new ArrayList<>();
        for (String id : ids) {
            try {
                String urlClean = "https://rest.uniprot.org/uniprotkb/" + id + ".fasta";
                System.out.println(urlClean);
                String fasta = restTemplate.getForObject(urlClean, String.class);
                if (fasta != null) sequences.add(fasta);
            } catch (Exception e) {
                System.out.println("Error loading sequence: " + id);
            }
        }
        return sequences;
    }

    /**
     * todo Launches MAFFT
     */
    public String submitMsaJob(List<String> sequencesFasta) {
        List<String> formattedFasta = new ArrayList<>();
        for (int i = 0; i < sequencesFasta.size(); i++) {
            String seq = sequencesFasta.get(i).replaceAll(">.*\\n", "").replaceAll("\\s+", "");
            formattedFasta.add(">seq" + (i + 1));
            formattedFasta.add(seq);
        }
        String fastaInput = String.join("\n", formattedFasta);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sequence", fastaInput);
        params.add("email", "kiraswav@gmail.com");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String mafftRunUrl = "https://www.ebi.ac.uk/Tools/services/rest/mafft/run";
        ResponseEntity<String> response = restTemplate.postForEntity(mafftRunUrl, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody(); // jobId
        } else {
            throw new RuntimeException("MAFFT startup error: " + response.getStatusCode());
        }
    }

    /**
     * todo Checks the status of the task execution
     */
    public String getJobStatus(String jobId) {
        String urll = MAFFT_STATUS_URL + jobId;
        ResponseEntity<String> response = restTemplate.getForEntity(urll, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error getting task status: " + response.getStatusCode());
        }
    }

    /**
     * todo Gets alignment in FASTA format
     */
    public String getMsaResult(String jobId) {
        String url = MAFFT_RESULT_URL + jobId + "/out";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error getting result MAFFT: " + response.getStatusCode());
        }
    }

    /**
     * todo Parsing FASTA alignment into a list of sequences (without headers)
     */
    public List<String> parseFastaAlignment(String fasta) {
        List<String> sequences = new ArrayList<>();
        StringBuilder currentSeq = new StringBuilder();

        for (String line : fasta.split("\n")) {
            if (line.startsWith(">")) {
                if (currentSeq.length() > 0) {
                    sequences.add(currentSeq.toString());
                    currentSeq = new StringBuilder();
                }
            } else {
                currentSeq.append(line.trim());
            }
        }
        if (currentSeq.length() > 0) {
            sequences.add(currentSeq.toString());
        }
        return sequences;
    }

    /**
     * todo Calculating Shannon Entropy for an Alignment Column
     */
    public double shannonEntropy(List<Character> column) {
        Map<Character, Integer> counts = new HashMap<>();
        for (Character c : column) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        double entropy = 0.0;
        int n = column.size();
        for (int count : counts.values()) {
            double p = (double) count / n;
            entropy -= p * (Math.log(p) / Math.log(2));
        }
        return entropy;
    }

    /**
     * todo Calculates entropy over alignment positions, returns Map: position (1-based) -> entropy
     */
    public Map<Integer, Double> calculateEntropyPerPosition(List<String> alignedSequences) {
        int length = alignedSequences.get(0).length();
        Map<Integer, Double> entropyMap = new HashMap<>();

        for (int i = 0; i < length; i++) {
            List<Character> column = new ArrayList<>();
            for (String seq : alignedSequences) {
                column.add(seq.charAt(i));
            }
            double entropy = shannonEntropy(column);
            entropyMap.put(i + 1, entropy);
        }
        return entropyMap;
    }

    /**
     * todo Main method: takes UniProt ID,
     * todo returns position map -> entropy
     */
    public Map<Integer, Double> getConservationByUniProtId(String uniprotId) throws InterruptedException {
        // Get the main sequence
        String selfFasta = getUniProtSequences(uniprotId).get(0);
        String sequence = selfFasta.replaceAll(">.*\\n", "").replaceAll("\\s+", "");

        // todo Get similar sequences via BLAST !30 max
        List<String> similarSeqs = getSimilarSequencesByBlast(sequence, 30);
        if (similarSeqs.isEmpty()) {
            System.out.println("BLAST returned no similar sequences. Perform MSA on own sequence only.");
            similarSeqs = List.of(selfFasta);
        }
        // Let's add our own sequence to the beginning.
        similarSeqs.add(0, selfFasta);
        // MSA
        String jobId = submitMsaJob(similarSeqs);
        String status;
        int retries = 20;
        do {
            Thread.sleep(3000);
            status = getJobStatus(jobId);
            System.out.println("MSA job status: " + status);
            retries--;
        } while (!"FINISHED".equals(status) && retries > 0);

        if (!"FINISHED".equals(status)) {
            throw new RuntimeException("MSA task not completed on time");
        }

        String msaFasta = getMsaResult(jobId);
        List<String> alignedSeqs = parseFastaAlignment(msaFasta);
        return calculateEntropyPerPosition(alignedSeqs);
    }
}

