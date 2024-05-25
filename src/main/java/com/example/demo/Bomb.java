package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb {
    private double bomb_x;
    private double bomb_y;
    private ImageView viewbomb;
    private Image imgbomb;
    private Image imgexplodedbomb;
    enum State { Untouched, Exploded }
    Bomb.State bomb_state;

    public Bomb(double x, double y) {

        imgbomb = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\bomb.png", 50, 50, false, false);
        imgexplodedbomb = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\exploded.png", 50, 50, false, false);


        bomb_x = x;
        bomb_y = y;

        bomb_state = Bomb.State.Untouched;

        viewbomb = new ImageView(imgbomb);
        viewbomb.setX(x);
        viewbomb.setY(y);

        Main.group.getChildren().add(viewbomb);
    }

    public void explodeBomb(){
        Main.group.getChildren().remove(viewbomb);
        bomb_state = State.Exploded;
        viewbomb = new ImageView(imgexplodedbomb);
        viewbomb.setX(bomb_x);
        viewbomb.setY(bomb_y);
        Main.group.getChildren().add(viewbomb);
    }

    public void setViewbomb(ImageView viewbomb) {
        this.viewbomb = viewbomb;
    }

    public double getBomb_x() {
        return bomb_x;
    }

    public double getBomb_y() {
        return bomb_y;
    }

    public State getBomb_state() {
        return bomb_state;
    }

    public void setBomb_state(State bomb_state) {
        this.bomb_state = bomb_state;
    }

    public void setBomb_x(double bomb_x) {
        this.bomb_x = bomb_x;
    }

    public void setBomb_y(double bomb_y) {
        this.bomb_y = bomb_y;
    }

}