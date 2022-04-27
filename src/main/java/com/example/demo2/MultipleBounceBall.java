package com.example.demo2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MultipleBounceBall extends Application implements Runnable {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
       MultipleBallPane ballPane = new MultipleBallPane();
        //BallPane2 ballPane = new BallPane2();
        ballPane.setStyle("-fx-border-color: black");

        Button btAdd = new Button("+");
        Button print = new Button("print");
        Button btSubtract = new Button("-");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btAdd, btSubtract,print);
        hBox.setAlignment(Pos.CENTER);

        // Add or remove a ball

        Thread ballThread = new Thread(){
            public void run() {
                btAdd.setOnAction(e -> ballPane.add());
                btSubtract.setOnAction(e -> ballPane.subtract());
                System.out.println("New ball thread");
            }
        };
        print.setOnAction(e -> ballPane.print());

        // Pause and resume animation
        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());

        // Use a scroll bar to control animation speed
        ScrollBar sbSpeed = new ScrollBar();
        sbSpeed.setMax(20);
        sbSpeed.setValue(10);
        ballPane.rateProperty().bind(sbSpeed.valueProperty());

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setTop(sbSpeed);
        pane.setBottom(hBox);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 550, 450);
        primaryStage.setTitle("Multiple Bounce Ball"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */

    //make shore when the ball is meet each other bounce back

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void run() {

    }
}