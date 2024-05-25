package com.example.demo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ChangeParametrs {
    public static void display(int soldierIndex ) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enter coordinates of a new orc");
        window.setMinWidth(250);

        ArrayList<String> paramsTochange = Main.getParamsToChange( soldierIndex );

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label xLabel=new Label();
        xLabel.setText("X:");
        TextField xText = new TextField();
        xText.setText(paramsTochange.get(0));

        Label yLabel=new Label();
        yLabel.setText("Y:");
        TextField yText = new TextField();
        yText.setText(paramsTochange.get(1));

        Button okButton=new Button("OK");
        okButton.setOnAction(e->{

            String sX = xText.getText();
            String sY = yText.getText();

            Main.changeSoldier(soldierIndex, sX, sY);

            window.close(); });


        layout.getChildren().addAll(xLabel, xText, yLabel, yText, okButton);

        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();

    }
}