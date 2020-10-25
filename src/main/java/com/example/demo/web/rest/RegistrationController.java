package com.example.demo.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;

import java.util.Date;

@Controller
public class RegistrationController
{
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm()))
        {
            bindingResult.rejectValue("password", "PasswordsAreNotEqual", "Passwords aren't equal");
            return "registration";
        }

        if (userForm.getBirthdate() != null) {
            var currentDate = new Date();
            var userFormDate = userForm.getBirthdate();
            if (userForm.getBirthdate().after(currentDate) || userForm.getBirthdate().equals(currentDate))
            {
                bindingResult.rejectValue("birthdate", "IncorrectBirthdate", "Birthdate must be earlier then current date");
                return "registration";
            }
        }

        if (!userService.save(userForm))
        {
            bindingResult.rejectValue("passwordConfirm", "UserHasAlreadyBeenCreated", "User has already been created");
            return "registration";
        }

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/menu";
    }

    @GetMapping("/login") // модел - это биндинг (для ошибок валидации)
    public String login(Model model, String error, String logout)
    {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/menu"})
    public String menu(Model model)
    {
        return "menu";
    }

//    @GetMapping({"/menu"})
//    public String menu(Model model)
//    {
//        return "menu";
//    }
}
