package com.ibericoders.ibericoders.actas.model;

import java.io.Serializable;

/**
 * Created by Jorge on 15/06/2017.
 */

public class Acta implements Serializable{

    private String titulo;
    private String fecha;
    private String hora;
    private String asistentes;
    private String relevos;
    private String memoria;
    private String puntos;
    private String conclusion;
    private String siguiente;
    private String compromisos;
    private String propuestas;
    private String evaluacion;
    private String proxima;

    public Acta(String titulo, String fecha, String hora, String asistentes, String relevos, String memoria, String puntos, String conclusion, String siguiente, String compromisos, String propuestas, String evaluacion, String proxima) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
        this.relevos = relevos;
        this.memoria = memoria;
        this.puntos = puntos;
        this.conclusion = conclusion;
        this.siguiente = siguiente;
        this.compromisos = compromisos;
        this.propuestas = propuestas;
        this.evaluacion = evaluacion;
        this.proxima = proxima;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(String asistentes) {
        this.asistentes = asistentes;
    }

    public String getRelevos() {
        return relevos;
    }

    public void setRelevos(String relevos) {
        this.relevos = relevos;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getCompromisos() {
        return compromisos;
    }

    public void setCompromisos(String compromisos) {
        this.compromisos = compromisos;
    }

    public String getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(String propuestas) {
        this.propuestas = propuestas;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getProxima() {
        return proxima;
    }

    public void setProxima(String proxima) {
        this.proxima = proxima;
    }

    public String toString() {
        return titulo+"-" + fecha + "-"+ hora+"-"+ asistentes+"-"+relevos+"-"+ memoria+"-"+ puntos
                +"-"+ conclusion+"-"+ siguiente+"-"+ compromisos+"-"+ propuestas+"-"+evaluacion+"-"+ proxima;
    }
}
