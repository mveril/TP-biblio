package fr.cleverdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cleverdev.model.Livre;

public class LivreDaoImpl implements Dao<Livre> {

	private static final String SQL_INSERT       = "INSERT INTO livre(id_auteur,titre,nb_pages,categorie) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,nom,prenom,telephone,email FROM livre";
    private static final String SQL_SELECT_BY_ID = "SELECT id,id_auteur,titre,nb_pages,categorie FROM livre WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM livre WHERE id = ? ";
	private static final String SQL_Modify_BY_ID = "UPDATE livre SET id_auteur = ?, titre = ?,nb_pages = ?,categorie = ? WHERE id = ? ";
	private DaoFactory factory;
	
	
	
	public LivreDaoImpl() {
		this(DaoFactory.getInstance());
	}

	public LivreDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Livre livre) throws DaoException {
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con = factory.getConnection();
			
			pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setLong( 1, livre.getAuteur().getId() );
			pst.setString( 2, livre.getTitre() );
			pst.setInt( 3, livre.getNbPages() );
			pst.setString( 4, livre.getCategorie() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Auteur (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                livre.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation livre (ID non retourn�)" );
            }
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Auteur",ex);
	    } finally {
	    	try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	factory.releaseConnection(con);
		}
			
	}

	@Override
	public Livre trouver(long id) throws DaoException {
		Livre             livre = null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  livre = map(rs);
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Auteur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return livre;
	}

	@Override
	public List<Livre> lister() throws DaoException {
		List<Livre> listeAuteurs = new ArrayList<Livre>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeAuteurs.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Auteur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeAuteurs;
	}

	private Livre map(ResultSet rs) throws DaoException {
		try {
			var auteur = factory.getAuteurDao().trouver(rs.getLong("id_auteur"));
			var livre = new Livre(rs.getLong("id"), auteur, rs.getString("titre"), rs.getInt("nb_pages"), rs.getString("category"));
			return livre;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Unable to get autor", e);
		}
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection con = null;
		PreparedStatement pst = null;
	    try {
			con = factory.getConnection();
			pst = con.prepareStatement( SQL_DELETE_BY_ID );
			pst.setLong(1, id);
			int statut = pst.executeUpdate();
			if ( statut == 0 ) {
			  throw new DaoException("Erreur de suppression Auteur("+id+")");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DaoException("Impossible d'éfaccer le livre",e);
		}
	    finally {
		    try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DaoException("Impossible de fermer la requète.");
			}
	    	factory.releaseConnection(con);
	    }

		
	}

	@Override
	public void mettreajour(Livre item) throws DaoException {
		Connection con=null;
		PreparedStatement pst = null;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement( SQL_Modify_BY_ID);
			pst.setLong( 1, item.getAuteur().getId() );
			pst.setString( 2, item.getTitre() );
			pst.setInt( 3, item.getNbPages() );
			pst.setString( 4, item.getCategorie() );
			pst.setLong( 5, item.getId() );
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException( "Echec mise à jour Auteur" );
		}
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				throw new DaoException("Erreur lors de la fermeture de la requète",e);
			}
			factory.releaseConnection(con);
		}
	}

}
