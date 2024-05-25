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

public class Base {
    private final double b_x;
    private final double b_y;
    private final Label b_name;
    private final ImageView viewbase;
    private Image imgbase;
    private ImageView viewMined;
    private Image imgMined;
    private Image imgDestroyed;
    private ImageView viewDestroyed;
    private final Rectangle rect2;

    enum State { Untouched, Revealed, Mined, Destroyed }
    State b_state;

    public Base (double b_x, double b_y) {

        imgbase = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\base.png", 50, 50, false, false);

        this.b_x = b_x;
        this.b_y = b_y;

        b_state = State.Untouched;

        b_name = new Label(" BASE");
        b_name.setLayoutX(b_x + 12);
        b_name.setLayoutY(b_y + 57);
        b_name.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        b_name.setTextFill(Color.WHITE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);
        b_name.setEffect(dropShadow);

        viewbase = new ImageView(imgbase);
        viewbase.setX(b_x + 5);
        viewbase.setY(b_y + 5);

        rect2 = new Rectangle(b_x, b_y,60, 80);
        rect2.setFill(Color.LIGHTGREY);
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeWidth(2);

        Main.group.getChildren().addAll(rect2, viewbase, b_name);

    }

    public State getB_state() {
        return b_state;
    }

    public void setB_state(State b_state) {
        this.b_state = b_state;
    }

    public double getB_x() {
        return b_x;
    }

    public double getB_y() {
        return b_y;
    }
    public void setRevealed(){
        if (this.getB_state()==State.Revealed) rect2.setStroke(Color.RED);
    }
    public void setMined() {
        if (this.getB_state()==State.Mined) {
            imgMined = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\mined.png", 60, 80, false, false);
            viewMined = new ImageView(imgMined);
            viewMined.setX(b_x);
            viewMined.setY(b_y);

            Main.group.getChildren().add(viewMined);
        }
    }
    public void setDestroyed() {
        Main.group.getChildren().removeAll(rect2, viewbase, b_name, viewMined);
        imgDestroyed = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\destroyed.png", 60, 60, false, false);
        viewDestroyed = new ImageView(imgDestroyed);
        viewDestroyed.setX(b_x);
        viewDestroyed.setY(b_y);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.RED);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.5);
        viewDestroyed.setEffect(dropShadow);

        Main.group.getChildren().add(viewDestroyed);
    }

    @Override
    public String toString() {
        return "Base{" +
                "b_x=" + b_x +
                ", b_y=" + b_y +
                ", b_name=" + b_name +
                ", b_state=" + b_state +
                '}';
    }
}