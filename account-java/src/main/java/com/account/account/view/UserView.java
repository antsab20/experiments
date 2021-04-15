package com.account.account.view;

import com.account.account.validation.PasswordMatches;
import com.account.account.validation.ValidEmail;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    @NotBlank
    @Getter
    @Setter
    private String firstName;
    @NotBlank
    @Getter
    @Setter
    private String lastName;
    @NotBlank
    @Getter
    @Setter
    private String password;
    @NotBlank
    @Getter
    @Setter
    private String matchingPassword;
    @NotBlank
    @ValidEmail
    @Getter
    @Setter
    private String email;
}
