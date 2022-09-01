package pack.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.spring.DAO.UserDAO;
import pack.spring.models.User;

import java.util.List;

@Controller
public class UsersController {
    private final UserDAO userDAO;
    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @GetMapping("/users")
    public String allUsers (Model model) {
        List<User> users = userDAO.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/{id}")
    public String showById (@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDAO.getUser(id));
        return "users";
    }
    @GetMapping("/create")
    public String newUserForm (@ModelAttribute("user") User user, Model model) {
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userDAO.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDAO.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/update/{id}")
    public String edit (@PathVariable("id") long id,Model model) {
        User user = userDAO.getUser(id);
        model.addAttribute("user", user);
        return "update";
    }
    @PostMapping ("/update")
    public String editUsers(User user) {
        userDAO.updateUser(user);
        return "redirect:/users";
    }
}
