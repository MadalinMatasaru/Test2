package com.utils;

import java.io.InputStream;
import java.util.Properties;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.Editor;
import com.hp.lft.sdk.java.List;
import com.hp.lft.sdk.java.ListDescription;
import com.hp.lft.sdk.java.Table;
import com.hp.lft.sdk.java.TableCell;
import com.hp.lft.sdk.java.TableRow;
import com.hp.lft.sdk.java.Window;
import com.hp.lft.sdk.stdwin.EditorDescription;

public class GeneralUtils {
	
	//The Login method should be used in any new test case, it executes the steps for loging into S.S> with TA and OPSWARE credentials on the 192.168.178.30 server
	public void Login() throws Exception
	{
		Properties prop = new Properties();
		InputStream inputStream;
		String propFileName = "config.properties";
		
					String server;
					String user;
					String password;
					
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		
		server = prop.getProperty("server");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		 
		
		//Start the application
	     Window saWin =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build());
	     
	     //Login into the application and wait 20 seconds for the Server Automation app to load
	     saWin.describe(List.class, new ListDescription.Builder().attachedText("Core Server:").build()).select(server);
	     saWin.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Continue").build()).click();
	     saWin.describe(Editor.class, new EditorDescription.Builder()
			.attachedText("User Name:").build()).setText(user);
	     saWin.describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Password:").build()).setText(password);
	     saWin.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Log In").build()).click();
         try {Thread.sleep(20000);} catch (InterruptedException e) {e.printStackTrace();}

	}

}
