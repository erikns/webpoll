package no.hib.megagruppe.webpoll.model;

import no.hib.megagruppe.webpoll.models.lecturer.QuestionCreationModel;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionCreationModelTest {
	@Test
	public void multipleChoiceNoOptionsNotReady() {
		String[] options = {};
		QuestionCreationModel model = new QuestionCreationModel(options, false,
				"Test question?");

		assertFalse(model.isReady());
	}

	@Test
	public void multipleChoiceWithOptionsReady() {
	    String[] options = {"Yes", "No"};
	    QuestionCreationModel model = new QuestionCreationModel(options, false,
				"Test question?");

	    assertTrue(model.isReady());
	}

	@Test
	public void freetextReadyByDefault() {
	    QuestionCreationModel model = new QuestionCreationModel("Test question?");
	    assertTrue(model.isReady());
	}
}
