module com.example.decoderenaultautoradio {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.lang;
    requires org.apache.logging.log4j;


    opens com.billystash.renaultautoradiodecode to javafx.fxml;
    exports com.billystash.renaultautoradiodecode.process;
    opens com.billystash.renaultautoradiodecode.process to javafx.fxml;
    exports com.billystash.renaultautoradiodecode.view;
    opens com.billystash.renaultautoradiodecode.view to javafx.fxml;
}