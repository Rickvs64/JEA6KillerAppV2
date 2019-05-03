package Repositories;

import Domains.Account;
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
public class AccountRepo {
    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;


    /**
     * Get all accounts.
     * @return List of existing accounts.
     */
    public List<Account> getAllAccounts() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }


    /**
     * Get one account that matches the given ID.
     * @param id Account ID to search for.
     * @return Account object.
     */
    public Account getAccountById(Integer id) {
        return em.find(Account.class, id);
    }


    /**
     * Persist a given account to store in the database.
     * @param account Account object to persist.
     * @return Boolean that returns FALSE if this action fails for any reason.
     */
    @Transactional(REQUIRED)
    public boolean addAccount(Account account) {
        try {
            // em.getTransaction().begin();
            em.persist(account);
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
