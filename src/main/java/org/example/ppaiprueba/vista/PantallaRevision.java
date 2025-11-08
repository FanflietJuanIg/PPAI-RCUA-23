// RegistrarRevisionFController.java
package org.example.ppaiprueba.vista;

import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.ppaiprueba.State.AutoDetectado;
import org.example.ppaiprueba.State.BloqueadoParaRevision;
import org.example.ppaiprueba.control.GestorRevisionManual;
import org.example.ppaiprueba.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.example.ppaiprueba.State.Estado;
import org.example.ppaiprueba.persistance.Initialice;
import org.example.ppaiprueba.persistance.Repository;

public class PantallaRevision {
    @FXML private Button opRegistrarRevision;
    @FXML private ListView<Map<String,Object>> listaEventos;
    @FXML private Button btnVisualizarMapa;
    @FXML private TitledPane formularioEdicion;
    @FXML private HBox opcionesRevision;
    @FXML private TextField txtMagnitudNumero;
    @FXML private Label lblMagnitudDescripcion;
    @FXML private TextField txtAlcance;
    @FXML private TextField txtOrigen;
    @FXML private Label lblAlcance;
    @FXML private Label lblOrigen;
    @FXML private Label lblClasificacion;
    @FXML private ImageView imgSismograma;
    @FXML private Button btnConfirmar;
    @FXML private Button btnRechazar;
    @FXML private Button btnDerivar;



    private EventoSismico eventoSeleccionado;
    private GestorRevisionManual cuController;

    @FXML
    public void initialize() {
        Initialice.initialice();

        Empleado empleado = new Empleado(
                "Juan",
                "Pérez",
                "juan.perez@empresa.com",
                "555-1234",
                "Analista Sísmico"
        );

        Usuario usuario = new Usuario("jperez", "contraseña123", empleado);
        Sesion sesion = new Sesion(usuario);


        // Inicializar el controlador con los datos
        cuController = new GestorRevisionManual(sesion, this);


        btnVisualizarMapa.setVisible(false);
        formularioEdicion.setVisible(false);
        opcionesRevision.setVisible(false);
    }

    private void habilitarPantalla(){
        cuController.opRegistrarRevision();
    }

    //Paso 5
    @FXML
    private void opRegistrarRevision() {
        System.out.println("Botón Iniciar Revisión presionado");
        //Paso 6
        habilitarPantalla();

    }

    public void mostrarOpcionCambioEstado(){
        opcionesRevision.setVisible(true);
    }

    @FXML
    private void tomarEventoSeleccionado() {
        eventoSeleccionado = (EventoSismico) listaEventos.getSelectionModel().getSelectedItem().get("Evento");
        if (eventoSeleccionado != null) {
            //aca cambia el estado a enRevision
            //Paso 8
            cuController.tomarEventoSeleccionado(eventoSeleccionado);
        }
    }

    @FXML
    public void mostrarDatosSismicos(Map<String, Object> eventoMapeado){
        lblOrigen.setText(eventoMapeado.get("Origen").toString());
        lblAlcance.setText(eventoMapeado.get("Alcance").toString());
        lblClasificacion.setText(eventoMapeado.get("Clasificacion").toString());
    }


    //Paso 6
    @FXML
    public void mostrarEventosSismicos(List<Map<String, Object>> eventos) {
        // Alternativa A1
        if (eventos == null) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Información");
            alerta.setHeaderText(null);
            alerta.setContentText("No hay eventos sísmicos pendientes de revisión");
            alerta.showAndWait();
        }


        listaEventos.setCellFactory(lv -> new ListCell<Map<String, Object>>() {
            @Override
            protected void updateItem(Map<String, Object> item, boolean empty) { //sube a la list view cada celda
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Aquí seleccionas solo los atributos que quieres mostrar y se les da el formato con el que mostrarlos
                    Object[] magnitud = (Object[])item.get("Magnitud");
                    String displayText = String.format("Fecha: %s /Latitud Hipocentro: %s / " +
                                    "Longitud Hipocentro: %s / Latitud Epicentro: %s / Longitud Epicentro: %s " +
                                    " / Magnitud: %s %s",
                        item.get("fecha y hora ocurrencia"),
                        item.get("Latitud Hipocentro"),
                        item.get("Longitud Hipocentro"),
                        item.get("Latitud Epicentro"),
                        item.get("Longitud Epicentro"),
                        magnitud[0],
                        magnitud[1]
                        // No incluimos el atributo que queremos ocultar
                    );
                    setText(displayText);
                }
            }
        });
        
        listaEventos.getItems().clear();
        listaEventos.getItems().addAll(eventos);
    }

    @FXML
    public void habilitarOpcionVerMapa() {
        btnVisualizarMapa.setVisible(true);
    }

    @FXML
    public void mostrarOpcionModificarDatos(EventoSismico eventoSeleccionado) {

        Object[] magnitud = eventoSeleccionado.getMagnitud();
        String descripcion = (String) magnitud[0];
        double valor = ((BigDecimal) magnitud[1]).doubleValue();

        lblMagnitudDescripcion.setText(descripcion);
        txtMagnitudNumero.setText(String.valueOf(valor));
        txtAlcance.setText(eventoSeleccionado.getAlcance());
        txtOrigen.setText(eventoSeleccionado.getOrigen());

        formularioEdicion.setVisible(true);
    }

    @FXML
    private void onVerMapa() {
        System.out.println("Mapa abierto (simulado). Estaciones: XYZ");
    }

    @FXML
    private void onGuardarEdicion() {
        eventoSeleccionado.setMagnitud( "descripcion",Double.parseDouble(txtMagnitudNumero.getText())); //hay que agregar la descripcion
        eventoSeleccionado.setAlcance( "descripcion",txtAlcance.getText());
        eventoSeleccionado.setOrigen( "descripcion", txtOrigen.getText());
    }
/*
    @FXML
    private void onConfirmar() {
        cuController.validarDatos(eventoSeleccionado, 2);
        limpiarVista();
        mostrarConfirmacionCambioEstado("Confirmado");
    }

    //paso 15
    @FXML
    private void onRechazar() {
        //paso 16
        cuController.validarDatos(eventoSeleccionado, 1);
        limpiarVista();
        mostrarConfirmacionCambioEstado("Rechazado");
    }

    @FXML
    private void onDerivar() {
//        cuController.derivarEvento(eventoSeleccionado, "Analista 1");
//        limpiarVista();
    }
*/
    @FXML
    private void tomarOpcionCambioEstado(ActionEvent event) {
        Button source = (Button) event.getSource();

        if (source == btnConfirmar) {
            cuController.tomarOpcionCambioEstadoConfirmado(eventoSeleccionado);
            mostrarConfirmacionCambioEstado("Confirmado");
            limpiarVista();

        } else if (source == btnRechazar) {
            cuController.tomarOpcionCambioEstadoRechazado(eventoSeleccionado);
            mostrarConfirmacionCambioEstado("Rechazado");
            limpiarVista();

        } else if (source == btnDerivar) {
            //cuController.tomarOpcionCambioEstado( eventoSeleccionado);

        }
    }

    private void limpiarVista() {
        listaEventos.getItems().clear();
        btnVisualizarMapa.setVisible(false);
        formularioEdicion.setVisible(false);
        opcionesRevision.setVisible(false);
        txtMagnitudNumero.clear();
        txtAlcance.clear();
        txtOrigen.clear();
        imgSismograma.setVisible(false);
    }

    public void mostrarSismograma() {

        String url = "https://c8.alamy.com/comp/2BG7AJH/frequency-seismograph-waves-seismogram-earthquake-graphs-seismic-wave-vector-set-illustration-of-vibration-seismometer-diagram-waveform-record-2BG7AJH.jpg";
        Image imagen = new Image(url);
        imgSismograma.setImage(imagen);
        imgSismograma.setVisible(true);
    }

    public void mostrarConfirmacionCambioEstado(String estado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("El evento cambió exitosamente al estado: " + estado);
            alert.showAndWait();
        }
    }

