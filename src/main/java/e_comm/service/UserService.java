package e_comm.service;


import e_comm.dto.LogInDTO;
import e_comm.jwt.JwtUtils;
import e_comm.modal.User;
import e_comm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> signUp(User user) {
        try {

            User user1 = userRepository.findUserByEmail(user.getEmail());

            if (Objects.isNull(user1)) {
                user.setRegisterDate(new Date());
                user.setRole("user");
                user.setStatus(true);

                userRepository.save(user);
                return new ResponseEntity<>("user added",HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("email already exists",HttpStatus.ALREADY_REPORTED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> logIn(LogInDTO logInDTO) {
        try {

            User user = userRepository.findUserByEmail(logInDTO.getEmail());

            if (Objects.isNull(user)) {
                return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
            } else {
                if (user.isStatus()) {
                    if (user.getEmail().equals(logInDTO.getEmail())) {
                        if (user.getPassword().equals(logInDTO.getPassword())) {
                            String token = jwtUtils.generateToken(user.getEmail(),user.getRole());
                            return new ResponseEntity<>(token,HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>("invalid password",HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<>("invalid username",HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("contact admin",HttpStatus.BAD_GATEWAY);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
