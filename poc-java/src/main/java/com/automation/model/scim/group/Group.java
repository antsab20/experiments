package com.automation.model.scim.group;

import com.automation.model.scim.Meta;
import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private String displayName;
    private List<Member> members;
    private Meta meta;
    private List<String> schemas;
}
