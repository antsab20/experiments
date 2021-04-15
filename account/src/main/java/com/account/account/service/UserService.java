package com.account.account.service;

import com.account.account.exception.UserAlreadyExistException;
import com.account.account.view.UserView;
import com.account.account.model.Account;
import com.account.account.model.Role;
import com.account.account.model.Users;
import com.account.account.repository.AccountRepository;
import com.account.account.repository.RoleRepository;
import com.account.account.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Users registerNewUserAccount(UserView userView)
            throws UserAlreadyExistException {

        if (emailExist(userView.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            +  userView.getEmail());
        }

        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Account account = accountRepository.save(Account.builder().amount(0).build());
        Users users = Users.builder()
                .email(userView.getEmail())
                .firstName(userView.getFirstName())
                .lastName(userView.getLastName())
                .password("{noop}"+userView.getPassword())
                .roles(roles)
                .account(account)
                .build();

        return usersRepository.save(users);
    }

    private boolean emailExist(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}
