package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.DriverManager;
import java.sql.ResultSet;


/**
 * Created by User on 08-May-17.
 */
public class DatabaseCommands {
    private Connection connnection;
    private java.sql.Connection connection;

    private ObservableList<ObservableList> datas;


    public ObservableList<ObservableList> fetchtableData() {
        datas = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM data";
            PreparedStatement preparedStatement = (PreparedStatement) this.connnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();


            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                //System.out.println("Column [" + i + "] ");
            }


            while (resultSet.next()) {

                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                //System.out.println("Row [1] added " + row);
                datas.add(row);

            }

            return datas;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        return null;

    }



    public boolean openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connnection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sdlab", "root", "");
            System.out.println("Connection established successfully with the database server.");
            return true;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return  false;
        }
    }


}
