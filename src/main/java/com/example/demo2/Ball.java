package com.example.demo2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle{
    protected double dx = 1;
    protected double dy = 1;

    public Ball(double x, double y, double radius, Color color) {
        super(x, y, radius);
        setFill(color); // Set ball color
    }
}
