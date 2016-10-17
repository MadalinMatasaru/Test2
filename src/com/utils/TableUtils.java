package com.utils;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.MouseButton;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.Table;
import com.hp.lft.sdk.java.TableCell;
import com.hp.lft.sdk.java.TableDescription;
import com.hp.lft.sdk.java.TableRow;
import com.hp.lft.sdk.java.TreeView;
import com.hp.lft.sdk.java.TreeViewDescription;
import com.hp.lft.sdk.java.Window;

public class TableUtils {

	// This function searches for a Text in a Table and when it finds it executes a DoubleClick 
public static void searchTableDoubleClick(Table servers, String searchString) throws Exception
{
    for (TableRow serverrows:servers.getRows()){
	 for (TableCell servercell:serverrows.getCells() ){
		 System.out.println(servercell.getValue().toString());
		 if (servercell.getValue().toString().equals(searchString)) {
			 servercell.doubleClick();
		}
	 }
    }

}

//This function searches for a Text in a Table and when it finds it executes a RightClick 
public static void searchTableRightClick(Table servers, String searchString) throws Exception
{
    for (TableRow serverrows:servers.getRows()){
	 for (TableCell servercell:serverrows.getCells() ){
		 System.out.println(servercell.getValue().toString());
		 if (servercell.getValue().toString().equals(searchString)) {
			 servercell.click(MouseButton.RIGHT);
		}
	 }
    }

}
}
