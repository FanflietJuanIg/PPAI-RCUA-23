package org.example.ppaiprueba.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.example.ppaiprueba.modelo.EventoSismico;
import org.example.ppaiprueba.modelo.Estado;
import org.example.ppaiprueba.modelo.MagnitudRichter;
import org.example.ppaiprueba.modelo.AlcanceSismo;
import org.example.ppaiprueba.modelo.OrigenDeGeneracion;
import org.example.ppaiprueba.modelo.ClasificacionSismo;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrarRevisionController implements Initializable {

    @FXML
    private ComboBox<EventoSismico> comboEventosPendientes;

    @FXML
    private Button btnSeleccionarEvento;

    private CUController controlador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                        "20º",           // latitudEpicentro
                        "-64º",         // longitudEpicentro
                        "15º",          // latitudHipocentro
                        "-65º",         // longitudHipocentro
                        new ArrayList<>() // seriesTemporales (lista vacía para simulación)
                ),
                new EventoSismico(
                        magnitud,
                        alcance,
                        origen,
                        clasificacion,
                        LocalDateTime.now().minusHours(6),
                        pendiente,
                        "21º",           // latitudEpicentro
                        "-63º",         // longitudEpicentro
                        "14º",          // latitudHipocentro
                        "-66º",         // longitudHipocentro
                        new ArrayList<>() // seriesTemporales (lista vacía para simulación)
                )
        );

        controlador = new CUController(eventos);
        comboEventosPendientes.getItems().addAll(controlador.obtenerEventosPendientes());
    }

    @FXML
    public void onSeleccionarEvento() {
        EventoSismico seleccionado = comboEventosPendientes.getValue();
        if (seleccionado != null) {
            System.out.println("Seleccionaste: " + seleccionado);
            // Simular rechazo
            controlador.RechazarEventoSismico(seleccionado);
        }
    }
}