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
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private Long id;
    private String nom;
    private CompteBancaire compte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public Modification() {
    }

    public void loadCompte() {
        if (compte == null) {
            this.compte = gestionnaireCompte.findById(id);
        }
    }

    public String modifierNomCompte() {
        String message = "Nom ("+compte.getNom()+") changé en ("+nom+")";
        compte.setNom(nom);
        gestionnaireCompte.updateCompte(compte);
        Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }
}
