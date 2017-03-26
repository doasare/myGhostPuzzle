package com.javaapp.myGhostPuzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import com.google.common.collect.Multimap;
/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
        SpringApplication.run(AppController.class, args);       
    }

    @Bean
	public WordRepository wordRepository() {
		WordRepository wordRepository = new WordRepository();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/word.lst")));
			String word;
			while ((word = br.readLine()) != null) {
	            if (word.length() > 4){
	            	wordRepository.add(word);
	            }      
	        } br.close();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
		return wordRepository;
	}
}
