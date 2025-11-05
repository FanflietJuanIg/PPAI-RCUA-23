// RegistrarRevisionFController.java
package org.example.ppaiprueba.vista;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.ppaiprueba.control.GestorRevisionManual;
import org.example.ppaiprueba.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.example.ppaiprueba.modelo.Estado;

public class PantallaRevision {
    @FXML private Button opRegistrarRevision;
    @FXML private ListView<Map<String,Object>> listaEventos;
    @FXML private Button btnVisualizarMapa;
    @FXML private VBox formularioEdicion;
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
        // Cargar eventos simulados
       // Estado pendiente = new Estado("Pendiente Revision");
        // Crear las listas
        List<Estado> estados = new ArrayList<>();
        List<EventoSismico> eventosSismicos = new ArrayList<>();

        // Inicializar estados
        estados.add(new Estado(Estado.Tipo.AUTODETECTADO, Estado.Ambito.EVENTO_SISMICO));
        estados.add(new Estado(Estado.Tipo.PENDIENTE_REVISION, Estado.Ambito.EVENTO_SISMICO));
        estados.add(new Estado(Estado.Tipo.EN_REVISION, Estado.Ambito.EVENTO_SISMICO));
        estados.add(new Estado(Estado.Tipo.RECHAZADO, Estado.Ambito.EVENTO_SISMICO));
        estados.add(new Estado(Estado.Tipo.CONFIRMADO, Estado.Ambito.EVENTO_SISMICO));

        // Inicializar eventos sísmicos
        LocalDateTime ahora = LocalDateTime.now();

        // Crear tipos de datos para muestras
        TipoDeDato tipoVelocidad = new TipoDeDato("Velocidad");
        TipoDeDato tipoFrecuencia = new TipoDeDato("Frecuencia");
        TipoDeDato tipoLongitud = new TipoDeDato("Longitud");

        // Crear detalles de muestras
        List<DetalleMuestraSismica> detalles1 = Arrays.asList(
                new DetalleMuestraSismica(5.2, tipoVelocidad),
                new DetalleMuestraSismica(3.8, tipoFrecuencia),
                new DetalleMuestraSismica(4, tipoLongitud)
        );

        List<DetalleMuestraSismica> detalles2 = Arrays.asList(
                new DetalleMuestraSismica(4.1, tipoVelocidad),
                new DetalleMuestraSismica(2.9, tipoFrecuencia),
                new DetalleMuestraSismica(2, tipoLongitud)
        );

        // Crear muestras sísmicas
        MuestraSismica muestra1 = new MuestraSismica(
                ahora.minusMinutes(30),
                detalles1
        );

        MuestraSismica muestra2 = new MuestraSismica(
                ahora.minusMinutes(15),
                detalles2
        );

        // Crear estaciones y sismógrafos
        EstacionSismologica estacionA = new EstacionSismologica("EST-A");
        EstacionSismologica estacionB = new EstacionSismologica("EST-B");
        EstacionSismologica estacionC = new EstacionSismologica("EST-C");

        Sismografo sismografoA = new Sismografo(estacionA);
        Sismografo sismografoB = new Sismografo(estacionB);
        Sismografo sismografoC = new Sismografo(estacionC);

        // Crear series temporales
        List<SerieTemporal> seriesEvento1 = Arrays.asList(
                new SerieTemporal(
                        "Normal",
                        ahora.minusHours(1),
                        ahora,
                        100.0,
                        Arrays.asList(muestra1),
                        sismografoA
                ),
                new SerieTemporal(
                        "Alerta",
                        ahora.minusHours(1),
                        ahora,
                        100.0,
                        Arrays.asList(muestra2),
                        sismografoB
                )
        );

        List<SerieTemporal> seriesEvento2 = Arrays.asList(
                new SerieTemporal(
                        "Precaución",
                        ahora.minusMinutes(30),
                        ahora,
                        100.0,
                        Arrays.asList(muestra1),
                        sismografoB
                ),
                new SerieTemporal(
                        "Normal",
                        ahora.minusMinutes(30),
                        ahora,
                        100.0,
                        Arrays.asList(muestra2),
                        sismografoC
                )
        );

        // Crear eventos sísmicos
        eventosSismicos.add(new EventoSismico(
                new MagnitudRichter("Fuerte", 6.5),
                new AlcanceSismo("Regional", "Alto impacto"),
                new OrigenDeGeneracion("Tectónico", "Subducción de placas"),
                new ClasificacionSismo(20.0, 500.0, ClasificacionSismo.Nombre.SUPERFICIAL),
                ahora.minusHours(2),
                new Estado(Estado.Tipo.AUTODETECTADO),
                "-33.4569",
                "-70.6483",
                "-33.4569",
                "-70.6483",
                seriesEvento1
        ));

        eventosSismicos.add(new EventoSismico(
                new MagnitudRichter("Moderado", 4.8),
                new AlcanceSismo("Local", "Impacto moderado"),
                new OrigenDeGeneracion("Volcánico", "Actividad magmática"),
                new ClasificacionSismo(0, 20, ClasificacionSismo.Nombre.INTERMEDIO),
                ahora.minusHours(1),
                new Estado(Estado.Tipo.PENDIENTE_REVISION),
                "-36.8529",
                "-73.0432",
                "-36.8529",
                "-73.0432",
                seriesEvento2
        ));

        eventosSismicos.add(new EventoSismico(
                new MagnitudRichter("Leve", 3.2),
                new AlcanceSismo("Local", "Bajo impacto"),
                new OrigenDeGeneracion("Tectónico", "Falla local"),
                new ClasificacionSismo(0, 20, ClasificacionSismo.Nombre.PROFUNDO),
                ahora.minusMinutes(30),
                new Estado(Estado.Tipo.EN_REVISION),
                "-35.4270",
                "-71.6483",
                "-35.4270",
                "-71.6483",
                seriesEvento2
        ));
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
        cuController = new GestorRevisionManual(eventosSismicos, estados, sesion, this);


        // Ocultar secciones
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
            cuController.tomarEventoSeleccionado(eventoSeleccionado, 3);
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
        double valor = (Double) magnitud[1];

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
            cuController.tomarOpcionCambioEstado(eventoSeleccionado, 2);
            mostrarConfirmacionCambioEstado("Confirmado");
            limpiarVista();
        } else if (source == btnRechazar) {
            cuController.tomarOpcionCambioEstado(eventoSeleccionado,1);
            mostrarConfirmacionCambioEstado("Rechazado");
            limpiarVista();
            //cuController.opRegistrarRevision();
        } else if (source == btnDerivar) {
            cuController.tomarOpcionCambioEstado( eventoSeleccionado, 4);

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

