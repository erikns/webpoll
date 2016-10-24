package no.hib.megagruppe.webpoll.data;

public interface RepositoryFactory {
    SurveyRepository getSurveyRepository();
    UserRepository getUserRepository();
}
