package no.hib.megagruppe.webpoll.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SurveyCodeGeneratorTest {

	@Test
	public void findsFile(){
		SurveyCodeGenerator.init();
		assertTrue(SurveyCodeGenerator.ready);
	}
	
	@Test
	public void generatedCodeIsCorrectPattern(){
		int digits = 2;
		for(int i = 0; i < 100; i++){
			String code = SurveyCodeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-z]+[0-9]{0,"+digits+"}"));
		}
		digits = 3;
		for(int i = 0; i < 100; i++){
			String code = SurveyCodeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-z]+[0-9]{0,"+digits+"}"));
		}
		digits = 10;
		for(int i = 0; i < 100; i++){
			String code = SurveyCodeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-z]+[0-9]{0,"+digits+"}"));
		}
	}
	
}
