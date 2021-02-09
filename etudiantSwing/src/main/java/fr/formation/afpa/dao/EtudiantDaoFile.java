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

public class EtudiantDaoFile implements IEtudiantDao{

	public List<Student> listStudents() {
		List<Student> liste = new ArrayList <Student>();
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
		liste.add(e);
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
			w.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public Student update(Student e) {
		// TODO Auto-generated method stub
		return null;
	}

}
