package no.hib.megagruppe.webpoll.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.ejb.Stateless;

@Stateless
public class SurveyCodeGeneratorImpl implements SurveyCodeGenerator {
	
	private ArrayList<String> words;
	private int numberOfWords;
	
	public SurveyCodeGeneratorImpl(){
		init();
	}
	
	@Override
	public void init(){
		try {
			
			URL url = SurveyCodeGeneratorImpl.class.getResource("dictionary.txt");
			FileReader fileReader = new FileReader(url.getPath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			words = new ArrayList<>();
			String word;
			while((word = bufferedReader.readLine()) != null){
				words.add(word);
			}
			bufferedReader.close();
			
			numberOfWords = words.size();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String generateSurveyCode(int digits){
		
		Random ran = new Random();
		String word = words.get(ran.nextInt(numberOfWords));
		
		int number = ran.nextInt((int)Math.pow(10, digits));

		return word+number;
	}
}
