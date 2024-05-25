package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NavigationButton {
    private ImageView viewNavigation;
    private Image imgNavigation;
    private double navigationX, navigationY;
    private Circle circleNavigation;

    public NavigationButton() {
        imgNavigation = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\navigation.png", 30, 30, false, false);

        navigationX = 40;
        navigationY = 20;

        viewNavigation = new ImageView(imgNavigation);
        viewNavigation.setX(navigationX);
        viewNavigation.setY(navigationY);

        circleNavigation = new Circle(navigationX + 15 , navigationY + 15, 20);
        circleNavigation.setFill(Color.WHITE);
        circleNavigation.setStroke(Color.BLACK);
        circleNavigation.setStrokeWidth(3);

        Main.group.getChildren().addAll(circleNavigation, viewNavigation);
    }

    public boolean isClicked(double mx, double my) {
        return viewNavigation.boundsInParentProperty().get().contains(mx, my);
    }

    public static void display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Navigation");


        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label label=new Label();

        try {
            String fileContent = readFile("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\Navigation.txt");
            label.setText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button okButton=new Button("OK");
        okButton.setOnAction(e->{

            window.close();
        });


        layout.getChildren().addAll(label, okButton);

        Scene scene=new Scene(layout,300,300);
        window.setScene(scene);
        window.showAndWait();

    }
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        reader.close();
        return content.toString();
    }
}
