package com.automation.model;

import lombok.Data;

import java.util.List;

@Data
public class Tenant {
    private String guid;
    private String environment;
    private String primaryDomain;
    private List<String> domainAliases;
    private boolean isTrial;
}
