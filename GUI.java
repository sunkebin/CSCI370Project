import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class GUI extends JFrame {
      
	public void DateGui() throws NumberFormatException, IOException {
		JFrame jf= new JFrame("swname");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500,500);
		jf.setLocation(30,30);
		createFileMenu(jf);
	
	    //visible
	    jf.pack();
	    jf.setVisible(true);
	}

	   private void createFileMenu(JFrame jf ) {
	      JMenuItem   item;
	      JMenuBar    menuBar  = new JMenuBar();
	      JMenu       fileMenu = new JMenu("Menu");
	      GUIhandler fmh  = new GUIhandler(jf);

	      item = new JMenuItem("Train");
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      fileMenu.addSeparator();         
	    
	      item = new JMenuItem("Predict");    
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      fileMenu.addSeparator();      
	      
	      item = new JMenuItem("Quit");   
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      jf.setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	    
	   } //createMenu


} 
