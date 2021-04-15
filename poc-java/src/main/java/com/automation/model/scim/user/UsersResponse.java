package com.automation.model.scim.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Data
@RegisterForReflection
public class UsersResponse {
    private List<String> schemas;
    private Integer totalResults;
    private Integer startIndex;
    private Integer itemsPerPage;
    @JsonbProperty(value = "Resources")
    private List<User> users;

}
