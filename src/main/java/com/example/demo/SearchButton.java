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

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchButton {
    private ImageView viewSearch;
    private Image imgSearch;
    private double searchX, searchY;
    private Circle circleSearch;

    public SearchButton() {
        imgSearch = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\search.png", 30, 30, false, false);

        searchX = 40;
        searchY = 70;

        viewSearch = new ImageView(imgSearch);
        viewSearch.setX(searchX);
        viewSearch.setY(searchY);

        circleSearch = new Circle(searchX + 15, searchY + 15, 20);
        circleSearch.setFill(Color.WHITE);
        circleSearch.setStroke(Color.BLACK);
        circleSearch.setStrokeWidth(3);

        Main.group.getChildren().addAll(circleSearch, viewSearch);
    }

    public boolean isClicked(double mx, double my) {
        return viewSearch.boundsInParentProperty().get().contains(mx, my);
    }

    public static void display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search");
        window.setMinWidth(250);

        VBox layout = new VBox(11);
        layout.setAlignment(Pos.CENTER);

        Label xLabel = new Label();
        xLabel.setText("Choose what do you want to find out:");

        RadioButton firstButton = new RadioButton("Where is a certain soldier situated?");
        RadioButton secondButton = new RadioButton("Which orcs are neutralizing in Ukrainian Shtab right now?");
        RadioButton thirdButton = new RadioButton("Which orcs are giving away their money to Orc Shtab right now?");
        RadioButton fourthButton = new RadioButton("Which orcs are neither in Ukrainian Shtab nor in Orsc Shtab?");

        Button okButton = new Button("OK");

        layout.getChildren().addAll(xLabel, firstButton, secondButton, thirdButton, fourthButton, okButton);

        okButton.setOnAction(e -> {
            if (firstButton.isSelected()) {
                window.close();
                choice(1);
            } else if (secondButton.isSelected()) {
                window.close();
                choice(2);
            } else if (thirdButton.isSelected()) {
                window.close();
                choice(3);
            } else if (fourthButton.isSelected()) {
                window.close();
                choice(4);
            }
        });

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void choice(int criteria) {
        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Search");
        window2.setMinWidth(250);

        VBox layout2 = new VBox(11);
        layout2.setAlignment(Pos.CENTER);

        Label xLabel2 = new Label();
        Label xLabel3 = new Label();

        int tmp = criteria;

        if (tmp == 1) {
            searchOrc();
        } else if (tmp == 2) {
            if (Main.ukrProcessed.size() == 0) {
                xLabel2.setText("There are no orcs in Ukrainian Shtab right now");
            } else {
                xLabel2.setText("These orcs are in the Ukrainian Shtab right now:");
                StringBuilder sb = new StringBuilder();
                for (Object soldier : Main.ukrProcessed) {
                    sb.append(soldier.toString()).append("\n");
                }
                xLabel3.setText(String.valueOf(sb));
            }
        } else if (tmp == 3) {
            if (Main.orcProcessed.size() == 0) {
                xLabel2.setText("There are no orcs in Orc Shtab right now");
            } else {
                xLabel2.setText("These orcs are in the Orc Shtab right now:");
                StringBuilder sb = new StringBuilder();
                for (Object soldier : Main.orcProcessed) {
                    sb.append(soldier.toString()).append("\n");
                }
                xLabel3.setText(String.valueOf(sb));
            }
        } else if (tmp == 4) {
            ArrayList<Soldier> buf = new ArrayList<>();
            for (Soldier s : Stream.concat(Stream.concat(Main.arrSoldiers.stream(), Main.arrSergeants.stream()), Main.arrOfficers.stream())
                         .collect(Collectors.toList())) {
                if (!s.getProcessed()) buf.add(s);
            }
            if (buf.size() == 0) xLabel2.setText("All of the orcs are processed");
            else {
                xLabel2.setText("These orcs are neither in Ukrainian Shtab nor in Orsc Shtab");
                StringBuilder sb = new StringBuilder();
                for (Object soldier : buf) {
                    sb.append(soldier.toString()).append("\n");
                }
                xLabel3.setText(String.valueOf(sb));
            }
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

    public static void searchOrc() {

        Stage window3 = new Stage();
        window3.initModality(Modality.APPLICATION_MODAL);
        window3.setTitle("Search orc");
        window3.setMinWidth(250);

        VBox layout2 = new VBox(11);
        layout2.setAlignment(Pos.CENTER);

        Label label = new Label();
        label.setText("Choose features of a necessary orc:\n");

        Label label2 = new Label();
        label2.setText("Rank:");

        RadioButton soldierB = new RadioButton("Soldier");
        RadioButton sergeantB = new RadioButton("Sergeant");
        RadioButton officerB = new RadioButton("Officer");

        Label label3 = new Label();
        label3.setText("Number of hearts:");

        RadioButton oneB = new RadioButton("One");
        RadioButton twoB = new RadioButton("Two");
        RadioButton threeB = new RadioButton("Three");

        Label label4 = new Label();
        label4.setText("Is it active?");

        RadioButton yesB = new RadioButton("Yes");
        RadioButton noB = new RadioButton("No");

        Label label5 = new Label();
        label5.setText("Number of salary:");

        RadioButton oneCoinB = new RadioButton("One");
        RadioButton twoCoinsB = new RadioButton("Two");
        RadioButton threeCoinsB = new RadioButton("Three");

        Button okButton = new Button("OK");

        layout2.getChildren().addAll(label, label2, soldierB, sergeantB, officerB, label3, oneB, twoB, threeB, label4, yesB, noB, label5, oneCoinB, twoCoinsB, threeCoinsB, okButton);

        okButton.setOnAction(e -> {
            String selectedRank = soldierB.isSelected() ? "Soldier" : sergeantB.isSelected() ? "Sergeant" : officerB.isSelected() ? "Officer" : null;
            int selectedHearts = oneB.isSelected() ? 1 : twoB.isSelected() ? 2 : threeB.isSelected() ? 3 : -1;
            boolean isActive = yesB.isSelected();
            int selectedSalary = oneCoinB.isSelected() ? 1 : twoCoinsB.isSelected() ? 2 : threeCoinsB.isSelected() ? 3 : -1;

            Soldier tmp = null;
            boolean flg = false;
            for (Soldier s : Stream.concat(Stream.concat(Main.arrSoldiers.stream(), Main.arrSergeants.stream()), Main.arrOfficers.stream())
                    .collect(Collectors.toList())) {
                if (s.getRank().equals(selectedRank) &&
                        s.getLives() == selectedHearts &&
                        s.getActive() == isActive &&
                        s.getSalary() == selectedSalary) {
                    tmp = s;
                    flg = true;
                    break;
                }
            }

            if (flg) {
                window3.close();
                System.out.println("Found orc: " + tmp);
                displaySoldier(tmp);
            } else {
                window3.close();
                System.out.println("No orc found with the selected features.");
                displaySoldier(tmp);
            }
        });



        Scene scene = new Scene(layout2, 300, 300);
        window3.setScene(scene);
        window3.showAndWait();
    }

    public static void displaySoldier(Soldier s) {
        Stage window3 = new Stage();
        window3.initModality(Modality.APPLICATION_MODAL);
        window3.setMinWidth(250);

        VBox layout2 = new VBox(11);
        layout2.setAlignment(Pos.CENTER);

        Label label = new Label();
        label.setText("Choose features of a necessary orc:\n");
    }
}