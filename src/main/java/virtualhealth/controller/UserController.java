package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import virtualhealth.config.JwtUtil;
import virtualhealth.dto.ClientUpdateDTO;
import virtualhealth.model.Client;
import virtualhealth.service.UserService;

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

    @GetMapping
    public List<Client> findAllUsers() {
        return userService.findAllUsers();
    } //работает

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
