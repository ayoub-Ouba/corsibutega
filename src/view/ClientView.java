package view;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class ClientView extends JFrame {
	public ClientView() {
		
	}

	 public void showMessage(String message) {
	      JOptionPane.showMessageDialog(this, message);
	  }


}
