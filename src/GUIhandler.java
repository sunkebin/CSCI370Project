package src;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class GUIhandler implements ActionListener {
    public JFrame jframe;
    public JTextArea ja;
    public JScrollPane sp;
   public GUIhandler (JFrame jf) {
      jframe = jf;
      ja= new JTextArea(10, 20);
      ja.setEditable(false);
      sp = new JScrollPane(ja);
      jframe.getContentPane().add(sp); 
   }
   
   public void actionPerformed(ActionEvent event) {
      String menuName = event.getActionCommand();
      if (menuName.equals("Train")) {
    	  JFileChooser chooser = new JFileChooser();
    	  FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Documents", "csv");
    	  chooser.setFileFilter(filter);
    	  int returnVal = chooser.showSaveDialog(null);
    	  if(returnVal == JFileChooser.APPROVE_OPTION) {
    		  try {
				readSource(chooser.getSelectedFile());
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
    	  }
      }else if(menuName.equals("Predict")) {
          JFileChooser chooser = new JFileChooser();
          FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Documents", "csv");
          chooser.setFileFilter(filter);
          int returnVal = chooser.showSaveDialog(null);
          if(returnVal == JFileChooser.APPROVE_OPTION) {
              try {
                  readSourcePredict(chooser.getSelectedFile());
              } catch (NumberFormatException | IOException e) {
                  e.printStackTrace();
              }
          }
      }
      else if (menuName.equals("Quit")) {
          System.exit(0);
      }
   }

    public void readSourcePredict(File selectedFile)  throws NumberFormatException, IOException{
        RandomForest rf=new RandomForest();
        int[] predictionR=rf.predict(selectedFile);
        ja.selectAll();
        ja.replaceSelection("");
        for(int i=0;i<predictionR.length;i++){
            if(predictionR[i]==1){
                ja.append("Patient "+i+" has a high possibility to get exacerbation of diabetes.\n");
            }else ja.append("Patient "+i+" has a low possibility to get exacerbation of diabetes.\n");
        }
    }

    public void readSource(File chosenFile) throws NumberFormatException, IOException {
        RandomForest rf=new RandomForest();
	    rf.readFile(chosenFile);
        rf.RandomForestAlg();
        ja.selectAll();
        ja.replaceSelection("");
        ja.append("Training Finished.\n");
        for(int i=0;i<rf.DecisionTrees.length;i++){
            ja.append("Tree "+i+" accuracy is "+rf.DecisionTrees[i].Accuracy+".\n");
        }
        //report?

    }
   
   
}