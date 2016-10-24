package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.util.PasswordHasher;

import javax.inject.Inject;

public class SecurityServiceImpl implements SecurityService {
    private final SecurityAdapter securityAdapter;
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    @Inject
    public SecurityServiceImpl(SecurityAdapter securityAdapter, UserRepository userRepository,
                               PasswordHasher passwordHasher) {
        this.securityAdapter = securityAdapter;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public boolean logIn(String username, String password) {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) return false;

        boolean passwordMatches = passwordHasher.comparePassword(password, user.getPassword());
        if (passwordMatches) securityAdapter.logIn(user.getId());
        return passwordMatches;
    }

    @Override
    public void logOut() {
        securityAdapter.logOut();
    }

    @Override
    public boolean isLoggedIn() {
        return securityAdapter.isLoggedIn();
    }

    @Override
    public UserEntity getLoggedInUser() {
        int sessionUser = securityAdapter.getLoggedInUser();
        if (sessionUser == SecurityAdapter.USER_NOT_LOGGED_IN) {
            return null;
        }

        return userRepository.findById(sessionUser);
    }
}
