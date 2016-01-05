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
import javafx.geometry.Orientation;
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
        System.out.println("STARTED");
        final WhatsBestClass wbc = new WhatsBestClass();
        primaryStage.setTitle("ET Valuta Comparer");
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.TOP_LEFT);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));

        Button buttonGo = new Button("Go!");
        Button buttonShow = new Button("ShowLast");
        HBox hbButtonGo = new HBox(10);
        HBox hbButtonShow = new HBox(30);
        hbButtonGo.setAlignment(Pos.TOP_LEFT);
        hbButtonShow.setAlignment(Pos.TOP_LEFT);
        hbButtonGo.getChildren().add(buttonGo);
        hbButtonShow.getChildren().add(buttonShow);
        grid1.add(hbButtonGo, 0, 5);
        grid1.add(hbButtonShow, 0, 6);

        Separator sepHor1 = new Separator();
        Separator sepHor2 = new Separator();
        Separator sepVer = new Separator();//Vertical separator

        sepVer.setOrientation(Orientation.VERTICAL);//Vertical separator
        //--new gridpane


        //--


        final Text actiontarget = new Text();
        grid1.add(actiontarget, 1, 6);

        //TODO knop voor current results maken
        //-------
        //-------
        Text scenetitle = new Text("Welcome Citizen47281");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle, 0, 1/*, 1, 1*/);

        final Text scenetitle2 = new Text("Last results:");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid1.add(scenetitle2, 0, 7/*, 10, 10*/);

        final TextField fromInputField = new TextField();
        final TextField toInputField = new TextField();

        buttonGo.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.BLUE);
                actiontarget.setText("Processing... " + counter ++);
                try {
                    ValutaResultObject vro1;// = new ValutaResultObject[1];
                    //TEMP vro1=wbc.main("1"); //TODO
                    //String input1 = userTextField.
                    //vro1=wbc.main("1","USD", "EUR");
                    vro1=wbc.main("1",fromInputField.getText(), toInputField.getText());
                    //vro1[0].fromVal="EEE";
                    System.out.println("---Check d1: " + vro1.toString());
                    System.out.println();
                    //SQLiteJDBC_Insert_RESULT_VALUES insert1 = new ;
                    for(int i=0;i<1;i++){
                        new SQLiteJDBC_Insert_RESULT_VALUES().insertResult(vro1);
                    }
                    //wbc.testMain();
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
                ValutaResultObject[] vro1 = new ValutaResultObject[1];
                vro1 = resultValues.getRESULT_VALUES();
                scenetitle2.setText(vro1[SQLiteJDBC_Insert_RESULT_VALUES.getLastID()].toString());
                //SQLiteJDBC_Insert_RESULT_VALUES.insertResult(vro1);
                //scenetitle2.setText(results[1]);

            }
        });



        //--pane table
        //StackPane root = new StackPane();
        String[][] staffArray =
                {
                        {"Uid", "FROM", "TO","Google","Soap"},
                        {"a", "b", "c","x","y"},
                        {"d", "e", "f","x","y"}
                };
       // ValutaResultObject[] vroAllResults = new ValutaResultObject[10];

        System.out.println("Check E1");
        ValutaResultObject vroAllResults[] = new SQLiteJDBC_Select_RESULT_VALUES().getRESULT_VALUES();
        System.out.println("Check E2");
        String[][] allResultsArray =
                {
                        {Integer.toString(vroAllResults[0].UID) ,vroAllResults[0].fromVal,vroAllResults[0].toVal,
                                Double.toString(vroAllResults[0].googleResult),Double.toString(vroAllResults[0].soapResult)

                        }

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



        //root.getChildren().addAll(grid2,sepHor2,table);
        //root.getChildren().add(table);
        //root.setAlignment(Pos.BOTTOM_RIGHT);

       // root.setLayoutX(112.5);


       // root.getChildren().add(grid);
        GridPane grid2 = new GridPane();
        //Text UID = new Text("UID");
        //grid2.getChildren().add(UID);

        Label userName = new Label("FROM valuta:");
        grid1.add(userName, 0, 3);


        grid1.add(fromInputField, 1, 3);
        //String input1=fromInputField.getText();
        //String input2=fromInputField.getText();

        Label pw = new Label("TO valuta:");
        grid1.add(pw, 0, 4);


        grid1.add(toInputField, 1, 4);


        //grid2.add(table,0,0);
        //grid1.add(grid2,2,0);
        grid1.add(table,2,0,1,70);
        //grid2.getChildren().addAll(table);

        //primaryStage.setScene(new Scene(root, 500, 550));
        primaryStage.setScene(new Scene(grid1, 900, 900));
        primaryStage.getScene().fillProperty();
        primaryStage.alwaysOnTopProperty();
        //primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
