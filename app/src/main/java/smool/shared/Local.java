package smool.shared;
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Margarida
 */
public class Local {
    protected double longitude,latitude;
    protected long horas;

    public Local(){}

    public Local(double longitude, double latitude, long horas) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.horas = horas;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getHoras() {
        return horas;
    }

    public void setHoras(long horas) {
        this.horas = horas;
    }
}
