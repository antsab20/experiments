package com.automation.model.scim.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScimUser {
    private List<String> schemas;
    private String id;
    private String externalId;
    private String displayName;
    private EmailAddress email;

    @Data
    @Builder
    public static class EmailAddress {
        private String emailAddress;
    }
}