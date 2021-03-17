package com.projects.FirstProject.controllers;

import antlr.StringUtils;
import com.projects.FirstProject.data.UserRepository;
import com.projects.FirstProject.models.Tag;
import com.projects.FirstProject.models.User;
import com.projects.FirstProject.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("create")
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());
        model.addAttribute("userTypes", UserType.values());
        return "login/create";
    }

    @PostMapping("create")
    public String processLoginForm(@ModelAttribute User user, Model model) {

        model.addAttribute("title", "Login");
        String userEmail = user.getUserEmail();

        if (!userEmail.equals("")){
            Iterable<User> userList = userRepository.findAll();
            for (User x : userList){
                if (x.getUserEmail().equals(userEmail) && x.getUserType().equals(user.getUserType())){
                    String password = x.getPassword();
                    String[] a=password.split("B",-2);
                    String result = a[0].substring(0,a[0].length() -1);
                    if (result.equals(String.valueOf(user.hashCode()))) {
                        if (user.getUserType().equals(UserType.EMPLOYEE))
                            return "/indexAfterLogin";
                        else
                            return "/indexAdmin";
                    }
                }
            }
        }
        model.addAttribute("title", "Create Event");
        model.addAttribute("errorMsg", "Bad login data or not registered!");
        model.addAttribute("userTypes", UserType.values());
        return "login/create";

    }
}
