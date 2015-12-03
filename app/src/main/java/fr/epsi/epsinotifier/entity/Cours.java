package fr.epsi.epsinotifier.entity;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Cours implements Serializable {
    private DateTime horaireDebut;
    private DateTime horaireFin;
    private String matiere;
    private String prof;
    private String salle;

    public DateTime getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(DateTime horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public DateTime getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(DateTime horaireFin) {
        this.horaireFin = horaireFin;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
