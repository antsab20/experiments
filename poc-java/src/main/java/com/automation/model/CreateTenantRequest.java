package com.automation.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class CreateTenantRequest {
    private String customer;
    private String environment;
    private String primaryDomain;
    private Set<String> domainAliases;
    private String signingKeyAlias;
    private Boolean isTrial;
    private String organizationIdentifier;
    private List<String> additionalApplications;

}
