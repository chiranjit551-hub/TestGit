package FrameworkTest;

import ConfigReader.ReadConfig;

public class TestFrameworkMethodsImplements extends ReadConfig
{
	
	public static void main(String[] args) {
		
		ReadConfig rcObj = new ReadConfig();
		rcObj.configLoader();
		String TestURL = rcObj.getURL();
		System.out.println(TestURL);
	/*	ReadLocator readLocator=new ReadLocator();
		readLocator.elementLocator();
		System.out.println(readLocator.getSignIn());*/
	}

}
