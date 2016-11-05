package no.hib.megagruppe.webpoll.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.data.UserRepository;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.entities.UserEntity;
import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
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
		SurveyCreationModel surveyCreationModel = new SurveyCreationModel(surveyName, owner.toString());
		
		return surveyCreationModel;
	}

	/**
	 * Generates a new name for the new survey.
	 * 
	 * @return THe newly generated name.
	 */
	private String generateSurveyName() {
		String surveyName = "New survey"; // TODO
		return surveyName;
	}

	@Override
	public void commitSurveyCreation(SurveyCreationModel surveyCreationModel) {
		
		SurveyEntity surveyEntity = new SurveyEntity();
		surveyEntity.setName(surveyCreationModel.getName());
		
		int userID = securityAdapter.getLoggedInUser();
		UserEntity owner = repositoryFactory.getUserRepository().findById(userID);
		surveyEntity.setOwner(owner);
		surveyEntity.setActive(false);
		
		surveyEntity.setQuestions(convertQuestionModelsToEntities(surveyCreationModel.getQuestions(), surveyEntity));
		
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		surveyEntity.setDateCreated(now);
		surveyEntity.setCode(generateSurveyCode());
		
		SurveyRepository surveyRepository = repositoryFactory.getSurveyRepository();
		surveyRepository.add(surveyEntity);
	}
	
	/**
	 * Generates a new unique code for the survey.
	 * @return A new unique code for the survey.
	 */
	private String generateSurveyCode(){
		String surveyCode = "fox39"; // FIXME generer unik kode.
		return surveyCode;
	}
	
	/**
	 * Converts the QuestionCreationModels to QuestionEntities.
	 * @param questionModels The list of QuestionCreationModels to be converted to entities.
	 * @param surveyEntity The SurveyEntity that owns the QuestionEntities.
	 * @return A list of QuestionEntities based on the model.
	 */
	private List<QuestionEntity> convertQuestionModelsToEntities(List<QuestionCreationModel> questionModels, SurveyEntity surveyEntity){
		
		List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
		for(QuestionCreationModel questionModel : questionModels){
			QuestionEntity questionEntity = new QuestionEntity();
			questionEntity.setSurvey(surveyEntity);
			questionEntity.setText(questionModel.getQuestionText());
			questionEntity.setType(questionModel.getQuestionType());
			questionEntity.setOptions(convertQuestionModelOptionsToOptionEntities(questionModel, questionEntity));
			
			questionEntities.add(questionEntity);
		}
		
		return questionEntities;
	}

	/**
	 * Converts the options stored in QuestionCreationModel to OptionEntities.
	 * @param questionModel The QuestionCreationModel that contains the options.
	 * @param questionEntity The QuestionEntity that owns the OptionEntities.
	 * @return A list of OptionEntities based on the options inside QuestionCreationModel.
	 */
	private List<OptionEntity> convertQuestionModelOptionsToOptionEntities(QuestionCreationModel questionModel, QuestionEntity questionEntity) {
		List<OptionEntity> optionEntities;
		
		if (questionEntity.getType().isMultipleChoice()) {
			optionEntities = new ArrayList<>();
			for(String option : questionModel.getOptions()){
				
				boolean optionNotEmpty = option != null && option != "";
				if(optionNotEmpty){
					OptionEntity optionEntity = new OptionEntity();
					optionEntity.setText(option);
					optionEntity.setQuestion(questionEntity);
					
					optionEntities.add(optionEntity);
				}
			}
		} else {
			optionEntities = null;
		}
		
		return optionEntities;
	}

}
