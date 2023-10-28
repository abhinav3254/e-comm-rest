package e_comm.controller;


import e_comm.dto.LogInDTO;
import e_comm.modal.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserController {

    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody User user);

    @PostMapping("/login")
    ResponseEntity<String> logIn(@RequestBody LogInDTO logInDTO);

}
