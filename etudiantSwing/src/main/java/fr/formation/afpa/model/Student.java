package fr.formation.afpa.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Student {
	private String nom;
	private String prenom;
	private String motDePasse;
	private int idStudent;
	private static int listStudents = 0;
	private List <Double> notes;

	public Student() {

	}

	public Student(String nom, String prenom, String motDePasse) {
		listStudents++;
		this.idStudent = listStudents;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.notes = new ArrayList<Double>();
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

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public static int getListStudents() {
		return listStudents;
	}

	public static void setListStudents(int listStudents) {
		Student.listStudents = listStudents;
	}

	public List<Double> getNotes() {
		return notes;
	}

	public void setNotes(List<Double> notes) {
		this.notes = notes;
	}

	public String toString() {
		return "Student " + nom + " " + prenom;
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
		File f = new File(nom+ ".student");
		try {
			f.createNewFile();
			
			Writer w = new FileWriter(f);
			BufferedWriter b = new BufferedWriter(w);

			b.write(this.toString() + " Moyenne : " + this.moyenne() + " " + this.notes.toString());
			
			b.close();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Entrer nom : ");
		String nomStudent = s.nextLine();
		System.out.print("Entrer prénom : ");
		String prenomStudent = s.nextLine();
		Student student = new Student(nomStudent, prenomStudent, "a");
		
		System.out.println("Combien de notes voulez vous entrer ?");
		int nombreNotes = s.nextInt();
		s.nextLine();
		for (int i = 0 ; i < nombreNotes ; i++) {
			System.out.print("Entrez la note " + i + " : " );
			student.notes.add(s.nextDouble());
			s.nextLine();
		}
		
		System.out.println(student.toString() + " Moyenne : " + student.moyenne());
		
		student.sauvegardeElements();
		
		s.close();
		
	}
}
