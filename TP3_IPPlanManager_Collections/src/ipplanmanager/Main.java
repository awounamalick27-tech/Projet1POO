package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TP3 Collections ===\n");

        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");

        // ========== SOUS-RESEAUX ==========
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Reseau administration");
        ReseauIP reseauTech = new ReseauIP("192.168.2.0", 24, "Reseau technique");
        ReseauIP reseauWiFi = new ReseauIP("192.168.3.0", 24, "Reseau WiFi invites");

        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech = new SousReseau("TECH", reseauTech);
        SousReseau wifi = new SousReseau("WIFI", reseauWiFi);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);

        // ========== ROUTEUR (2 interfaces) ==========
        AdresseIP ipRouteur1 = new AdresseIP("192.168.1.1");
        AdresseIP ipRouteur2 = new AdresseIP("192.168.2.1");

        InterfaceReseau eth0 = new InterfaceReseau("eth0", ipRouteur1);
        InterfaceReseau eth1 = new InterfaceReseau("eth1", ipRouteur2);

        eth0.activer();
        eth1.activer();

        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(eth0);
        routeur.ajouterInterface(eth1);

        infrastructure.ajouterEquipement(routeur);

        // ========== SWITCH (3 interfaces, sans IP) ==========
        InterfaceReseau swEth0 = new InterfaceReseau("FastEthernet0/1", null);
        InterfaceReseau swEth1 = new InterfaceReseau("FastEthernet0/2", null);
        InterfaceReseau swEth2 = new InterfaceReseau("FastEthernet0/3", null);

        swEth0.activer();
        swEth1.activer();
        swEth2.activer();

        Equipement switch1 = new Equipement("SW1_CORE", "Switch");
        switch1.ajouterInterface(swEth0);
        switch1.ajouterInterface(swEth1);
        switch1.ajouterInterface(swEth2);

        infrastructure.ajouterEquipement(switch1);

        // ========== SERVEUR (2 interfaces) ==========
        AdresseIP ipServeur1 = new AdresseIP("192.168.1.10");
        AdresseIP ipServeur2 = new AdresseIP("10.0.0.10");

        InterfaceReseau serveurEth0 = new InterfaceReseau("eth0", ipServeur1);
        InterfaceReseau serveurEth1 = new InterfaceReseau("eth1", ipServeur2);

        serveurEth0.activer();
        serveurEth1.activer();

        Equipement serveur = new Equipement("SRV_DNS", "Serveur");
        serveur.ajouterInterface(serveurEth0);
        serveur.ajouterInterface(serveurEth1);

        infrastructure.ajouterEquipement(serveur);

        // ========== POSTE CLIENT ADMIN (1 interface WiFi) ==========
        AdresseIP ipClientAdmin = new AdresseIP("192.168.1.50");
        InterfaceReseau clientWlan = new InterfaceReseau("wlan0", ipClientAdmin);
        clientWlan.activer();

        Equipement clientAdmin = new Equipement("PC_ADMIN", "Poste client");
        clientAdmin.ajouterInterface(clientWlan);

        infrastructure.ajouterEquipement(clientAdmin);

        // ========== POSTE CLIENT TECH (1 interface, inactive) ==========
        AdresseIP ipClientTech = new AdresseIP("192.168.2.50");
        InterfaceReseau clientTechEth = new InterfaceReseau("eth0", ipClientTech);
        // reste inactive

        Equipement clientTech = new Equipement("PC_TECH", "Poste client");
        clientTech.ajouterInterface(clientTechEth);

        infrastructure.ajouterEquipement(clientTech);

        // ========== POINT D'ACCES WIFI (2 interfaces) ==========
        AdresseIP ipAP1 = new AdresseIP("192.168.3.1");
        AdresseIP ipAP2 = new AdresseIP("192.168.1.100");

        InterfaceReseau apWlan = new InterfaceReseau("wlan0", ipAP1);
        InterfaceReseau apEth = new InterfaceReseau("eth0", ipAP2);

        apWlan.activer();
        apEth.activer();

        Equipement pointAcces = new Equipement("AP_LABO", "Point d'acces WiFi");
        pointAcces.ajouterInterface(apWlan);
        pointAcces.ajouterInterface(apEth);

        infrastructure.ajouterEquipement(pointAcces);

        // ========== AFFICHAGE ==========
        System.out.println("\n=== Test recherche equipement ===");
infrastructure.rechercherEquipement("R1_EDGE");
infrastructure.rechercherEquipement("SW1_CORE");
infrastructure.rechercherEquipement("EQUIPEMENT_INEXISTANT");
        infrastructure.afficher();
    }
}