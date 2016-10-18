package com.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.Dialog;
import com.hp.lft.sdk.java.DialogDescription;
import com.hp.lft.sdk.java.Editor;
import com.hp.lft.sdk.java.List;
import com.hp.lft.sdk.java.ListDescription;
import com.hp.lft.sdk.java.Menu;
import com.hp.lft.sdk.java.MenuDescription;
import com.hp.lft.sdk.java.Table;
import com.hp.lft.sdk.java.TableDescription;
import com.hp.lft.sdk.java.TreeView;
import com.hp.lft.sdk.java.TreeViewDescription;
import com.hp.lft.sdk.java.Window;
import com.hp.lft.sdk.stdwin.EditorDescription;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.winforms.WindowDescription;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

	public LeanFtTest() {
		//Change this constructor to private if you supply your own public constructor
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = new LeanFtTest();
		globalSetup(LeanFtTest.class);
		Runtime.getRuntime().exec("C:\\Program Files (x86)\\HPE SA\\launcher\\hpe_sa_launcher.exe");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		globalTearDown();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws GeneralLeanFtException {
		//Start the application
	     Window saWin =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build());
	     
	     //Login into the application and wait 20 seconds for the Server Automation app to load
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build()).describe(List.class, new ListDescription.Builder()
			.attachedText("Core Server:").build()).select("192.168.178.30");
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Continue").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("User Name:").build()).setText("ta");
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Password:").build()).setText("opsware");
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation Client Login").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Log In").build()).click();
        try {Thread.sleep(20000);} catch (InterruptedException e) {e.printStackTrace();}

        //Select Library and then Import Software form the Actions menu         
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Packages;Red Hat;Red Hat Enterprise Linux Server 5");
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("Import Software...");
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        
        // Import a dummy .zip file - the Browse buttons are identical so that we need to use Location property to distinguish them
        // also close all the modal windows that are being opened
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.attachedText("Browse...").location(new com.hp.lft.sdk.LocationProperty().setX(651).setY(7)).build()).click();
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Open").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("File Name:").build()).setText("test.zip");
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Open").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Open").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Dialog.class, new DialogDescription.Builder()
			.title("Software Imports").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Folder:").build()).setText("/");
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Import").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Replace").build()).click();
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Imports").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Close").build()).click();
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        
        //Create a new Software Policy - select the Software Policy target from the tree and then Actions - New menu
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Software Policies;Red Hat;Red Hat Enterprise Linux Server 5");
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).click();
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
			.label("Actions").build()).selectSubMenu("New...");
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: New Software Policy*").build()).describe(Editor.class, new EditorDescription.Builder()
			.attachedText("Name:").build()).setText("test policy");
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: test policy*").build()).describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Views").build()).select("Software Policy:;Policy Items");
        
        // press the add button to add the zip file
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: test policy*").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("add16").build()).click();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: test policy*").build()).describe(TreeView.class, new TreeViewDescription.Builder()
			.selectedNodes(new String[] { "#0;Application Configuration" }).build())
			.select("#0;Package");
        
        //enter 'test' in teh search field and select the test policy 
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
			.title("Software Policy: test policy*").build()).describe(Editor.class, new com.hp.lft.sdk.java.EditorDescription.Builder()
			.tagName("JTextField").nativeClass("javax.swing.JTextField").build()).setText("test");  
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build()).close();
        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
		.label("Yes").build()).click();	
        
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("Software Policy: test policy*").build()).describe(Table.class, new TableDescription.Builder()
//			.objectName("com.opsware.ngui.library.ui.panels.LibraryFilterPanel").build()).selectRows(0);
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("Software Policy: test policy*").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("Select").build()).click();
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("Software Policy: test policy*").build()).close();
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("Software Policy: test policy*").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("No").build()).click();
//       
//       //Select the managed server to which we want to attach the policy 
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("Devices").build()).click();
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(TreeView.class, new TreeViewDescription.Builder()
//			.attachedText("Devices").build()).select("Devices;Servers;All Managed Servers");
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Table.class, new TableDescription.Builder()
//			.objectName("server-table").build()).selectRows(0); 
//       
//       //Attach the Policy 
//        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}  
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
//			.label("Actions").build()).click();
//        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Menu.class, new MenuDescription.Builder()
//			.label("Actions").build()).selectSubMenu("Attach;Software Policy...");
//        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Table.class, new TableDescription.Builder()
//			.objectName("com.opsware.ngui.library.ui.panels.LibraryFilterPanel").build()).selectRows(7);
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("HPE Server Automation - 192.168.178.30").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("Attach").build()).click();
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title("Remediate").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("Start Job").build()).click();
//        try {Thread.sleep(15000);} catch (InterruptedException e) {e.printStackTrace();}
////        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
////			.title("Remediate").build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
////			.label("Close").build()).click();
//        Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
//			.title(new RegExpProperty("Remediate.*")).build()).describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
//			.label("Close").build()).click();
	}

}
 