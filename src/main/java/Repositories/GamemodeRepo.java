package Repositories;

import Domains.Gamemode;
import Domains.Hero;
import Interceptor.RepoInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Interceptors(RepoInterceptor.class)
@Stateless
public class GamemodeRepo {

    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;

    /**
     * Get all gamemodes.
     * @return List of existing gamemodes, even ones that haven't been played yet by anyone.
     */
    public List<Gamemode> getAllGamemodes() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT g FROM Gamemode g", Gamemode.class).getResultList();
    }


    /**
     * Get a specific gamemode by ID.
     * @param id The gamemode ID to search for.
     * @return The gamemode result.
     */
    public Gamemode getGamemodeById(Integer id) {
        return em.find(Gamemode.class, id);
    }


    /**
     * Persist a given gamemode to store in the database.
     * @param gamemode Gamemode object to persist.
     * @return Boolean that returns FALSE if this action fails for any reason.
     */
    @Transactional(REQUIRED)
    public boolean addGamemode(Gamemode gamemode) {
        try {
            // em.getTransaction().begin();
            em.persist(gamemode);
            // em.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            // em.getTransaction().rollback();
            // System.out.println(ex.getMessage());
            return false;
        }
    }

}
