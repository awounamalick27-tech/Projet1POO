package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan) {
        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }

    // Travail supplémentaire : afficher les VLANs avec capacité > 100 hôtes
    public void afficherVLANsCritiques() {
        System.out.println("\n=== VLANs CRITIQUES (capacite > 100 hotes) ===");
        boolean trouve = false;
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > 100) {
                System.out.println("VLAN critique detecte : VLAN " + vlan.getId() + 
                    " - " + vlan.getNom() + " - " + vlan.getCapacite() + " hotes");
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun VLAN critique trouve.");
        }
    }
    
    // Travail supplémentaire : afficher le VLAN avec la plus grande capacité
    public void afficherVLANMaxCapacite() {
        if (vlans.isEmpty()) {
            System.out.println("Aucun VLAN disponible.");
            return;
        }
        VLAN vlanMax = vlans.get(0);
        for (VLAN vlan : vlans) {
            if (vlan.getCapacite() > vlanMax.getCapacite()) {
                vlanMax = vlan;
            }
        }
        System.out.println("\n=== VLAN AVEC LA PLUS GRANDE CAPACITE ===");
        System.out.println("VLAN " + vlanMax.getId() + " - " + vlanMax.getNom() + 
            " - " + vlanMax.getCapacite() + " hotes");
    }
}