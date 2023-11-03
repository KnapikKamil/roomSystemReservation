package org.exampleHotel.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.guest.dto.GuestDTO;

public class GuestsTab {

    private Tab guestTab;
    private GuestService guestService = ObjectPool.getGuestService();

    public GuestsTab(){
        TableView<GuestDTO> tableView = new TableView<>();
        this.guestTab = new Tab("Goście", tableView);
        guestTab.setClosable(false);

        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("Imię");
       firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<GuestDTO, Integer> ageColumn = new TableColumn<>("Wiek");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Płeć");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn);

        tableView.getItems().addAll(guestService.getAllAsDTO());
    }

    public Tab getGuestTab() {
        return guestTab;
    }
}
