package Repositories;

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
public class HeroRepo {

    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;

    /**
     * Get all existing heroes.
     * @return List of heroes.
     */
    public List<Hero> getAllHeroes() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
    }


    /**
     * Get a specific hero by their ID.
     * @param id ID to search for.
     * @return An entire hero object.
     */
    public Hero getHeroById(Integer id) {
        return em.find(Hero.class, id);
    }


    /**
     * Persist a given hero to store in the database.
     * @param hero Hero object to persist.
     * @return Boolean that returns FALSE if this action fails for any reason.
     */
    @Transactional(REQUIRED)
    public boolean addHero(Hero hero) {
        try {
            // em.getTransaction().begin();
            em.persist(hero);
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
