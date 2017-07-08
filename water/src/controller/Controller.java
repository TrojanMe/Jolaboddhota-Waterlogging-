package controller;

import database.DatabaseCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.Main;

public class Controller {

    Main main;
    DatabaseCommands databaseCommands;


    @FXML
    Button connect;
    @FXML
    Button next;
    @FXML
    Label connectionstatus;
    @FXML
    Label lala;




    public void setMain(Main main) {
        this.main = main;
    }

    public void setDatabaseCommands(DatabaseCommands databaseCommands) {
        this.databaseCommands = databaseCommands;
    }


    public void connectToDatabase(ActionEvent actionEvent) {
        if(databaseCommands.openConnection()){ connectionstatus.setText("Connected"); next.setVisible(true); lala.setVisible(true);}
        else connectionstatus.setText("Not connected");

        connectionstatus.setVisible(true);


    }

    public void controlLayout() {
        connectionstatus.setVisible(false);
        next.setVisible(false);
        lala.setVisible(false);
    }

    public void gotomainScreen(ActionEvent actionEvent) {
        main.laterScreen();
    }
}
