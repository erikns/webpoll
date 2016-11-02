package no.hib.megagruppe.webpoll.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;

@Stateless
public class SurveyCreationServiceImpl implements SurveyCreationService {

	private final RepositoryFactory repositoryFactory;
	private final SecurityAdapter securityAdapter;

	@Inject
	public SurveyCreationServiceImpl(RepositoryFactory repositoryFactory, SecurityAdapter securityAdapter) {
		this.repositoryFactory = repositoryFactory;
		this.securityAdapter = securityAdapter;
	}

	@Override
	public SurveyCreationModel instantiateNewSurvey() {

		UserRepository userRepository = repositoryFactory.getUserRepository();
		int userId = securityAdapter.getLoggedInUser();
		UserEntity owner = userRepository.findById(userId);
		
		String surveyName = generateSurveyName();
		SurveyCreationModel surveyCreationModel = new SurveyCreationModel(owner);
		surveyCreationModel.setName(surveyName);
		
		return surveyCreationModel;
	}

	/**
	 * Generates a new name for the new survey.
	 * 
	 * @return THe newly generated name.
	 */
	private String generateSurveyName() {
		String surveyName = "New survey";
		return surveyName;
	}

	@Override
	public void commitSurveyCreation(SurveyCreationModel surveyCreationModel) {
		// TODO Auto-generated method stub
		
	}

}
