package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import virtualhealth.model.Client;
import virtualhealth.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v0.1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService; //нашел реализацию контроллера и использует её первый уровень исп второй контроллер - сервис

    @GetMapping
    public List<Client> findAllUsers() {
        return userService.findAllUsers();
        //todo
    }

    @PostMapping("save_client")
    public void addUser(@RequestBody Client client) {
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
    }

    @DeleteMapping("delete_user/{email}")
    public void deleteUserByEmail(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
    }
}
