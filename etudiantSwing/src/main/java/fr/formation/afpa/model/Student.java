package fr.formation.afpa.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Student implements Serializable {
	private String nom;
	private String prenom;
	private String motDePasse;
	private int idStudent;
	private static int listStudents = 0;
	private List <Double> notes;
	private Date dateDeNaissance;

	public Student() {

	}

//	public Student(String nom, String prenom, String motDePasse, int jourDeNaissance, int moisDeNaissance, int anneeDeNaissance) {
//		listStudents++;
//		this.idStudent = listStudents;
//		this.nom = nom;
//		this.prenom = prenom;
//		this.motDePasse = motDePasse;
//		this.notes = new ArrayList<Double>();
//		dateDeNaissance = new GregorianCalendar();
//		dateDeNaissance.set(jourDeNaissance, moisDeNaissance, anneeDeNaissance);
//	}

	public Student(String nom, String prenom, String motDePasse, Date dateDeNaissance) {
		listStudents++;
		this.idStudent = listStudents;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.notes = new ArrayList<Double>();
		this.dateDeNaissance = dateDeNaissance;
	}

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public List<Double> getNotes() {
		return notes;
	}

	public void setNotes(List<Double> notes) {
		this.notes = notes;
	}

	public String toString() {
		return idStudent + "Student " + nom + " " + prenom;
	}

	public double moyenne() {
		Iterator<Double> i = this.notes.iterator();
		double notes = 0;
		while (i.hasNext()) {
			notes = notes + i.next();
		}
		return notes/this.notes.size();
	}
	
	public void sauvegardeElements() {
		File f = new File("students.txt");
		if (!f.exists()) {
			try {
				System.out.println("oooo");
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			OutputStream w;
			try {
				w = new FileOutputStream(f);
				ObjectOutputStream o = new ObjectOutputStream(w);
				o.writeObject(this);
				w.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			//b.write(this.toString() + " Moyenne : " + this.moyenne() + " " + this.notes.toString());

			//b.close();
		
	}


	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Entrer nom : ");
		String nomStudent = s.nextLine();
		System.out.print("Entrer prénom : ");
		String prenomStudent = s.nextLine();
		Student student = new Student();
		
		System.out.println("Combien de notes voulez vous entrer ?");
		int nombreNotes = s.nextInt();
		s.nextLine();
		for (int i = 0 ; i < nombreNotes ; i++) {
			System.out.print("Entrez la note " + i + " : " );
			 student.notes.add(s.nextDouble());
			s.nextLine();
		}
		
//		System.out.println(student.toString() + " Moyenne : " + student.moyenne());
//		
		student.sauvegardeElements();
		
		s.close();
		
	}
}
