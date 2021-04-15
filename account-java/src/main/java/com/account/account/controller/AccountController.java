package com.account.account.controller;

import com.account.account.view.AccountView;
import com.account.account.model.Account;
import com.account.account.model.Users;
import com.account.account.repository.AccountRepository;
import com.account.account.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UsersRepository usersRepository;
    private final AccountRepository accountRepository;


    @GetMapping("/account")
    public String showRegistrationForm(Model model) throws Exception {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Users> maybeUser = usersRepository.findByEmail(principal.getUsername());

        Account account;
        if (maybeUser.isPresent()) {
            account = maybeUser.get().getAccount();
        } else {
            throw new Exception("");
        }

        model.addAttribute("account", account);
        return "account";
    }

    @PostMapping("/account")
    public String account(@ModelAttribute("account") AccountView accountView, BindingResult bindingResult) throws Exception {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Users> maybeUser = usersRepository.findByEmail(principal.getUsername());

        if (maybeUser.isEmpty()) {
            throw new Exception("");
        }

        Users user = maybeUser.get();

        Calendar myDate = Calendar.getInstance();
        int dow = myDate.get (Calendar.DAY_OF_WEEK);
        boolean isWeekday = ((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY));

        LocalTime start = LocalTime.parse( "09:00");
        LocalTime stop = LocalTime.parse( "17:00");
        LocalTime target = LocalTime.now();


        if (!isWeekday || !(target.isBefore(stop) && target.isAfter(start))) {
            return "not-available";
        }

        Account account = user.getAccount();
        account.setAmount(accountView.getAmount());
        accountRepository.save(account);

        return "account";
    }
}
