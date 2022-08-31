package pack.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack.spring.DAO.UserDAO;
import pack.spring.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDAO userDAO;

    @Autowired
    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("user",userDAO.show(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user")User user, @PathVariable("id")int id){
        userDAO.update(id,user);
        return "redirect:/users";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}
