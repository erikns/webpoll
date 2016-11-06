package no.hib.megagruppe.webpoll.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.hib.megagruppe.webpoll.data.RepositoryFactory;
import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.AnswerEntity;
import no.hib.megagruppe.webpoll.entities.OptionEntity;
import no.hib.megagruppe.webpoll.entities.QuestionEntity;
import no.hib.megagruppe.webpoll.entities.ResponseEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;
import no.hib.megagruppe.webpoll.models.lecturer.*;

@Stateless
public class SurveyOverviewServiceImpl implements SurveyOverviewService {
	private final RepositoryFactory repositoryFactory;
	private final SecurityAdapter securityAdapter;
	private final SurveyCreationService scs; //  Brukes for å opprette ny SurveyModel i metoden cloneSurvey(...).

	@Inject
	public SurveyOverviewServiceImpl(RepositoryFactory repositoryFactory, SecurityAdapter securityAdapter, SurveyCreationService scs) {
		this.repositoryFactory = repositoryFactory;
		this.securityAdapter = securityAdapter;
		this.scs = scs;
	}

	@Override
	public List<SurveyBasicInfoModel> getSurveyOverviews() {

		Integer userID = securityAdapter.getLoggedInUser();
		
		SurveyRepository surveyRepository = repositoryFactory.getSurveyRepository();
		List<SurveyEntity> surveys = surveyRepository.findAllByUser(userID);
		List<SurveyBasicInfoModel> surveyBasicInfoModels = new ArrayList<>();
		for(SurveyEntity survey : surveys){
			Long responseCount = surveyRepository.numberOfResponses(survey);
			SurveyBasicInfoModel surveyBasicInfoModel = new SurveyBasicInfoModel(survey.getId(), survey.getName(), survey.getDateCreated(), survey.getDeadline(), survey.getActive(), responseCount);
			surveyBasicInfoModels.add(surveyBasicInfoModel);
		}

		return surveyBasicInfoModels;
	}

	@Override
	public Boolean cloneSurvey(Integer surveyID) {

		SurveyEntity survey = repositoryFactory.getSurveyRepository().findById(surveyID);
		Boolean foundSurvey = survey != null;

		if (foundSurvey) {
			SurveyCreationModel surveyCreation = new SurveyCreationModel(survey.getName(), survey.getOwner().toString());
			surveyCreation.setName(survey.getName() + "_2");
			copyQuestions(survey, surveyCreation);
			scs.commitSurveyCreation(surveyCreation);
		}

		return foundSurvey;
	}

	/**
	 * Converts an SurveyEntity to a SurveyOverviewModel. This method is very memory-heavy because it needs all the composite
	 * tables of SurveyEntity to be able to correctly fill in all the responses and map them to the correct QuestionOverviewModel.
	 * \n If the question is multiple choice then the answerdata strings will be the option chosen, if the question is free-text
	 * then the answerdata strings will be each word in an answer.
	 * 
	 * @param survey
	 *            The SurveyEntity.
	 * @return A SurveyOverviewModel based on the SurveyEntity.
	 */
	private SurveyOverviewModel convertSurveyToOverviewModel(SurveyEntity survey) {

		// The questions mapped by their ID.
		HashMap<Integer, QuestionOverviewModel> questionOverviewModelsMap = new HashMap<>();

		// Iterates through all the answers in every response.
		List<ResponseEntity> responses = survey.getResponses();
		if (responses != null) {
			for (ResponseEntity response : responses) {
				List<AnswerEntity> answers = response.getAnswers();
				for (AnswerEntity answer : answers) {

					QuestionEntity question = answer.getQuestion();
					Integer questionID = question.getId();

					// If there is no mapped value for the current question, add it to the map.
					if (!questionOverviewModelsMap.containsKey(questionID)) {
						QuestionOverviewModel questionOverviewModel = new QuestionOverviewModel(question.getText());
						questionOverviewModelsMap.put(questionID, questionOverviewModel);
					}

					// Adds the answer to the question. How it is added is based on the type of question.
					if (question.getType().isMultipleChoice()) {
						// Multiple choice adds the option that was chosen by the student.
						String optionAnswer = answer.getOption().getText();
						questionOverviewModelsMap.get(questionID).addAnswer(optionAnswer);
					} else {
						// Freetext adds every word in the answertext, ready to be used in a wordcloud.
						String[] answerWords = getWordsFromString(answer.getFreetext());
						for (String answerWord : answerWords) {
							questionOverviewModelsMap.get(questionID).addAnswer(answerWord);
						}
					}
				}
			}
		}

		List<QuestionOverviewModel> questionOverviewModels = new ArrayList<>();
		questionOverviewModels.addAll(questionOverviewModelsMap.values());
		int numberOfResponses = responses == null ? 0 : responses.size();
		SurveyOverviewModel surveyOverviewModel = new SurveyOverviewModel(questionOverviewModels, survey, numberOfResponses);

		return surveyOverviewModel;
	}

	/**
	 * Splits the string into lowercase words, including numbers, separated by whitespace.
	 * 
	 * @param answerText
	 *            The answertext to be split into words.
	 * @return An array of the words in the answerText.
	 */
	private String[] getWordsFromString(String answerText) {
		answerText = answerText.toLowerCase();
		answerText = answerText.replaceAll("[^a-z0-9\\s]+", "");
		String[] answerWords = answerText.split(" ");

		return answerWords;
	}

	private void copyQuestions(SurveyEntity survey, SurveyCreationModel surveyCreation) {
		for (QuestionEntity question : survey.getQuestions()) {

			QuestionCreationModel questionCreation = new QuestionCreationModel(question);

			if (question.getType().isMultipleChoice()) {
				List<OptionEntity> optionEntities = question.getOptions();
				String[] options = new String[optionEntities.size()];

				for (int i = 0; i < optionEntities.size(); i++) {
					options[i] = optionEntities.get(i).getText();
				}

				questionCreation.setOptions(options);
			}

			surveyCreation.addQuestionCreationModel(questionCreation);
		}
	}

	@Override
	public SurveyOverviewModel getSurveyOverviewModel(Integer surveyID) {

		SurveyEntity survey = repositoryFactory.getSurveyRepository().findById(surveyID);
		SurveyOverviewModel surveyOverview = convertSurveyToOverviewModel(survey);

		return surveyOverview;
	}

	@Override
	public SurveyResultModel getSurveyResult(Integer id) {
		return new SurveyResultModel(); // TODO: implement
	}

	@Override
	public void activateSurvey(Timestamp deadline, Integer SurveyID) {
		SurveyRepository surveyRepository = repositoryFactory.getSurveyRepository();
		SurveyEntity survey = surveyRepository.findById(SurveyID);
		survey.setDeadline(deadline);
		surveyRepository.update(survey);
	}

}
