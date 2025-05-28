package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import virtualhealth.config.JwtUtil;
import virtualhealth.dto.ClientUpdateDTO;
import virtualhealth.dto.PatientFullProfileDTO;
import virtualhealth.dto.TestDisplayDTO;
import virtualhealth.dto.VaccinationDisplayDTO;
import virtualhealth.mapper.*;
import virtualhealth.model.*;
import virtualhealth.service.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0.1/users")
@AllArgsConstructor
//todo 18.05 добавила
public class UserController {

    private UserService userService; //нашел реализацию контроллера и использует её первый уровень исп второй контроллер - сервис
    private HistoryService historyService;
    private DataService<Test> dataServiceTest;
    private DataService<Prescription> dataServicePrescr;
    private DataService<Vaccination> dataServiceVacc;
    private DataService<Consultation> dataServiceConsult;
    private DoctorService doctorService;
    private CalendarService calendarService;

    private ClientMapper clientMapper;
    private TestMapper testMapper;
    private PrescriptionMapper prescriptionMapper;
    private VaccinationMapper vaccinationMapper;
    private ConsultationMapper consultationMapper;
    private ScheduleMapper scheduleMapper;

    @GetMapping
    public List<Client> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Client client) {
        try {
            Client foundClient = userService.findByEmail(client.getEmail());
            if (foundClient == null || !foundClient.getPassword().equals(client.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect login or password");
            }
            try {
                String token = JwtUtil.generateToken(foundClient.getEmail());
                return ResponseEntity.ok().body(Map.of("token", token));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Error generating token");
            }

        } catch (java.lang.Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        }

    }

    @PostMapping("/signup")
    public void addUser(@RequestBody Client client) {
        System.out.println("JSON: " + client);
        userService.addUser(client);
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(@RequestHeader("Authorization") String authHeader) {
        try {
            if (!authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Токен отсутствует или некорректен");
            }
            String token = authHeader.substring(7);
            String email = JwtUtil.validateTokenAndGetEmail(token);
            Client client = userService.findByEmail(email);
            return ResponseEntity.ok(client);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный или просроченный токен");
        }
    }

    @GetMapping("/profile/full")
    public ResponseEntity getFullProfile(@RequestHeader("Authorization") String authHeader) {
        if (!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String token = authHeader.substring(7);
        String email = JwtUtil.validateTokenAndGetEmail(token);
        Client client = userService.findByEmail(email);
        History history = historyService.findByIdClient(client.getIdClient());

        PatientFullProfileDTO dto = new PatientFullProfileDTO();
        dto.setClient(clientMapper.toDTO(client));
        dto.setTests(testMapper.toDTOs(dataServiceTest.findAllByIdHistory(history.getIdHistory())));
        dto.setPrescriptions(prescriptionMapper.toDTOs(dataServicePrescr.findAllByIdHistory(history.getIdHistory())));
        dto.setVaccinations(vaccinationMapper.toDTOs(dataServiceVacc.findAllByIdHistory(history.getIdHistory())));
        dto.setConsultations(consultationMapper.toDTOs(dataServiceConsult.findAllByIdHistory(history.getIdHistory())));
        dto.setReservations(scheduleMapper.toDTOs(calendarService.findByIdClient(client.getIdClient())));

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/profile/full/add_vaccination")
    public ResponseEntity<String> addVaccination(@RequestBody VaccinationDisplayDTO dto, Principal principal) {
        String email = principal.getName();
        Client client = userService.findByEmail(email);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        Vaccination vaccination = new Vaccination();
        History history = historyService.findByIdClient(client.getIdClient());
        vaccination.setName(dto.getName());
        vaccination.setDate(dto.getDate());
        vaccination.setTime(dto.getTime());
        vaccination.setReason(dto.getReason());
        vaccination.setPlace(dto.getPlace());
        vaccination.setNameVac(dto.getNameVac());
        vaccination.setDocument(dto.getDocument());
        vaccination.setHistory(history);

        dataServiceVacc.addData(vaccination);
        System.out.println(dto);
        return ResponseEntity.ok("Vaccination successfully added");
    }

    @PostMapping("/profile/full/add_test")
    public ResponseEntity<String> addTest(@RequestBody TestDisplayDTO dto, Principal principal) {
        String email = principal.getName();
        Client client = userService.findByEmail(email);
        History history = historyService.findByIdClient(client.getIdClient());

        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        Test test = new Test();
        test.setNameTest(dto.getNameTest());
        test.setDate(dto.getDate());
        test.setTime(dto.getTime());
        test.setResult(dto.getResult());
        test.setPlace(dto.getPlace());
        test.setReason(dto.getReason());
        test.setDocument(dto.getDocument());
        test.setHistory(history);

        dataServiceTest.addData(test);
        System.out.println(dto);
        return ResponseEntity.ok("Test added successfully");
    }


    @PutMapping("/profile/update")
    public ResponseEntity updateProfile(@RequestBody ClientUpdateDTO dto, Principal principal) {
        System.out.println("Principal: " + principal.getName()); //todo email
        Client user = userService.findByEmail(principal.getName());

        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getSex() != null && !dto.getSex().isEmpty()) {
            user.setSex(dto.getSex());
        }
        if (dto.getTel() != null) user.setTel(dto.getTel());
        if (dto.getDateBirth() != null && !dto.getDateBirth().isEmpty()) {
            user.setDateBirth(LocalDate.parse(dto.getDateBirth()));
        }

        try {
            userService.updateUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Profile update error");
        }
    }

    //получение почты через url адресс
    @GetMapping("/{email}")
    public Client findUserById(@PathVariable("email") String email) { //идет неявное сопоставление переменной
        return userService.findByEmail(email);
    }

    @PutMapping("update_client")
    public Client updateUser(@RequestBody Client client) {
        return userService.updateUser(client);
    }   //работает

    @DeleteMapping("delete_user/{email}")
    public void deleteUserByEmail(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
    }
}
