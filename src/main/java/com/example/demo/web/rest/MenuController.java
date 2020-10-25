package com.example.demo.web.rest;

import java.util.Date;

import com.example.demo.validator.UserValidator;
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

@Controller
public class MenuController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/editProfile")
    public String editProfile(Model model)
    {
        var loggedInUserName = securityService.findLoggedInUsername();
        var user = userService.findByUsername(loggedInUserName);

        model.addAttribute("editProfileForm", user);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editUserProfile(@ModelAttribute("editProfileForm") User editProfileForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "editProfile";
        }

        var loggedInUserName = securityService.findLoggedInUsername();
        var formUsername = editProfileForm.getUsername();

//        userValidator.validate(editProfileForm, bindingResult);

        if (!formUsername.equals(loggedInUserName))
        {
            if (formUsername.equals(""))
            {
                bindingResult.rejectValue("username", "UsernameCannotBeEmpty", "Username cannot be empty!");
                return "editProfile";
            }

            if (!userService.editProfile(editProfileForm))
            {
                bindingResult.rejectValue("username", "ThisUsernameIsAlreadyUsed", "This username is already used");
                return "editProfile";
            }
        }

        if (editProfileForm.getBirthdate() != null) {
            var currentDate = new Date();
            // TODO удалить, если не надо
            var userFormDate = editProfileForm.getBirthdate();
            if (editProfileForm.getBirthdate().after(currentDate) || editProfileForm.getBirthdate().equals(currentDate)) {
                bindingResult.rejectValue("birthdate", "IncorrectBirthdate", "Birthdate must be earlier then current date");
                return "editProfile";
            }
        }

        securityService.autoLoginWithoutPassword(editProfileForm.getUsername());

        return "menu";
    }


}
