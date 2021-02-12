package fr.formation.afpa.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.formation.afpa.model.Student;
import jdk.internal.org.jline.utils.Log;

public class EtudiantDaoFile implements IEtudiantDao{
	List<Student> liste;

	public List<Student> listStudents() {
		liste = new ArrayList <Student>();
		File f = new File("students.txt");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fichier créé");
		}
		InputStream b;

		if(!(f.length() == 0)) {
			try {
				b = new FileInputStream(f);
				ObjectInputStream o = new ObjectInputStream(b);
				liste = (List<Student>) o.readObject();
				o.close();

				b.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return liste;
	}

	public void add(Student e) {
		List <Student> liste = listStudents();
		if(e.getIdStudent() <= Student.getListStudents() && liste.size()!= 0) {
			for (int i = 0 ; i < liste.size(); i++) {
				if (e.getIdStudent() == liste.get(i).getIdStudent()) {
					liste.remove(i);
					liste.add(i,e);
				}
			}
		} else {
			liste.add(e);
		}
		File f = new File("students.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
		OutputStream w;
		try {
			w = new FileOutputStream(f);
			ObjectOutputStream o = new ObjectOutputStream(w);

			o.writeObject(liste);
			o.close();
			w.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(liste);

	}

	public Student update(Student e) {
		liste = listStudents();
		for (int i = 0 ; i < liste.size(); i++) {
			if (e.getIdStudent() == liste.get(i).getIdStudent()) {
				add(e);
				break;
			}
		}
		System.out.println(e.toString());
		return e;
	}

}
// chercher ancien supp ancien par nouveau


//liste.get(e.getIdStudent()).setNom(e.getNom());
//liste.get(e.getIdStudent()).setPrenom(e.getPrenom());
//liste.get(e.getIdStudent()).setPhoto(e.getPhoto());
//liste.get(e.getIdStudent()).setMotDePasse(e.getMotDePasse());
//liste.get(e.getIdStudent()).setDateDeNaissance(e.getDateDeNaissance());
