package org.exampleHotel.ui.gui.rooms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.domain.room.dto.RoomDTO;
import org.exampleHotel.util.SystemUtils;

import java.util.ArrayList;
import java.util.List;

public class EditRoomScene {
    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();

    public EditRoomScene(Stage stg, TableView<RoomDTO> tableView, RoomDTO room) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();
        roomNumberField.setText(String.valueOf(room.getNumber()));

        roomNumberField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")){
                roomNumberField.setText(oldValue);
            }
        });

        gridPane.add(roomNumberLabel, 0, 0);
        gridPane.add(roomNumberField, 1, 0);


        Label bedTypeLabel = new Label("Typy łóżek:");

        Button addNewBedButton = new Button();

        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("add.png"));

        ImageView imageView = new ImageView(icon);

        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        addNewBedButton.setGraphic(imageView);

        addNewBedButton.setPadding(Insets.EMPTY);

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBedButton, 1, 1);

        int bedsCount = room.getBedsCount();
        String[] split = room.getBeds().split(",");

        VBox bedsVerticalLayout = new VBox();

        for (int i = 0; i < bedsCount; i++){
            ComboBox<String> comboBox = getComboBox();
            comboBox.setValue(split[i]);
            bedsVerticalLayout.getChildren().add(comboBox);
        }

        addNewBedButton.setOnAction(actionEvent -> {
            bedsVerticalLayout.getChildren().add(getComboBox());
        });
        Button editRoomButton = new Button("Edytuj pokój");
        editRoomButton.setOnAction(actionEvent -> {
            int newRoomNumber = Integer.parseInt(roomNumberField.getText());
            List<String> bedTypes = new ArrayList<>();

            this.comboBoxes.forEach(comboBoxes -> {
                bedTypes.add(comboBoxes.getValue());
            });
            this.roomService.edit(room.getId(), newRoomNumber, bedTypes);


            tableView.getItems().clear();
            List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
            tableView.getItems().addAll(allAsDTO);
            stg.close();

        });

        editRoomButton.setPadding(new Insets(4, 4, 4, 4));

        gridPane.add(bedsVerticalLayout, 1, 2);
        gridPane.add(editRoomButton, 1, 3);


        this.mainScene = new Scene(gridPane, 1280, 640);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("hotelReservation.css").toExternalForm());
    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll(SystemUtils.SINGLE_BED, SystemUtils.DOUBLE_BED, SystemUtils.KING_SIZE);
        bedTypeField.setValue("Pojedyńcze");
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}