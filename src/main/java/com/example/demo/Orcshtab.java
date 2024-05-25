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

public class Orcshtab {
    private double o_x, o_y;
    private int money;
    private int areProcessedO;
    private Label processedLabelO;
    private Label moneyLabel;
    private Label o_name;
    private ImageView vieworcshtab;
    private Image imgorcshtab;
    private Rectangle rec2;
    public Orcshtab () {

        imgorcshtab = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\orcshtab.png", 400, 400, false, false);

        o_x = 2650;
        o_y = 20;

        o_name = new Label("Orc Shtab");
        o_name.setLayoutX(o_x + 120);
        o_name.setLayoutY(o_y + 405);
        o_name.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 30));
        o_name.setTextFill(Color.WHITE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKOLIVEGREEN);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);
        o_name.setEffect(dropShadow);

        money = 0;
        moneyLabel = new Label();
        moneyLabel.setText("Money: " + money);
        moneyLabel.setLayoutX(o_x - 160);
        moneyLabel.setLayoutY(o_y + 30);
        moneyLabel.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 24));

        areProcessedO = 0;
        processedLabelO = new Label();
        processedLabelO.setText("In process: " + areProcessedO);
        processedLabelO.setLayoutX(o_x - 160);
        processedLabelO.setLayoutY(o_y);
        processedLabelO.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 24));

        vieworcshtab = new ImageView(imgorcshtab);
        vieworcshtab.setX(o_x);
        vieworcshtab.setY(o_y);

        rec2 = new Rectangle(o_x, o_y, 400, 400);
        rec2.setFill(Color.DARKOLIVEGREEN);
        rec2.setStroke(Color.BLACK);


        Main.group.getChildren().addAll(rec2, vieworcshtab, o_name, moneyLabel, processedLabelO);
    }

    public void getPaid(int tmp){
        money += tmp;
        moneyLabel.setText("Money: " + money);
    }

    public int getProcessed() {
        return Main.orcProcessed.size();
    }
    public void setProcessed() {
        areProcessedO = this.getProcessed();
        processedLabelO.setText("In process: " + areProcessedO);
   }

    public double getO_x() {
        return o_x;
    }

    public double getO_y() {
        return o_y;
    }
}
