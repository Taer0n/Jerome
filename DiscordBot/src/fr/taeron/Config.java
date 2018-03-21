package fr.taeron;

import java.io.File;

public class Config {

	public static String PREFIX;
	public static File CONFIG = new File(System.getProperty("user.home") + "\\JeromeConfig");
	
	static {
		PREFIX = "=";
	}
}
