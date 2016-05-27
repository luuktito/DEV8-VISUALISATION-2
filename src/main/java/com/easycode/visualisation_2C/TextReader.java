/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2C;

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
        FileReader reader = new FileReader("studentcijfers.txt");
        BufferedReader buffer = new BufferedReader(reader);
                
        String line;
        buffer.readLine();
        buffer.readLine();
        while ((line = buffer.readLine()) != null) {
            String[] lineSplit = line.split("\\t");
            
            int stnr = Integer.parseInt(lineSplit[0]);
            int lftd = Integer.parseInt(lineSplit[1]);
            Float ANA = Float.parseFloat(lineSplit[2].replace(',', '.'));
            Float DEV = Float.parseFloat(lineSplit[3].replace(',', '.'));
            Float PRJ = Float.parseFloat(lineSplit[4].replace(',', '.'));
            Float SKL = Float.parseFloat(lineSplit[5].replace(',', '.'));
            
            System.out.println(stnr + ", " + lftd + ", " + ANA + ", " + DEV + ", " + PRJ  + ", " + SKL);
            PlotData PD = new PlotData(stnr, lftd, ANA, DEV, PRJ, SKL);
            returnList.add(PD);
        }
        return returnList;
    }
}
