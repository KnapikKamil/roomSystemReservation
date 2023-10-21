package org.exampleHotel.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.domain.room.dto.RoomDTO;

import java.util.List;

public class RoomsTab {
    private Tab troomTab;
    private RoomService roomService = new RoomService();

    public RoomsTab() {
        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Numer");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Typy łóżek");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));
        tableView.getColumns().addAll(numberColumn, bedsColumn);
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);


        this.troomTab = new Tab("Pokoje", tableView);
        this.troomTab.setClosable(false);
    }

    public Tab getTroomTab() {
        return troomTab;
    }
}
