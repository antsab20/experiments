package com.automation.mapper;

import com.automation.model.scim.user.ScimUser;
import lombok.NonNull;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;

@ApplicationScoped
public class EmailToScimUserMapper {
    private static final String USER_EMAIL_EXTENSION = "urn:com:xxx:xxx:authenticate:EmailAddress";
    private static final String USER_SCHEMA = "urn:ietf:params:scim:schemas:core:2.0:User";

    @NonNull
    public ScimUser map(@NonNull String email) {
        return ScimUser.builder()
                .email(ScimUser.EmailAddress.builder()
                        .emailAddress(email)
                        .build())
                .schemas(new ArrayList<>(Arrays.asList(USER_EMAIL_EXTENSION, USER_SCHEMA)))
                .build();
    }
}
