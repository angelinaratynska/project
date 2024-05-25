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

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortButton {
    private ImageView viewSort;
    private Image imgSort;
    private double sortX, sortY;
    private Circle circleSort;

    public SortButton() {
        imgSort = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\sort.png", 30, 30, false, false);

        sortX = 40;
        sortY = 170;

        viewSort = new ImageView(imgSort);
        viewSort.setX(sortX);
        viewSort.setY(sortY);

        circleSort = new Circle(sortX + 15, sortY + 15, 20);
        circleSort.setFill(Color.WHITE);
        circleSort.setStroke(Color.BLACK);
        circleSort.setStrokeWidth(3);

        Main.group.getChildren().addAll(circleSort, viewSort);
    }

    public static String getStringFromSoldiers() {
        StringBuilder sb = new StringBuilder();
        for (Object s : Main.arrSoldiers) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    public boolean isClicked(double mx, double my) {
        return viewSort.boundsInParentProperty().get().contains(mx, my);
    }

    public static void display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sort");
        window.setMinWidth(250);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label xLabel = new Label();
        xLabel.setText("Choose a criteria of sorting soldiers:");

        RadioButton yButton = new RadioButton("Sorting by y-coordinate");
        RadioButton liveButton = new RadioButton("Sorting by lives");
        RadioButton salaryButton = new RadioButton("Sorting by salary");

        Button okButton = new Button("OK");

        layout.getChildren().addAll(xLabel, yButton, liveButton, salaryButton, okButton);

        okButton.setOnAction(e -> {
            if (yButton.isSelected()) {
                window.close();
                choice(1);
            } else if (liveButton.isSelected()) {
                window.close();
                choice(2);
            } else if (salaryButton.isSelected()) {
                window.close();
                choice(3);
            }
        });

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void choice(int criteria) {
        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Sort");
        window2.setMinWidth(250);

        VBox layout2 = new VBox(11);
        layout2.setAlignment(Pos.CENTER);

        Label xLabel2 = new Label();
        Label xLabel3 = new Label();

        int tmp = criteria;

        if (tmp == 1) {
            xLabel2.setText("The array of soldiers before sorting by y-coordinate:\n" + getStringFromSoldiers());
            Collections.sort(Main.arrSoldiers, new Comparator<Soldier>() { //the second argument is an anonymous class
                @Override
                public int compare(Soldier s1, Soldier s2) {
                    return Double.compare(s1.getY(), s2.getY());
                }
            });
            xLabel3.setText("The array of soldiers after sorting by y-coordinate:\n" + getStringFromSoldiers());
        } else if (tmp == 2) {
            xLabel2.setText("The array of soldiers before sorting by lives:\n" + getStringFromSoldiers());
            Collections.sort(Main.arrSoldiers, new Comparator<Soldier>() { //the second argument is an anonymous class
                @Override
                public int compare(Soldier s1, Soldier s2) {
                    return Double.compare(s1.getLives(), s2.getLives());
                }
            });
            xLabel3.setText("The array of soldiers after sorting by lives:\n" + getStringFromSoldiers());
        } else if (tmp == 3) {
            xLabel2.setText("The array of soldiers before sorting by salary:\n" + getStringFromSoldiers());
            Collections.sort(Main.arrSoldiers, new Comparator<Soldier>() { //the second argument is an anonymous class
                @Override
                public int compare(Soldier s1, Soldier s2) {
                    return Double.compare(s1.getSalary(), s2.getSalary());
                }
            });
            xLabel3.setText("The array of soldiers after sorting by salary:\n" + getStringFromSoldiers());
        }


        Button sceneOkButton = new Button("OK");

        sceneOkButton.setOnAction(e -> {
            window2.close();
        });
        layout2.getChildren().addAll(xLabel2, xLabel3, sceneOkButton);

        Scene scene = new Scene(layout2, 300, 300);
        window2.setScene(scene);
        window2.showAndWait();
    }
}