package org.exampleHotel.ui.gui.reservations;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.domain.room.dto.RoomDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddNewReservationScene {

        private Scene mainScene;
        private RoomService roomService = ObjectPool.getRoomService();
        private GuestService guestService = ObjectPool.getGuestService();
        private ReservationService reservationService = ObjectPool.getReservationService();

        public AddNewReservationScene(Stage modalStage, TableView<ReservationDTO> tableView){

                GridPane gridPane = new GridPane();
                gridPane.setAlignment(Pos.CENTER);
gridPane.setVgap(14);

                Label fromDateLabel = new Label("Data rozpoczęcia: ");
                Label toDateLabel = new Label("Data zakończenia: ");
                Label roomLabel = new Label("Pokój: ");
                Label guestLabel = new Label("Rezerwujący: ");

                DatePicker fromDateField = new DatePicker();
                DatePicker toDateField = new DatePicker();

            List<RoomDTO> allAsDTO = this.roomService.getAllAsDTO();

            List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();

            allAsDTO.forEach( dto -> {
                roomSelectionItems.add(
                        new RoomSelectionItem(dto.getNumber(), (int) dto.getId()));
            });

            List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();

            this.guestService.getAllAsDTO().forEach( dto -> {
                guestSelectionItems.add(new GuestSelectionItem(dto.getFirstName(), dto.getLastName(), (int)dto.getId()));
            });

            ComboBox<RoomSelectionItem> roomField = new ComboBox<>();
            roomField .getItems().addAll(roomSelectionItems);
            ComboBox<GuestSelectionItem> guestField = new ComboBox<>();
            guestField.getItems().addAll(guestSelectionItems);

            Button button = new Button("Utwórz rezerwację");
            button.setOnAction(actionEvent -> {
                LocalDate from = fromDateField.getValue();
                LocalDate to = toDateField.getValue();
                long guestId = guestField.getValue().getId();
                long roomId = roomField.getValue().getId();

               try {
                   this.reservationService.createNewReservation(from, to, roomId, guestId);

                   tableView.getItems().clear();
                   tableView.getItems().addAll(this.reservationService.getAsDTO());
                   modalStage.close();
               }catch (IllegalArgumentException e){
                   Label errorLabel = new Label("Niepoprawne daty rezerwacji");
                   errorLabel.setTextFill(Color.RED);
                   errorLabel.setFont(new Font(20));
                   gridPane.add(errorLabel, 0, 5);
               }
            });



            gridPane.add(fromDateLabel, 0, 0);
            gridPane.add(fromDateField, 1, 0);
            gridPane.add(toDateLabel, 0, 1);
            gridPane.add(toDateField, 1, 1);
            gridPane.add(roomLabel, 0, 2);
            gridPane.add(roomField, 1, 2);
            gridPane.add(guestLabel, 0, 3);
            gridPane.add(guestField, 1, 3);
            gridPane.add(button, 1, 4);

            this.mainScene = new Scene(gridPane, 1280, 640);
                this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("hotelReservation.css").toExternalForm());

        }
        public Scene getMainScene() {
                return this.mainScene;
        }

}
