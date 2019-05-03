package Repositories;

import Domains.Gamemode;
import Domains.PlayedMatch;
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
public class PlayedMatchRepo {

    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;

    /**
     * Retrieve all matches ever played by anyone. May cause data strain: use with caution.
     * @return Full list of all matches.
     */
    public List<PlayedMatch> getAllMatches() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT m FROM PlayedMatch m", PlayedMatch.class).getResultList();
    }


    /**
     * Get a specific match by its ID.
     * @param id ID to search for.
     * @return Match object with corresponding ID.
     */
    public PlayedMatch getMatchById(Integer id) {
        return em.find(PlayedMatch.class, id);
    }


    /**
     * Persist a given match to store in the database.
     * @param match Match object to persist.
     * @return Boolean that returns FALSE if this action fails for any reason.
     */
    @Transactional(REQUIRED)
    public boolean addMatch(PlayedMatch match) {
        try {
            // em.getTransaction().begin();
            em.persist(match);
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
