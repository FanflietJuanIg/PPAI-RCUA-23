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
import java.util.ArrayList;
import java.util.List;

public class RegistrarRevisionFController {
    @FXML private Button btnIniciarRevision;
    @FXML private ListView<EventoSismico> listaEventos; // Usamos ListView según el FXML
    @FXML private ComboBox<EventoSismico> comboEventosPendientes; // Mantendremos el ComboBox del FXML
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
        // Crear eventos simulados directamente
        Estado pendiente = Estado.PENDIENTE;
        MagnitudRichter magnitud = new MagnitudRichter("Alta", 3.4);
        OrigenDeGeneracion origen = new OrigenDeGeneracion("Zona Norte", "Cordoba");
        ClasificacionSismo clasificacion = new ClasificacionSismo(20, 40, "Maria");
        AlcanceSismo alcance = new AlcanceSismo("Alto", "Mucho");

        List<EventoSismico> eventos = List.of(
                new EventoSismico(
                        magnitud,
                        alcance,
                        origen,
                        clasificacion,
                        LocalDateTime.now().minusHours(3),
                        pendiente,
                        "20º", "-64º", "15º", "-65º", new ArrayList<>()
                ),
                new EventoSismico(
                        magnitud,
                        alcance,
                        origen,
                        clasificacion,
                        LocalDateTime.now().minusHours(6),
                        pendiente,
                        "21º", "-63º", "14º", "-66º", new ArrayList<>()
                )
        );

        cuController = new CUController(eventos);

        // Configurar ListView
        listaEventos.getItems().addAll(cuController.obtenerEventosPendientes());
        listaEventos.setCellFactory(param -> new ListCell<EventoSismico>() {
            @Override
            protected void updateItem(EventoSismico item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getFechaHoraOcurrencia() + " - " + item.getMagnitud());
                }
            }
        });

        // Configurar ComboBox (opcional, si decides usarlo)
        comboEventosPendientes.getItems().addAll(cuController.obtenerEventosPendientes());
        comboEventosPendientes.setCellFactory(param -> new ListCell<EventoSismico>() {
            @Override
            protected void updateItem(EventoSismico item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getFechaHoraOcurrencia() + " - " + item.getMagnitud());
                }
            }
        });
        comboEventosPendientes.setButtonCell(new ListCell<EventoSismico>() {
            @Override
            protected void updateItem(EventoSismico item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Seleccione un evento");
                } else {
                    setText(item.getFechaHoraOcurrencia() + " - " + item.getMagnitud());
                }
            }
        });

        // Ocultar secciones
        btnVisualizarMapa.setVisible(false);
        formularioEdicion.setVisible(false);
        opcionesRevision.setVisible(false);
    }

    @FXML
    private void onIniciarRevision() {
        listaEventos.getItems().setAll(cuController.obtenerEventosPendientes());
    }

    @FXML
    private void onSeleccionarEvento() {
        eventoSeleccionado = listaEventos.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado != null) {
            eventoSeleccionado.setEstado("Bloqueado en revisión");
            btnVisualizarMapa.setVisible(true);
            formularioEdicion.setVisible(true);
            opcionesRevision.setVisible(true);

            txtMagnitud.setText(String.valueOf(eventoSeleccionado.getMagnitud()));
            txtAlcance.setText(eventoSeleccionado.getAlcance());
            txtOrigen.setText(eventoSeleccionado.getOrigen());
        }
    }

    @FXML
    private void onVerMapa() {
        System.out.println("Mapa abierto (simulado). Estaciones: XYZ");
    }

    @FXML
    private void onGuardarEdicion() {
        eventoSeleccionado.setMagnitud(Double.parseDouble(txtMagnitud.getText()));
        eventoSeleccionado.setAlcance(txtAlcance.getText());
        eventoSeleccionado.setOrigen(txtOrigen.getText());
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