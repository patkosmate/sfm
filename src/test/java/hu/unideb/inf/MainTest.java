package hu.unideb.inf;

import hu.unideb.inf.DAO.CustomerDAO;
import hu.unideb.inf.DAO.CustomerJpaDAO;
import hu.unideb.inf.DAO.VinylDAO;
import hu.unideb.inf.DAO.VinylJpaDAO;
import hu.unideb.inf.model.Customer;
import hu.unideb.inf.model.Vinyl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MainTest {
    static VinylDAO vinylDAO;
    static CustomerDAO customerDAO;


    @BeforeAll
    static void init() {
        vinylDAO = new VinylJpaDAO();
        customerDAO = new CustomerJpaDAO();

    }

    @Test
    void testVinylSave() {
        Vinyl newVinyl = new Vinyl();

        newVinyl.setArtist("Test Artist");
        newVinyl.setTitle("Ram Ranch");
        newVinyl.setDate(LocalDate.now());
        newVinyl.setRented(false);

        vinylDAO.saveVinyl(newVinyl);
        Assertions.assertEquals(1, vinylDAO.getVinyls().size());
        Assertions.assertTrue(vinylDAO.getVinyls().contains(newVinyl));
    }


    @Test
    void customerLogin() {
        Customer newCustomer = new Customer();
        newCustomer.setName("Teszt Elek");

        customerDAO.saveCustomer(newCustomer);

        Assertions.assertEquals(1, customerDAO.getCustomerDAO().size());
        Assertions.assertTrue(customerDAO.getCustomerDAO().contains(newCustomer));
        Assertions.assertEquals(newCustomer, customerDAO.getCustomerByNameDAO("Teszt Elek"));
    }

    @Test
    void testVinylRent() {
        Vinyl selectedVinyl = new Vinyl();

        selectedVinyl.setArtist("Test Artist");
        selectedVinyl.setTitle("Ram Ranch");
        selectedVinyl.setDate(LocalDate.now());
        vinylDAO.saveVinyl(selectedVinyl);


        Customer signedUpCustomer = new Customer();
        signedUpCustomer.setName("AAA");
        customerDAO.saveCustomer(signedUpCustomer);


        selectedVinyl.setRented(true);
        selectedVinyl.setCustomer(signedUpCustomer);
        signedUpCustomer.setVinyl(selectedVinyl);

        customerDAO.updateCustomerDAO(signedUpCustomer);
        vinylDAO.updateVinyl(selectedVinyl);

        Assertions.assertTrue(vinylDAO.getRentedVinyl().contains(selectedVinyl));
        Assertions.assertTrue(customerDAO.getCustomerByNameDAO("AAA").getVinyls().contains(selectedVinyl));
        Assertions.assertEquals(vinylDAO.getRentedVinyl().get(0).getCustomer(), signedUpCustomer);

    }


}
