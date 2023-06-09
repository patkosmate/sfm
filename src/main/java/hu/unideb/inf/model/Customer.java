package hu.unideb.inf.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public List<Vinyl> getVinyls() {
        return vinyls;
    }

    public void setVinyls(List<Vinyl> vinyls) {
        this.vinyls = vinyls;
    }
    public void setVinyl(Vinyl vinyl) {
        this.vinyls.add(vinyl);
    }

    @OneToMany(mappedBy = "customer")

    private List<Vinyl> vinyls = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
