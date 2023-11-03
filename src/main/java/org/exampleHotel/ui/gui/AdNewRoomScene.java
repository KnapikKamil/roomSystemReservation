package org.exampleHotel.ui.gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.domain.room.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class AdNewRoomScene {

     private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();
    public AdNewRoomScene(Stage stg, TableView<RoomDTO> tableView){
        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();
        HBox roomNumber = new HBox(roomNumberLabel, roomNumberField);

        Label bedTypeLabel = new Label("Typy łóżek:");

        Button adNewBedButton = new Button("Dodaj kolejne łóżko");

        HBox bedType = new HBox(bedTypeLabel, adNewBedButton);



        VBox bedsVerticalLayout = new VBox(bedType, getComboBox());

        adNewBedButton.setOnAction(actionEvent -> {
        bedsVerticalLayout.getChildren().add(getComboBox());
        });
        Button addNewRoomButton = new Button("Dodaj nowy pokój");
        addNewRoomButton.setOnAction(actionEvent -> {
            int newRoomNumber = Integer.parseInt(roomNumberField.getText());
            List<String> bedTypes = new ArrayList<>();

            this.comboBoxes.forEach(comboBoxes -> {
                bedTypes.add(comboBoxes.getValue());
            });
           this.roomService.createNewRoom(newRoomNumber, bedTypes);
           tableView.getItems().clear();
           List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
           tableView.getItems().addAll(allAsDTO);

           stg.close();
        });

        VBox vmainFormLayout = new VBox(roomNumber, bedsVerticalLayout, addNewRoomButton);

        this.mainScene = new Scene(vmainFormLayout,1280,640);
    }

    private ComboBox <String> getComboBox() {
        ComboBox <String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll("Pojedyńcze", "podwójne", "Królewskie");
        bedTypeField.setValue("Pojedyńcze");
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
