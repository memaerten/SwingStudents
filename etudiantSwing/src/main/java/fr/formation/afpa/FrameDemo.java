package fr.formation.afpa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.formation.afpa.model.Student;
import fr.formation.afpa.service.EtudiantService;
import net.miginfocom.swing.MigLayout;


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

		String[] columns = {"id", "Nom", "Prénom", "Date de naissance"};
		List<Student> students = service.listEtudiant();
		String[] idStudents = new String[students.size()];
		String[] nomStudents = new String[students.size()];
		String[] prenomStudents = new String[students.size()];
		String[] dateStudents = new String[students.size()];
		for (int i = 0; i < students.size() ; i++) {
			idStudents[i] = String.valueOf(students.get(i).getIdStudent());
			nomStudents[i] = students.get(i).getNom();
			prenomStudents[i] = students.get(i).getPrenom();
			dateStudents[i] = students.get(i).getDateDeNaissance().toLocaleString();
		}
		Object[] studentsArray = students.toArray();
		JTable table = new JTable(new Object[][] {}, columns);
		// System.out.println(new Object[][] {idStudents,nomStudents,prenomStudents,dateStudents});
		for (int i = 0; i < studentsArray.length; i++) {
			studentsArray[i] = new Student();
		}
		System.out.println(nomStudents);

		panel.add(new JScrollPane(table));
		//		table.setVisible(false);

		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new MigLayout("", "[1px][31px][77px][7px][33px][54px][][5px][2px][28px][36px]", "[14px][19px][19px][][20px][21px][][][][]"));


		JLayeredPane layeredPane = new JLayeredPane();
		Date today = new Date();
		panelAdd.add(layeredPane, "cell 0 0,alignx left,aligny center");
		JLabel labelNom = new JLabel("Nom : ");
		panelAdd.add(labelNom, "cell 1 1,alignx left,aligny center");
		JTextField nomTextField = new JTextField(10);
		panelAdd.add(nomTextField, "cell 2 1 3 1,alignx left,growy");
		JLabel labelPrenom = new JLabel("Prénom : ");
		panelAdd.add(labelPrenom, "cell 5 1,alignx left,aligny center");
		JTextField prenomTextField = new JTextField(10);
		panelAdd.add(prenomTextField, "cell 6 1 4 1,alignx left,aligny top");
		JLabel labelMotDePasse = new JLabel("Mot de passe");
		panelAdd.add(labelMotDePasse, "cell 1 2,alignx left,aligny center");
		JPasswordField motDePasse = new JPasswordField(10);
		panelAdd.add(motDePasse, "cell 2 2 3 1,alignx left,aligny top");
		JLabel labelDateDeNaissance = new JLabel("Date de naissance : ");
		panelAdd.add(labelDateDeNaissance, "cell 5 2,alignx right,aligny center");

		super.getContentPane().add(panelAdd, BorderLayout.NORTH);
		JSpinner dateDeNaissance = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dateDeNaissance, "dd/MM/yy");
		dateDeNaissance.setEditor(editor);
		panelAdd.add(dateDeNaissance, "cell 6 2 4 1,alignx left,aligny top");
		JButton addButton = new JButton("Ajouter");
		panelAdd.add(addButton, "cell 4 8,alignx center,aligny center");
		JButton cancelButton = new JButton("Annuler");
		panelAdd.add(cancelButton, "cell 5 8,alignx center,aligny top");

		panelAdd.setVisible(false);
		add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrameDemo.super.getContentPane().removeAll();
				FrameDemo.super.add(panelAdd);
				repaint();
				panelAdd.setVisible(true);
				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						System.out.println(nomTextField.getText() + " " + prenomTextField.getText() + " " + dateDeNaissance.getValue());
						service.ajouterEtudiant(new Student(nomTextField.getText(),prenomTextField.getText(),motDePasse.getText(),(Date) dateDeNaissance.getValue()));
					}
				});
				
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						FrameDemo.super.getContentPane().removeAll();
						FrameDemo.super.add(panel);
						repaint();
						System.out.println(service.listEtudiant());
						
					}
				});

			}

		});

		liste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrameDemo.super.getContentPane().removeAll();
				FrameDemo.super.add(panel);
				repaint();
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
		log.debug("The window has been activated");
	}

	public void windowClosed(WindowEvent event) {
		log.debug("The window has been closed");
	}

	public void windowClosing(WindowEvent event) {
		log.debug("About to close the window");
	}

	public void windowDeactivated(WindowEvent event) {
		log.debug("The window has been deactivated");
	}

	public void windowDeiconified(WindowEvent event) {
		log.debug("The window has been restored");
	}

	public void windowIconified(WindowEvent event) {
		log.debug("The window has been minimized");
	}

	public void windowOpened(WindowEvent event) {
		log.debug("The window has been opened");
	}
}
