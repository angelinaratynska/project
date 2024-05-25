package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Ukrshtab {
    private double u_x, u_y;
    private int areProcessedU;
    private int neutralized;
    private Label neutralizedLabel;
    private Label processedLabelU;
    private Label u_name;
    private Image imgukrshtab;
    private ImageView viewukrshtab;
    private Rectangle rec;
    public Ukrshtab (){

        imgukrshtab = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\ukrshtab.png", 400, 400, false, false);

        u_x = 100;
        u_y = 1100;

        u_name = new Label("Ukrainian Shtab");
        u_name.setLayoutX(u_x + 100);
        u_name.setLayoutY(u_y + 400);
        u_name.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 30));
        u_name.setTextFill(Color.WHITE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.PURPLE);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);
        u_name.setEffect(dropShadow);

        areProcessedU = 0;
        processedLabelU = new Label();
        processedLabelU.setText("In process: " + areProcessedU);
        processedLabelU.setLayoutX(u_x + 410);
        processedLabelU.setLayoutY(u_y);
        processedLabelU.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 24));

        neutralized = 0;
        neutralizedLabel = new Label();
        neutralizedLabel.setText("Neutralized: " + neutralized);
        neutralizedLabel.setLayoutX(u_x + 410);
        neutralizedLabel.setLayoutY(u_y + 30);
        neutralizedLabel.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 24));

        viewukrshtab = new ImageView(imgukrshtab);
        viewukrshtab.setX(u_x);
        viewukrshtab.setY(u_y);

        rec = new Rectangle(u_x, u_y, 400, 400);
        rec.setFill(Color.PURPLE);
        rec.setStroke(Color.BLACK);

        Main.group.getChildren().addAll(rec, viewukrshtab, u_name, processedLabelU, neutralizedLabel);
    }

    public void setNeutralized(){
        ++neutralized;
        neutralizedLabel.setText("Neutralized: " + neutralized);
    }

    public int getProcessed() {
        return Main.ukrProcessed.size();
    }
    public void setProcessed() {
        areProcessedU = this.getProcessed();
        processedLabelU.setText("In process: " + Integer.toString(areProcessedU));
    }

    public double getU_x() {
        return u_x;
    }

    public double getU_y() {
        return u_y;
    }
}
