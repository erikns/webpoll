package no.hib.megagruppe.webpoll.data;

import java.util.List;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;

/**
 * Repository specific to the SurveyEntity. All repository access methods specific
 * to the SurveyRepository should be listed here.
 */
public interface SurveyRepository extends Repository<SurveyEntity> {
    /**
     * Finds a survey entity by its code
     * @param code The code to search for
     * @return The found entity, or null on error
     */
    SurveyEntity findByCode(String code);
    
    /**
     * Returns all the surveys owned by the given user.
     * @param user The user that owns the surveys.
     * @return All the surveys owned by the given user.
     */
    List<SurveyEntity> findAllByUser(Integer userID);
    
    /**
     * Returns the number of responses to the given survey.
     * @param survey The survey that owns the responses.
     * @return The number of responses to the given survey.
     */
    Long numberOfResponses(SurveyEntity survey);

    /**
     * Returns true if there exists a survey with the same code in the database.
     * @param code The code that is being checked.
     * @return True if there exists a survey with the same code in the database.
     */
	boolean existsActiveSurveyWithCode(String code);
}
