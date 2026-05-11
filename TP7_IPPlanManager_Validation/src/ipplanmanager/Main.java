package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP6 - VLANs =====");
        System.out.println();

        // Scénario 1 : Besoins standards (entreprise)
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("SERVEURS", 20));

        System.out.println("--- Scénario 1 : Entreprise ---");
        for (BesoinReseau b : besoins) {
            b.afficher();
        }

        // Génération du plan VLSM
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);

        // Création des VLANs
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int numeroVLAN = 10;
        for (ResultatVLSM resultat : resultats) {
            VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, 
                "VLAN du service " + resultat.getNomBesoin());
            gestionnaire.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }

        System.out.println("\n=== VLANS GENEREES ===");
        gestionnaire.afficherTousLesVLANs();

        // Test de recherche VLAN
        System.out.println("\n=== TEST DE RECHERCHE VLAN ===");
        VLAN vlanRecherche = gestionnaire.rechercherVLAN(20);
        if (vlanRecherche != null) {
            System.out.println("VLAN trouve :");
            vlanRecherche.afficher();
        } else {
            System.out.println("VLAN 20 non trouve");
        }

        // Travail supplémentaire
        gestionnaire.afficherVLANsCritiques();
        gestionnaire.afficherVLANMaxCapacite();

        System.out.println("\nNombre total de VLANs : " + gestionnaire.obtenirNombreVLANs());

        // ========== Scénario 2 : Université (travail demandé) ==========
        System.out.println("\n\n===== Scénario 2 : Universite =====");
        
        ArrayList<BesoinReseau> besoinsUniversite = new ArrayList<>();
        besoinsUniversite.add(new BesoinReseau("ETUDIANTS", 500));
        besoinsUniversite.add(new BesoinReseau("WIFI_PUBLIC", 200));
        besoinsUniversite.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoinsUniversite.add(new BesoinReseau("LABORATOIRES", 60));
        besoinsUniversite.add(new BesoinReseau("SERVEURS", 30));

        System.out.println("\n--- Besoins de l'universite ---");
        for (BesoinReseau b : besoinsUniversite) {
            b.afficher();
        }

        // Génération du plan VLSM
        ArrayList<ResultatVLSM> resultatsUniversite = moteur.genererPlan("10.0.0.0", besoinsUniversite);

        // Création des VLANs pour l'université
        GestionnaireVLAN gestionnaireUniversite = new GestionnaireVLAN();
        int vlanId = 100;
        for (ResultatVLSM resultat : resultatsUniversite) {
            VLAN vlan = new VLAN(vlanId, resultat.getNomBesoin(), resultat,
                "VLAN - " + resultat.getNomBesoin());
            gestionnaireUniversite.ajouterVLAN(vlan);
            vlanId += 10;
        }

        System.out.println("\n=== VLANS DE L'UNIVERSITE ===");
        gestionnaireUniversite.afficherTousLesVLANs();

        System.out.println("\n=== STATISTIQUES UNIVERSITE ===");
        gestionnaireUniversite.afficherVLANsCritiques();
        gestionnaireUniversite.afficherVLANMaxCapacite();
        System.out.println("Nombre total de VLANs : " + gestionnaireUniversite.obtenirNombreVLANs());
    }
}