package ba.delalich.tablanette.services;

import ba.delalich.tablanette.models.User;
import ba.delalich.tablanette.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<User> getUsersByName(List<String> userNames) {
        return this.userRepository.getUsersByNameIn(userNames);
    }

}
