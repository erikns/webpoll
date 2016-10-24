package no.hib.megagruppe.webpoll.data;

import no.hib.megagruppe.webpoll.entities.SurveyEntity;

public interface SurveyRepository extends Repository<SurveyEntity> {
    SurveyEntity findByCode(String code);
}
