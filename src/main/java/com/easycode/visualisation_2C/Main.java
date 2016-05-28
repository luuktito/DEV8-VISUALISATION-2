/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2C;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
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
    AxisValues axisValuesLFTD;
    AxisValues axisValuesANA;
    AxisValues axisValuesDEV;
    AxisValues axisValuesPRJ;
    AxisValues axisValuesSKL;
    List<List<Float>> plotValues = new ArrayList<List<Float>>();

    
    public static void main(String[] args) {
        try {
            scatterPlotDataList = newTextReader.createScatterPlotList();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        PApplet.main( new String[]{"com.easycode.visualisation_2C.Main"} ); 
    }
    
    @Override
    public void settings() {
        size(1030, 1030);
    }
    
    @Override
    public void setup(){
        noLoop();
        background(255,255,255);
        calculateMinMax();
        calculateMapCoordinates();
    }

    @Override
    public void draw() {
        drawScatterPlot();
        drawLegend();
    }
    
    public void calculateMinMax() {
        PlotData minPlotValueLftd = null;
        PlotData minPlotValueANA = null;
        PlotData minPlotValueDEV = null;
        PlotData minPlotValuePRJ = null;
        PlotData minPlotValueSKL = null;
        PlotData maxPlotValueLftd = null;
        PlotData maxPlotValueANA = null;
        PlotData maxPlotValueDEV = null;
        PlotData maxPlotValuePRJ = null;
        PlotData maxPlotValueSKL = null;
        
        for (PlotData SP : scatterPlotDataList) {
            minPlotValueLftd = (minPlotValueLftd == null || SP.getLftd() < minPlotValueLftd.getLftd()) ? SP:minPlotValueLftd;
            minPlotValueANA = (minPlotValueANA == null || SP.getANA() < minPlotValueANA.getANA()) ? SP:minPlotValueANA;
            minPlotValueDEV = (minPlotValueDEV == null || SP.getDEV() < minPlotValueDEV.getDEV()) ? SP:minPlotValueDEV;
            minPlotValuePRJ = (minPlotValuePRJ == null || SP.getPRJ() < minPlotValuePRJ.getPRJ()) ? SP:minPlotValuePRJ;
            minPlotValueSKL = (minPlotValueSKL == null || SP.getSKL() < minPlotValueSKL.getSKL()) ? SP:minPlotValueSKL;
   
            maxPlotValueLftd = (maxPlotValueLftd == null || SP.getLftd() > maxPlotValueLftd.getLftd()) ? SP:maxPlotValueLftd;
            maxPlotValueANA = (maxPlotValueANA == null || SP.getANA() > maxPlotValueANA.getANA()) ? SP:maxPlotValueANA;
            maxPlotValueDEV = (maxPlotValueDEV == null || SP.getDEV() > maxPlotValueDEV.getDEV()) ? SP:maxPlotValueDEV;
            maxPlotValuePRJ = (maxPlotValuePRJ == null || SP.getPRJ() > maxPlotValuePRJ.getPRJ()) ? SP:maxPlotValuePRJ;
            maxPlotValueSKL = (maxPlotValueSKL == null || SP.getSKL() > maxPlotValueSKL.getSKL()) ? SP:maxPlotValueSKL;
        }
        
        axisValuesLFTD = new AxisValues(minPlotValueLftd.getLftd(), maxPlotValueLftd.getLftd());
        axisValuesANA = new AxisValues(minPlotValueANA.getANA(), maxPlotValueANA.getANA());
        axisValuesDEV = new AxisValues(minPlotValueDEV.getDEV(), maxPlotValueDEV.getDEV());
        axisValuesPRJ = new AxisValues(minPlotValuePRJ.getPRJ(), maxPlotValuePRJ.getPRJ());
        axisValuesSKL = new AxisValues(minPlotValueSKL.getSKL(), maxPlotValueSKL.getSKL());
    }

    public void calculateMapCoordinates() {
        for (int i = 0; i < 5; i++) {
            plotValues.add(new ArrayList<Float>());
        }
        
        for (PlotData SP : scatterPlotDataList) {
            plotValues.get(0).add(map(SP.getLftd(), axisValuesLFTD.getMinValue(), axisValuesLFTD.getMaxValue(), 100, 250));
            plotValues.get(1).add(map(SP.getANA(), axisValuesANA.getMinValue(), axisValuesANA.getMaxValue(), 100, 250));
            plotValues.get(2).add(map(SP.getDEV(), axisValuesDEV.getMinValue(), axisValuesDEV.getMaxValue(), 100, 250));
            plotValues.get(3).add(map(SP.getPRJ(), axisValuesPRJ.getMinValue(), axisValuesPRJ.getMaxValue(), 100, 250));
            plotValues.get(4).add(map(SP.getSKL(), axisValuesSKL.getMinValue(), axisValuesSKL.getMaxValue(), 100, 250));
        }
    }
    public void drawScatterPlot() {
        rectMode(CENTER);      
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                noFill();
                strokeWeight((float) 1.0);
                rect((float) (170 * i) + 175, (float) (170 * j) + 175, 160, 160);
                strokeWeight((float) 0.1);
                fill(0);
                if (i != j) {
                    for (int k = 0; k < plotValues.get(0).size(); k++) {
                        ellipse(plotValues.get(i).get(k) + (float) (170 * i), (150 - plotValues.get(j).get(k)) + (float) (170 * j) + 200, 3, 3);    
                    }
                }
            }
        }
    }

    public void drawLegend() {
        textSize(24);
        textAlign(CENTER);
        text("lftd", 175, 185);
        text("ANA", 345, 355);
        text("DEV", 515, 525);
        text("PRJ", 685, 695);
        text("SKL", 855, 865);
    }
}