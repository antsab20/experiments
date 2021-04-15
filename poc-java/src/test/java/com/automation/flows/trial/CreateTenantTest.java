package com.automation.flows.trial;


import com.automation.config.ApplicationHeaderBuilder;
import com.automation.model.CreateTenantRequest;
import com.automation.model.CreateTenantResponse;
import com.automation.model.scim.user.ScimUser;
import com.automation.model.mautic.MauticForm;
import com.automation.mapper.EmailToScimUserMapper;

import com.automation.service.ApplicationRestClient;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;

import static org.mockito.Mockito.*;

public class CreateTenantTest {
    private static final String AUTHORIZATION = "Authorization";
    private static final String ENVIRONMENT = "federation-dev";
    private static final String PRIMARY_DOMAIN_PATTERN = "{0}.gov.ro";
    private static final String USER = "admin";
    private static final String PASS = "xxx";
    private String AUTH = "Basic " +
            Base64.getEncoder().encodeToString(
                    (
                            USER + ":" + PASS
                    )
                            .getBytes()
            );

    private CreateTenant testSubject;

    @Mock
    private ApplicationHeaderBuilder applicationHeaderBuilderMock;
    @Mock
    private MauticForm mauticFormMock;
    @Mock
    private CreateTenantRequest createTenantRequestMock;
    @Mock
    private CreateTenantResponse createTenantResponseMock;
    @Mock
    private ApplicationRestClient applicationRestClientMock;
    @Mock
    private ScimUser scimUserMock;
    @Mock
    private EmailToScimUserMapper emailToScimUserMapperMock;
    @Mock
    private CreateTenant createTenant;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        testSubject = new CreateTenant();
    }

    @Test
    public void testApply(){
        String email = mauticFormMock.getEmail();
        when(scimUserMock.getDisplayName()).thenReturn("");
        when(emailToScimUserMapperMock.map(email)).thenReturn(scimUserMock);
        when(applicationHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH);
        when(applicationRestClientMock.createTenant(AUTHORIZATION,createTenantRequestMock)).thenReturn(createTenantResponseMock);

        verifyZeroInteractions(createTenant);
    }

    @Test
    public void tesCreateTenantRequestSuccessful(){
        String customer = mauticFormMock.getFirstName();
        when(createTenantRequestMock.getCustomer()).thenReturn(customer);
        when(createTenantRequestMock.getEnvironment()).thenReturn(ENVIRONMENT);
        when(createTenantRequestMock.getIsTrial()).thenReturn(Boolean.TRUE);
        when(createTenantRequestMock.getPrimaryDomain()).thenReturn(PRIMARY_DOMAIN_PATTERN);
        when(createTenantRequestMock.getDomainAliases()).thenReturn(new HashSet<>());
        when(createTenantRequestMock.getAdditionalApplications()).thenReturn(new ArrayList<>());
    }
}
