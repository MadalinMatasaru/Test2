package com.testing;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unittesting.UnitTestClassBase;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.MouseButton;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.Dialog;
import com.hp.lft.sdk.java.DialogDescription;
import com.hp.lft.sdk.java.Editor;
import com.hp.lft.sdk.java.List;
import com.hp.lft.sdk.java.ListDescription;
import com.hp.lft.sdk.java.Menu;
import com.hp.lft.sdk.java.MenuDescription;
import com.hp.lft.sdk.java.Table;
import com.hp.lft.sdk.java.TableCell;
import com.hp.lft.sdk.java.TableDescription;
import com.hp.lft.sdk.java.TableRow;
import com.hp.lft.sdk.java.TreeView;
import com.hp.lft.sdk.java.TreeViewDescription;
import com.hp.lft.sdk.java.UiObject;
import com.hp.lft.sdk.java.UiObjectDescription;
import com.hp.lft.sdk.java.Window;
import com.hp.lft.sdk.stdwin.EditorDescription;
import com.utils.GeneralUtils;
import com.utils.TableUtils;


public class FTest extends UnitTestClassBase {
	Properties prop = new Properties();
	InputStream inputStream;
	String propFileName = "config.properties";
	
//				String server;
//				String user;
//				String password;
				String policyserver;
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
		
		GeneralUtils generals = new GeneralUtils();
		generals.Login();
	}
	@Test
	public void test() throws Exception {
		 //Select Library and then Import Software form the Actions menu      
		policyserver = prop.getProperty("policyserver");
		
		Window afterLoginWindow =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build());
		
		Window testPolicyWindow = Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("Software Policy: test policy*").build()); 
		
		 afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
		 afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Packages;Red Hat;Red Hat Enterprise Linux Server 5");
        Thread.sleep(5000); 
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("Import Software...");
         Thread.sleep(5000); 
         
         // Import a dummy .zip file - the Browse buttons are identical so that we need to use Location property to distinguish them
         // also close all the modal windows that are being opened
        
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
  			.attachedText("Browse...").index(0).build()).click();
         Thread.sleep(5000); 
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
         Thread.sleep(3000); 
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Imports").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Close").build()).click();
         Thread.sleep(3000); 
         
         //Create a new Software Policy - select the Software Policy target from the tree and then Actions - New menu
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
         afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Software Policies;Red Hat;Red Hat Enterprise Linux Server 5");
         Thread.sleep(3000); 
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         Thread.sleep(5000); 
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("New...");
         Thread.sleep(3000); 
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: New Software Policy*").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Name:").build()).setText("test policy");
         Thread.sleep(3000); 
         testPolicyWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Views").build()).select("Software Policy:;Policy Items");
         
         // press the add button to add the zip file
         testPolicyWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("add16").build()).click();
         testPolicyWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.selectedNodes(new String[] { "#0;Application Configuration" }).build())
			.select("#0;Package");
         
         //enter 'test' in teh search field and select the test policy 
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
         Thread.sleep(5000);   
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
         Thread.sleep(5000); 
         afterLoginWindow.describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("Attach;Software Policy...");
         Thread.sleep(5000); 
		 
		 
		 Table table =Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Attach Software Policy").build()).describe(Table.class, new TableDescription());
		
		 TableUtils.searchTableDoubleClick(table, "Test Policy");	 
         
         afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Attach").build()).click();
         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Remediate").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Start Job").build()).click();
         Thread.sleep(15000); 
 
         
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
		
		TableUtils.searchTableDoubleClick(servers, policyserver);
		Thread.sleep(3000); 
		
		Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder().title("Server: tv-355.ta.opsware.com").build())
		 .describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder().label("Management Policies").build()).click();
		 Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Server: tv-355.ta.opsware.com").build()).describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Management Policies").build()).select("Management Policies;Software Policies");	
		 Thread.sleep(3000); 
		 
     Table Policies = Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("Server: tv-355.ta.opsware.com").build()).describe(Table.class, new TableDescription.Builder()
		.attachedText("Software Policies").build());
     
     TableUtils.searchTableRightClick(Policies,"Test Policy");
                
				 Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Server: tv-355.ta.opsware.com").build()).describe(Menu.class, new MenuDescription.Builder()
					.label("Actions").build()).click();
				 Thread.sleep(5000); 
		         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Server: tv-355.ta.opsware.com").build()).describe(Menu.class, new MenuDescription.Builder()
					.label("Actions").build()).selectSubMenu("Uninstall Software...");
					
		         
		         Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title("Uninstall Software").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
					.label("Start Job").build()).click();
		            Thread.sleep(20000); 
					Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
					.title(new RegExpProperty("Uninstall Software.*")).build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
					.label("Close").build()).click();			
        
		afterLoginWindow.close();
		afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
		.label("Yes").build()).click();	
	}
}
 