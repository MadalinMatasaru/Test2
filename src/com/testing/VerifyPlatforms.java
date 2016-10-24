package com.testing;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import unittesting.UnitTestClassBase;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.TreeView;
import com.hp.lft.sdk.java.TreeViewDescription;
import com.hp.lft.sdk.java.TreeViewNode;
import com.hp.lft.sdk.java.Window;
import com.utils.GeneralUtils;

public class VerifyPlatforms extends UnitTestClassBase {

	public VerifyPlatforms() {
		//Change this constructor to private if you supply your own public constructor
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		instance = new VerifyPlatforms();
		globalSetup(VerifyPlatforms.class);
		
		Runtime.getRuntime().exec("C:\\Program Files (x86)\\HPE SA\\launcher\\hpe_sa_launcher.exe");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		globalTearDown();
	}

	@Before
	public void setUp() throws Exception {

		GeneralUtils generals = new GeneralUtils();
		generals.Login();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws GeneralLeanFtException, Exception {
		Window afterLoginWindow =  Desktop.describe(Window.class, new com.hp.lft.sdk.java.WindowDescription.Builder()
		.title("HPE Server Automation - 192.168.178.30").build());
		
//		JTree libraryPackages = (JTree) afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
//			.attachedText("Library").build());
		 
		TreeView libraryPackages = afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder().attachedText("Library").build());
	 
	
		 afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
			.label("Library").build()).click();
		 afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Packages;AIX;VIOS 2.2");
		 afterLoginWindow.describe(TreeView.class, new TreeViewDescription.Builder()
			.attachedText("Library").build()).select("By Type;Packages;AIX;VIOS 2.2");
		 //System.out.println(libraryPackages.buildNodePath("By Type", "Packages", "AIX", "Citrix XenServer 5"));
		 //System.out.println(libraryPackages.buildNodePath("By Type;Packages;XenServer;Citrix XenServer 5"));
		 Thread.sleep(5000); 
		 
        
		 //System.out.println(libraryPackages.getNode("By Type;Packages;AIX;VIOS 2.2").toString());
		 //System.out.println(libraryPackages.getNode("By Type;Packages;XenServer;Citrix XenServer 6").toString());
		 libraryPackages.getNode("By Type;Packages;Cent OS").expand();
		 libraryPackages.getNode("By Type;Packages;HP-UX").expand();
		 libraryPackages.getNode("By Type;Packages;Oracle Linux").expand();
		 libraryPackages.getNode("By Type;Packages;Red Hat").expand();
		 libraryPackages.getNode("By Type;Packages;Solaris").expand();
		 libraryPackages.getNode("By Type;Packages;SUSE").expand();
		 libraryPackages.getNode("By Type;Packages;UBUNTU").expand();
		 libraryPackages.getNode("By Type;Packages;VMware").expand();
		 libraryPackages.getNode("By Type;Packages;Windows").expand();
		 libraryPackages.getNode("By Type;Packages;XenServer").expand();
		 
		 List<TreeViewNode> nodes = new ArrayList(libraryPackages.getSelectedNodes()); 
		
		nodes.add(libraryPackages.getNode("By Type;Packages;AIX"));
		nodes.add(libraryPackages.getNode("By Type;Packages;AIX;AIX 6.1"));
		nodes.add(libraryPackages.getNode("By Type;Packages;AIX;AIX 7.1"));
		nodes.add(libraryPackages.getNode("By Type;Packages;AIX;VIOS 2.2"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS;Cent OS 5"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS;Cent OS 5 X86_64"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS;Cent OS 6"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS;Cent OS 6 X86_64"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Cent OS;Cent OS 7 X86_64"));
		nodes.add(libraryPackages.getNode("By Type;Packages;HP-UX"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Oracle Linux"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Red Hat"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Solaris"));
		nodes.add(libraryPackages.getNode("By Type;Packages;SUSE"));
		nodes.add(libraryPackages.getNode("By Type;Packages;UBUNTU"));
		nodes.add(libraryPackages.getNode("By Type;Packages;VMware"));
		nodes.add(libraryPackages.getNode("By Type;Packages;Windows"));
		nodes.add(libraryPackages.getNode("By Type;Packages;XenServer"));
		
		
		System.out.println(nodes.containsAll(new Platforms().platforms));
	
		
//		DefaultMutableTreeNode root = (DefaultMutableTreeNode)libraryPackages.getModel().getRoot();
//		    Enumeration e = root.preorderEnumeration();
//		    while(e.hasMoreElements()){
//		        System.out.println(e.nextElement());
//		    }
//		String result = "\n";
//	    Enumeration enumer = root.preorderEnumeration();
//	    while (enumer.hasMoreElements()) {
//	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumer.nextElement();
//	        String nodeValue = String.valueOf(node.getUserObject());
//	        String indent = "";
//	        while (node.getParent() != null) {
//	           indent += "    "; 
//	           node = (DefaultMutableTreeNode) node.getParent();
//	        }
//	        result += indent + nodeValue + "\n";
//	    }
		
	   
	 	//for (TreeViewNode treeViewNode : nodes) {
		//	System.out.println(treeViewNode);
		//}
       //libraryPackages.selectRange("By Type;Packages;AIX;VIOS 2.2","By Type;Packages;XenServer;Citrix XenServer 6");
        //System.out.println(libraryPackages.getSelectedNodes());
        
		afterLoginWindow.close();
		afterLoginWindow.describe(Button.class, new com.hp.lft.sdk.java.ButtonDescription.Builder()
		.label("Yes").build()).click();	
	}

}
 