package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChooseOrc {
    public static void display(double x, double y) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enter coordinates of a new orc");
        window.setMinWidth(250.0);
        ArrayList<String> soldiers = Main.getNames();
        Label label = new Label("Choose the orc that you want to move:");
        ComboBox cBox = new ComboBox();
        int count = 1;

        for(Iterator var9 = soldiers.iterator(); var9.hasNext(); ++count) {
            String s = (String)var9.next();
            ObservableList var10000 = cBox.getItems();
            String var10001 = Integer.toString(count);
            var10000.add(var10001 + " " + s);
        }

        VBox layout = new VBox(11.0);
        layout.setAlignment(Pos.CENTER);
        Button okButton = new Button("OK");
        okButton.setOnAction((e) -> {
            if (cBox.getValue() != null) {
                String[] strChoice = cBox.getValue().toString().split(" ");
                ChangeParametrs.display(Integer.parseInt(strChoice[0])-1);
            }

            window.close();
        });
        layout.getChildren().addAll(new Node[]{label, cBox, okButton});
        Scene scene = new Scene(layout, 300.0, 300.0);
        window.setScene(scene);
        window.showAndWait();
    }
}
