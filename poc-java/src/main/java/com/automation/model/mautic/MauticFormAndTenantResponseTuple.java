package com.automation.model.mautic;

import com.automation.model.CreateTenantResponse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MauticFormAndTenantResponseTuple {
    private MauticForm mauticForm;
    private CreateTenantResponse createTenantResponse;
}
