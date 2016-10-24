package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.util.PasswordHasher;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

public class SecurityServiceImplTest {
    @Test
    public void noLoggedInUserReturnsNoneLoggedIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        TestUserRepository userRepository = new TestUserRepository();

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, null);

        assertFalse(securityService.isLoggedIn());
        assertEquals(1, securityAdapter.isLoggedInCalled);
    }

    @Test
    public void loggedInUserReturnsUserLoggedIn() {
        TestSecurityAdapter securityAdapter = new TestSecurityAdapter();
        TestUserRepository userRepository = new TestUserRepository();

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, null);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, passwordHasher);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, passwordHasher);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, passwordHasher);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, null, null);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, passwordHasher);

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

        SecurityServiceImpl securityService = new SecurityServiceImpl(securityAdapter, userRepository, passwordHasher);

        UserEntity user = securityService.getLoggedInUser();

        assertEquals(0, userRepository.findByIdCalled);
        assertNull(user);
    }

    /**
     * Dummy password hasher that just does nothing to the password
     */
    private class TestPasswordHasher implements PasswordHasher {
        public boolean hashPasswordCalled = false;
        public boolean comparePasswordCalled = false;

        @Override
        public String hashPassword(String password) {
            hashPasswordCalled = true;
            return password;
        }

        @Override
        public boolean comparePassword(String password, String hash) {
            comparePasswordCalled = true;
            return password.equals(hash);
        }
    }

    private class TestUserRepository implements UserRepository {
        public UserEntity userToReturn;
        public int findByEmailCalled;
        public String findByEmailCalledWith;
        public int findByIdCalledWith;
        public int findByIdCalled;

        @Override
        public UserEntity add(UserEntity entity) {
            return null;
        }

        @Override
        public UserEntity findById(int id) {
            findByIdCalled++;
            findByIdCalledWith = id;
            return userToReturn;
        }

        @Override
        public List<UserEntity> findAll() {
            return null;
        }

        @Override
        public UserEntity update(UserEntity entity) {
            return null;
        }

        @Override
        public void remove(UserEntity entity) {

        }

        @Override
        public UserEntity findByEmail(String email) {
            findByEmailCalled++;
            findByEmailCalledWith = email;
            return userToReturn;
        }
    }

    private class TestSecurityAdapter implements SecurityAdapter {
        public boolean isLoggedInToReturn = false;
        public int isLoggedInCalled = 0;
        public boolean logInToReturn = false;
        public int logInCalledWith;
        public int logInCalled = 0;
        public int logOutCalled = 0;
        public int getLoggedInUserToReturn;

        @Override
        public boolean isLoggedIn() {
            isLoggedInCalled++;
            return isLoggedInToReturn;
        }

        @Override
        public int getLoggedInUser() {
            return getLoggedInUserToReturn;
        }

        @Override
        public boolean logIn(int userId) {
            logInCalledWith = userId;
            logInCalled++;
            return logInToReturn;
        }

        @Override
        public void logOut() {
            logOutCalled++;
        }
    }
}
