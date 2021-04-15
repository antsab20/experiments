package com.account.account.view;

import com.account.account.validation.PasswordMatches;
import lombok.*;

import javax.validation.constraints.Min;

@Data
@Builder
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
public class AccountView {

    @Getter
    @Setter
    @Min(0)
    private int amount;
}
