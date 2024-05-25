package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class OriginalityLabel implements Cloneable {
    Label s_state;
    double sX, sY;
    public OriginalityLabel(double x, double y, String m_state) {

        sX = x;
        sY = y;

        s_state = new Label(m_state);
        s_state.setLayoutX(sX);
        s_state.setLayoutY(sY - 20);
        s_state.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);
        s_state.setEffect(dropShadow);

        Main.group.getChildren().add(s_state);
    }

    public Label getLabel() {
        return s_state;
    }
    public String getTxt() {
        return s_state.getText();
    }

    public void setCoordinates(double x, double y) {
        s_state.setLayoutX(x);
        s_state.setLayoutY(y - 20);
    }

    public Object clone() throws CloneNotSupportedException {

        String tmp = "I`m cloned";
        double x = (s_state.getLayoutX()+200);
        double y = (s_state.getLayoutY()+220);
        OriginalityLabel clonedLabel = new OriginalityLabel(x, y, tmp);

        return clonedLabel;
    }
    public void setSt(Label m_state, Double x, Double y) {
        Main.group.getChildren().remove(s_state);
        s_state = m_state;
        s_state.setLayoutX(x);
        s_state.setLayoutY(y - 20);
        s_state.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        Main.group.getChildren().add(s_state);
    }
}