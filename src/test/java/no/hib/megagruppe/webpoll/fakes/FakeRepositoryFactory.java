package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.data.ResponseRepository;
import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.data.UserRepository;

public class FakeRepositoryFactory implements RepositoryFactory {
    private UserRepository userRepository = null;
    private SurveyRepository surveyRepository = null;

    public FakeRepositoryFactory(UserRepository userRepository, SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.surveyRepository = surveyRepository;
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
		// TODO Auto-generated method stub
		return null;
	}
}
