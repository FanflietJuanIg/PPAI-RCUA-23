module org.example.ppaiprueba {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jakarta.persistence;
    requires jakarta.validation;
    requires org.hibernate.orm.core;

    exports org.example.ppaiprueba.vista;
    exports org.example.ppaiprueba.control;
    exports org.example.ppaiprueba.modelo;
    exports org.example.ppaiprueba.State;

    opens org.example.ppaiprueba.vista to javafx.fxml;
    opens org.example.ppaiprueba.control to javafx.fxml;
    opens org.example.ppaiprueba.modelo to org.hibernate.orm.core, javafx.fxml;
    opens org.example.ppaiprueba.State to org.hibernate.orm.core, javafx.fxml;
}