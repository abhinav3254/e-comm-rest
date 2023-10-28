package e_comm.initializer;


import e_comm.modal.User;
import e_comm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        List<User> list = userRepository.findAll();

        if (list.isEmpty()) {
            User user = new User();
            user.setEmail("admin");
            user.setPassword("12");
            user.setRole("admin");
            user.setStatus(true);

            userRepository.save(user);
        }

    }
}
