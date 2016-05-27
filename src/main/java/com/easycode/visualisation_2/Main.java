/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;
import static processing.core.PApplet.println;
import static processing.core.PConstants.CENTER;
import processing.core.PImage;

/**
 *
 * @author Luuk
 */
public class Main extends PApplet{

    PImage photo;
    private static ArrayList<PlotData> scatterPlotDataList = new ArrayList<>();
    private static TextReader newTextReader = new TextReader();
//    private static ArrayList<Earthquake> earthquakeList = new ArrayList<>();
//    private static final JSONtoEarthquake newJsontoEarthquake = new JSONtoEarthquake();
//    JsonObject earthquakes;
//    JsonArray values;

    public static void main(String[] args) {
        try {
            scatterPlotDataList = newTextReader.createScatterPlotList();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        PApplet.main( new String[]{"com.easycode.visualisation_2.Main"} ); 
    }
    
    @Override
    public void settings() {
        size(700, 700);
    }
    
    @Override
    public void setup(){
        noLoop();
        background(255,255,255);
        //photo = loadImage("IcelandMap.png", "png");
        //image(photo, 100,100,1000,812);
        
        //drawScatterPlot();
        //drawLegend();
        //drawEarthquakes();
    }

    @Override
    public void draw() {
        drawLegend();
        drawScatterPlot();
        //setGradient(900, 950, 200, 10);
    }
    
    public PlotData calculateMin(String type) {
        PlotData minPlotValue = null;
        if (type.equals("EIG1")) {
            for (PlotData SP : scatterPlotDataList) {
                minPlotValue = (minPlotValue == null || SP.getEIG1() < minPlotValue.getEIG1()) ? SP:minPlotValue;
            }
        }
        else {
            for (PlotData SP : scatterPlotDataList) {
                minPlotValue = (minPlotValue == null || SP.getEIG2() < minPlotValue.getEIG2()) ? SP:minPlotValue;
            }            
        }
        return minPlotValue;
    }
    
    public PlotData calculateMax(String type) {
        PlotData maxPlotValue = null;
        if (type.equals("EIG1")) {
            for (PlotData SP : scatterPlotDataList) {
                maxPlotValue = (maxPlotValue == null || SP.getEIG1() > maxPlotValue.getEIG1()) ? SP:maxPlotValue;
            }
        }
        else {
            for (PlotData SP : scatterPlotDataList) {
                maxPlotValue = (maxPlotValue == null || SP.getEIG2() > maxPlotValue.getEIG2()) ? SP:maxPlotValue;
            }            
        }
        return maxPlotValue;
    }
    
    public void drawScatterPlot() {
        for (PlotData SP : scatterPlotDataList) {
            float maxValueX = calculateMax("EIG1").getEIG1();
            float maxValueY = calculateMax("EIG2").getEIG2();
            
            float xPos = map(SP.getEIG1(), 0, maxValueX, 100, 600);
            float yPos = map(SP.getEIG2(), 0, maxValueY, 600, 100);
            
            ellipse(xPos, yPos, 5, 5);
            
//            int c = color(255, (255 - ((EQ.getSize()+1)*85)), 0);
//            fill(c);
//            ellipse(((27+EQ.getLongitude())*74),(((68-EQ.getLatitude())*180)), (EQ.getDepth() * 2), (EQ.getDepth() * 2));
//            
//            println("Latitude: " + EQ.getLatitude() + ", Longitude: " + EQ.getLongitude() + ", Depth: " + EQ.getDepth() + ", Size: " + EQ.getSize());
        }
    }

    public void drawLegend() {
        strokeWeight((float) 1.5);
        fill(255,255,255);
        stroke(5);
        rect(90, 90, 520, 520);
        fill(0,0,0);
        stroke(1);
//        stroke(4);
//        textAlign(CENTER);
//        textSize(14);
//        fill(1);
//        text("Scale of Richter", 955, 940);
//        textSize(12);
//        text("-1              0               1               2", 1000, 975);
//        textSize(20);
//        text("Earthquakes Iceland from " + earthquakeList.get(earthquakeList.size() -1).getTimestamp()+ " to " + earthquakeList.get(0).getTimestamp(), 600, 50);
//        textSize(14);
//        text("From vedur.is", 600, 80);
//
//        strokeWeight((float) 1.0);
//        fill(255,255,255);
//        rect(750, 950, 50, 10);
//        fill(0,0,0);
//        rect(800, 950, 50, 10);
//        line(750, 950, 750, 965);
//        line(850, 950, 850, 965);
//
//        textSize(12);
//        text("0                       25 km", 815, 975);
//        textSize(14);
//        text("Depth in KM", 791, 940);
    }
//    
//    public void setGradient(int x, int y, float w, float h) {
//        for (int i = x; i <= x+w; i+=1) {
//          float inter = map(i, x, x+w, 0, 1);
//          int c = lerpColor(color(255, 255, 0), color(255, 0, 0), inter);
//          stroke(c);
//          line(i, y, i, y+h);
//        }
//    }
}