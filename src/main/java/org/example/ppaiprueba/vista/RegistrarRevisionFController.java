// RegistrarRevisionFController.java
package org.example.ppaiprueba.vista;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.ppaiprueba.control.CUController;
import org.example.ppaiprueba.modelo.EventoSismico;
import org.example.ppaiprueba.modelo.Estado;
import org.example.ppaiprueba.modelo.MagnitudRichter;
import org.example.ppaiprueba.modelo.AlcanceSismo;
import org.example.ppaiprueba.modelo.OrigenDeGeneracion;
import org.example.ppaiprueba.modelo.ClasificacionSismo;
import java.time.LocalDateTime;
import java.util.Arrays;

import java.util.List;

public class RegistrarRevisionFController {
    @FXML private Button btnIniciarRevision;
    @FXML private ListView<EventoSismico> listaEventos;
    @FXML private Button btnVisualizarMapa;
    @FXML private VBox formularioEdicion;
    @FXML private HBox opcionesRevision;
    @FXML private TextField txtMagnitud;
    @FXML private TextField txtAlcance;
    @FXML private TextField txtOrigen;

    private EventoSismico eventoSeleccionado;
    private CUController cuController;

    @FXML
    public void initialize() {
        // Cargar eventos simulados
       // Estado pendiente = new Estado("Pendiente Revision");
        MagnitudRichter magnitud = new MagnitudRichter("Alta", 3.4);
        OrigenDeGeneracion origen = new OrigenDeGeneracion("Zona Norte", "Cordoba");
        ClasificacionSismo clasificacion= new ClasificacionSismo(20, 40, "Maria");
        AlcanceSismo alcance = new AlcanceSismo("Alto", "Mucho");

        List<EventoSismico> eventos = List.of(
                //FIXME: completar datos faltantes
                new EventoSismico(magnitud, alcance, origen, clasificacion,LocalDateTime.now().minusHours(3), pendiente, "20º"),
                new EventoSismico(magnitud, alcance, origen, clasificacion,LocalDateTime.now().minusHours(6), pendiente)
        );
        //FIXME: agregar datos faltantes
        cuController = new CUController(eventos);

        // Ocultar secciones
        btnVisualizarMapa.setVisible(false);
        formularioEdicion.setVisible(false);
        opcionesRevision.setVisible(false);
    }

    @FXML
    private void onIniciarRevision() {
        /*FIXME: si se quiere que el gestor retorne los datos, agregar un return. Si se quiere que el gestor llame a esta funcion,
           agregar el metodo en el gestor
         */
        List<EventoSismico> eventos = cuController.obtenerEventosPendientes();
        listaEventos.getItems().setAll(eventos);
    }

    @FXML
    private void onSeleccionarEvento() {
        eventoSeleccionado = listaEventos.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado != null) {
            // Lógica ficticia para bloqueo y obtención de datos
            eventoSeleccionado.setEstadoActual("En revisión"); //hay que pasarle un objeto estado creado previamente

            btnVisualizarMapa.setVisible(true);
            formularioEdicion.setVisible(true);
            opcionesRevision.setVisible(true);

            // Precargar datos
            txtMagnitud.setText(Arrays.toString(eventoSeleccionado.getMagnitud()));
            txtAlcance.setText(Arrays.toString(eventoSeleccionado.getAlcance()));
            txtOrigen.setText(Arrays.toString(eventoSeleccionado.getOrigen()));
        }
    }

    @FXML
    private void onVerMapa() {
        System.out.println("Mapa abierto (simulado). Estaciones: XYZ");
    }

    @FXML
    private void onGuardarEdicion() {
        eventoSeleccionado.setMagnitud( "descripcion",Double.parseDouble(txtMagnitud.getText())); //hay que agregar la descripcion
        eventoSeleccionado.setAlcance( "descripcion",txtAlcance.getText());
        eventoSeleccionado.setOrigen( "descripcion", txtOrigen.getText());
    }

    @FXML
    private void onConfirmar() {
        cuController.confirmarEvento(eventoSeleccionado, "Analista 1");
        limpiarVista();
    }

    @FXML
    private void onRechazar() {
        cuController.rechazarEvento(eventoSeleccionado, "Analista 1");
        limpiarVista();
    }

    @FXML
    private void onDerivar() {
        cuController.derivarEvento(eventoSeleccionado, "Analista 1");
        limpiarVista();
    }

    private void limpiarVista() {
        listaEventos.getItems().clear();
        btnVisualizarMapa.setVisible(false);
        formularioEdicion.setVisible(false);
        opcionesRevision.setVisible(false);
        txtMagnitud.clear();
        txtAlcance.clear();
        txtOrigen.clear();
    }
}
