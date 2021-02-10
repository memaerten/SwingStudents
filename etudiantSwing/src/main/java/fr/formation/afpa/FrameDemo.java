package fr.formation.afpa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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
	// afficher nouvelle JFrame avec coordonnées Student

	public void afficher(Student e) {
		log.debug("Affichage de " + e.toString());
		JDialog frame = new JDialog(this);
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JPanel panelAdd = new JPanel();
		Date today = new Date();
		panelAdd.setLayout(new MigLayout("", "[1px][31px][77px,grow][7px][33px][54px][][][5px][2px][28px][36px]", "[14px][19px][19px][][20px][21px][][][][][]"));


		JLayeredPane layeredPane = new JLayeredPane();
		panelAdd.add(layeredPane, "cell 0 0,alignx left,aligny center");

		frame.getContentPane().add(panelAdd, BorderLayout.NORTH);

		//ImageIcon image = new ImageIcon(e.getPhoto());

		JLabel label = new JLabel("Photo :");
		panelAdd.add(label, "cell 1 1,alignx trailing");

		JLabel labelNom = new JLabel("Nom : ");
		panelAdd.add(labelNom, "cell 1 5,alignx left,aligny center");
		JTextField nomTextField = new JTextField(10);
		
		panelAdd.add(nomTextField, "cell 2 5 3 1,alignx left,growy");
		JLabel labelPrenom = new JLabel("Prénom : ");
		panelAdd.add(labelPrenom, "cell 5 5,alignx left,aligny center");
		JTextField prenomTextField = new JTextField(10);
		panelAdd.add(prenomTextField, "cell 7 5,alignx left,aligny top");
		JLabel labelMotDePasse = new JLabel("Mot de passe");
		panelAdd.add(labelMotDePasse, "cell 1 6,alignx left,aligny center");
		JPasswordField motDePasse = new JPasswordField(10);
		panelAdd.add(motDePasse, "cell 2 6 3 1,alignx left,aligny top");
		JLabel labelDateDeNaissance = new JLabel("Date de naissance : ");
		panelAdd.add(labelDateDeNaissance, "cell 5 6,alignx right,aligny center");
		JSpinner dateDeNaissance = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dateDeNaissance, "dd/MM/yy");
		dateDeNaissance.setEditor(editor);
		panelAdd.add(dateDeNaissance, "cell 7 6,alignx left,aligny top");
		
		nomTextField.setEditable(false);
		prenomTextField.setEditable(false);
		motDePasse.setEditable(false);
		dateDeNaissance.setEnabled(false);

		nomTextField.setText(e.getNom());
		prenomTextField.setText(e.getPrenom());
		motDePasse.setText(e.getMotDePasse());
		dateDeNaissance.setValue(e.getDateDeNaissance());

		JButton modifierButton = new JButton("Modifier");
		panelAdd.add(modifierButton, "cell 4 9");
		JButton cancelButton = new JButton("Annuler");
		panelAdd.add(cancelButton, "cell 5 9,alignx center,aligny top");
		frame.setVisible(true);
		
		modifierButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.debug("Modifcation de " + e.toString());
				nomTextField.setEditable(true);
				prenomTextField.setEditable(true);
				motDePasse.setEditable(true);
				dateDeNaissance.setEnabled(true);
				
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		});

	}
	
	public void liste() {
		
	}

	public FrameDemo() {
		super("Frame Demo");

		EtudiantService service = new EtudiantService();

		JMenuBar menuBar = new JMenuBar();
		JMenu studentBar = new JMenu("Etudiant");
		JMenuItem add = new JMenuItem("Ajouter");
		JMenuItem liste = new JMenuItem("Liste");

		JPanel panel = new JPanel();

		menuBar.add(studentBar);
		studentBar.add(add);
		studentBar.add(liste);

		String[] columns = {"id", "Nom", "Prénom", "Date de naissance"};
		List<Student> students = service.listEtudiant();
		String[] idStudents = new String[students.size()];
		String[] nomStudents = new String[students.size()];
		String[] prenomStudents = new String[students.size()];
		String[] dateStudents = new String[students.size()];
		Object[] tableStudents = new Student[5];
		for (int i = 0; i < students.size() ; i++) {
			Student st = students.get(i);
			//tableStudents[0] = students.get(i).getIdStudent();
			nomStudents[i] = students.get(i).getNom();
			prenomStudents[i] = students.get(i).getPrenom();
			dateStudents[i] = students.get(i).getDateDeNaissance().toLocaleString();
			
		}
		
		//revalidate vider l'image 
		Object[] studentsArray = students.toArray();
		//		JLabel labelTable = new JLabel("Liste");
		//		panel.add(labelTable);
		JTable table = new JTable(new Object[][] {tableStudents}, columns);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				afficher(students.get(table.getSelectedRow()));
				log.debug(students.get(table.getSelectedRow()));

			}
		});
		for (int i = 0; i < studentsArray.length; i++) {
			studentsArray[i] = new Student();
		}

		panel.add(new JScrollPane(table));

		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new MigLayout("", "[1px][31px][77px][7px][33px][54px][][5px][2px][28px][36px]", "[14px][19px][19px][][20px][21px][][][][]"));


		Date today = new Date();
		super.getContentPane().add(panelAdd, BorderLayout.NORTH);


		JLabel labelPhoto = new JLabel("Photo :");
		panelAdd.add(labelPhoto, "cell 1 1,alignx trailing");

		JTextField photo = new JTextField();
		photo.setEditable(false);
		panelAdd.add(photo, "cell 2 1 5 1,growx");
		photo.setColumns(10);
		JButton btnNewButton = new JButton("Parcourir...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File(".//..//..//.."));

				if (fileChooser.showOpenDialog(null)== 
						JFileChooser.APPROVE_OPTION) {
					log.debug("Fichier sélectionné : " + fileChooser.getSelectedFile().toString());
					photo.setText(fileChooser.getSelectedFile().toString());
				}
			}
		});
		 UtilDateModel model = new UtilDateModel();
	        Properties p = new Properties();
	        p.put("text.today", "Today");
	        p.put("text.month", "Month");
	        p.put("text.year", "Year");
	        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	        datePanel.setBounds(115, 272, 255, 35);
	        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	        datePicker.setBounds(115, 272, 255, 30);
	        panelAdd.add(datePicker);
	        
		panelAdd.add(btnNewButton, "cell 7 1");
		JLabel labelNom = new JLabel("Nom : ");
		panelAdd.add(labelNom, "cell 1 2,alignx right,aligny center");
		JTextField nomTextField = new JTextField(15);
		panelAdd.add(nomTextField, "cell 2 2,alignx left,growy");
		JLabel labelPrenom = new JLabel("Prénom : ");
		panelAdd.add(labelPrenom, "cell 5 2,alignx right,aligny center");
		JTextField prenomTextField = new JTextField(15);
		panelAdd.add(prenomTextField, "cell 7 2,alignx left,aligny top");
		JLabel labelMotDePasse = new JLabel("Mot de passe : ");
		panelAdd.add(labelMotDePasse, "cell 1 3,alignx left,aligny center");
		JPasswordField motDePasse = new JPasswordField(15);
		panelAdd.add(motDePasse, "cell 2 3,alignx left,aligny top");
		JLabel labelDateDeNaissance = new JLabel("Date de naissance : ");
		panelAdd.add(labelDateDeNaissance, "cell 5 3,alignx right,aligny center");
		JSpinner dateDeNaissance = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dateDeNaissance, "dd/MM/yy");
		dateDeNaissance.setEditor(editor);
		panelAdd.add(datePicker, "cell 7 3,alignx left,aligny top");
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
						Student student = new Student(nomTextField.getText(),prenomTextField.getText(),motDePasse.getText(),(Date) dateDeNaissance.getValue());
						System.out.println(student);
						service.ajouterEtudiant(student);
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
