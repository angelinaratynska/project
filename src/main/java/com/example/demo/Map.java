package com.example.demo;

import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Map {
    private ImageView viewMap;
    private Label orkostanLabel;
    private static Image imgMap;
    Map() throws FileNotFoundException {

        imgMap = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\ukr.png", 3000, 1500, false, false);

        viewMap = new ImageView(imgMap);
        viewMap.setTranslateX(100);
        viewMap.setTranslateY(70);
        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setColor(Color.GREY);
        dropShadow1.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow1.setSpread(0.7);
        viewMap.setEffect(dropShadow1);

        orkostanLabel = new Label();
        orkostanLabel.setFont(Font.font("Times New Romans", FontWeight.BOLD,  50));
        orkostanLabel.setText("O R K O S T A N");
        orkostanLabel.setTextFill(Color.WHITE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLUE);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.6);
        orkostanLabel.setEffect(dropShadow);
        orkostanLabel.setTranslateX(2650);
        orkostanLabel.setTranslateY(1450);


        Main.group.getChildren().addAll(viewMap, orkostanLabel);
    }
}
