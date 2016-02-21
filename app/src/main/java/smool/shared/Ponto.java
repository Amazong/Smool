package smool.shared;

/**
 * Created by Vian on 19/02/2016.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Ponto implements Serializable {
    private int x,y;
    private String name;
    private ArrayList <Ponto> pontosamigos = new ArrayList();

    public Ponto(String name,int x,int y) {
        this.y = y;
        this.name = name;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addAmigo(ArrayList <Ponto> X){
        this.pontosamigos = X;
    }

    public String mostraAmigos(){
        String aux = new String("");
        int length = this.pontosamigos.size();
        for (int i = 0; i < length; i++){
            aux += this.pontosamigos.get(i);
        }
        return aux;
    }

    @Override
    public String toString() {
        return "Ponto{" +
                "y=" + y +
                ", name='" + name + '\'' +
                ", x=" + x +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
