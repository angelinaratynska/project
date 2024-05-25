package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class changeCopy {
    public static void display(Soldier s) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enter some text to change an orc`s state");
        window.setMinWidth(250);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label textLabel = new Label();
        textLabel.setText("Text:");
        TextField text = new TextField();

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            String sText = text.getText();
            Label label = new Label(sText);
            s.setOriginality(label, s.getX(), s.getY());
            window.close();
        });


        layout.getChildren().addAll(textLabel, text, okButton);

        Scene scene=new Scene(layout,303,300);
        window.setScene(scene);
        window.showAndWait();

    }
}
