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
    public String dia;
    public String email;
    public String telefono;

    public String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public  Visita(){

    }

    public Visita(String idVisita, String nombreExperimento, String nombreUsuario, String dia,String email, String telefono) {
        this.idVisita = idVisita;
        this.nombreExperimento = nombreExperimento;
        this.nombreUsuario = nombreUsuario;
        this.dia = dia;
        this.email = email;
        this.telefono = telefono;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

}
