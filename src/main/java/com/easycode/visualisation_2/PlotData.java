/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2;

/**
 *
 * @author Luuk
 */
public class PlotData {
    private int CAT;
    private float EIG1;
    private float EIG2;

    public PlotData(int CAT, float EIG1, float EIG2) {
        this.CAT = CAT;
        this.EIG1 = EIG1;
        this.EIG2 = EIG2;
    }
    
    public PlotData() {
    }

    public int getCAT() {
        return CAT;
    }

    public void setCAT(int CAT) {
        this.CAT = CAT;
    }

    public float getEIG1() {
        return EIG1;
    }

    public void setEIG1(float EIG1) {
        this.EIG1 = EIG1;
    }

    public float getEIG2() {
        return EIG2;
    }

    public void setEIG2(float EIG2) {
        this.EIG2 = EIG2;
    }
}
