package org.example.ppaiprueba.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ppaiprueba.modelo.SerieTemporal;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/org/example/ppaiprueba/vista/RegistrarRevisionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Revisión Manual de Eventos Sísmicos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SerieTemporal serieTemporal;
        launch();

    }
}