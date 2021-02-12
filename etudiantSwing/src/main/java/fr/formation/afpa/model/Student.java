package fr.formation.afpa.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String motDePasse;
	private int idStudent;
	private static int listStudents = 0;
	public static void setListStudents(int listStudents) {
		Student.listStudents = listStudents;
	}

	// private List <Double> notes;
	private Date dateDeNaissance;
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Student() {
		listStudents++;
		this.idStudent = listStudents;
	}

	public Student(String nom, String prenom) {
		listStudents++;
		this.idStudent = listStudents;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Student(String nom, String prenom, String motDePasse) {
		listStudents++;
		this.idStudent = listStudents;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;

	}

	public Student(String nom, String prenom, String motDePasse, Date dateDeNaissance, String photo) {
		listStudents++;
		this.idStudent = listStudents;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
	//	this.notes = new ArrayList<Double>();
		this.dateDeNaissance = dateDeNaissance;
		this.photo = photo;
	}


	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
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

//	public List<Double> getNotes() {
//		return notes;
//	}
//
//	public void setNotes(List<Double> notes) {
//		this.notes = notes;
//	}

	public String toString() {
		return idStudent + " Student " + nom + " " + prenom;
	}

//	public double moyenne() {
//		Iterator<Double> i = this.notes.iterator();
//		double notes = 0;
//		while (i.hasNext()) {
//			notes = notes + i.next();
//		}
//		return notes/this.notes.size();
//	}

	public static int getListStudents() {
		return listStudents;
	}




}
