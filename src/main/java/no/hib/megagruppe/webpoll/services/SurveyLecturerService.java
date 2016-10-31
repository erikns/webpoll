package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.SurveyOverviewModel;

import java.util.List;

/**
 * Interface to support the use cases of the lecturer editing / viewing surveys
 */
public interface SurveyLecturerService {
    // NOTES:
    // Create
    // Clone
    // See results

    List<SurveyOverviewModel> getSurveyOverviews();
}
