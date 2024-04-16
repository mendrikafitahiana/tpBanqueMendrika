/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.mendrika.tpbanquemendrika.jsf;

import fr.mendrika.tpbanquemendrika.entity.CompteBancaire;
import fr.mendrika.tpbanquemendrika.service.GestionnaireCompte;
import fr.mendrika.tpbanquemendrika.util.Util;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;

/**
 *
 * @author Mendrika Fitahiana
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    
    private String nom;
    @PositiveOrZero(message = "Le solde doit être positif ou nul.")
    private int solde;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String getNom() {
        return nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    public String action() {
        CompteBancaire compte = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compte);
        Util.addFlashInfoMessage("Création de compte réussi!");
        return "listeComptes?faces-redirect=true";
    }
}
