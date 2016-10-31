package no.hib.megagruppe.webpoll.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryFactoryImpl implements RepositoryFactory {
    private ResponseRepository responseRepository;
    private SurveyRepository surveyRepository;
    private UserRepository userRepository;

    @Inject
    public RepositoryFactoryImpl(SurveyRepository surveyRepository, UserRepository userRepository,
                                 ResponseRepository responseRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.responseRepository = responseRepository;
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
