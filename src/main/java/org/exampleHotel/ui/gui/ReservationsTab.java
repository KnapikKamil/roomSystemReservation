package org.exampleHotel.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;

public class ReservationsTab {
    private Tab reservationTab;
    private ReservationService reservationService = new ReservationService();

    public ReservationsTab(){
        this.reservationTab = new Tab();
        reservationTab.setClosable(false);
        TableView<ReservationDTO> tableView = new TableView<>();

        /*
       ivate int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int roomId;
    private int roomNumber;
    private int guestId;
    private String name;

        * */

        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("Od");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("Do");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, Integer> roomColumn = new TableColumn<>("Pokój");
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<ReservationDTO, Integer> guestColumn = new TableColumn<>("Rezerwujący");
        guestColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.getColumns().addAll(fromColumn, toColumn, roomColumn, guestColumn);


        tableView.getItems().addAll(reservationService.getAsDTO());

        this.reservationTab = new Tab("Rezerwacje", tableView);
        reservationTab.setClosable(false);


    }

    public Tab getReservationTab() {
        return reservationTab;
    }
}
