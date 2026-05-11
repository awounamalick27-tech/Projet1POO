package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP8 - Recommandations =====");
        System.out.println();

        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("WIFI_INVITES", 120));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        besoins.add(new BesoinReseau("CAMERAS", 80));
        besoins.add(new BesoinReseau("VOIP", 60));

        MoteurVLSM moteurVLSM = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan("192.168.10.0", besoins);

        GestionnaireVLAN gestionnaireVLAN = new GestionnaireVLAN();
        int numeroVLAN = 10;

        try {
            for (ResultatVLSM resultat : resultats) {
                VLAN vlan = new VLAN(numeroVLAN, resultat.getNomBesoin(), resultat, "VLAN " + resultat.getNomBesoin());
                gestionnaireVLAN.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }

        System.out.println("Plan VLAN genere :");
        gestionnaireVLAN.afficherTousLesVLANs();

        MoteurRecommandation moteurRecommandation = new MoteurRecommandation();
        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
        moteurRecommandation.ajouterRegle(new RecommandationAdministration());
        moteurRecommandation.ajouterRegle(new RecommandationMargeAdresse());

        ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());

        System.out.println("\nRecommandations proposees :");
        moteurRecommandation.afficherRecommandations(recommandations);
    }
}