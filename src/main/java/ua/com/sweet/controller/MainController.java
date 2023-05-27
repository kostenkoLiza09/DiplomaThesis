package ua.com.sweet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.sweet.models.Position;
import ua.com.sweet.models.User;
import ua.com.sweet.repository.UserRepository;
import ua.com.sweet.security.CustomUserDetails;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Головна сторінка");
        return "home";
    }

    @GetMapping("/profile")
    public String contact(Model model) {
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        model.addAttribute("title");
        return "profile";
    }

    @GetMapping("/profile/edit")
    private String profileEdit(Model model) {
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "profile_edit";
    }

    @PostMapping("/profile/edit")
    private String profileUpdate(@RequestParam String email, @RequestParam String firstName,
                                 @RequestParam String lastName, @RequestParam String position) {
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPosition(Position.valueOf(position));
        userRepository.save(user);
        return "redirect:/profile";
    }
}