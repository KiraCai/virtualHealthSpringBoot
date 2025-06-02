package virtualhealth.dto;

public class PneumoniaRiskDTO {
    public Integer age;
    public Boolean confusion;
    public Double bloodUreaNitrogen;
    public Integer respiratoryRate;
    public Integer systolicBloodPressure;
    public Double temperature;
    public Comorbidities comorbidities;

    public static class Comorbidities {
        public Boolean cancer;
        public Boolean liverDisease;
        public Boolean heartFailure;
        public Boolean cerebrovascularDisease;
        public Boolean renalDisease;
    }
}
