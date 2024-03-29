package com.example.wikway3;
/*
    This class represents Job
 */
public class JobAd {
    //TODO(1) MAKE THIS
    private String title;
    private String imageLink;
    private String bundesland;
    private String ort;
    private String anschreiben;
    private String abteilung;
    private String qualifizirung;
    private String srasse;
    private String anforderung;
    private String deadline;
    private String artDerStelle;
    private String email;

    private boolean fav;

    public JobAd() {
    }

    public JobAd(String title, String imageLink,
                 String bundesland, String ort,
                 String anschreiben, String abteilung,
                 String qualifizirung, String srasse,
                 String anforderung, String deadline,
                 String artDerStelle, String email){
        this.title=title;
        this.imageLink = imageLink;
        this.bundesland = bundesland;
        this.ort = ort;
        this.anschreiben = anschreiben;
        this.abteilung = abteilung;
        this.qualifizirung = qualifizirung;
        this.srasse = srasse;
        this.anforderung = anforderung;
        this.deadline = deadline;
        this.artDerStelle = artDerStelle;
        this.email = email;
        fav = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getAnschreiben() {
        return anschreiben;
    }

    public void setAnschreiben(String anschreiben) {
        this.anschreiben = anschreiben;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getQualifizirung() {
        return qualifizirung;
    }

    public void setQualifizirung(String qualifizirung) {
        this.qualifizirung = qualifizirung;
    }

    public String getSrasse() {
        return srasse;
    }

    public void setSrasse(String srasse) {
        this.srasse = srasse;
    }

    public String getAnforderung() {
        return anforderung;
    }

    public void setAnforderung(String anforderung) {
        this.anforderung = anforderung;

    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getArtDerStelle() {
        return artDerStelle;
    }

    public void setArtDerStelle(String artDerStelle) {
        this.artDerStelle = artDerStelle;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
