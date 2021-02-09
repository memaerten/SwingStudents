package fr.formation.afpa.service;

import java.util.List;

import fr.formation.afpa.dao.EtudiantDaoFile;
import fr.formation.afpa.dao.IEtudiantDao;
import fr.formation.afpa.model.Student;

public class EtudiantService implements IEtudiantService {
	
	private IEtudiantDao dao = new EtudiantDaoFile();

	public List<Student> listEtudiant() {
		return dao.listStudents();
	}

	public void ajouterEtudiant(Student e) {
		dao.add(e);
		// ajouter dans Etudiants.txt
	}

	public Student modifierEtudiant(Student e) {
		return dao.update(e);
	}

}
