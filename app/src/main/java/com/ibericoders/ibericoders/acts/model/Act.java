package com.ibericoders.ibericoders.acts.model;

import java.io.Serializable;


public class Act implements Serializable{

    private String title;
    private String date;
    private String hour;
    private String assitants;
    private String reliefs;
    private String memory;
    private String point;
    private String conclusion;
    private String next;
    private String compromise;
    private String proposals;
    private String evaluation;
    private String nextMeeting;

    public Act(String title, String date, String hour, String assitants, String reliefs, String memory, String points, String conclusion, String next, String compromise, String proposals, String evaluation, String nextMeeting) {
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.assitants = assitants;
        this.reliefs = reliefs;
        this.memory = memory;
        this.point = points;
        this.conclusion = conclusion;
        this.next = next;
        this.compromise = compromise;
        this.proposals = proposals;
        this.evaluation = evaluation;
        this.nextMeeting = nextMeeting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAssitants() {
        return assitants;
    }

    public void setAssitants(String assitants) {
        this.assitants = assitants;
    }

    public String getReliefs() {
        return reliefs;
    }

    public void setReliefs(String reliefs) {
        this.reliefs = reliefs;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCompromise() {
        return compromise;
    }

    public void setCompromise(String compromise) {
        this.compromise = compromise;
    }

    public String getProposals() {
        return proposals;
    }

    public void setProposals(String proposals) {
        this.proposals = proposals;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getNextMeeting() {
        return nextMeeting;
    }

    public void setNextMeeting(String nextMeeting) {
        this.nextMeeting = nextMeeting;
    }

    public String toString() {
        return title +"-" + date + "-"+ hour +"-"+ assitants +"-"+ reliefs +"-"+ memory +"-"+ point
                +"-"+ conclusion+"-"+ next +"-"+ compromise +"-"+ proposals +"-"+ evaluation +"-"+ nextMeeting;
    }
}
