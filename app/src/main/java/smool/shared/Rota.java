/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smool.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Margarida
 */

public class Rota implements Serializable {

    protected int userid;
    protected ArrayList <Local> locais = new ArrayList<>();
    protected int dia;
    protected Date horas;
    protected int probabilidade = 0;

    public Rota(){}

    public int getUserid() {
        return userid;
    }

    public ArrayList<Local> getLocais() {
        return locais;
    }

    public int getDia() {
        return dia;
    }

    public Date getHoras() {
        return horas;
    }

    public int getProbabilidade() {
        return probabilidade;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setLocais(ArrayList<Local> locais) {
        this.locais = locais;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setHoras(Date horas) {
        this.horas = horas;
    }

    public void setProbabilidade(int probabilidade) {
        this.probabilidade = probabilidade;
    }

    public void adicionalocal(Local l){
        locais.add(l);
    }
}
