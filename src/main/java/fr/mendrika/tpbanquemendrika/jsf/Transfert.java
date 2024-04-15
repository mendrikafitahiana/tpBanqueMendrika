/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.mendrika.tpbanquemendrika.jsf;

import fr.mendrika.tpbanquemendrika.entity.CompteBancaire;
import fr.mendrika.tpbanquemendrika.service.GestionnaireCompte;
import fr.mendrika.tpbanquemendrika.util.Util;
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
        boolean erreur = false;
        CompteBancaire source = gestionnaireCompte.findById(idSource);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant !", "Solde insuffisant !", "form:montant");
                erreur = true;
            }
        }
        CompteBancaire destination = gestionnaireCompte.findById(idDestination);
        if (destination == null) {
            Util.messageErreur("Aucun compte avec cet id!", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }
        
        if (montant <= 0) {
            Util.messageErreur("Veuillez insérez un montant positif !", "Veuillez insérez un montant positif !", "form:montant");
            erreur = true;
        }

        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.transfererArgent(source, destination, montant);
        Util.addFlashInfoMessage("Transfert de "+montant+" Ar depuis "+source.getNom()+" vers "+destination.getNom()+" réussi!");
        return "listeComptes?faces-redirect=true";
    }
}
