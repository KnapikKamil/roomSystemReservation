package org.exampleHotel.ui.gui.guests;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.guest.dto.GuestDTO;
import org.exampleHotel.util.Properties;


public class AddNewGuestScene {
    private Scene mainScene;
    private GuestService guestService = ObjectPool.getGuestService();

    public AddNewGuestScene(Stage modelStage, TableView<GuestDTO> guestsTableView) {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(8);

        Label firstNameLabel = new Label("Imię:");
        Label lastNameLabel = new Label("Nazwisko:");
        Label ageLabel = new Label("Wiek:");
        Label genderLabel = new Label("Płeć:");

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField ageField = new TextField();
        ComboBox<String> genderField = new ComboBox<>();

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
        genderField.getItems().addAll(Properties.FEMALE, Properties.MALE, Properties.LGBT);
          genderField.setValue(Properties.LGBT);

        Button createNewGuestButton = new Button("Utwórz gościa");

        createNewGuestButton.setOnAction(actionEvent -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getValue();
            int genderSelection;

            if (gender.equals(Properties.FEMALE)) {
                genderSelection = 1;
            } else if (gender.equals(Properties.MALE)) {
                genderSelection = 2;
            } else if (gender.equals(Properties.LGBT)) {
                genderSelection = 3;
            } else {
                genderSelection = -1;
            }

            this.guestService.createNewGuest(firstName, lastName, age, genderSelection);

            guestsTableView.getItems().clear();
            /* Błąd odczytu, do poprawki
            Mężczyzna w GUI odczytuje jako nie binarny
            */
            guestsTableView.getItems().addAll(this.guestService.getAllAsDTO());
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
        gridPane.add(createNewGuestButton, 1, 4);

        this.mainScene = new Scene(gridPane, 1280, 640);
        this.mainScene.getStylesheets().add(getClass().getClassLoader().getResource("hotelReservation.css").toExternalForm());

    }

    public Scene getMainScene() {
        return this.mainScene;
    }

}