/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abmc.controller;

import abmc.model.Persona;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FillDataExampleController implements Initializable {

    @FXML
    private Label lbl_name_value;
    @FXML
    private Label lbl_lastname_value;
    @FXML
    private Label lbl_address_value;
    @FXML
    private Label lbl_phone_value;
    @FXML
    private Label lbl_cell_value;
    @FXML
    private Label lbl_email_value;
    @FXML
    private Button btn_nuevo;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_exit;
    @FXML
    private TableView<Persona> tbl_personas;
    @FXML
    private TableColumn<Persona, String> col_nombre;
    @FXML
    private TableColumn<Persona, String> col_apellido;

    final ObservableList<Persona> data = FXCollections.observableArrayList(
            new Persona("Jacob", "Smith", "", "", "", "jacob.smith@example.com"),
            new Persona("Isabella", "Johnson", "", "", "", "isabella.johnson@example.com"),
            new Persona("Ethan", "Williams", "", "", "", "ethan.williams@example.com"),
            new Persona("Emma", "Jones", "", "", "", "emma.jones@example.com"),
            new Persona("Michael", "Brown", "", "", "", "michael.brown@example.com")
    );

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the person table with the two columns.
        col_nombre.setCellValueFactory(cellData -> cellData.getValue().nombre());
        col_apellido.setCellValueFactory(cellData -> cellData.getValue().apellido());

        // Listener para detectar el cambio de seleccion
        tbl_personas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> cargarPersonaSeleccionada(newValue));

        tbl_personas.setItems(data);

    }

    private void llenarTablaDePersonas() {

    }

    @FXML
    private void personaNuevo(ActionEvent event) {
        cargarPersonaSeleccionada(null);
    }

    @FXML
    private void editarPersona(ActionEvent event) {


    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void eliminarPersona() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("Esta a punto de eliminar el Auto...");
        alert.setContentText("desea continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Persona p = tbl_personas.getSelectionModel().getSelectedItem();

            try {

                int selectedIndex = tbl_personas.getSelectionModel().getSelectedIndex();
                tbl_personas.getItems().remove(selectedIndex);
            } catch (Exception ex) {
                // Muestra el mensaje de error
            }

        }
    }

    private void cargarPersonaSeleccionada(Persona p) {
        if (p != null) {
            lbl_name_value.setText(p.getNombre());
            lbl_lastname_value.setText(p.getApellido());
            lbl_address_value.setText(p.getDireccion());
            lbl_phone_value.setText(p.getTelefono());
            lbl_cell_value.setText(p.getCelular());
            lbl_email_value.setText(p.getEmail());
        } else {
            lbl_name_value.setText("");
            lbl_lastname_value.setText("");
            lbl_address_value.setText("");
            lbl_phone_value.setText("");
            lbl_cell_value.setText("");
            lbl_email_value.setText("");
        }
        btn_editar.setDisable(false);
        btn_borrar.setDisable(false);
    }

    @FXML
    private void handlerBtnExit(ActionEvent event) {
        Platform.exit();
    }

}
