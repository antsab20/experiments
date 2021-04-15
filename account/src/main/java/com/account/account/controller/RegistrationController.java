package com.account.account.controller;

import com.account.account.view.UserView;
import com.account.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private static final String REGISTRATION_TEMPLATE = "registration";

    private final UserService userService;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserView userDto = UserView.builder().build();
        model.addAttribute("user", userDto);
        return REGISTRATION_TEMPLATE;
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") UserView userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION_TEMPLATE;
        }
        userService.registerNewUserAccount(userView);
        return "redirect:/";
    }
}
