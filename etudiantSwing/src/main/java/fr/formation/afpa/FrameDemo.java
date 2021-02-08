package fr.formation.afpa;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Hello world!
 *
 */
public class FrameDemo extends JFrame implements WindowListener {
	public static final Log log = LogFactory.getLog(FrameDemo.class);
	
	public static void main(String[] args) {
		// log.debug("Test");
		FrameDemo fd = new FrameDemo(); 
	
		
	}
	 
    public FrameDemo() {
        super("Frame Demo");
        
    	JMenuBar menuBar = new JMenuBar();
		JMenu studentBar = new JMenu("Etudiant");
		JMenuItem add = new JMenuItem("Ajouter");
		JMenuItem liste = new JMenuItem("Liste");
		
		menuBar.add(studentBar);
		studentBar.add(add);
		studentBar.add(liste);
		
		super.setJMenuBar(menuBar);
 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 400);
        addWindowListener(this); 
        setVisible(true);
    }
 
    public void windowActivated(WindowEvent event) {
        System.out.println("The window has been activated");
    }
 
    public void windowClosed(WindowEvent event) {
        System.out.println("The window has been closed");
    }
 
    public void windowClosing(WindowEvent event) {
        System.out.println("About to close the window");
    }
 
    public void windowDeactivated(WindowEvent event) {
        System.out.println("The window has been deactivated");
    }
 
    public void windowDeiconified(WindowEvent event) {
        System.out.println("The window has been restored");
    }
 
    public void windowIconified(WindowEvent event) {
        System.out.println("The window has been minimized");
    }
 
    public void windowOpened(WindowEvent event) {
        System.out.println("The window has been opened");
    }
}
