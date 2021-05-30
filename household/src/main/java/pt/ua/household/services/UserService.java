package pt.ua.household.services;

import pt.ua.household.entities.User;

public interface UserService {

    User getUserByEmail(String email);

    User getUserById(Long id);

    User saveUser(User user);

}
