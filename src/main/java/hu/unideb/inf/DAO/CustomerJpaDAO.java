package hu.unideb.inf.DAO;
import hu.unideb.inf.model.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerJpaDAO implements CustomerDAO {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public void saveCustomer(Customer c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Customer> getCustomerDAO() {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT a FROM Customer a  ", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getCustomerByNameDAO(String name) {
        TypedQuery<Customer> query = entityManager.createQuery("SELECT a FROM Customer a  ", Customer.class);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.isEmpty());
        if(!customers.isEmpty()) {
            for (Customer c: customers) {
                if(c.getName().equals(name)) return c;
            }
        }
        return null;
    }

    @Override
    public void updateCustomerDAO(Customer c) {
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteCustomer(Customer c) {
        entityManager.getTransaction().begin();
        entityManager.remove(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
