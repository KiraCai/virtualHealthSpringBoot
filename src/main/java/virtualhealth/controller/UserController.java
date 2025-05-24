package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import virtualhealth.model.Client;
import virtualhealth.service.UserService;

import java.util.List;

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
            if (foundClient == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Пользователь не найден");
            }
            if (!foundClient.getPassword().equals(client.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный пароль");
            }
            return ResponseEntity.ok(foundClient);

        } catch (java.lang.Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка сервера");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        }

    }

    @PostMapping("/signup")
    public void addUser(@RequestBody Client client) {
        System.out.println("JSON: " + client);
        userService.addUser(client);
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
