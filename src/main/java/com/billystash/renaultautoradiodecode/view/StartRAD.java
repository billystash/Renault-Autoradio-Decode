package com.billystash.renaultautoradiodecode.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;
public class StartRAD extends Application {

    private static final Logger LOGGER =  LogManager.getLogger(StartRAD.class);

    /**
     * Function for open main view
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        LOGGER.info("Start Renault Autoradio Decode");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/billystash/renaultautoradiodecode/RADMainView.fxml")));
        Scene scene = new Scene(root, 450, 200);
        stage.setTitle("Renault Autoradio Decode");
        stage.getIcons().add(new Image(String.valueOf((getClass().getResource("/com/billystash/renaultautoradiodecode/images/renaultLogo.png")))));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e-> LOGGER.info("Exit Renault Autoradio Decode"));
    }

    /**
     * Main function for start application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}