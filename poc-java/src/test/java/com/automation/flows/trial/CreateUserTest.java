package com.automation.flows.trial;


public class CreateUserTest {
    /*
    private final String AUTHORIZATION = "Authorization";
    private final String USER = "admin";
    private final String PASS = "ugh4Vahwoj9Pheeceethui7lichain";
    private final UUID TENANT_ID = UUID.randomUUID();
    private String AUTH = "Basic " +
                           Base64.getEncoder().encodeToString(
                                   (
                                        USER + ":" + PASS
                                   )
                                        .getBytes()
                           );

    private Response.ResponseBuilder rb = Response.ok();
    private Response response = rb.build();

    private CreateUser testSubject;

    @Mock
    private MyConnectisHeaderBuilder myConnectisHeaderBuilderMock;
    @Mock
    private AttributeProviderClient attributeProviderClientMock;
    @Mock
    private ScimUser scimUserMock;
    @Mock
    private MauticForm mauticFormMock;
    @Mock
    private UsersResponse usersResponseMock;
    @Mock
    private CreateTenant createTenantMock;
    @Mock
    private CreateTenantResponse createTenantResponseMock;
    @Mock
    private TenantMetadata tenantMetadataMock;
    @Mock
    private GroupGet groupGetMock;
    @Mock
    private List<User> userListMock;
    @Mock
    private Stream<User> userStreamMock;
    @Mock
    private SendErrorResponse sendErrorResponseMock;
    @Mock
    private EmailToScimUserMapper emailToScimUserMapperMock;
    @Mock
    private AuthorizeOrganizations authorizeOrganizationsMock;
    @Mock
    private SendSuccessResponse sendSuccessResponseMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        testSubject = new CreateUser();
    }

    @Test
    public void testUserExists(){
        when(myConnectisHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH);
        when(attributeProviderClientMock.getUsers(AUTHORIZATION)).thenReturn(usersResponseMock);
        when(createTenantMock.apply(mauticFormMock)).thenReturn(createTenantResponseMock);
        when(groupGetMock.apply(createTenantResponseMock)).thenReturn(tenantMetadataMock);
        when(usersResponseMock.getUsers()).thenReturn(userListMock);
        when(userListMock.stream()).thenReturn(userStreamMock);
        Predicate<User> p = user -> user.getEmails() != null && user.getEmails().getEmailAddress().equals(mauticFormMock.getEmail());
        when(userStreamMock.anyMatch(p)).thenReturn(Boolean.TRUE);

        verifyNoMoreInteractions(sendErrorResponseMock);
    }

    @Test
    public void testUserNonexistent(){
        String email = mauticFormMock.getEmail();
        when(scimUserMock.getDisplayName()).thenReturn("");
        when(emailToScimUserMapperMock.map(email)).thenReturn(scimUserMock);
        when(myConnectisHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH);
        when(attributeProviderClientMock.addUser(AUTHORIZATION, scimUserMock)).thenReturn(response);
        when(createTenantMock.apply(mauticFormMock)).thenReturn(createTenantResponseMock);
        when(groupGetMock.apply(createTenantResponseMock)).thenReturn(tenantMetadataMock);
        when(myConnectisHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH);
        when(attributeProviderClientMock.getUsers(AUTHORIZATION)).thenReturn(usersResponseMock);
        when(createTenantMock.apply(mauticFormMock)).thenReturn(createTenantResponseMock);
        when(groupGetMock.apply(createTenantResponseMock)).thenReturn(tenantMetadataMock);
        when(usersResponseMock.getUsers()).thenReturn(userListMock);

        verifyZeroInteractions(sendSuccessResponseMock);
    }

    @Test
    public void testCreateUSerFails(){
        String email = mauticFormMock.getEmail();
        when(scimUserMock.getDisplayName()).thenReturn("");
        when(emailToScimUserMapperMock.map(email)).thenReturn(scimUserMock);
        when(myConnectisHeaderBuilderMock.getAuthKeys()).thenReturn(AUTH);
        when(attributeProviderClientMock.addUser(AUTHORIZATION, scimUserMock)).thenReturn(response);
        when(createTenantMock.apply(mauticFormMock)).thenReturn(createTenantResponseMock);
        when(groupGetMock.apply(createTenantResponseMock)).thenReturn(tenantMetadataMock);
    }

     */
}
