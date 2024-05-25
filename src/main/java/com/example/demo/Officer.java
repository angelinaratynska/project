package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Officer extends Sergeant{
    protected Image imgOfficer;
    protected Image imgHighProtection;
    protected ImageView viewHighProtection;

    protected boolean highProtection;

    public Officer(double x, double y, int s_lives, int s_salary) {
        super(x, y, s_lives, s_salary);

        imgHighProtection = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\highprotection.png", 120, 100, false, false);
        viewHighProtection = new ImageView(imgHighProtection);
        viewHighProtection.setX(x - 20);
        viewHighProtection.setY(y - 5);

        highProtection = true;

        Main.group.getChildren().add(viewHighProtection);

        toString();
    }
    public Officer(){
        super();
    }
    public String toString() {
        String or = originality.getTxt();
        String s_r = s_rank.getText();
        return "Officer{" +
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
        s_rank = new Label("OFFICER");
        s_rank.setLayoutX(x + 10);
        s_rank.setLayoutY(y + 100);
        s_rank.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        s_rank.setEffect(dropShadow);
    }

    @Override
    void createViewSoldier(double x, double y, DropShadow dropShadow) {
        imgOfficer = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\officer.png", 140, 100, false, false);
        viewSoldier = new ImageView(imgOfficer);
        viewSoldier.setX(x - 40);
        viewSoldier.setY(y);
        viewSoldier.setEffect(dropShadow);
    }

    public void setCoordinates() {
        super.setCoordinates();

        viewHighProtection.setX(s_x - 20);
        viewHighProtection.setY(s_y - 10);
    }

   @Override
    public void reduceHearts() {
        for (Bomb b : Main.bombs) {
            if (this.getX() >= b.getBomb_x() && this.getX() <= b.getBomb_x() + 50 &&
                    this.getY() >= b.getBomb_y() && this.getY() <= b.getBomb_y() + 50) {
                if (b.getBomb_state() == Bomb.State.Untouched) {
                    if (!getHighProtection()) {
                        super.reduceHearts();
                        break;
                    }
                    else if (this.getHighProtection()) {
                        this.setHighProtection(false);
                        this.removeHighProtection();
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
                if (b.getB_state() == Base.State.Mined) {
                    int salaryTmp = 3;
                    this.setSalary(salaryTmp);
                    b.setB_state(Base.State.Destroyed);
                    b.setDestroyed();
                }
            }
        }
    }

    public void setHighProtection(boolean highProtection){
        this.highProtection = highProtection;
    }

    public boolean getHighProtection() {
        return highProtection;
    }
    public void removeHighProtection(){
        if (!getHighProtection()) Main.group.getChildren().remove(viewHighProtection);
    }

    public Officer clone() throws CloneNotSupportedException {

        Officer clonedOfficer = new Officer(this.getX()+200, this.getY()+200, this.getLives(), this.getSalary());

        clonedOfficer.flipActivation();

        Main.group.getChildren().remove(clonedOfficer.originality.getLabel());
        clonedOfficer.originality = (OriginalityLabel) this.originality.clone();

        if (this.getState() == State.Neutralized) clonedOfficer.setNeutralized();

        System.out.println("\nClonning:");
        System.out.println("Original " + this);
        System.out.println("Cloned " + clonedOfficer + '\n');

        return clonedOfficer;
    }
}
