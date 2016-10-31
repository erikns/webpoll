package no.hib.megagruppe.webpoll.services;

import no.hib.megagruppe.webpoll.models.SurveyOverviewModel;

import java.util.List;

/**
 * Interface to support the use cases of the lecturer editing / viewing surveys.
 * Note: this service assumes nothing about security, and expects the checking of
 * user credentials etc to be done before any service method is called.
 */
public interface SurveyLecturerService {
    // NOTES:
    // Create
    // Clone
    // See results

    List<SurveyOverviewModel> getSurveyOverviews();
}
