/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.mendrika.tpbanquemendrika.jsf;

import fr.mendrika.tpbanquemendrika.entity.CompteBancaire;
import fr.mendrika.tpbanquemendrika.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author Mendrika Fitahiana
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {
    
    private long idSource;
    private long idDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
    public long getIdSource() {
        return idSource;
    }

    public void setIdSource(long idSource) {
        this.idSource = idSource;
    }

    public long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of ListeComptes
     */
    public Transfert() {
    }
    
    public String transfererArgent(){
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        gestionnaireCompte.transfererArgent(source, destination, montant);
        return "listeComptes?faces-redirect=true";
    }
}
