package br.rv.buscacao.modelos.gps;


import java.io.Serializable;

public class Local  implements Serializable {
    private String lat;
    private String lng;
    public Local() {
        super();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
