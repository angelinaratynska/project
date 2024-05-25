package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetParametrs {
    private static int choice  = 0;
    public static void display(double x, double y) {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create orc");
        window.setMinWidth(250);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label chooseLabel=new Label();
        chooseLabel.setText("Choose a rank of an orc:");
        RadioButton soldier = new RadioButton("Soldier");
        RadioButton sergeant = new RadioButton("Sergeant");
        RadioButton officer = new RadioButton("Officer");

        Label coordLabel=new Label();
        coordLabel.setText("Enter coordinates of an orc:");

        Label xLabel=new Label();
        xLabel.setText("X:");
        TextField xText = new TextField();
        xText.setText(Double.toString(x));

        Label yLabel=new Label();
        yLabel.setText("Y:");
        TextField yText = new TextField();
        yText.setText(Double.toString(y));

        Button okButton=new Button("OK");

        layout.getChildren().addAll(chooseLabel, soldier, sergeant, officer, coordLabel, xLabel, xText, yLabel, yText, okButton);

        okButton.setOnAction(e->{
            String sX = xText.getText();
            String sY = yText.getText();
            if (soldier.isSelected()) {
                window.close();
                setChoice(1);
            } else if (sergeant.isSelected()) {
                window.close();
                setChoice(2);
            } else if (officer.isSelected()) {
                window.close();
                setChoice(3);
            }

            Main.createNewSoldier(sX, sY, choice);
            window.close(); });


        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void setChoice(int tmp){
        choice = tmp;
    }
}
