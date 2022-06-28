package fr.cleverdev;

import java.util.Iterator;
import java.util.List;

import fr.cleverdev.dao.Dao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Auteur;

public class Launcher {

	public static void main(String[] args) throws DaoException {

		Dao<Auteur> auteurDao = DaoFactory.getInstance().getAuteurDao();
		List<Auteur> listAuteurs;
		
		try {
			listAuteurs = auteurDao.lister();
			
		    Iterator<Auteur> i = listAuteurs.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }

		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

}
