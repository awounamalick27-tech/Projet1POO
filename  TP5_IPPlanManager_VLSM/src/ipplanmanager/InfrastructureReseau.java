package ipplanmanager;

import java.util.ArrayList;

public class InfrastructureReseau {
    private String nom;
    private ArrayList<Equipement> equipements;
    private ArrayList<SousReseau> sousReseaux;

    public InfrastructureReseau(String nom) {
        this.nom = nom;
        equipements = new ArrayList<>();
        sousReseaux = new ArrayList<>();
    }

    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
    }

    public void ajouterSousReseau(SousReseau sousReseau) {
        sousReseaux.add(sousReseau);
    }

    public void afficherEquipements() {
        for (Equipement equipement : equipements) {
            equipement.afficher();
            System.out.println();
        }
    }

    public void afficherSousReseaux() {
        for (SousReseau sousReseau : sousReseaux) {
            sousReseau.afficher();
            System.out.println();
        }
    }

    public void afficher() {
        System.out.println("=== Infrastructure : " + nom + " ===\n");
        System.out.println("--- Sous-reseaux ---");
        afficherSousReseaux();
        System.out.println("--- Equipements ---");
        afficherEquipements();
    }

    // Travail supplementaire : rechercher un equipement par son nom
    public void rechercherEquipement(String nom) {
        for (Equipement equipement : equipements) {
            if (equipement.getNom().equals(nom)) {
                System.out.println("Equipement trouve :");
                equipement.afficher();
                return;
            }
        }
        System.out.println("Equipement " + nom + " introuvable.");

    }
    
}