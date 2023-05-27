package ua.com.sweet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.sweet.dto.UserDto;
import ua.com.sweet.models.User;
import ua.com.sweet.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {

        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getFirstName(), userDto.getLastName(), userDto.getPosition());
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
