package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public void addUser(@RequestBody Client client) {
        System.out.println("JSON: " + client);
        userService.addUser(client);
    }
    //public void addUser(@RequestBody Client client) {
    //    System.out.println("JSON: " + client);
    //    System.out.println("Form: " + client);
    //    userService.addUser(client);
    //}ч

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
