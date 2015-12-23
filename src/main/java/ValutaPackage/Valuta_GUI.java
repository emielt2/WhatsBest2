package ValutaPackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

public class Valuta_GUI extends Application {
    static int counter=10;
    @Override
    public void start(Stage primaryStage) {
        final WhatsBest2 wb2 = new WhatsBest2();
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
                    wb2.testMain();
                    //scenetitle2.setText("Y");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        SQLiteJDBC_Select_RESULT_VALUES resultValues = new SQLiteJDBC_Select_RESULT_VALUES();
        buttonShow.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.BLUE);
                actiontarget.setText("Processing... " + counter ++);
                scenetitle2.setText("Y");
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

        Scene scene = new Scene(grid, 800, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
        scenetitle2.setText("X");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
