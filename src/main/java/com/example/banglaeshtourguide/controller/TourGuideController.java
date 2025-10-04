package com.example.banglaeshtourguide.controller;

import com.example.banglaeshtourguide.dao.DistrictDAO;
import com.example.banglaeshtourguide.dao.SpotDAO;
import com.example.banglaeshtourguide.model.District;
import com.example.banglaeshtourguide.model.Spot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TourGuideController {

    @FXML
    private ComboBox<District> districtComboBox;
    @FXML
    private TableView<Spot> spotTableView;
    @FXML
    private TableColumn<Spot, String> spotNameColumn;
    @FXML
    private TableColumn<Spot, String> ticketPriceColumn; // Use String or BigDecimal for price
    @FXML
    private TableColumn<Spot, String> facilitiesColumn;

    private DistrictDAO districtDAO = new DistrictDAO();
    private SpotDAO spotDAO = new SpotDAO();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        spotNameColumn.setCellValueFactory(new PropertyValueFactory<>("spotName"));
        ticketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        facilitiesColumn.setCellValueFactory(new PropertyValueFactory<>("facilities"));

        // Load districts when the application starts
        loadDistricts();

        // Add a listener to the ComboBox to load spots when a district is selected
        districtComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadSpotsForDistrict(newValue);
            } else {
                spotTableView.getItems().clear(); // Clear table if no district is selected
            }
        });
    }

    private void loadDistricts() {
        List<District> districts = districtDAO.getAllDistricts();
        ObservableList<District> observableDistricts = FXCollections.observableArrayList(districts);
        districtComboBox.setItems(observableDistricts);
    }

    private void loadSpotsForDistrict(District district) {
        List<Spot> spots = spotDAO.getSpotsByDistrictId(district.getDistrictId());
        ObservableList<Spot> observableSpots = FXCollections.observableArrayList(spots);
        spotTableView.setItems(observableSpots);
    }

    @FXML
    private void refreshDistricts(ActionEvent event) {
        loadDistricts();
        spotTableView.getItems().clear(); // Clear spots when districts are refreshed
    }
}
