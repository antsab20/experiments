package com.automation.flows.trial;

import com.automation.config.ApplicationHeaderBuilder;
import com.automation.model.TenantMetadata;
import com.automation.service.ApplicationRestClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class AuthorizeOrganizationsTest {
    private static final String ORGANIZATION_ID = "organization id";
    private static final String USER = "admin";
    private static final String PASS = "xxx";
    private static final String AUTH_KEYS = "Basic" + Base64.getEncoder().encodeToString((USER + ":" + PASS).getBytes());
    private static final String AUTHORIZATION = "authorization";
    private static final UUID TENANT_ID = UUID.randomUUID();

    @Mock
    private AuthorizeOrganizations testSubject;
    @Mock
    private ApplicationRestClient applicationRestClientMock;
    @Mock
    private TenantMetadata tenantMetadataMock;
    @Mock
    private ApplicationHeaderBuilder applicationHeaderBuilderMock;
    @Mock
    private List<UUID> listMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        testSubject = new AuthorizeOrganizations();
    }

    @Test
    public void authorizeNewTenantSuccessful(){
        when(applicationHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH_KEYS);
        when(tenantMetadataMock.getOrganizationId()).thenReturn(ORGANIZATION_ID);
        when(tenantMetadataMock.getId()).thenReturn(TENANT_ID);
        when(applicationRestClientMock.listAuthorizedOrganization(AUTHORIZATION, ORGANIZATION_ID)).thenReturn(listMock);
        when(listMock.add(TENANT_ID)).thenReturn(Boolean.TRUE);
        when(applicationRestClientMock.authorizeNewTenant(AUTH_KEYS, ORGANIZATION_ID, listMock)).thenReturn(Collections.singletonList(UUID.randomUUID()));
    }
}
