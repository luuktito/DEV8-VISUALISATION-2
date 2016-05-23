/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Luuk
 */
public class TextReader {
    
    
     public ArrayList<PlotData> createScatterPlotList() throws IOException {
        ArrayList<PlotData> returnList = new ArrayList<>();
        FileReader reader = new FileReader("scatterplot.txt");
        BufferedReader buffer = new BufferedReader(reader);
                
        String line;
        buffer.readLine();
        while ((line = buffer.readLine()) != null) {
            String[] lineSplit = line.split("\\t");
            
            Float CAT = Float.parseFloat(lineSplit[0]);
            Float EIG1 = Float.parseFloat(lineSplit[1]);
            Float EIG2 = Float.parseFloat(lineSplit[2].replace(',', '.'));
            
            System.out.println(CAT + ", " + EIG1 + ", " + EIG2);
            PlotData PD = new PlotData(CAT, EIG1, EIG2);
            returnList.add(PD);
        }
        return returnList;
    }
}
