package hu.unideb.inf.controller;
import hu.unideb.inf.DAO.CustomerDAO;
import hu.unideb.inf.DAO.CustomerJpaDAO;
import hu.unideb.inf.DAO.VinylDAO;
import hu.unideb.inf.DAO.VinylJpaDAO;
import hu.unideb.inf.model.Customer;
import hu.unideb.inf.model.Vinyl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLVinylRentalController implements Initializable {

    CustomerDAO customerDAO = new CustomerJpaDAO();
    VinylDAO vinylDAO = new VinylJpaDAO();
    Customer signedUpCustomer = null;
    List<Vinyl> rentedVinyls = new ArrayList<>();
    List<Vinyl> vinyls = new ArrayList<>();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Integer index;

    //ObservableList<TableModel> listView = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Vinyl, Date> vinylDateColumn;

    @FXML
    private TableColumn<Vinyl, Date> rentedDateColumn;

    @FXML
    private TextField handleNameTyping;

    @FXML
    private TextField handleArtistTyping;

    @FXML
    private TextField handleTitleTyping;

    @FXML
    private ImageView img;

    @FXML
    private TableColumn<Vinyl, String> vinylNameColumn;

    @FXML
    private TableColumn<Vinyl, String> vinylTitleColumn;

    @FXML
    private TableView<Vinyl> vinylTableView;

    @FXML
    private TableView<Vinyl> rentedTableView1;

    @FXML
    private TableColumn<Vinyl, String> rentedNameColumn;

    @FXML
    private TableColumn<Vinyl, String> rentedTitleColumn;

    @FXML
    private TableColumn<Vinyl, String> rentedRenterColumn;

    @FXML
    void getitem(MouseEvent event) {

    }

    @FXML
    void refreshRentedButtonPushed(ActionEvent event) {
        rentedVinyls = vinylDAO.getRentedVinyl();
    }

    @FXML
    void refreshVinylButtonPushed(ActionEvent event) {
        vinyls = vinylDAO.getVinyls();
    }

    @FXML
    void rentButtonPushed(ActionEvent event) {

    }

    @FXML
    void signUpButtonPushed(ActionEvent event) {
        if (!handleNameTyping.getText().isEmpty()) {
            Customer newCustomer;
            String nameInput = handleNameTyping.getText();
            newCustomer = customerDAO.getCustomerByNameDAO(nameInput);
            if (newCustomer == null) {
                newCustomer = new Customer();
                newCustomer.setName(nameInput);
                customerDAO.saveCustomer(newCustomer);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("You are registered!");
                alert.setHeaderText("You can start to upload/rent!");
                alert.setContentText(null);
                alert.showAndWait();
                signedUpCustomer = newCustomer;
            } else {
                signedUpCustomer = newCustomer;
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("You are signed in!");
                alert.setHeaderText(null);
                alert.setHeaderText("You can start to upload/rent!");
                alert.showAndWait();
            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Missing data!");
            alert.setHeaderText(null);
            alert.setContentText("Please provide your name!");
            alert.showAndWait();
        }
    }

    @FXML
    void uploadButtonPushed(ActionEvent event) {
        if(signedUpCustomer != null) {
            if (!handleArtistTyping.getText().isEmpty() && !handleTitleTyping.getText().isEmpty()) {
            Vinyl newVinyl = new Vinyl();
            newVinyl.setArtist(handleArtistTyping.getText());
            newVinyl.setTitle(handleTitleTyping.getText());
            newVinyl.setDate(LocalDate.now());
            newVinyl.setRented(false);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Upload successful!");
            alert.setHeaderText("Upload successful!");
            alert.setContentText(null);
            alert.showAndWait();
            vinylDAO.saveVinyl(newVinyl);
            List<Vinyl> vinyls1 = vinylDAO.getVinyls();
            vinylTableView.getItems().setAll(vinyls1);
            } else {
                /*showAlertBox();*/
            }
        }
            else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Please registrate or sing in");
                alert.setHeaderText(null);
                alert.setContentText("Name is necessary!");
                alert.showAndWait();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vinylNameColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        vinylTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        vinylDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        rentedNameColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        rentedTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        rentedRenterColumn.setCellValueFactory(new PropertyValueFactory<>("Renter"));
        rentedDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        List<Vinyl> vinyls1 = vinylDAO.getVinyls();
        List<Vinyl> rentedVinyls1 = vinylDAO.getRentedVinyl();
        vinylTableView.getItems().setAll(vinyls1);
        rentedTableView1.getItems().setAll(rentedVinyls1);
    }
}
