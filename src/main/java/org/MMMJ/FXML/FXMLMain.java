package org.MMMJ.FXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FXMLMain extends Application {
    /** An instance of the {@link FXMLController} class**/

    /**
     * Initialized the user interface for the game
     * @param primaryStage the primary stage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/2048.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        FXMLController controller = loader.getController();
        controller.setScene(scene);
        controller.initEventHandlers();

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
