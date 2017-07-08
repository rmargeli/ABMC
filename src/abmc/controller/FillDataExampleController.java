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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FillDataExampleController implements Initializable {

    @FXML
    private TextField txt_name_value;
    @FXML
    private TextField txt_lastname_value;
    @FXML
    private TextField txt_address_value;
    @FXML
    private TextField txt_phone_value;
    @FXML
    private TextField txt_cell_value;
    @FXML
    private TextField txt_email_value;
    @FXML
    private Button btn_nuevo;
    @FXML
    private Button btn_grabar;
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
                txt_name_value.setDisable(true);
        txt_lastname_value.setDisable(true);
        txt_address_value.setDisable(true);
        txt_phone_value.setDisable(true);
        txt_cell_value.setDisable(true);
        txt_email_value.setDisable(true);
        btn_grabar.setDisable(true);

    }

    private void llenarTablaDePersonas() {

    }

    @FXML
    private void personaNuevo(ActionEvent event) {
        cargarPersonaSeleccionada(null);
        btn_grabar.setDisable(false);
    }
    
    @FXML
    private void grabarPersona(){
        if(txt_name_value != null && txt_lastname_value != null){
           Persona p = new Persona(txt_name_value.getText(), txt_lastname_value.getText(), txt_address_value.getText(), txt_phone_value.getText(), txt_cell_value.getText(), txt_email_value.getText());
           data.add(p);
           tbl_personas.setItems(data);  
           cargarPersonaSeleccionada(p);
           btn_editar.setDisable(false);
           btn_grabar.setDisable(true);
           btn_borrar.setDisable(false);
        }
        
        
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
        alert.setHeaderText("Esta a punto de eliminar la persona...");
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
            txt_name_value.setText(p.getNombre());
            txt_lastname_value.setText(p.getApellido());
            txt_address_value.setText(p.getDireccion());
            txt_phone_value.setText(p.getTelefono());
            txt_cell_value.setText(p.getCelular());
            txt_email_value.setText(p.getEmail());
            txt_name_value.setDisable(true);
            txt_lastname_value.setDisable(true);
            txt_address_value.setDisable(true);
            txt_phone_value.setDisable(true);
            txt_cell_value.setDisable(true);
            txt_email_value.setDisable(true);
        } else {
            txt_name_value.setText("");            
            txt_lastname_value.setText("");
            txt_address_value.setText("");
            txt_phone_value.setText("");
            txt_cell_value.setText("");
            txt_email_value.setText("");
            txt_name_value.setDisable(false);
            txt_lastname_value.setDisable(false);
            txt_address_value.setDisable(false);
            txt_phone_value.setDisable(false);
            txt_cell_value.setDisable(false);
            txt_email_value.setDisable(false);
            btn_editar.setDisable(true);
            btn_borrar.setDisable(true);
        }

        
    }    
  
    @FXML
    private void handlerBtnExit(ActionEvent event) {
        Platform.exit();
    }

}
