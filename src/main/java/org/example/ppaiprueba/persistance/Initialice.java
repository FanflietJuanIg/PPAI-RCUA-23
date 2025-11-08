package org.example.ppaiprueba.persistance;

import org.example.ppaiprueba.State.AutoDetectado;
import org.example.ppaiprueba.State.BloqueadoParaRevision;
import org.example.ppaiprueba.control.GestorRevisionManual;
import org.example.ppaiprueba.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialice {
    public static void initialice() {
        // Cargar eventos simulados
        // Estado pendiente = new Estado("Pendiente Revision");
        // Crear las listas

        List<EventoSismico> eventosSismicos = new ArrayList<>();

        // Inicializar eventos sísmicos
        LocalDateTime ahora = LocalDateTime.now();

        // Crear tipos de datos para muestras
        TipoDeDato tipoVelocidad = new TipoDeDato("Velocidad");
        TipoDeDato tipoFrecuencia = new TipoDeDato("Frecuencia");
        TipoDeDato tipoLongitud = new TipoDeDato("Longitud");
/*
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

 */

        // Crear muestras sísmicas
        MuestraSismica muestra1 = new MuestraSismica();
        muestra1.setFechaHoraMuestra(ahora.minusMinutes(30));
        muestra1.setDetalleMuestraSismica(Arrays.asList(
                new DetalleMuestraSismica(5.2, tipoVelocidad),
                new DetalleMuestraSismica(3.8, tipoFrecuencia),
                new DetalleMuestraSismica(4, tipoLongitud)
        ));
        for (DetalleMuestraSismica de : muestra1.getDetalleMuestraSismica()) {
            de.setMuestraSismica(muestra1);
        }

        MuestraSismica muestra2 = new MuestraSismica();
        muestra2.setFechaHoraMuestra(ahora.minusMinutes(15));
        muestra2.setDetalleMuestraSismica(Arrays.asList(
                new DetalleMuestraSismica(4.1, tipoVelocidad),
                new DetalleMuestraSismica(2.9, tipoFrecuencia),
                new DetalleMuestraSismica(2, tipoLongitud)
        ));
        for (DetalleMuestraSismica de : muestra2.getDetalleMuestraSismica()) {
            de.setMuestraSismica(muestra2);
        }

        // Crear estaciones y sismógrafos
        EstacionSismologica estacionA = new EstacionSismologica("EST-A");
        EstacionSismologica estacionB = new EstacionSismologica("EST-B");
        EstacionSismologica estacionC = new EstacionSismologica("EST-C");

        Sismografo sismografoA = new Sismografo(estacionA);
        Sismografo sismografoB = new Sismografo(estacionB);
        Sismografo sismografoC = new Sismografo(estacionC);
/*

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

 */

        // Crear eventos sísmicos
        EventoSismico evento1 = new  EventoSismico(
                new MagnitudRichter("Fuerte", 6.5),
                new AlcanceSismo("Regional", "Alto impacto"),
                new OrigenDeGeneracion("Tectónico", "Subducción de placas"),
                new ClasificacionSismo(20.0, 500.0, "SUPERFICIAL"),
                ahora.minusHours(2),
                new AutoDetectado(),
                "-33.4569",
                "-70.6483",
                "-33.4569",
                "-70.6483",
                Arrays.asList(
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
                )
        );
        for (SerieTemporal st : evento1.getSeriesTemporales()) {
            st.setEventoSismico(evento1);
        }

        EventoSismico evento2 = new EventoSismico(
                new MagnitudRichter("Moderado", 4.8),
                new AlcanceSismo("Local", "Impacto moderado"),
                new OrigenDeGeneracion("Volcánico", "Actividad magmática"),
                new ClasificacionSismo(0, 20, "INTERMEDIO"),
                ahora.minusHours(1),
                new BloqueadoParaRevision(),
                "-36.8529",
                "-73.0432",
                "-36.8529",
                "-73.0432",
                Arrays.asList(
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
                )
        );

        for (SerieTemporal st : evento2.getSeriesTemporales()) {
            st.setEventoSismico(evento2);
        }

        EventoSismico evento3 = new EventoSismico(
                new MagnitudRichter("Leve", 3.2),
                new AlcanceSismo("Local", "Bajo impacto"),
                new OrigenDeGeneracion("Tectónico", "Falla local"),
                new ClasificacionSismo(0, 20, "PROFUNDO"),
                ahora.minusMinutes(30),
                new AutoDetectado(),
                "-35.4270",
                "-71.6483",
                "-35.4270",
                "-71.6483",
                Arrays.asList(
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
                )
        );
        for (SerieTemporal st : evento3.getSeriesTemporales()) {
            st.setEventoSismico(evento3);
        }

        eventosSismicos.add(evento1);
        eventosSismicos.add(evento2);
        eventosSismicos.add(evento3);


        guardarEnBD(eventosSismicos);
    }

    public static void guardarEnBD(List<EventoSismico> eventos) {
        Repository<EventoSismico> repository = new Repository<>(EventoSismico.class);

        for (EventoSismico ev : eventos) {
            repository.guardar(ev);
        }
    }
}
