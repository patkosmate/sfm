package hu.unideb.inf.DAO;
import hu.unideb.inf.model.Customer;
import java.util.List;

public interface CustomerDAO extends AutoCloseable {
    public void saveCustomer(Customer c);

    public List<Customer> getCustomerDAO();

    public Customer getCustomerByNameDAO(String name);

    public void updateCustomerDAO(Customer c);

    public void deleteCustomer(Customer c);
}
