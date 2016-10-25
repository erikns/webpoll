package no.hib.megagruppe.webpoll.data;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepositoryFactoryImpl implements RepositoryFactory {
    private static SurveyRepository surveyRepository; // FIXME: temporary!
    private static UserRepository userRepository;

    public RepositoryFactoryImpl() {
        userRepository = null;
        surveyRepository = new InMemorySurveyRepository();
    }

    @Override
    public SurveyRepository getSurveyRepository() {
        return surveyRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
