package fr.formation.afpa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.formation.afpa.model.Student;
import fr.formation.afpa.service.EtudiantService;


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
        
        EtudiantService service = new EtudiantService();
        
    	JMenuBar menuBar = new JMenuBar();
		JMenu studentBar = new JMenu("Etudiant");
		JMenuItem add = new JMenuItem("Ajouter");
		JMenuItem liste = new JMenuItem("Liste");
		JMenuItem modification = new JMenuItem("Modifier");
		
		JPanel panel = new JPanel();
		
		menuBar.add(studentBar);
		studentBar.add(add);
		studentBar.add(liste);
		studentBar.add(modification);
		
		String[] columns = {"id", "Nom", "Prénom", "Date de naissance", "Notes"};
		List<Student> students = service.listEtudiant();
		System.out.println(students.size());
//		students.add(new Student("Aaaaa","A","ooooooo",01,01,2000));
		//JTable table = new JTable(new Object[][] {service.listEtudiant().toArray()}, columns);
//		
//		panel.add(new JScrollPane(table));
//		table.setVisible(false);
		
		JPanel panelAdd = new JPanel();
		JLabel labelNom = new JLabel("Nom : ");
		JTextField nomTextField = new JTextField(10);
		JLabel labelPrenom = new JLabel("Prénom : ");
		JTextField prenomTextField = new JTextField(10);
		JLabel labelDateDeNaissance = new JLabel("Date de naissance : ");
		JButton addButton = new JButton("Ajouter");
		JButton cancelButton = new JButton("Annuler");
		panelAdd.add(labelNom);
		panelAdd.add(nomTextField);
		panelAdd.add(labelPrenom);
		panelAdd.add(prenomTextField);
		panelAdd.add(labelDateDeNaissance);
		panelAdd.add(addButton);
		panelAdd.add(cancelButton);
		
		super.add(panelAdd);
		panelAdd.setVisible(false);
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				FrameDemo.super.add(panelAdd);
				panelAdd.setVisible(true);
				
			}
			
		});
		
		liste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//table.setVisible(true);
				System.out.println(service.listEtudiant());
				
			}
			
		});
		
		modification.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		super.add(panel);
		panel.setVisible(true);
		super.setJMenuBar(menuBar);
 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
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
