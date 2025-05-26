package virtualhealth.dto;

import lombok.Data;

@Data
public class ClientUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String sex;
    private String tel;
    private String dateBirth;
}
