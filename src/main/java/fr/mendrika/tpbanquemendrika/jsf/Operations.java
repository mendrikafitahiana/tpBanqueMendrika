/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package fr.mendrika.tpbanquemendrika.jsf;

import fr.mendrika.tpbanquemendrika.entity.CompteBancaire;
import fr.mendrika.tpbanquemendrika.entity.OperationBancaire;
import fr.mendrika.tpbanquemendrika.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mendrika Fitahiana
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long id;
    private CompteBancaire compte;

    @Inject
    GestionnaireCompte gestionnaireCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * Creates a new instance of Mouvement
     */
    public Operations() {
    }

    public void loadCompte() {
        if (compte == null) {
            this.compte = gestionnaireCompte.findById(id);
        }
    }
    
    public List<OperationBancaire> getOperations() {
        return compte.getOperations();
    }
}
