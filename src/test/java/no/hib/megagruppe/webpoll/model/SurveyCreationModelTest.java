package no.hib.megagruppe.webpoll.model;

import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import no.hib.megagruppe.webpoll.models.lecturer.SurveyCreationModel;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SurveyCreationModelTest {
	@Test
	public void addQuestionAddsQuestion() {
	    SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
	    model.addQuestionCreationModel(new QuestionCreationModel("Q1"));

	    assertEquals(1, model.getQuestions().size());
	}

	@Test
	public void deleteQuestionDeletesQuestion() {
	    SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
	    model.addQuestionCreationModel(new QuestionCreationModel("Q1"));

	    model.removeQuestionAtIndex(0);

	    assertEquals(0, model.getQuestions().size());
	}

	@Test
	public void deleteQuestionAtIndexDeletesCorrectQuestion() {
	    SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
        model.addQuestionCreationModel(new QuestionCreationModel("Q1"));
        model.addQuestionCreationModel(new QuestionCreationModel("Q2"));
        model.addQuestionCreationModel(new QuestionCreationModel("Q3"));

        model.removeQuestionAtIndex(1);

        assertEquals("Q1", model.getQuestions().get(0).getQuestionText());
        assertEquals("Q3", model.getQuestions().get(1).getQuestionText());
    }
	
    @Test
    public void notReadyWithNullInputs() {
        SurveyCreationModel model = buildModelWithNameAndOwner(null, null);
        assertFalse(model.isReady());
    }

    @Test
    public void notReadyWithJustOwner() {
        SurveyCreationModel model = buildModelWithNameAndOwner(null, "test-user");
        assertFalse(model.isReady());
    }

    @Test
    public void notReadyWithOwnerAndName() {
        SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
        assertFalse(model.isReady());
    }

    @Test
    public void notReadyWhenOwnerAndNameIsZeroLength() {
        SurveyCreationModel model = buildModelWithNameAndOwner("", "");
        assertFalse(model.isReady());
    }

    @Test
    public void notReadyWhenZeroQuestions() {
        SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
        assertFalse(model.isReady());
    }

    @Test
    public void readyWithOneQuestion() {
        SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
        model.addQuestionCreationModel(new QuestionCreationModel("Question 1"));

        assertTrue(model.isReady());
    }

    @Test
    public void readyWithThreeQuestions() {
        SurveyCreationModel model = buildModelWithDefaultNameAndOwner();
        model.addQuestionCreationModel(new QuestionCreationModel("Q1"));
        model.addQuestionCreationModel(new QuestionCreationModel("Q2"));

        assertTrue(model.isReady());
    }

    private static SurveyCreationModel buildModelWithDefaultNameAndOwner() {
        return new SurveyCreationModel("name", "owner");
    }

    private static SurveyCreationModel buildModelWithNameAndOwner(String name, String owner) {
        return new SurveyCreationModel(name, owner);
    }
}
