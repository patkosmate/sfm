package hu.unideb.inf.DAO;
import hu.unideb.inf.model.Vinyl;
import java.util.List;

public interface VinylDAO extends AutoCloseable {
    public void saveVinyl(Vinyl v);

    public List<Vinyl> getVinyls();

    public void updateVinyl(Vinyl v);

    public void deleteVinyl(Vinyl v);

    public List<Vinyl> getRentedVinyl();
}
