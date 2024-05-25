package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Sergeant extends Soldier{
    protected Image imgSergeant;
    protected Image imgProtection;
    protected boolean protection;
    protected ImageView viewProtection;

    public Sergeant(double x, double y, int s_lives, int s_salary) {
        super(x, y, s_lives, s_salary);

        imgProtection = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\protection.png", 150, 180, false, false);
        viewProtection = new ImageView(imgProtection);
        viewProtection.setX(x - 40);
        viewProtection.setY(y );

        protection = true;

        Main.group.getChildren().add(viewProtection);

        toString();
    }
    public Sergeant(){
        super();
    }

    @Override
    public String toString() {
        String or = originality.getTxt();
        String s_r = s_rank.getText();
        return "Sergeant{" +
                "s_x=" + s_x +
                ", s_y=" + s_y +
                ", isActive=" + isActive +
                ", isProcessed=" + isProcessed +
                ", state=" + state +
                ", lives=" + lives +
                ", salary=" + salary +
                ", originality=" + or +
                ", s_rank=" + s_r +
                '}';
    }
    @Override
    void createRank(double x, double y, DropShadow dropShadow) {
        s_rank = new Label("SERGEANT");
        s_rank.setLayoutX(x + 10);
        s_rank.setLayoutY(y + 100);
        s_rank.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        s_rank.setEffect(dropShadow);
    }

    @Override
    void createViewSoldier(double x, double y, DropShadow dropShadow) {
        imgSergeant = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\sergeant.png", 140, 100, false, false);
        viewSoldier = new ImageView(imgSergeant);
        viewSoldier.setX(x - 40);
        viewSoldier.setY(y);
        viewSoldier.setEffect(dropShadow);
    }

    @Override
    public void setCoordinates() {
       super.setCoordinates();

       viewProtection.setX(s_x - 40);
       viewProtection.setY(s_y - 45);
    }

    @Override
    public void reduceHearts() {
        for (Bomb b : Main.bombs) {
            if (this.getX() >= b.getBomb_x() && this.getX() <= b.getBomb_x() + 50 &&
                    this.getY() >= b.getBomb_y() && this.getY() <= b.getBomb_y() + 50) {
                if (b.getBomb_state() == Bomb.State.Untouched) {
                    if (!getProtection()) {
                      super.reduceHearts();
                      break;
                    }
                    else if (this.getProtection() && b.getBomb_state()== Bomb.State.Untouched) {
                        this.setProtection(false);
                        this.removeProtection();
                        b.setBomb_state(Bomb.State.Exploded);
                        b.explodeBomb();
                        break;
                    }
                }
            }
        }
    }


    @Override
    void getPaid() {
        for (Base b : Main.bases) {
            if (this.getX() >= b.getB_x() && this.getX() <= b.getB_x() + 50 && this.getY() >= b.getB_y()&& this.getY() <= b.getB_y() + 50) {
                if (b.getB_state() == Base.State.Revealed) {
                    int salaryTmp = 2;
                    this.setSalary(salaryTmp);
                    b.setB_state(Base.State.Mined);
                    b.setMined();
                }
            }
        }
    } //dynamic polymorphism


    public void setProtection(boolean protection){
        this.protection = protection;
    }

    public boolean getProtection() {
        return protection;
    }
    public void removeProtection(){
        if (!getProtection()) Main.group.getChildren().remove(viewProtection);
    }

    public Sergeant clone() throws CloneNotSupportedException {

        Sergeant clonedSergeant = new Sergeant(this.getX()+200, this.getY()+200, this.getLives(), this.getSalary());

        clonedSergeant.flipActivation();

        Main.group.getChildren().remove(clonedSergeant.originality.getLabel());
        clonedSergeant.originality = (OriginalityLabel) this.originality.clone();

        if (this.getState() == State.Neutralized) clonedSergeant.setNeutralized();

        System.out.println("\nClonning:");
        System.out.println("Original " + this);
        System.out.println("Cloned " + clonedSergeant + '\n');

        return clonedSergeant;
    }
}
