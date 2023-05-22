package com.billystash.renaultautoradiodecode.view;

import com.billystash.renaultautoradiodecode.process.RenaultAutoradioDecode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RADController implements Initializable {
    private static final Logger LOGGER = LogManager.getLogger(StartRAD.class);
    @FXML
    TextField TFPrecode = new TextField();
    @FXML
    TextField TFCode;

    RenaultAutoradioDecode rad = new RenaultAutoradioDecode();

    String validField = "-fx-border-radius: 5;-fx-border-color: #008000; -fx-border-width: 2px;";
    String badField = "-fx-border-radius: 5;-fx-border-color: #FF0000; -fx-border-width: 2px;";
    String initField = "-fx-border-radius: 5;-fx-border-color: #D3D3D3;-fx-border-width: 2px;";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TFPrecode.setOnKeyTyped(x -> TFPrecode.setStyle(rad.validPrecode(TFPrecode.getText().toUpperCase()) ? validField : badField));
    }

    /**
     * Function to determinate if code is valid.
     * @return True if valid code, else False.
     */
    private boolean validCode(String code) {
        return Pattern.compile("[0-9]{4}").matcher(code).find();
    }

    @FXML
    protected void onExit() {
        LOGGER.info("Exit Renault Autoradio Decode");
        System.exit(0);
    }

    /**
     * Function to launch decode processing.
     */
    @FXML
    protected void decode() {
        TFPrecode.setText(TFPrecode.getText().toUpperCase());
        String precode = TFPrecode.getText();
        String code = rad.decodeProcess(precode);
        TFCode.setText(code);
        LOGGER.info(String.format("PRECODE : %s -> CODE : %s", precode, code));
        TFCode.setStyle(validCode(TFCode.getText()) ? validField : badField);
    }

    /**
     * Function to reset field.
     */
    @FXML
    protected void resetField() {
        TFPrecode.clear();
        TFPrecode.setStyle(initField);

        TFCode.clear();
        TFCode.setStyle(initField);
    }

    /**
     * Function for open logs on window.
     */
    public void openLogs() {
        try {
            showDialog("Logs", Files.readString(Path.of(new File("RAD.log").getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function for open logs windows.
     */
    public static void showDialog(String title, String logs) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);

        TextArea textArea = new TextArea(logs);
        textArea.setEditable(false);
        textArea.setWrapText(false);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpanded(true);
        alert.getDialogPane().setExpandableContent(expContent);

        alert.show();
    }
}

