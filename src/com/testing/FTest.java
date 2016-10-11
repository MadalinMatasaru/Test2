package com.testing;

import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;

import javax.imageio.ImageIO;
import javax.swing.text.LabelView;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Timeout;

import unittesting.UnitTestClassBase;

import com.google.common.collect.Table.Cell;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.java.*;
import com.hp.lft.sdk.sap.gui.LabelDescription;
import com.hp.lft.sdk.stdwin.EditorDescription;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.web.XPathDescription;
import com.hp.lft.sdk.winforms.WindowDescription;
import com.hp.lft.verifications.Verify;
import com.hp.lft.sdk.insight.*;

import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import static org.junit.Assert.*;

public class FTest extends UnitTestClassBase {
	Properties prop = new Properties();
	InputStream inputStream;
	String propFileName = "config.properties";
	// get the property value and print it out
				String server;
				String user;
				String password;
		
	public FTest() {
		//Change this constructor to private if you supply your own public constructor
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = new FTest();
		globalSetup(FTest.class);
		
		Runtime.getRuntime().exec("C:\\Program Files (x86)\\HPE SA\\launcher\\hpe_sa_launcher.exe");
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		globalTearDown();
	}
	
	@Before
	public void setUp() throws Exception {	
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
	@Test
	public void test() throws GeneralLeanFtException {
		 //Select Library and then Import Software form the Actions menu      
		
		Window afterLoginWindow =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build());
		
		Window testPolicyWindow = Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("Software Policy: test policy*").build()); 
		
		 afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
		 afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Packages;Red Hat;Red Hat Enterprise Linux Server 5");
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("Import Software...");
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         
         // Import a dummy .zip file - the Browse buttons are identical so that we need to use Location property to distinguish them
         // also close all the modal windows that are being opened
        
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
  			.attachedText("Browse...").index(0).build()).click();
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Open").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("File Name:").build()).setText("test.zip");
         afterLoginWindow.describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Open").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Open").build()).click();
         afterLoginWindow.describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Folder:").build()).setText("/");
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Import").build()).click();
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Replace").build()).click();
         try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Imports").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Close").build()).click();
         try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
         
         //Create a new Software Policy - select the Software Policy target from the tree and then Actions - New menu
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
         afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Software Policies;Red Hat;Red Hat Enterprise Linux Server 5");
         try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("New...");
         try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: New Software Policy*").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Name:").build()).setText("test policy");
         try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
         testPolicyWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Views").build()).select("Software Policy:;Policy Items");
         
         // press the add button to add the zip file
         testPolicyWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("add16").build()).click();
         testPolicyWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.selectedNodes(new String[] { "#0;Application Configuration" }).build())
			.select("#0;Package");
         
         //enter 'test' in the search field and select the test policy 
         testPolicyWindow.describe(Editor.class, new com.hp.lft.sdk.java.EditorDescription.Builder()
			.tagName("JTextField").nativeClass("javax.swing.JTextField").build()).setText("test");  
         testPolicyWindow.describe(Table.class, new TableDescription.Builder()
			.objectName("com.opsware.ngui.library.ui.panels.LibraryFilterPanel").build()).selectRows(0);
         testPolicyWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Select").build()).click();
         testPolicyWindow.close();
         testPolicyWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("No").build()).click();
        
        //Select the managed server to which we want to attach the policy 
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Devices").build()).click();
         afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Devices").build()).select("Devices;Servers;All Managed Servers");
     
         afterLoginWindow.describe(Table.class, new TableDescription.Builder()
			.objectName("server-table").build()).selectRows(0); 
        
        //Attach the Policy 
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("Attach;Software Policy...");
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}

		 Table table =Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Attach Software Policy").build()).describe(Table.class, new TableDescription());
		
				 
		 for (TableRow row:table.getRows()){
			 for (TableCell cell:row.getCells() ){
				
				 if (cell.getValue().toString().equals("Test Policy")) {
					 cell.click();
					 
				}
				
			 }
		 }
		
		 
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Attach").build()).click();
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Remediate").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Start Job").build()).click();
         try {Thread.sleep(15000);} catch (InterruptedException e) {e.printStackTrace();}
       
         UiObject tabel = Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title(new RegExpProperty("Remediate.*")).build()).describe(UiObject.class, new UiObjectDescription.Builder()
			.nativeClass("com.opsware.ngui.apppolicy.task.RemediatePoliciesTask$2").build());
         
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title(new RegExpProperty("Remediate.*")).build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Close").build()).click();
	}

	@After
	public void tearDown() throws Exception {
		Window afterLoginWindow =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build());
		
		Table servers = afterLoginWindow.describe(Table.class, new TableDescription.Builder()
		.objectName("server-table").build()); 
        for (TableRow serverrows:servers.getRows()){
		 for (TableCell servercell:serverrows.getCells() ){
			 //System.out.println(servercell.getValue().toString());
			 if (servercell.getValue().toString().equals("tv-355.ta.opsware.com")) {
				 servercell.doubleClick();
				 Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder().title("Server: tv-355.ta.opsware.com").build())
				 .describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder().label("Management Policies").build()).click();
				 Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Server: tv-355.ta.opsware.com").build()).describe(TreeView.class, new TreeViewDescription.Builder()
					.attachedText("Management Policies").build()).select("Management Policies;Software Policies");	
			}
			 
		 }
	 }
     
     Table Policies = Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("Server: tv-355.ta.opsware.com").build()).describe(Table.class, new TableDescription.Builder()
		.attachedText("Software Policies").build());
     for (TableRow Policiesrows:Policies.getRows()){
		 for (TableCell Policiescell:Policiesrows.getCells() ){
			 if (Policiescell.getValue().toString().equals("Test Policy")) {
				 Policiescell.click(MouseButton.RIGHT);
				 
				 Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Server: tv-355.ta.opsware.com").build()).describe(Menu.class, new MenuDescription.Builder()
					.label("Actions").build()).click();
		         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
		         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Server: tv-355.ta.opsware.com").build()).describe(Menu.class, new MenuDescription.Builder()
					.label("Actions").build()).selectSubMenu("Uninstall Software...");
					
		         
		         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Uninstall Software").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
					.label("Start Job").build()).click();
					try {Thread.sleep(20000);} catch (InterruptedException e) {e.printStackTrace();}
					Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title(new RegExpProperty("Uninstall Software.*")).build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
					.label("Close").build()).click();
			 }
			 }
		 }
   
        
		afterLoginWindow.close();
		afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
		.label("Yes").build()).click();	
	}
}
 
 