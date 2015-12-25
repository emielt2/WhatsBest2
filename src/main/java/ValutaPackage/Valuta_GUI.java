package ValutaPackage;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Valuta_GUI extends Application {
    static int counter=10;
    @Override
    public void start(Stage primaryStage) {
        final WhatsBestClass wbc = new WhatsBestClass();
        primaryStage.setTitle("ET Valuta Comparer");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Button buttonGo = new Button("Go!");
        Button buttonShow = new Button("Show");
        HBox hbButtonGo = new HBox(10);
        HBox hbButtonShow = new HBox(30);
        hbButtonGo.setAlignment(Pos.TOP_LEFT);
        hbButtonShow.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtonGo.getChildren().add(buttonGo);
        hbButtonShow.getChildren().add(buttonShow);
        grid.add(hbButtonGo, 1, 4);
        grid.add(hbButtonShow, 4, 6);

        //--new gridpane
        GridPane grid2 = new GridPane();
        Text UID = new Text("UID");
        grid2.getChildren().add(UID);

        //--


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        //TODO knop voor current results maken
        //-------
        //-------
        Text scenetitle = new Text("Welcome Citizen47281");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        final Text scenetitle2 = new Text("Last results:");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle2, 5, 5, 2, 1);

        buttonGo.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.BLUE);
                actiontarget.setText("Processing... " + counter ++);
                try {
                    wbc.testMain();
                    //scenetitle2.setText("Y");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        final SQLiteJDBC_Select_RESULT_VALUES resultValues = new SQLiteJDBC_Select_RESULT_VALUES();

        buttonShow.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.BLUE);
                actiontarget.setText("Processing... " + counter ++);
                ValutaResultObject vro1;
                vro1 = resultValues.getRESULT_VALUES();
                scenetitle2.setText(vro1.toString());
                //scenetitle2.setText(results[1]);

            }
        });

        Label userName = new Label("FROM valuta:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("TO valuta:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        //Scene scene = new Scene(grid, 800, 275);
        //Scene scene = new Scene(grid, 800, 275);
        //primaryStage.setScene(scene);

        //--pane table
        StackPane root = new StackPane();
        String[][] staffArray =
                {
                        {"nice to ", "have", "titles"},
                        {"a", "b", "c"},
                        {"d", "e", "f"}
                };
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(staffArray));
        data.remove(0);//remove titles from data
        TableView<String[]> table = new TableView<>();
        for (int i = 0; i < staffArray[0].length; i++) {
            TableColumn tc = new TableColumn(staffArray[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);



        root.getChildren().addAll(table,grid2);
        //root.getChildren().add(table);
        root.setAlignment(Pos.BOTTOM_RIGHT);

       // root.setLayoutX(112.5);


       // root.getChildren().add(grid);


        primaryStage.setScene(new Scene(root, 500, 550));
       // primaryStage.setScene(new Scene(grid, 500, 550));
        primaryStage.alwaysOnTopProperty();

        //primaryStage.show();
        //----


        primaryStage.show();
        scenetitle2.setText("X");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
