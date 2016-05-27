/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easycode.visualisation_2C;

/**
 *
 * @author Luuk
 */
public class PlotData {
    private int stnr;
    private int lftd;
    private float ANA;
    private float DEV;
    private float PRJ;
    private float SKL;

    public int getSTNR() {
        return stnr;
    }

    public void setSTNR(int stnr) {
        this.stnr = stnr;
    }

    public int getLftd() {
        return lftd;
    }

    public void setLftd(int lftd) {
        this.lftd = lftd;
    }

    public float getANA() {
        return ANA;
    }

    public void setANA(float ANA) {
        this.ANA = ANA;
    }

    public float getDEV() {
        return DEV;
    }

    public void setDEV(float DEV) {
        this.DEV = DEV;
    }

    public float getPRJ() {
        return PRJ;
    }

    public void setPRJ(float PRJ) {
        this.PRJ = PRJ;
    }

    public float getSKL() {
        return SKL;
    }

    public void setSKL(float SKL) {
        this.SKL = SKL;
    }

    public PlotData(int stnr, int lftd, float ANA, float DEV, float PRJ, float SKL) {
        this.stnr = stnr;
        this.lftd = lftd;
        this.ANA = ANA;
        this.DEV = DEV;
        this.PRJ = PRJ;
        this.SKL = SKL;
    }
}
