package fr.formation.afpa;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Box;
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
import javax.swing.table.DefaultTableModel;

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
	EtudiantService service = new EtudiantService();
	JTable table;
	DefaultTableModel modele;
	List<Student> students;
	BufferedImage img;
	Image photoStudent;

	public static void main(String[] args) {
		// log.debug("Test");
		FrameDemo fd = new FrameDemo(); 

	}
	// afficher nouvelle JFrame avec coordonnées Student

	public void ajouter() {

	}

	public void afficher(Student studentAffiche) {
		log.debug("Affichage de " + studentAffiche.toString());
		JDialog frame = new JDialog(this);
		frame.setBounds(100, 100, 650, 500);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		img = null;
		try {
			img = ImageIO.read(new File(studentAffiche.getPhoto()));
		}  catch (IOException e) {
			e.printStackTrace();
		}

		JPanel infoStudent = new JPanel();

		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new MigLayout("", "[25.00][25.00px][31px][77px,grow][43.00px][33px][25.00px][25.00px]", "[][][][][][][][][][]"));


		photoStudent = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		ImageIcon image = new ImageIcon(photoStudent);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panelAdd.add(verticalStrut_3, "cell 3 0");
		JLabel labelNom = new JLabel("Nom : ");
		panelAdd.add(labelNom, "cell 2 1,alignx right,aligny center");
		JTextField nomTextField = new JTextField(10);
		panelAdd.add(nomTextField, "cell 3 1,growx");
		JLabel labelPrenom = new JLabel("Prénom : ");
		panelAdd.add(labelPrenom, "cell 2 2,alignx right,aligny center");
		JTextField prenomTextField = new JTextField(10);
		panelAdd.add(prenomTextField, "cell 3 2,growx");
		JLabel labelMotDePasse = new JLabel("Mot de passe : ");
		panelAdd.add(labelMotDePasse, "cell 2 3,alignx right");
		JPasswordField motDePasse = new JPasswordField(10);
		panelAdd.add(motDePasse, "cell 3 3,growx");

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelAdd.add(horizontalStrut, "cell 0 4");
		JLabel labelDateDeNaissance = new JLabel("Date de naissance : ");
		panelAdd.add(labelDateDeNaissance, "cell 2 4,alignx right,aligny center");
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setBounds(115, 272, 255, 35);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(115, 272, 255, 30);

		panelAdd.add(datePicker, "cell 3 4,growx");

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelAdd.add(horizontalStrut_1, "cell 7 4");

		Component verticalStrut = Box.createVerticalStrut(20);
		panelAdd.add(verticalStrut, "cell 3 6");
		JButton cancelButton = new JButton("Annuler");
		panelAdd.add(cancelButton, "cell 2 7,alignx center,aligny top");

		JButton modifier = new JButton("Modifier");
		panelAdd.add(modifier, "cell 4 7");

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelAdd.add(verticalStrut_2, "cell 2 9");
		frame.getContentPane().add(infoStudent);
		infoStudent.setLayout(new MigLayout("", "[25:37.00][25:25][25][57.00][31][][97.00][][][25:128.00px]", "[201.00][]"));

		JLabel label = new JLabel("Photo :");
		infoStudent.add(label, "cell 5 0,alignx right");

		JTextField photoTextField = new JTextField();
		photoTextField.setEditable(false);

		JLabel imageP = new JLabel();

		photoTextField.setColumns(10);
		infoStudent.add(photoTextField, "cell 6 0");
		JButton btnNewButton = new JButton("Parcourir...");

		nomTextField.setEditable(false);
		prenomTextField.setEditable(false);
		motDePasse.setEditable(false);
		datePicker.setEnabled(false);

		photoTextField.setVisible(false);
		btnNewButton.setVisible(false);

		nomTextField.setText(studentAffiche.getNom());
		prenomTextField.setText(studentAffiche.getPrenom());
		motDePasse.setText(studentAffiche.getMotDePasse());
		datePicker.getModel().setDate(studentAffiche.getDateDeNaissance().getDay(), studentAffiche.getDateDeNaissance().getMonth(), studentAffiche.getDateDeNaissance().getYear());

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File(".//..//..//.."));

				if (fileChooser.showOpenDialog(null)== 
						JFileChooser.APPROVE_OPTION) {
					//log.debug("Fichier sélectionné : " + fileChooser.getSelectedFile().toString());
					photoTextField.setText(fileChooser.getSelectedFile().toString());
					ImageIcon picture = new ImageIcon(new ImageIcon(fileChooser.getSelectedFile().toString()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
					imageP.setIcon(picture);

				}
			}
		});
		infoStudent.add(btnNewButton, "cell 7 0");

		modifier.addActionListener(new ActionListener() {
			boolean modeEdition = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				Student modification = studentAffiche;
				if (modeEdition == false) {
					modeEdition = true;
					log.debug("Modification de " + studentAffiche.toString());
					modifier.setText("Enregistrer");
					nomTextField.setEditable(true);
					prenomTextField.setEditable(true);
					motDePasse.setEditable(true);
					datePicker.setEnabled(true);

					photoTextField.setText(studentAffiche.getPhoto());
					photoTextField.setVisible(true);
					btnNewButton.setVisible(true);
				} else {
					studentAffiche.setNom(nomTextField.getText());
					studentAffiche.setPrenom(prenomTextField.getText());
					studentAffiche.setMotDePasse(motDePasse.getText());
					studentAffiche.setDateDeNaissance((Date) datePicker.getModel().getValue());
					studentAffiche.setPhoto(photoTextField.getText());
					System.out.println(modification.getIdStudent());
					System.out.println(nomTextField.getText() + " " + prenomTextField.getText() + " " + motDePasse.getText()+ " " + (Date) datePicker.getModel().getValue());

					photoTextField.setVisible(false);
					btnNewButton.setVisible(false);

					//service.ajouterEtudiant(service.modifierEtudiant(studentAffiche));
					modele.fireTableDataChanged();
					frame.dispose();
				}

			}
		});

		// Panel photo

		infoStudent.add(imageP, "cell 8 0");
		imageP.setSize(150, 150);
		imageP.setIcon(image);
		infoStudent.add(panelAdd, "cell 0 1 10 1,grow");		


		//
		//		nomTextField.setText(studentAffiche.getNom());
		//		prenomTextField.setText(studentAffiche.getPrenom());
		//		motDePasse.setText(studentAffiche.getMotDePasse());
		//		datePicker.getModel().setDate(studentAffiche.getDateDeNaissance().getDay(), studentAffiche.getDateDeNaissance().getMonth(), studentAffiche.getDateDeNaissance().getYear());
		//
		//		JButton modifierButton = new JButton("Modifier");
		//		panelAdd.add(modifierButton, "cell 4 9");
		//		JButton cancelButton = new JButton("Annuler");
		//		panelAdd.add(cancelButton, "cell 5 9,alignx center,aligny top");
		//		frame.add(photo);
		frame.setVisible(true);

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

		JMenuBar menuBar = new JMenuBar();
		JMenu studentBar = new JMenu("Etudiant");
		JMenuItem add = new JMenuItem("Ajouter");
		JMenuItem liste = new JMenuItem("Liste");


		menuBar.add(studentBar);
		studentBar.add(add);
		studentBar.add(liste);


		JPanel panel = new JPanel();


		table = new JTable();

		refresh();
		JScrollPane scroll = new JScrollPane(table);

		//revalidate vider l'image 
		Object[] studentsArray = students.toArray();
		//		JLabel labelTable = new JLabel("Liste");
		//		panel.add(labelTable);
		// table = new JTable(new Object[][] {tableStudents}, columns);


		panel.add(scroll);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
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
						service.ajouterEtudiant(new Student(nomTextField.getText(),prenomTextField.getText(),motDePasse.getText(),(Date) datePicker.getModel().getValue(), photo.getText()));
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
				students = service.listEtudiant();
				FrameDemo.super.add(panel);
				repaint();
				refresh();
				System.out.println(service.listEtudiant());

			}

		});


		super.add(panel);
		panel.setVisible(true);
		super.setJMenuBar(menuBar);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		addWindowListener(this); 
		setVisible(true);
	}

	public void refresh() {
		students = service.listEtudiant();
		String[] columns = {"id", "Nom", "Prénom", "Date de naissance"};
		DefaultTableModel modele = new DefaultTableModel();
		modele.setColumnIdentifiers(columns);
		if (students.size() == 0) {

		} else {
			Object[] tableStudents = new Object[4];
			for (int i = 0; i < students.size() ; i++) {
				Object student = students.get(i);
				tableStudents[0] = ((Student) student).getIdStudent();
				tableStudents[1] = ((Student) student).getNom();
				tableStudents[2] = ((Student) student).getPrenom();
				tableStudents[3] = ((Student) student).getDateDeNaissance().toLocaleString();
				modele.addRow(tableStudents);
				modele.fireTableRowsUpdated(0, students.size());

			}
		}
		table.setModel(modele);
		FrameDemo.log.debug(students);
	
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
