package view;

import controller.Controller;
import controller.mainScreenCntroller;
import database.DatabaseCommands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application  {

    Stage pm;
    DatabaseCommands dc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        dc = new DatabaseCommands();
        pm = primaryStage;
        general();
    }

    public void general() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.controlLayout();
        controller.setMain(this);
        controller.setDatabaseCommands(dc);
        pm.setTitle("Database Connection");
        pm.setScene(new Scene(root, 661,365));
        pm.show();
    }

    public  void laterScreen() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainScreem.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainScreenCntroller maincontroller = loader.getController();
        maincontroller.setDatabaseCommands(dc);
        maincontroller.primarySetup();
        pm.setTitle("Main Screen");
        pm.setScene(new Scene(root, 1231,806));
        pm.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
