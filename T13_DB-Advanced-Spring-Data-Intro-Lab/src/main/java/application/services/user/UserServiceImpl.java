package application.services.user;

import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
       //Long id=user.getId();
//
       //if (id!=null){
       //    if(userRepository.exists(id)){
       //        throw new RuntimeException("Duplicate user ids");
       //    }
       //}
//
       //String username=user.getUsername();
//
       //if (username!=""){
       //    User byUsername= (User) userRepository.findByUsername(username);
//
       //    if (byUsername!=null){
       //        throw new RuntimeException("Duplicate usernames");
       //    }
       //}
//
        userRepository.save(user);
    }
}
