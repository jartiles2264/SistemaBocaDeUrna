package ec.edu.pucem.BocaUrna.dominio;

import java.io.Serializable;

public class Prefecto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String partido;
    private String provincia;
    private int votos;

    public Prefecto(String nombre, String partido, String provincia) {
        this.nombre = nombre;
        this.partido = partido;
        this.provincia = provincia;
        this.votos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPartido() {
        return partido;
    }

    public String getProvincia() {
        return provincia;
    }

    public int getVotos() {
        return votos;
    }

    public void incrementarVotos() {
        this.votos++;
    }

    @Override
    public String toString() {
        return nombre + " (" + partido + ")";
    }
}
