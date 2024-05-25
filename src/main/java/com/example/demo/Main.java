package com.example.demo;

//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends Application {
    static Group group;
    public static Scene scene;

    public static Ukrshtab u;
    public static Orcshtab o;
    public static Map wp;

    public static double dogX=35;

    public static double dogY=58;

    public static NavigationButton navigationButton;
    public static CountButton countButton;
    public static SortButton sortButton;
    public static SearchButton searchButton;

    public static Image imgPatron;

    public static ArrayList<Soldier> arrSoldiers = new ArrayList<>(); //parameterized collection
    public static ArrayList<Sergeant> arrSergeants = new ArrayList<>();
    public static ArrayList<Officer> arrOfficers = new ArrayList<>();
    public static ArrayList<Soldier.Zombie> zombieArr = new ArrayList<>();
    public static ArrayList<Base> bases = new ArrayList<>();
    public static ArrayList<Bomb> bombs = new ArrayList<>();
    public static ArrayList<Soldier> arrNeutralized = new ArrayList<>();
    public static ArrayList<Soldier> clonedArr = new ArrayList<>();
    public static ArrayList<Soldier> orcProcessed = new ArrayList<>();
    public static ArrayList<Soldier> ukrProcessed = new ArrayList<>();

    Serealizator ser = new Serealizator();

  /*  public static void json(Object object) throws IOException {
        Writer file = new FileWriter("data.json", true);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(file);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(object.getClass().getSimpleName());
        jsonGenerator.writeString(object.toString());
        jsonGenerator.writeEndObject();
        file.write("\n");
        jsonGenerator.close();
    }*/
    public static ArrayList<String> getParamsToChange(int index) {

        Soldier s = arrSoldiers.get(index);
        ArrayList<String> arr = new ArrayList<>();
        arr.add(s.getStringX());
        arr.add(s.getStrY());

        return arr;
    }
    public static ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        arrSoldiers.stream().map(Soldier::toString).forEach(names::add); //method reference, forEach() Stream API
        return names;
    }

    public static void changeSoldier(int soldierIndex, String sX, String sY) {
        Soldier s = arrSoldiers.get(soldierIndex);
        s.setX(sX);
        s.setY(sY);
    }
    public static void createMf(int soldierIndex) {
        Soldier s = arrSoldiers.get(soldierIndex);
        s.createMonstrFirst();
    }
    public static void createNewSoldier(String sX, String sY, int choice) {
        double x;
        try {
            x = Double.parseDouble(sX);
        } catch (Exception e) {
            x = 0.0;
        }

        double y;
        try {
            y = Double.parseDouble(sY);
        } catch (Exception e) {
            y = 0.0;
        }
        if (choice == 1){
            Soldier s = new Soldier(x, y, 3, 0);
            arrSoldiers.add(s);}
        else if(choice == 2){
            Sergeant serg = new Sergeant(x, y, 3, 0);
            arrSergeants.add(serg);}
        else if(choice == 3){
            Officer o = new Officer(x, y, 3, 0);
            arrOfficers.add(o);}
    }
    private ImageView generatePatron() {

        ImageView viewPatron = new ImageView(imgPatron);
        group.getChildren().add(viewPatron);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GREY);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);
        dropShadow.setSpread(0.7);
        viewPatron.setEffect(dropShadow);

        return viewPatron;
    }
    public void generateBases(){
        bases.add(new Base(320, 750));
        bases.add(new Base(450, 500));
        bases.add(new Base(500, 700));
        bases.add(new Base(650, 220));
        bases.add(new Base(700, 550));
        bases.add(new Base(700, 750));
        bases.add(new Base(850, 250));
        bases.add(new Base(930, 550));
        bases.add(new Base(1200, 350));
        bases.add(new Base(1200, 650));
        bases.add(new Base(1300, 1200));
        bases.add(new Base(1500, 450));
        bases.add(new Base(1450, 1000));
        bases.add(new Base(1700, 580));
        bases.add(new Base(1750, 250));
        bases.add(new Base(1750, 970));
        bases.add(new Base(1800, 750));
        bases.add(new Base(2000, 250));
        bases.add(new Base(2000, 530));
        bases.add(new Base(2050, 1050));
        bases.add(new Base(2100, 1350));
        bases.add(new Base(2200, 730));
        bases.add(new Base(2300, 950));
        bases.add(new Base(2450, 550));
        bases.add(new Base(2650, 780));
        bases.add(new Base(2850, 600));
    }
    public void generateBombs(){
        bombs.add(new Bomb(220, 720));
        bombs.add(new Bomb(350, 520));
        bombs.add(new Bomb(600, 730));
        bombs.add(new Bomb(650, 300));
        bombs.add(new Bomb(720, 650));
        bombs.add(new Bomb(900, 650));
        bombs.add(new Bomb(870, 350));
        bombs.add(new Bomb(930, 450));
        bombs.add(new Bomb(1100, 300));
        bombs.add(new Bomb(1200, 750));//10
        bombs.add(new Bomb(1400, 1200));
        bombs.add(new Bomb(1500, 550));
        bombs.add(new Bomb(1600, 900));
        bombs.add(new Bomb(1650, 250));
        bombs.add(new Bomb(1250, 450));
        bombs.add(new Bomb(1850, 970));
        bombs.add(new Bomb(1600, 650));
        bombs.add(new Bomb(1950, 300));
        bombs.add(new Bomb(2150, 570));
        bombs.add(new Bomb(2100, 800));//20
        bombs.add(new Bomb(2200, 1350));
        bombs.add(new Bomb(2300, 1030));
        bombs.add(new Bomb(2400, 900));
        bombs.add(new Bomb(2650, 700));
        bombs.add(new Bomb(2750, 850));
        bombs.add(new Bomb(2780, 600));
        bombs.add(new Bomb(550, 470));
        bombs.add(new Bomb(1100, 400));
        bombs.add(new Bomb(1450, 350));
        bombs.add(new Bomb(2550, 470));//30
        bombs.add(new Bomb(2370, 600));
        bombs.add(new Bomb(950, 250));
        bombs.add(new Bomb(1100, 650));
        bombs.add(new Bomb(1600, 450));
        bombs.add(new Bomb(1800, 150));
        bombs.add(new Bomb(2450, 1050));
        bombs.add(new Bomb(2880, 700));
        bombs.add(new Bomb(1950, 1050));
        bombs.add(new Bomb(2070, 1250));
        bombs.add(new Bomb(2100, 350));//40
        bombs.add(new Bomb(2350, 750));
        bombs.add(new Bomb(1300, 700));
        bombs.add(new Bomb(1400, 900));
        bombs.add(new Bomb(1700, 750));
        bombs.add(new Bomb(1900, 770));
        bombs.add(new Bomb(1950, 470));
        bombs.add(new Bomb(1550, 1050));
    }
    public void createWallpaper() throws FileNotFoundException {
        wp = new Map();
    }
    private void moveImage(ImageView imageView, double x, double y) {
        imageView.setX(x-dogX);
        imageView.setY(y-dogY);
        //System.out.println(imageView.getX()+"///"+imageView.getY());
    }

    @Override
    public void start(Stage stage) throws IOException {

        group = new Group();

        imgPatron = new Image("C:\\Users\\38098\\IdeaProjects\\demo\\src\\main\\resources\\patron.png", 110, 90, false, false);

        createWallpaper();
        generateBases();
        generateBombs();
        u = new Ukrshtab();
        //json(u);
        o = new Orcshtab();
        //json(o);
        navigationButton = new NavigationButton();
        searchButton = new SearchButton();
        countButton = new CountButton();
        sortButton = new SortButton();

        scene = new Scene(group);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double adjustedX = mouseEvent.getX() - group.getLayoutX();
                double adjustedY = mouseEvent.getY() - group.getLayoutY();

                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    ChooseOrc.display(adjustedX, adjustedY);
                } else if (mouseEvent.isControlDown()) {
                    System.out.println("Mouse clicked! " + adjustedX + ", " + adjustedY);
                    if (navigationButton.isClicked(adjustedX, adjustedY)) {
                        navigationButton.display();
                        System.out.println("Mouse clicked!");
                    } else if (searchButton.isClicked(adjustedX, adjustedY)) {
                        searchButton.display();
                    } else if (countButton.isClicked(adjustedX, adjustedY)) {
                        countButton.displayCriterias();
                    } else if (sortButton.isClicked(adjustedX, adjustedY)) {
                        sortButton.display();
                    }
                }

                boolean flg = false;
                for (Soldier s : Stream.concat(Stream.concat(arrSoldiers.stream(), arrSergeants.stream()), arrOfficers.stream())
                        .collect(Collectors.toList())) {
                    if (s.mouseActivate(adjustedX, adjustedY)) {
                        flg = true;
                        break; // No need to continue the loop if a soldier is found
                    }
                }

                if (!flg) {
                    SetParametrs.display(adjustedX, adjustedY);
                }
            }
        });

        /*scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    ChooseOrc.display(mouseEvent.getX()-group.getTranslateX(), mouseEvent.getY() - group.getTranslateY());
                } else if (mouseEvent.isControlDown()) {
                    System.out.println("Mouse clicked!"+ mouseEvent.getX() + mouseEvent.getY());
                        if (navigationButton.isClicked(mouseEvent.getX()-group.getTranslateX(), mouseEvent.getY()-group.getTranslateY())) {
                            navigationButton.display();
                            System.out.println("Mouse clicked!");
                        } else if (searchButton.isClicked(mouseEvent.getX(), mouseEvent.getY())) {
                            searchButton.display();
                        } else if (countButton.isClicked(mouseEvent.getX(), mouseEvent.getY())) {
                            countButton.displayCriterias();
                        } else if (sortButton.isClicked(mouseEvent.getX(), mouseEvent.getY())) {
                            sortButton.display();
                        } else {


                    }
                }
                boolean flg = false;
                for (Soldier s : Stream.concat(Stream.concat(arrSoldiers.stream(), arrSergeants.stream()), arrOfficers.stream())
                        .collect(Collectors.toList())) {
                    if (s.mouseActivate(mouseEvent.getX() - group.getTranslateX(), mouseEvent.getY() - group.getTranslateY())) flg = true;
                }

                if (!flg) {
                    SetParametrs.display(mouseEvent.getX()-group.getTranslateX(), mouseEvent.getY()-group.getTranslateY());
                }//create soldier
            }
        });*/

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                for (Soldier s : Stream.concat(Stream.concat(arrSoldiers.stream(), arrSergeants.stream()), arrOfficers.stream())
                        .collect(Collectors.toList())) {

                    if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                        if (s.getActive()) {
                            s.deleteSoldier();
                           /* try {
                                json(s);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }*/
                        }
                    } else if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
                        if (s.getActive()) {
                            s.escapeSoldier();
                            /*try {
                                json(s);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }*/
                        }

                    }else if (keyEvent.getCode().equals(KeyCode.L)) {
                       Monstr.display();

                    } else if (keyEvent.getCode().equals(KeyCode.C)) {
                        if (s.getActive()) {
                            try {
                                Soldier clonedSoldier = s.clone();
                                clonedArr.add(clonedSoldier);
                                /*try {
                                    json(s);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }*/
                            } catch (CloneNotSupportedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        arrSoldiers.addAll(clonedArr);
                        clonedArr.removeAll(clonedArr);
                    } else if (keyEvent.getCode().equals(KeyCode.M)) {
                        if (s.getActive()) {
                            changeCopy.display(s);
                               /* try {
                                    json(s);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }*/
                        }
                    } else if (keyEvent.getCode().equals(KeyCode.P)) {
                        System.out.println("Interface Comparable:\nThe array of soldiers before sorting by x-coordinate: " + arrSoldiers);
                        Collections.sort(arrSoldiers);
                        System.out.println("The array of soldiers after sorting by x-coordinate: " + arrSoldiers);
                    }
                }

                boolean flg = false;
                double dx = 0.0;
                double dy = 0.0;

                if (keyEvent.getCode().equals(KeyCode.W)) {
                    dy = -10.0;
                    flg = true;
                }
                if (keyEvent.getCode().equals(KeyCode.A)) {
                    dx = -10.0;
                    flg = true;
                }
                if (keyEvent.getCode().equals(KeyCode.S)) {
                    dy = 10.0;
                    flg = true;
                }
                if (keyEvent.getCode().equals(KeyCode.D)) {
                    dx = 10.0;
                    flg = true;
                }


                if (flg) {
                    for (Soldier s : Stream.concat(Stream.concat(arrSoldiers.stream(), arrSergeants.stream()), arrOfficers.stream())
                            .collect(Collectors.toList())) {
                        if (s.getActive()) {
                            s.move(dx, dy);
                        }
                    }

                }
                dx=100;
                if (keyEvent.getCode().equals(KeyCode.RIGHT) && group.getLayoutX()-dx>-1000) {
                    group.setLayoutX(group.getLayoutX() - dx);
                    dogX-=dx;
                }
                if (keyEvent.getCode().equals(KeyCode.LEFT)&& group.getLayoutX()+dx<1000) {
                    group.setLayoutX(group.getLayoutX() + dx);
                    dogX+=dx;
                }
                if (keyEvent.getCode().equals(KeyCode.DOWN) && group.getLayoutY()-dx>-700) {
                    group.setLayoutY(group.getLayoutY() - dx);
                    dogY-=dx;
                }
                if (keyEvent.getCode().equals(KeyCode.UP)&& group.getLayoutY()+dx<400) {
                    group.setLayoutY(group.getLayoutY() + dx);
                    dogY+=dx;
                }
                if(keyEvent.getCode().equals(KeyCode.HOME)) {
                    System.exit(0);
                }

                if(keyEvent.getCode().equals(KeyCode.K)) {
                    countButton.displayCriterias();
                }
               if (keyEvent.getCode().equals(KeyCode.J)) {
                    SetParametrs.display(0.0, 0.0);
                }

                /*switch (keyEvent.getCode()) {
                    case UP:
                        group.setLayoutY(group.getLayoutY() + 15);
                        System.out.println();
                        break;
                    case DOWN:
                        group.setLayoutY(group.getLayoutY() - 15);
                        break;

                }*/
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                for (Soldier s : Stream.concat(Stream.concat(arrSoldiers.stream(), arrSergeants.stream()), arrOfficers.stream())
                        .collect(Collectors.toList())) {

                    if (s.getActive() && !s.checkUkrshtabInteraction() && !s.checkOrcshtabInteraction()) {
                        ukrProcessed.remove(s);
                        u.setProcessed();
                    }
                    else if (s.getActive() && s.getState() == Soldier.State.Alive){
                        s.ukrshtabInteraction();
                    }
                    else if (!s.getActive() && s.getState() == Soldier.State.Neutralized ){
                        s.bounceMove();
                        ukrProcessed.remove(s);
                        u.setProcessed();
                    }
                    else if (s.getState() == Soldier.State.Died){
                        ukrProcessed.remove(s);
                        orcProcessed.remove(s);
                        o.setProcessed();
                        u.setProcessed();
                    }

                    s.reduceHearts();//dynamic polymorphism

                    if (!s.getActive() && s.getState() == Soldier.State.MonstrFirst && Main.arrNeutralized.size()==0) {
                        s.bounceMove();
                    }
                    else if (!s.getActive() && s.getState() == Soldier.State.MonstrFirst && Main.arrNeutralized.size()>0){
                        s.move(0,0);
                        s.eatNeutralized();

                    }

                    if (s instanceof Soldier && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary() < 1) {
                        s.bounceMove();
                        s.getPaid();
                        orcProcessed.remove(s);
                        ukrProcessed.remove(s);
                        o.setProcessed();
                        u.setProcessed();
                    }
                    else if (s instanceof Soldier && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary()==1){
                        s.move(0,0);
                        s.orcshtabInteraction();
                    }

                    if (s instanceof Sergeant && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary() < 2) {
                        s.bounceMove();
                        s.getPaid();
                        orcProcessed.remove(s);
                        ukrProcessed.remove(s);
                        o.setProcessed();
                        u.setProcessed();
                    }
                    else if (s instanceof Sergeant && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary()==2){
                        s.move(0,0);
                        s.orcshtabInteraction();
                    }
                    if (s instanceof Officer && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary() < 3) {
                        s.bounceMove();
                        s.getPaid();
                        orcProcessed.remove(s);
                        ukrProcessed.remove(s);
                        o.setProcessed();
                        u.setProcessed();
                    }
                    else if (s instanceof Officer && !s.getActive() && s.getState() == Soldier.State.Alive && s.getSalary()==3){
                        s.move(0,0);
                        s.orcshtabInteraction();
                    }

                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        ImageView imgPatron = generatePatron();
        scene.setOnMouseMoved(event -> {
            double x = event.getX();
            double y = event.getY();
            moveImage(imgPatron, x, y);
        });

        stage.setTitle("ORKOSTAN");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}