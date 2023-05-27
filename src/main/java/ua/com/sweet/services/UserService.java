package ua.com.sweet.services;


import ua.com.sweet.dto.UserDto;
import ua.com.sweet.models.User;

public interface UserService {
  void saveUser(UserDto userDto);

  User findUserByEmail(String email);
}
