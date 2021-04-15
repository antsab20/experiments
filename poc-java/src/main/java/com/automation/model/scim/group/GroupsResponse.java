package com.automation.model.scim.group;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Data
public class GroupsResponse {
    private List<String> schemas;
    private Integer totalResults;
    private Integer startIndex;
    private Integer itemsPerPage;
    @JsonbProperty(value = "Resources")
    private List<Group> groups;
}
