package fr.formation.afpa;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class FrameDemo extends JFrame implements WindowListener {
	public static void main(String[] args) {
		FrameDemo fd = new FrameDemo(); 
				
	}
	 
    public FrameDemo() {
        super("Frame Demo");
        // initialization code...
 
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
