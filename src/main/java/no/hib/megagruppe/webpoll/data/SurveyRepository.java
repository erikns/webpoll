package no.hib.megagruppe.webpoll.data;

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
}
