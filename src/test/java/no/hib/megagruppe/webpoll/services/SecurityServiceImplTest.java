package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.fakes.FakeRepositoryFactory;
import no.hib.megagruppe.webpoll.fakes.TestPasswordHasher;
import no.hib.megagruppe.webpoll.fakes.TestSecurityAdapter;
import no.hib.megagruppe.webpoll.fakes.TestUserRepository;
import no.hib.megagruppe.webpoll.util.PasswordHasher;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class SecurityServiceImplTest {
    @Test
    public void noLoggedInUserReturnsNoneLoggedIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();

        SecurityService securityService = buildService(securityAdapter, null, null);

        assertFalse(securityService.isLoggedIn());
        assertEquals(1, securityAdapter.isLoggedInCalled);
    }

    @Test
    public void loggedInUserReturnsUserLoggedIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        TestUserRepository userRepository = new TestUserRepository();

        SecurityService securityService = buildService(securityAdapter, null, userRepository);

        securityAdapter.isLoggedInToReturn = true;
        assertTrue(securityService.isLoggedIn());
        assertEquals(1, securityAdapter.isLoggedInCalled);
    }

    @Test
    public void logInWithValidUsernameAndPasswordLogsUserIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        securityAdapter.logInToReturn = true;

        TestUserRepository userRepository = new TestUserRepository();
        TestPasswordHasher passwordHasher = new TestPasswordHasher();

        SecurityService securityService = buildService(securityAdapter, passwordHasher, userRepository);

        UserEntity user = new UserEntity();
        user.setEmail("test@user.none");
        user.setPassword("test");
        user.setId(42);

        userRepository.userToReturn = user;

        boolean result = securityService.logIn("test@user.none", "test");

        assertEquals(1, userRepository.findByEmailCalled);
        assertEquals("test@user.none", userRepository.findByEmailCalledWith);
        assertTrue(passwordHasher.comparePasswordCalled);
        assertEquals(42, securityAdapter.logInCalledWith);
        assertTrue(result);
    }

    @Test
    public void logInWithInvalidPasswordDoesNotLogUserIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        securityAdapter.logInToReturn = false;

        TestUserRepository userRepository = new TestUserRepository();
        TestPasswordHasher passwordHasher = new TestPasswordHasher();

        SecurityService securityService = buildService(securityAdapter, passwordHasher, userRepository);

        UserEntity user = new UserEntity();
        user.setEmail("test@user.none");
        user.setPassword("test");
        user.setId(42);

        userRepository.userToReturn = user;

        boolean result = securityService.logIn("test@user.none", "wrong");

        assertEquals(1, userRepository.findByEmailCalled);
        assertEquals("test@user.none", userRepository.findByEmailCalledWith);
        assertTrue(passwordHasher.comparePasswordCalled);
        assertEquals(0, securityAdapter.logInCalled);
        assertFalse(result);
    }

    @Test
    public void logInWithInvalidUsernameDoesNotLogUserIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        securityAdapter.logInToReturn = false;

        TestUserRepository userRepository = new TestUserRepository();
        TestPasswordHasher passwordHasher = new TestPasswordHasher();

        SecurityService securityService = buildService(securityAdapter, passwordHasher, userRepository);

        UserEntity user = new UserEntity();
        user.setEmail("test@user.none");
        user.setPassword("test");
        user.setId(42);

        userRepository.userToReturn = null;

        boolean result = securityService.logIn("test@user.wrong", "test");

        assertEquals(1, userRepository.findByEmailCalled);
        assertEquals("test@user.wrong", userRepository.findByEmailCalledWith);
        assertFalse(passwordHasher.comparePasswordCalled);
        assertEquals(0, securityAdapter.logInCalled);
        assertFalse(result);
    }

    @Test
    public void logOutCallsLogOutOnSecurityAdapter() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();

        SecurityService securityService = buildService(securityAdapter, null, null);

        securityService.logOut();
        assertEquals(1, securityAdapter.logOutCalled);
    }

    @Test
    public void getLoggedInUserWithLoggedInUserReturnsCorrectUser() {
        UserEntity returnThisUser = new UserEntity();
        returnThisUser.setId(42);

        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        securityAdapter.getLoggedInUserToReturn = 42;

        TestUserRepository userRepository = new TestUserRepository();
        userRepository.userToReturn = returnThisUser;

        TestPasswordHasher passwordHasher = new TestPasswordHasher();

        SecurityService securityService = buildService(securityAdapter, passwordHasher, userRepository);

        UserEntity user = securityService.getLoggedInUser();

        assertEquals(42, userRepository.findByIdCalledWith);
        assertTrue(user.getId() == 42);
    }

    @Test
    public void getLoggedInUserWithoutLoggedInUserReturnsNullUser() {
        UserEntity returnThisUser = new UserEntity();
        returnThisUser.setId(42);

        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        securityAdapter.getLoggedInUserToReturn = SecurityAdapter.USER_NOT_LOGGED_IN;

        TestUserRepository userRepository = new TestUserRepository();
        TestPasswordHasher passwordHasher = new TestPasswordHasher();

        SecurityService securityService = buildService(securityAdapter, passwordHasher, userRepository);

        UserEntity user = securityService.getLoggedInUser();

        assertEquals(0, userRepository.findByIdCalled);
        assertNull(user);
    }

    private static SecurityService buildService(SecurityAdapter securityAdapter, PasswordHasher passwordHasher,
                                                UserRepository userRepository) {
        return new SecurityServiceImpl(securityAdapter, passwordHasher,
                new FakeRepositoryFactory(userRepository, null));
    }

}
