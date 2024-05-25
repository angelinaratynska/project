package com.example.demo;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.*;

public class Soldier implements Cloneable, Comparable<Soldier> {
    protected double s_x, s_y;
    protected double bounceX = 1;
    protected double bounceY = 1;
    protected boolean isActive;
    protected boolean isProcessed;
    enum State { Alive, Died, MonstrFirst, Neutralized, Eaten }
    State state;
    protected int lives;
    protected int salary;
    protected Label salaryLabel;
    OriginalityLabel originality; //reference type

    protected Label s_rank;
    protected ImageView viewSoldier;
    protected ImageView viewCoins;
    protected Rectangle rectActive;
    protected Image imgSoldier;
    protected Image imgHeart;
    protected Image imgCoins;
    protected Image imgMonstrFirst;
    protected HashSet<ImageView> hearts = new HashSet<>(); //parameterized collection

    @Override
    public String toString() {
        String or = originality.getTxt();
        String s_r = s_rank.getText();
        return "Soldier{" +
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
    }//virtual function

    static {
        System.out.println("Static initializing Soldier class.");
    }

    {
        System.out.println("Non-static initializing a new Soldier object.");
    }

    public Soldier(double x, double y, int s_lives, int s_salary) {
        System.out.println("The constructor with parameters of a class Soldier is working!");

        imgHeart = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\heart.png", 20, 20, false, false);
        imgCoins = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\coins.png", 20, 20, false, false);

        s_x = x;
        s_y = y;

        isActive = false;
        isProcessed = false;

        state = State.Alive;

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);

        originality = new OriginalityLabel(x, y, "I`m original");

        createRank(x, y, dropShadow);

        createViewSoldier(x, y, dropShadow);

        viewCoins = new ImageView(imgCoins);
        viewCoins.setX(x + 65);
        viewCoins.setY(y + 10);
        viewCoins.setEffect(dropShadow);

        salary = s_salary;
        salaryLabel = new Label();
        salaryLabel.setText(Integer.toString(s_salary));
        salaryLabel.setLayoutX(x + 55);
        salaryLabel.setLayoutY(y + 10);
        salaryLabel.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        salaryLabel.setEffect(dropShadow);

        rectActive = new Rectangle(x - 20, y - 45, 110, 165);
        rectActive.setFill(Color.TRANSPARENT);
        rectActive.setStroke(Color.GREEN);
        rectActive.setStrokeWidth(3);
        rectActive.setEffect(dropShadow);

        addToGroup();


        lives = s_lives;
        int a = 0;
        for (int i = 0; i < s_lives; i++) {
            ImageView heart = new ImageView(imgHeart);
            heart.setX(x + a);
            heart.setY(y - 40);
            heart.setEffect(dropShadow);
            hearts.add(heart);
            Main.group.getChildren().add(heart);
            a += 25;
        }

        System.out.println(toString());

    } //constructor

    private void addToGroup() {
        Main.group.getChildren().addAll(viewSoldier, s_rank, viewCoins, salaryLabel);
    }

    void createViewSoldier(double x, double y, DropShadow dropShadow) {
        imgSoldier = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\soldier.png", 140, 100, false, false);
        viewSoldier = new ImageView(imgSoldier);
        viewSoldier.setX(x - 40);
        viewSoldier.setY(y);
        viewSoldier.setEffect(dropShadow);
    }//virtual function images/Heavy.png

    void createRank(double x, double y, DropShadow dropShadow) {
        s_rank = new Label("SOLDIER");
        s_rank.setLayoutX(x + 10);
        s_rank.setLayoutY(y + 100);
        s_rank.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
        s_rank.setEffect(dropShadow);
    }//virtual function

    public Soldier() {
        this(0.0, 0.0, 0, 0);
    }

    public void setX(String strX) {
        try {
            s_x = Double.parseDouble(strX);
        } catch (Exception e) {
            s_x = 0.0;
        }
        setX(s_x);
    }
    public void setX(double x) {
        s_x = x;
        setCoordinates();
    } //static polymorphism

    public Double getX() {
        return s_x;
    }
    public String getStringX() {
        return Double.toString(s_x);
    }

    public void setY(String strY) {
        try {
            s_y = Double.parseDouble(strY);
        } catch (Exception e) {
            s_y = 0.0;
        }
        setY(s_y);
    }
    public void setY(double y) {
        s_y = y;
        setCoordinates();
    }

    public String getStrY() {
        return Double.toString(s_y);
    }
    public Double getY() {
        return s_y;
    }
    public void setCoordinates() {
        s_rank.setLayoutX(s_x + 10);
        s_rank.setLayoutY(s_y + 100);

        originality.setCoordinates(s_x, s_y);

        int a = 0;
        for (ImageView heart : hearts) {
            heart.setX(s_x + a);
            heart.setY(s_y - 40);
            a += 25;
        }

        viewSoldier.setX(s_x - 40);
        viewSoldier.setY(s_y);

        rectActive.setX(s_x - 20);
        rectActive.setY(s_y - 45);

        viewCoins.setX(s_x + 65);
        viewCoins.setY(s_y + 10);

        salaryLabel.setLayoutX(s_x + 55);
        salaryLabel.setLayoutY(s_y + 10);
    }

    public boolean getActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getProcessed() {
        return isProcessed;
    }
    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public OriginalityLabel getOriginality() { return originality; }
    public void setOriginality(Label st, double x, double y){
        originality.setSt(st, x, y);
    }

    public int getLives(){return lives;}
    public void setLives(int l) {
        lives = l;
    }

    public ImageView getViewSoldier() {
        return viewSoldier;
    }
    public void setViewSoldier(ImageView viewSoldier) {
        this.viewSoldier = viewSoldier;
    }

    public Label getRank() {
        return s_rank;
    }
    public void setRank(String rank) {
        s_rank.setText(rank);
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    public void setNeutralized() {
        this.setState(State.Neutralized);
        rectActive.setFill(Color.TRANSPARENT);
        rectActive.setStroke(Color.PURPLE);
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
        salaryLabel.setText(Integer.toString(salary));
    }


    public void setRectActive(Rectangle rectActive) { this.rectActive = rectActive; }
    public Rectangle getRectActive() { return rectActive;}

    public void deleteSoldier() {
        Main.group.getChildren().removeAll(viewSoldier, s_rank, originality.getLabel(), viewCoins, salaryLabel);
        for (ImageView heart : hearts) {
            Main.group.getChildren().remove(heart);
        }
        hearts.clear();
        if(isActive) Main.group.getChildren().remove(rectActive);

        isActive = false;
        this.setState(State.Died);

        Zombie z = new Zombie();
        Main.zombieArr.add(z);
    }
    public void createMonstrFirst() {
        Main.group.getChildren().remove(viewSoldier);

        System.out.println("A monstrfirst is created!");

        imgMonstrFirst = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\monstrFirst.png", 110, 100, false, false);

        ImageView viewMonstrFirst = new ImageView(imgMonstrFirst);
        setViewSoldier(viewMonstrFirst);

        Main.group.getChildren().add(viewSoldier);

        this.setState(State.MonstrFirst);

    }

    public void eatNeutralized(){
        for (Soldier s: Main.arrNeutralized){
        if (this.getX() >= s.getX() && this.getX() <= s.getX() + 100
                && this.getY() >= s.getY()&& this.getY() <= s.getY() + 20) {

           // this.move(0, 0);
            s.setX(200);
            s.setY(100);


            s.setState(State.Eaten);
            Main.arrNeutralized.remove(s);
            //this.flipActivation();


        } else if(s.getState()!=State.Eaten){

            double signdx = Math.signum(s.getX() - this.getX());
            double dx = Math.abs(s.getX() - this.getX());
            dx = ((dx < 1) ? dx : 1);
            dx = signdx * dx * 2;

            double signdy = Math.signum(s.getY() - this.getY());
            double dy = Math.abs(s.getY() - this.getY());
            dy = ((dy < 1) ? dy : 1);
            dy = signdy * dy *2;

            move(dx, dy);
        }
        }
    }

    public void escapeSoldier() {
        Main.group.getChildren().remove(rectActive);
        isActive = !isActive;
    }

    void getPaid() {
        for (Base b : Main.bases) {
            if (this.getX() >= b.getB_x() && this.getX() <= b.getB_x() + 50 && this.getY() >= b.getB_y()&& this.getY() <= b.getB_y() + 50) {
                if (b.getB_state() == Base.State.Untouched) {
                    this.setSalary(++salary);
                    b.setB_state(Base.State.Revealed);
                    b.setRevealed();
                }
            }
        }
    }//virtual function
    public void reduceHearts(){
        for (Bomb b : Main.bombs) {
            if (this.getX() >= b.getBomb_x() && this.getX() <= b.getBomb_x() + 50 && this.getY() >= b.getBomb_y()&& this.getY() <= b.getBomb_y() + 50) {
                if (b.getBomb_state() == Bomb.State.Untouched){
                    boolean heartRemoved = false;
                    if (!hearts.isEmpty()) {
                        Iterator<ImageView> iterator = hearts.iterator();
                        ImageView heartToRemove = null;
                        while (iterator.hasNext()) {
                            heartToRemove = iterator.next();
                        }
                        if (heartToRemove != null) {
                            heartRemoved = Main.group.getChildren().remove(heartToRemove);
                            if (heartRemoved) {
                                iterator.remove();
                            }
                        }
                    }

                    b.setBomb_state(Bomb.State.Exploded);
                    b.explodeBomb();

                    if (this.getLives() == 1) this.deleteSoldier();

                    this.setLives(--lives);
                }
            }
        }
    }

    public boolean checkOrcshtabInteraction(){
        boolean tmp = true;
        if(this.getX() >= Main.o.getO_x() && this.getX() <= Main.o.getO_x() + 100
                && this.getY() >= Main.o.getO_y()&& this.getY() <= Main.o.getO_y() + 20) {
            return tmp;
        }
        else {
            this.setProcessed(false);
            return !tmp;
        }
    }

    public void orcshtabInteraction(){

        if (checkOrcshtabInteraction()) {

            this.move(50, 50);
            this.setProcessed(true);
            this.flipActivation();
            Main.o.getPaid(this.getSalary());
            Main.orcProcessed.add(this);
            Main.o.setProcessed();
            this.setSalary(0);

        } else {

            double signdx = Math.signum(Main.o.getO_x() - this.getX());
            double dx = Math.abs(Main.o.getO_x() - this.getX());
            dx = ((dx < 1) ? dx : 1);
            dx = signdx * dx;

            double signdy = Math.signum(Main.o.getO_y() - this.getY());
            double dy = Math.abs(Main.o.getO_y() - this.getY());
            dy = ((dy < 1) ? dy : 1);
            dy = signdy * dy;

            move(dx, dy);
        }
    }

    public boolean checkUkrshtabInteraction(){
        boolean tmp = true;
        if(this.getX() >= Main.u.getU_x() && this.getX() <= Main.u.getU_x() + 200
                && this.getY() >= Main.u.getU_y()&& this.getY() <= Main.u.getU_y() + 200) return tmp;
        else {
            this.setProcessed(false);
            return !tmp;
        }
    }


    public void ukrshtabInteraction(){
        if (checkUkrshtabInteraction()) {
            Main.ukrProcessed.add(this);
            Main.arrNeutralized.add(this);
            //Main.arrSoldiers.remove(this);
            Main.u.setProcessed();
            Main.u.setNeutralized();
            this.setProcessed(true);
            this.setNeutralized();
        }
    }

    public boolean flipActivation() {
        if (isActive) Main.group.getChildren().remove(rectActive);
        else if (!isActive && this.getState() != State.Died) Main.group.getChildren().add(rectActive);
        isActive = !isActive;
        return isActive;
    }

    public boolean mouseActivate(double mx, double my) {
        if (viewSoldier.boundsInParentProperty().get().contains(mx, my)) {
            flipActivation();
            return true;
        }
        return false;
    }

    public void move(double dx, double dy) {
        s_x = s_x + dx;
        s_y = s_y + dy;
        setCoordinates();
    }

    public void bounceMove() {

        Bounds bounds = Main.scene.getRoot().getBoundsInLocal();
        boolean rightBorder = (this.getX() >= (bounds.getMaxX() - 120));
        boolean leftBorder = (this.getX() <= (bounds.getMinX() + 60));
        boolean bottomBorder = (this.getY() >= (bounds.getMaxY() - 140));
        boolean topBorder = (this.getY() <= (bounds.getMinY() + 60));

        if (rightBorder || leftBorder) {
            bounceX *= -1;
        }
        if (bottomBorder || topBorder) {
            bounceY *= -1;
        }

        this.move(bounceX, bounceY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Soldier soldier)) return false;
        return Double.compare(soldier.s_x, s_x) == 0 && Double.compare(soldier.s_y, s_y) == 0 && Double.compare(soldier.bounceX, bounceX) == 0 && Double.compare(soldier.bounceY, bounceY) == 0 && getActive() == soldier.getActive() && getProcessed() == soldier.getProcessed() && getLives() == soldier.getLives() && getSalary() == soldier.getSalary() && getState() == soldier.getState() && Objects.equals(salaryLabel, soldier.salaryLabel) && Objects.equals(getOriginality(), soldier.getOriginality()) && Objects.equals(s_rank, soldier.s_rank) && Objects.equals(getViewSoldier(), soldier.getViewSoldier()) && Objects.equals(viewCoins, soldier.viewCoins) && Objects.equals(getRectActive(), soldier.getRectActive()) && Objects.equals(imgSoldier, soldier.imgSoldier) && Objects.equals(imgHeart, soldier.imgHeart) && Objects.equals(imgCoins, soldier.imgCoins) && Objects.equals(hearts, soldier.hearts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s_x, s_y, bounceX, bounceY, getActive(), getProcessed(), getState(), getLives(), getSalary(), salaryLabel, getOriginality(), s_rank, getViewSoldier(), viewCoins, getRectActive(), imgSoldier, imgHeart, imgCoins, hearts);
    }

    public Soldier clone() throws CloneNotSupportedException {

        Soldier clonedSoldier = new Soldier(this.getX()+200, this.getY()+200, this.getLives(), this.getSalary());

        clonedSoldier.flipActivation();

        Main.group.getChildren().remove(clonedSoldier.originality.getLabel());
        clonedSoldier.originality = (OriginalityLabel) this.originality.clone();

        if (this.getState() == State.Neutralized) clonedSoldier.setNeutralized();

        System.out.println("\nClonning:");
        System.out.println("Original " + this);
        System.out.println("Cloned " + clonedSoldier + '\n');

        return clonedSoldier;
    } // interface cloneable

    @Override
    public int compareTo(Soldier anotherSoldier) {
        return (int) (this.s_x - anotherSoldier.s_x);
    }//Interface Comparable

    public class Zombie {
        protected Image imgZombie;
        protected ImageView viewZombie;
        protected Label zombie;

        public Zombie() {
            System.out.println("A soldier is killed, a zombie is created!");

            imgZombie = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\zombie.png", 110, 100, false, false);

            isActive = false;

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.WHITE);
            dropShadow.setSpread(0.7);

            labelZombie(dropShadow);

            viewZombie = new ImageView(imgZombie);
            viewZombie.setX(s_x - 30);
            viewZombie.setY(s_y - 20);
            viewZombie.setEffect(dropShadow);

            Main.group.getChildren().addAll(zombie, viewZombie);
        }

        void labelZombie(DropShadow dropShadow) {
            zombie = new Label("ZOMBIE");
            zombie.setLayoutX(s_x + 15);
            zombie.setLayoutY(s_y + 80);
            zombie.setFont(Font.font("TimesNewRomans", FontWeight.BOLD, 12));
            zombie.setEffect(dropShadow);
        }

        public Label getZombie() {
            return zombie;
        }
        public void setZombie(Label zombie) {
            this.zombie = zombie;
        }
        public ImageView getViewZombie() {
            return viewZombie;
        }
        public void setViewZombie(ImageView viewZombie) {
            this.viewZombie = viewZombie;
        }
    } // inner/nested class

}