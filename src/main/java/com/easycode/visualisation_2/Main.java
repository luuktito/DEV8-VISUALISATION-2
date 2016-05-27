/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2;

import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;
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
    float minValueX;
    float minValueY; 
    float maxValueX; 
    float maxValueY; 

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
    }

    @Override
    public void draw() {
        minValueX = calculateMin("EIG1").getEIG1();
        minValueY = calculateMin("EIG2").getEIG2();
        maxValueX = calculateMax("EIG1").getEIG1();
        maxValueY = calculateMax("EIG2").getEIG2();
        drawLegend();
        drawScatterPlot();
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
        strokeWeight((float) 0.5);
        for (PlotData SP : scatterPlotDataList) {
            float xPos = map(SP.getEIG1(), minValueX, maxValueX, 100, 600);
            float yPos = map(SP.getEIG2(), minValueY, maxValueY, 600, 100);
            int color = 0;
            switch(SP.getCAT()) {
                case 1 :
                    color = color(255, 0, 0);
                    break;
                case 2 :
                    color = color(255, 255, 0);
                    break;
                case 3 :
                    color = color(0, 255, 0);
                    break;
                case 4 :
                    color = color(0, 0, 255);
                    break;
                default:
            }
            fill(color);
            ellipse(xPos, yPos, 8, 8);
        }
    }

    public void drawLegend() {
        float differenceMinMaxX = maxValueX - minValueX;
        float differenceMinMaxY = maxValueY - minValueY;
        
        textAlign(CENTER);
        strokeWeight((float) 1.5);
        fill(255,255,255);
        stroke(5);
        rect(90, 90, 520, 520);
        fill(0,0,0);
        stroke(1);
        textSize(20);
        text("Scatterplot of EIG2 over EIG1", width / 2, height / 10);
        textSize(16);
        text("EIG1", width / 2, 660);
        text("EIG2", 23, height / 2);
        textSize(12);
        
        text("CAT =  1:          2:", 540, 660);
        text("           3:          4:", 540, 680);
        fill(255, 0, 0);
        ellipse(550, 655, 8, 8);
        fill(255, 255, 0);
        ellipse(600, 655, 8, 8);
        fill(0, 255, 0);
        ellipse(550, 675, 8, 8);
        fill(0, 0, 255);
        ellipse(600, 675, 8, 8);
        
        fill(0, 0, 0);
        for (int i = 0; i < 510; i = i+50) {
            line(100 + i, 610, 100 + i, 615);
            text("" + (int) (minValueX + (differenceMinMaxX * ((float)i/500))), 100 + i, 630);
        }
        
        textAlign(RIGHT);
        for (int i = 0; i < 510; i = i+50) {
            line(90, 100 + i, 85, 100 + i);
            text("" + (int) (maxValueY - (differenceMinMaxY * ((float)i/500))), 70, 100 + i);
        }
    }
}