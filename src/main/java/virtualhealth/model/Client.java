package virtualhealth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "client")
public class Client {
    //todo добавить ограничения обязательные к заполнению поля и тип данных документа и фото удалять не из всех таблиц все
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "client_id")
    private Long idClient;

    // history table
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private History history;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "sex")
    Character sex;

    @Column(unique = true, name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "tel")
    String tel;

    @Column(name = "address")
    String address;

    @Column(name = "document")
    String document;

    @Column(name = "photo")
    String photo;

}
