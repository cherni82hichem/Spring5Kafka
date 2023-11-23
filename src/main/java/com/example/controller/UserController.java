package com.example.controller;
import com.example.entity.User;
import com.example.kafka.UserConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> userList = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "user-topic";

    @Autowired
    private UserConsumer userConsumer;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        user.setId(System.currentTimeMillis());
        userList.add(user);
        // Envoyer le nouvel utilisateur à Kafka
        kafkaTemplate.send(TOPIC, user);
        //Recupere le nouvel utilisteur de kafka
        userConsumer.consume(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = findUserById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        User user = findUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = findUserById(id);
        userList.remove(user);
        return "redirect:/users";
    }

    private User findUserById(Long id) {
        return userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
