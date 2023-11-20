package org.exampleHotel.ui.gui.guests;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.guest.dto.GuestDTO;
import org.exampleHotel.util.SystemUtils;

public class EditGuestScene {

    private Scene mainScene;
    private GuestService guestService = ObjectPool.getGuestService();
    public EditGuestScene(Stage modelStage, TableView<GuestDTO> tableView, GuestDTO guest) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);

        Label firstNameLabel = new Label("Imię:");
        Label lastNameLabel = new Label("Nazwisko:");
        Label ageLabel = new Label("Wiek:");
        Label genderLabel = new Label("Płeć:");
        Button editGuestButton = new Button("Edytuj gościa");

        TextField firstNameField = new TextField();
        firstNameField.setText(guest.getFirstName());

        TextField lastNameField = new TextField();
        lastNameField.setText(guest.getLastName());

        TextField ageField = new TextField();
        ageField.setText(String.valueOf(guest.getAge()));

        ComboBox<String> genderField = new ComboBox<>();
        genderField.setValue(guest.getGender());

        firstNameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                firstNameField.setText(oldValue);
            }
        });
        lastNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                lastNameField.setText(oldValue);
            }
        }));
        ageField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ageField.setText(oldValue);
            }
        });
        genderField.getItems().addAll(SystemUtils.FEMALE, SystemUtils.MALE, SystemUtils.LGBT);
        genderField.setValue(SystemUtils.LGBT);



        editGuestButton.setOnAction(actionEvent -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getValue();
            int genderSelection;

            if (gender.equals(SystemUtils.FEMALE)) {
                genderSelection = 1;
            } else if (gender.equals(SystemUtils.MALE)) {
                genderSelection = 2;
            } else if (gender.equals(SystemUtils.LGBT)) {
                genderSelection = 3;
            } else {
                genderSelection = -1;
            }

            this.guestService.edit(guest.getId() ,firstName, lastName, age, genderSelection);

            tableView.getItems().clear();
            /* Błąd odczytu, do poprawki
            Mężczyzna w GUI odczytuje jako nie binarny
            */
            tableView.getItems().addAll(this.guestService.getAllAsDTO());
            modelStage.close();
        });

        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(ageLabel, 0, 2);
        gridPane.add(ageField, 1, 2);
        gridPane.add(genderLabel, 0, 3);
        gridPane.add(genderField, 1, 3);
        gridPane.add(editGuestButton, 1, 4);

        this.mainScene = new Scene(gridPane, 1280, 640);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("hotelReservation.css").toExternalForm());
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
