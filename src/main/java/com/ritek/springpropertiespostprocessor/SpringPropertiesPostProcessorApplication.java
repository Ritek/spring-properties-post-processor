package com.ritek.springpropertiespostprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPropertiesPostProcessorApplication {
	private static String property;
	private static String fileProperty1;
	private static String fileProperty2;

	@Value("${property}")
	public void showProp(String prop) {
		property = prop;
	}
	@Value("${file1PropertyName}")
	public void showProp2(String prop) {
		fileProperty1 = prop;
	}
	@Value("${file2PropertyName}")
	public void showProp3(String prop) {
		fileProperty2 = prop;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringPropertiesPostProcessorApplication.class, args);

		System.err.println("Property value: " + property);
		System.err.println("Property from file1: " + fileProperty1);
		System.err.println("Property from file2: " + fileProperty2);
	}

}
