package com.automation.model.scim.user;

import com.automation.model.scim.Meta;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Emails emails;
    private String id;
    private Meta meta;
    private List<String> schemas;
    private List<Group> groups;
}
