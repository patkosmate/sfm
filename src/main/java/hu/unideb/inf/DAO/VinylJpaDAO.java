package hu.unideb.inf.DAO;
import hu.unideb.inf.model.Vinyl;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class VinylJpaDAO implements VinylDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Override
    public void saveVinyl(Vinyl v) {
        entityManager.getTransaction().begin();
        entityManager.persist(v);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Vinyl> getVinyls() {
        TypedQuery<Vinyl> query = entityManager.createQuery("SELECT a FROM Vinyl a WHERE a.rented = false", Vinyl.class);
        return query.getResultList();
    }

    @Override
    public void updateVinyl(Vinyl v) {
        entityManager.getTransaction().begin();
        entityManager.merge(v);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteVinyl(Vinyl v) {
        entityManager.getTransaction().begin();
        entityManager.remove(v);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Vinyl> getRentedVinyl() {
        TypedQuery<Vinyl> query = entityManager.createQuery("SELECT a FROM Vinyl a WHERE a.rented = true", Vinyl.class);
        return query.getResultList();
    }

    @Override
    public void close() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }
}
