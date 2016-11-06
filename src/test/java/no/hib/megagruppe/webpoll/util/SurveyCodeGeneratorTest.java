package no.hib.megagruppe.webpoll.util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SurveyCodeGeneratorTest {

    private SurveyCodeGenerator codeGenerator;

	@Before
	public void setup() {
        StubResourceReader resourceReader = new StubResourceReader();
        codeGenerator = new SurveyCodeGeneratorImpl(resourceReader);
	}

	@Test
	public void generatedCodeIsCorrectPattern(){
		int digits = 2;
		for(int i = 0; i < 100; i++){
			String code = codeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-zæøå]+[0-9]{1,"+digits+"}"));
		}
		digits = 3;
		for(int i = 0; i < 100; i++){
			String code = codeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-zæøå]+[0-9]{1,"+digits+"}"));
		}
		digits = 10;
		for(int i = 0; i < 100; i++){
			String code = codeGenerator.generateSurveyCode(digits);
			assertTrue(code.matches("[a-zæøå]+[0-9]{1,"+digits+"}"));
		}
	}

    class StubResourceReader implements ResourceReader {
        @Override
        public List<String> readAllLines() {
            List<String> wordlist = new ArrayList<>();
            wordlist.add("fox");
            wordlist.add("rabbit");
            wordlist.add("badger");
            return wordlist;
        }
    }
	
}
