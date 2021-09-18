package com.example.convertrgbtogray;

public class App {
    String color;
    String name;
    String textColor;

    public App(String color, String name, String textColor) {
        this.color = color;
        this.name = name;
        this.textColor = textColor;

         }

    public String getColor(){ return  color;}
    public String getTextColor(){return textColor;}
    public String getName() { return name; }

    }
