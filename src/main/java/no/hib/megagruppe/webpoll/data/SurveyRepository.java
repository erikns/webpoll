package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;

/**
 * Repository specific to the SurveyEntity. All repository access methods specific
 * to the SurveyRepository should be listed here.
 */
public interface SurveyRepository extends Repository<SurveyEntity> {
    SurveyEntity findByCode(String code);
}
