package com.dep.services.user.services;

import com.dep.common.exception.DepUnprocessableException;
import com.dep.common.model.user.Company;
import com.dep.common.model.user.Department;
import com.dep.common.model.user.User;
import com.dep.common.model.user.UserPrivilege;
import com.dep.common.util.AwsS3Utilities;
import com.dep.common.util.DepEnum;
import com.dep.common.util.DepEnum.Status;
import com.dep.services.user.client.UserCommunicationClient;
import com.dep.services.user.repository.CompanyRepository;
import com.dep.services.user.repository.DepartmentRepository;
import com.dep.services.user.repository.UserRepository;
import com.dep.services.user.repository.common.DatabaseBaseTest;
import com.dep.services.user.services.notification.CompanyServiceNotificationService;
import com.dep.services.user.services.notification.UserNotificationSettingService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserRepository.class, UserService.class})
public class UserServiceTest extends DatabaseBaseTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CompanyRepository companyRepository;
    @MockBean
    private RoleService roleService;
    @MockBean
    private CompanyServiceNotificationService notificationService;
    @MockBean
    private UserCommunicationClient communicationClient;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private UserNotificationSettingService notificationSettingService;


    @MockBean
    private AwsS3Utilities awsS3Utilities;
    private User user;
    private User loggedUser;
    private Company company;
    private Department department;

    @Before
    public void init() {
        user = new User();
        user.setCompanyId(2L);
        user.setCompanyTypeId(1L);
        user.setAdvertiserCompanyId(2L);
        user.setDepartmentId(1L);
        user.setFirstName("Tester");
        user.setLastName("JUnit");
        user.setEmail("advertiserUser@acompany.com");
        user.setId(10L);
        user.setRole(DepEnum.UserRole.ROLE_ADVT_CMPY_ADMIN.name());


        company = new Company();
        company.setCountryId(1L);
        company.setCompanyTypeId(3L);
        company.setName("IBM");
        company.setActiveIndicator(Status.ACTIVE.getIndicator());

        department = new Department();
        department.setCountryId(company.getCountryId());
        department.setName("Department new");

        loggedUser = new User();
        loggedUser.setId(42L);
        loggedUser.setFirstName("Test");
        loggedUser.setLastName("User");
        loggedUser.setEmail("testUser2@gmail.com");
        loggedUser.setRoleId(1L);
        loggedUser.setCountryId(2L);
        loggedUser.setCompanyId(1L);
        loggedUser.setCurrencyId(1L);
        loggedUser.setDepartmentId(3L);
        loggedUser.setCurrency("United States Dollar");
        loggedUser.setTimezoneId(1L);
        loggedUser.setTimezone("Pacific Standard Time");
        loggedUser.setPhone("88000888");
    }


    @Test
    public void testListAllUserRoles() {
        List<String> userRole = Collections.singletonList(user.getRole());
        when(userRepository.findAll(anyString(), any(), any(Map.class))).thenReturn(Collections.singletonList(user.getRole()));
        assertNotNull(userService.listAllUserRoles(user));
        assertEquals(userService.listAllUserRoles(user), userRole);
    }


    @Test
    public void testFindLoggedInUser() {
        when(userRepository.findLoggedInUser(anyString())).thenReturn(loggedUser);
        assertNotNull(userService.findLoggedInUser("testUser2@gmail.com"));
        assertEquals(userService.findLoggedInUser("testUser2@gmail.com").getFirstName(), "Test");
    }

    @Test
    public void testDeleteUser() throws DepUnprocessableException {
        when(userRepository.save(anyString(), any(Map.class))).thenReturn(1);
        assertEquals(userService.deleteUser(user, loggedUser), 1);
    }


}
