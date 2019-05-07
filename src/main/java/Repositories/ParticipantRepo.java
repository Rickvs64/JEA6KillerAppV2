package Repositories;

import Domains.Hero;
import Domains.Participant;
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
public class ParticipantRepo {

    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;

    /**
     * Get all existing participants. This is generally not a desired function.
     * @return List of participants.
     */
    public List<Participant> getAllParticipants() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT p FROM Participant p", Participant.class).getResultList();
    }


    /**
     * Get a specific participant by their ID.
     * @param id ID to search for.
     * @return An entire participant object.
     */
    public Participant getParticipantById(Integer id) {
        return em.find(Participant.class, id);
    }


    /**
     * Persist a given participant to store in the database.
     * @param participant Participant object to persist.
     * @return Boolean that returns FALSE if this action fails for any reason.
     */
    @Transactional(REQUIRED)
    public boolean addParticipant(Participant participant) {
        try {
            // em.getTransaction().begin();
            em.persist(participant);
            // em.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            // em.getTransaction().rollback();
            // System.out.println(ex.getMessage());
            return false;
        }
    }


    /**
     * Get all participant objects that belong to a given account.
     * @param accountId ID of the account to search for.
     * @return List of participants (matches this account participated in)
     */
    public List<Participant> getAllParticipantsByAccountId(Integer accountId) {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT p FROM Participant p WHERE p.accountId = :accountid", Participant.class)
                .setParameter("accountid", accountId)
                .getResultList();
    }


    /**
     * Get all participant objects that belong to a given match.
     * @param matchId ID of the match to get all participants of.
     * @return List of participants (players that participated in this match).
     */
    public List<Participant> getAllParticipantsByMatchId(Integer matchId) {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT p FROM Participant p WHERE p.matchId = :matchid", Participant.class)
                .setParameter("matchid", matchId)
                .getResultList();
    }

}
