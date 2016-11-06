package no.hib.megagruppe.webpoll.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class SurveyCodeGenerator {
	
	public static boolean ready = false;
	private static ArrayList<String> words;
	private static int numberOfWords;
	
	/**
	 * Reads the dictionary and stores all the words in an ArrayList.
	 */
	public static void init(){
		try {
			
			URL url = SurveyCodeGenerator.class.getResource("dictionary.txt");
			FileReader fileReader = new FileReader(url.getPath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			words = new ArrayList<>();
			String word;
			while((word = bufferedReader.readLine()) != null){
				words.add(word);
			}
			bufferedReader.close();
			
			ready = true;
			numberOfWords = words.size();
			
		} catch (IOException e) {
			ready = false;
		}
	}
	
	/**
	 * Generates a surveycode based on a dictionary-file concatenated by a number with specified digits.
	 * @param digits The number of digits that will be used in the code.
	 * @return A surveycode with a word and a number.
	 */
	public static String generateSurveyCode(int digits){
		
		if(!ready){
			init();
		}
		
		Random ran = new Random();
		String word = words.get(ran.nextInt(numberOfWords));
		
		int number = ran.nextInt((int)Math.pow(10, digits));

		return word+number;
	}
}
