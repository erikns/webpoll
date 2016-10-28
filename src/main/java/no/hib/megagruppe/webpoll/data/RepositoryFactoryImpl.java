package no.hib.megagruppe.webpoll.data;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepositoryFactoryImpl implements RepositoryFactory {
    private static SurveyRepository surveyRepository; // FIXME: temporary!
    private static UserRepository userRepository;
    private static ResponseRepository responseRepository;

    public RepositoryFactoryImpl() {
        userRepository = new InMemoryUserRepository();
        surveyRepository = new InMemorySurveyRepository();
        responseRepository = new InMemoryResponseRepository();
    }

    @Override
    public SurveyRepository getSurveyRepository() {
        return surveyRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

	@Override
	public ResponseRepository getResponseRepository() {
		return responseRepository;
	}
    
    
}
