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
    float[] lftdValues;
    float[] anaValues;
    float[] devValues;
    float[] prjValues;
    float[] sklValues;
    
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
        size(1200, 1200);
    }
    
    @Override
    public void setup(){
        noLoop();
        background(255,255,255);
        calculateMinMax();
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
        
        System.out.println(minPlotValueLftd.getLftd() + ", " + maxPlotValueLftd.getLftd());
        System.out.println(axisValuesLFTD.getMinValue() + ", " + axisValuesLFTD.getMaxValue());
    }

    public void drawScatterPlot() {
        strokeWeight((float) 0.1);
        for (int i = 0; i < 5; i++) {
            plotValues.add(new ArrayList<Float>());
        }
        for (PlotData SP : scatterPlotDataList) {
            plotValues.get(0).add(map(SP.getLftd(), axisValuesLFTD.getMinValue(), axisValuesLFTD.getMaxValue(), 100, 300));
            plotValues.get(1).add(map(SP.getANA(), axisValuesANA.getMinValue(), axisValuesANA.getMaxValue(), 100, 300));
            plotValues.get(2).add(map(SP.getDEV(), axisValuesDEV.getMinValue(), axisValuesDEV.getMaxValue(), 100, 300));
            plotValues.get(3).add(map(SP.getPRJ(), axisValuesPRJ.getMinValue(), axisValuesPRJ.getMaxValue(), 100, 300));
            plotValues.get(4).add(map(SP.getSKL(), axisValuesSKL.getMinValue(), axisValuesSKL.getMaxValue(), 100, 300));
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != j) {
                    fill(0);
                    for (int k = 0; k < plotValues.get(0).size(); k++) {
                        ellipse(plotValues.get(i).get(k) + (float) (200 * i), (200 - plotValues.get(j).get(k)) + (float) (200 * j) + 200, 4, 4);    
                    }
                }
            }
        }
    }
//
    public void drawLegend() {
        textSize(24);
        textAlign(CENTER);
        text("lftd", 200, 200);
        text("ANA", 400, 400);
        text("DEV", 600, 600);
        text("PRJ", 800, 800);
        text("SKL", 1000, 1000);
    }
}