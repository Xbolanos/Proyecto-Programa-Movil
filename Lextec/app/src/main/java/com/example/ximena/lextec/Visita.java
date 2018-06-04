package com.example.ximena.lextec;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Ximena on 3/6/2018.
 */

public class Visita {
    public String idVisita;
    public String nombreExperimento;
    public String nombreUsuario;
    public Date dia;

    public String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Visita(String idVisita, String nombreExperimento, String nombreUsuario, Date dia) {
        this.idVisita = idVisita;
        this.nombreExperimento = nombreExperimento;
        this.nombreUsuario = nombreUsuario;
        this.dia = dia;

    }

    public String getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(String idVisita) {
        this.idVisita = idVisita;
    }

    public String getNombreExperimento() {
        return nombreExperimento;
    }

    public void setNombreExperimento(String nombreExperimento) {
        this.nombreExperimento = nombreExperimento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

}
