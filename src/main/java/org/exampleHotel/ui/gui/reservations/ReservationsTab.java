package org.exampleHotel.ui.gui.reservations;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;

public class ReservationsTab {
    private Tab reservationTab;
    private ReservationService reservationService = ObjectPool.getReservationService();
    public ReservationsTab(Stage primaryStage) {
        TableView<ReservationDTO> tableView = getReservationDTOTableView();
        Button btn = new Button("Utwórz rezerwację");
        btn.setOnAction(actionEvent -> {
            Stage stg = new Stage();
            stg.initModality(Modality.WINDOW_MODAL);
            stg.initOwner(primaryStage);
            stg.setScene(new AddNewReservationScene(stg, tableView).getMainScene());
            stg.setTitle("Tworzenie rezerwacji");

            stg.showAndWait();
        });
        VBox layout = new VBox(btn, tableView);

        this.reservationTab = new Tab("Rezerwacje", layout);
        this.reservationTab.setClosable(false);


    }

    private TableView<ReservationDTO> getReservationDTOTableView() {
        TableView<ReservationDTO> tableView = new TableView<>();


        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("Od");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("Do");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, Integer> roomColumn = new TableColumn<>("Pokój");
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        TableColumn<ReservationDTO, Integer> guestColumn = new TableColumn<>("Rezerwujący");
        guestColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<ReservationDTO, ReservationDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory( value -> new ReadOnlyObjectWrapper(value.getValue()));

        deleteColumn.setCellFactory( param -> new TableCell<>(){
            Button deleteButton = new Button("Usuń");

            @Override
            protected void updateItem(ReservationDTO value, boolean empty){
                super.updateItem(value, empty);

                if (value==null){
                    setGraphic(null);
                }else{
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(actionEvent -> {
                        reservationService.remove(value.getId());
                        tableView.getItems().remove(value);
                    });
                }
            }
        });


        tableView.getColumns().addAll(fromColumn, toColumn, roomColumn, guestColumn, deleteColumn);


        tableView.getItems().addAll(reservationService.getAsDTO());
        return tableView;
    }

    public Tab getReservationTab() {
        return reservationTab;
    }
}
