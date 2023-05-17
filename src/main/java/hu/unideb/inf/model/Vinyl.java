package hu.unideb.inf.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vinyl {
    @Id
    @GeneratedValue
    private int id;

    private String artist;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")

    private Customer customer;

    private Boolean rented;

    private LocalDate date;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Vinyl() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
