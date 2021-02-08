package fr.formation.afpa.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
		try {
			b = new FileInputStream(f);
			ObjectInputStream o = new ObjectInputStream(b);

			while(o.read() != -1) {
					liste.add((Student) o.readObject());
			}
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}



		System.out.println(liste.size());
		return liste;
	}

	public void add(Student e) {
		// TODO Auto-generated method stub

	}

	public Student update(Student e) {
		// TODO Auto-generated method stub
		return null;
	}

}
