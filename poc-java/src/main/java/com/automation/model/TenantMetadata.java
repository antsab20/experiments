package com.automation.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TenantMetadata {
    private UUID id;
    private String organizationId;
    private String email;
    private String domainName;
    private String customer;
    private String contactId;
}
