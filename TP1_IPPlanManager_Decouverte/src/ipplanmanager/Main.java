package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP1 =====");
        System.out.println("Decouverte des premieres classes du projet");
        System.out.println();

        // Creation des adresses IP
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient = new AdresseIP("192.168.1.50");
        AdresseIP ipAP = new AdresseIP("192.168.1.100");  // Pour le point d'acces WiFi

        // Creation des interfaces reseau
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur);
        InterfaceReseau interfaceClient = new InterfaceReseau("wlan0", ipClient);
        InterfaceReseau interfaceAP = new InterfaceReseau("wlan0", ipAP);
        
        // Interface sans adresse IP (pour le switch)
        InterfaceReseau interfaceSwitch = new InterfaceReseau("eth0", null);
        
        // Interface pour le poste client supplementaire (sans IP)
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth0", null);

        // Activation des interfaces
        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceAP.activer();
        // interfaceClient reste inactive (demande dans le TP)
        // interfaceSwitch reste inactive
        // interfaceClient2 reste inactive

        // Creation des equipements
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interfaceRouteur);
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceServeur);
        Equipement client = new Equipement("PC_ADMIN", "Poste client", interfaceClient);
        Equipement wifiAP = new Equipement("AP_LABO", "Point d'acces WiFi", interfaceAP);
        Equipement switchPrincipal = new Equipement("SW1_CORE", "Switch", interfaceSwitch);
        Equipement client2 = new Equipement("PC_INVITE", "Poste client supplementaire", interfaceClient2);

        // Creation des reseaux
        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0", 24, "Reseau principal du laboratoire IRT");
        ReseauIP reseauWiFi = new ReseauIP("10.0.0.0", 24, "Reseau invites WiFi");

        // Affichage des reseaux
        System.out.println("--- Reseaux crees ---");
        reseauPrincipal.afficher();
        System.out.println();
        reseauWiFi.afficher();
        System.out.println();

        // Affichage des equipements
        System.out.println("--- Equipements crees ---");
        System.out.println();
        
        routeur.afficher();
        System.out.println();
        
        serveur.afficher();
        System.out.println();
        
        client.afficher();
        System.out.println();
        
        wifiAP.afficher();
        System.out.println();
        
        switchPrincipal.afficher();
        System.out.println();
        
        client2.afficher();
    }
}