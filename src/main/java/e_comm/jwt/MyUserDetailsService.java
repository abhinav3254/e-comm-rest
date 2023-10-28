package e_comm.jwt;

import java.util.ArrayList;

import e_comm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    e_comm.modal.User user;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = userDao.findUserByEmail(username);
        if (user != null) {
            return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User Not Find By the email"+username);
        }
    }



    public e_comm.modal.User getUserDetails() {
        return user;
    }

}