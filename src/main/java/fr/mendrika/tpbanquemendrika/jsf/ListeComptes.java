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
import java.util.List;

/**
 *
 * @author Mendrika Fitahiana
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private List<CompteBancaire> compteBancaireList;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        if (compteBancaireList == null) {
            compteBancaireList = gestionnaireCompte.getAllComptes();
        }
        return compteBancaireList;
    }
    
    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
