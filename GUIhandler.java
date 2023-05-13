import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class GUIhandler implements ActionListener {
   JFrame jframe;
   
   public GUIhandler (JFrame jf) {
      jframe = jf;
   }
   
   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Open")) {
    	  JFileChooser chooser = new JFileChooser();
    	  FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV");
    	  chooser.setFileFilter(filter);
    	  int returnVal = chooser.showOpenDialog(null);
    	  if(returnVal == JFileChooser.APPROVE_OPTION) {
    		  try {
				readSource(chooser.getSelectedFile());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
    	  }
      }else if(menuName.equals("Predict")) {
    	  PredictInput();
      }
      else if (menuName.equals("Quit")) {
          JOptionPane.showMessageDialog(null,"You clicked on Quit"); 
      }
   }
   
   private void PredictInput() {
	   
	   
   }

   private void readSource(File chosenFile) throws NumberFormatException, IOException {
   
	   
   }
   
   
}