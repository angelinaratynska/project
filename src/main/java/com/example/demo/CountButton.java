package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountButton {
    private ImageView viewCount;
    private Image imgCount;
    private double countX, countY;
    private Circle circleCount;

    public CountButton() {

        imgCount = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\count.png", 30, 30, false, false);

        countX = 40;
        countY = 120;

        viewCount = new ImageView(imgCount);
        viewCount.setX(countX);
        viewCount.setY(countY);

        circleCount = new Circle(countX + 15 , countY + 15, 20);
        circleCount.setFill(Color.WHITE);
        circleCount.setStroke(Color.BLACK);
        circleCount.setStrokeWidth(3);

        Main.group.getChildren().addAll(circleCount, viewCount);

    }

    public static long countActive() {
        LinkedList<Soldier> activeOrcs = new LinkedList<>(); //parameterized collection
        for (Soldier s : Stream.concat(Stream.concat(Main.arrSoldiers.stream(), Main.arrSergeants.stream()), Main.arrOfficers.stream())
                .collect(Collectors.toList())) if (s.getActive()) activeOrcs.add(s);
        Stream<Soldier> stream = activeOrcs.stream();
        return stream.count();
    }
    public static long countAlive() {
        LinkedList<Soldier> aliveOrcs = new LinkedList<>(); //parameterized collection
        for (Soldier s : Stream.concat(Stream.concat(Main.arrSoldiers.stream(), Main.arrSergeants.stream()), Main.arrOfficers.stream())
                .collect(Collectors.toList())) if (s.getState() == Soldier.State.Alive || s.getState() == Soldier.State.Neutralized) aliveOrcs.add(s);
        Stream<Soldier> stream = aliveOrcs.stream();
        return stream.count();
    }
    public static long countNeutralized() {
        LinkedList<Soldier> neutralizedOrcs = new LinkedList<>(); //parameterized collection
        for (Soldier s : Stream.concat(Stream.concat(Main.arrSoldiers.stream(), Main.arrSergeants.stream()), Main.arrOfficers.stream())
                .collect(Collectors.toList())) if (s.getState() == Soldier.State.Neutralized) neutralizedOrcs.add(s);
        Stream<Soldier> stream = neutralizedOrcs.stream();
        return stream.count();
    }

    public boolean isClicked(double mx, double my) {
        return viewCount.boundsInParentProperty().get().contains(mx, my);
    }

    public static void displayCriterias() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Count");
        window.setMinWidth(250);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label xLabel=new Label();
        xLabel.setText("Choose a criteria of counting orcs:");

        RadioButton activeButton = new RadioButton("Active Orcs");
        RadioButton aliveButton = new RadioButton("Alive Orcs");
        RadioButton neutralizedButton = new RadioButton("Neutralized Orcs");

        Button okButton = new Button("OK");

        layout.getChildren().addAll(xLabel, activeButton, aliveButton, neutralizedButton, okButton);

        okButton.setOnAction(e->{
            if (activeButton.isSelected()) {
                window.close();
                choice(1);
            } else if (aliveButton.isSelected()) {
                window.close();
                choice(2);
            } else if (neutralizedButton.isSelected()) {
                window.close();
                choice(3);
            } });

            Scene scene=new Scene(layout,300,300);
            window.setScene(scene);
            window.showAndWait();
    }

    public static void choice(int criteria) {
        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Count");
        window2.setMinWidth(250);

        VBox layout2 = new VBox(11);
        layout2.setAlignment(Pos.CENTER);

        Label xLabel2=new Label();

        int tmp = criteria;
        switch (tmp){
            case 1: xLabel2.setText("Active orcs: " + countActive()); break;
            case 2: xLabel2.setText("Alive orcs: " + countAlive()); break;
            case 3: xLabel2.setText("Neutralized orcs: " + countNeutralized());break;
        }

        Button sceneOkButton = new Button("OK");

        sceneOkButton.setOnAction(e -> {
            window2.close();
        });
        layout2.getChildren().addAll( xLabel2, sceneOkButton);

        Scene scene=new Scene(layout2,300,300);
        window2.setScene(scene);
        window2.showAndWait();
    }
}