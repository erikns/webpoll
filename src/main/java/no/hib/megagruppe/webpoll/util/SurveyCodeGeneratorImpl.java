package no.hib.megagruppe.webpoll.util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@Stateless
public class SurveyCodeGeneratorImpl implements SurveyCodeGenerator {
	private List<String> words;
	private int numberOfWords;

    @Inject
	public SurveyCodeGeneratorImpl(ResourceReader reader){
        words = reader.readAllLines();
        numberOfWords = words.size();
    }
	
	@Override
	public String generateSurveyCode(int digits){
		Random ran = new Random();
		String word = words.get(ran.nextInt(numberOfWords));
		
		int number = ran.nextInt((int)Math.pow(10, digits));

		return word+number;
	}
}
