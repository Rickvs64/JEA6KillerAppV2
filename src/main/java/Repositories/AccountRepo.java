package Repositories;

import Domains.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Stateless
public class AccountRepo {
    @PersistenceContext(unitName = "JEA6KillerAppV2")
    private EntityManager em;


    public List<Account> getAllAccounts() {
        // For some reason this query isn't fully recognized as valid, so the below comment blocks it from QlInspections.
        //noinspection JpaQlInspection
        return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }


    public Account getAccountById(Long id) {
        return em.find(Account.class, id);
    }


    @Transactional(REQUIRED)
    public boolean addAccount(Account account) {
        try {
            em.persist(account);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
