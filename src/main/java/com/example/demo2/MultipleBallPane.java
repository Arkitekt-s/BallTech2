package com.example.demo2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class MultipleBallPane extends Pane {
    private Timeline animation;

    public MultipleBallPane() {
        // Create an animation for moving the ball
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
    }
    //make shore when the ball is meet each other bounce back


    public void add() {
        Color color = new Color(Math.random(),
                Math.random(), Math.random(), 0.5);
        getChildren().add(new Ball(30, 30, 20, color));



    }

    public void subtract() {
        if (getChildren().size() > 0) {
            getChildren().remove(getChildren().size() - 1);
        }
    }


    public void play() {
        animation.play();
    }

    public void pause() {
        animation.pause();
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    public void moveBall() {
        for (Node node: this.getChildren()) {
            Ball ball = (Ball)node;
            // Check boundaries
            if (ball.getCenterX() < ball.getRadius() ||
                    ball.getCenterX() > getWidth() - ball.getRadius()) {
                ball.dx *= -1; // Change ball move direction
            }
            if (ball.getCenterY() < ball.getRadius() ||
                    ball.getCenterY() > getHeight() - ball.getRadius()) {
                ball.dy *= -1;// Change ball move direction
            }

            // Adjust ball position
            ball.setCenterX(ball.dx + ball.getCenterX());
            ball.setCenterY(ball.dy + ball.getCenterY());
            //make shore when the ball is meet each other bounce back
            for (Node node2: this.getChildren()) {
                Ball ball2 = (Ball)node2;
                if (ball != ball2) {
                    if (ball.getBoundsInParent().intersects(ball2.getBoundsInParent())) {
                        ball.dx *= -1;
                        ball.dy *= -1;
                        ball2.dx *= -1;
                        ball2.dy *= -1;

                    }
//no balls should be in the same place
                    if (ball.getCenterX() == ball2.getCenterX() &&
                            ball.getCenterY() == ball2.getCenterY()) {
                        ball.dx *= -1;
                        ball.dy *= -1;
                        ball2.dx *= -1;
                        ball2.dy *= -1;

                    }

                }
            }
        }
    }
}