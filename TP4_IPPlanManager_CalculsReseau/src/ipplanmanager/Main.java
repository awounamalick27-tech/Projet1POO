package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TP4 : Calculs Reseau Automatiques ===\n");

        InfrastructureReseau infrastructure = new InfrastructureReseau("Infrastructure YFY");

        // Creation des sous-reseaux avec differents CIDR
        ReseauIP reseauAdmin = new ReseauIP("192.168.1.0", 24, "Reseau Administration");
        ReseauIP reseauTech = new ReseauIP("172.16.0.0", 16, "Reseau Technique");
        ReseauIP reseauWiFi = new ReseauIP("10.0.0.0", 8, "Reseau WiFi");
        ReseauIP reseauDMZ = new ReseauIP("192.168.50.0", 28, "Reseau DMZ");

        SousReseau admin = new SousReseau("ADMIN", reseauAdmin);
        SousReseau tech = new SousReseau("TECH", reseauTech);
        SousReseau wifi = new SousReseau("WIFI", reseauWiFi);
        SousReseau dmz = new SousReseau("DMZ", reseauDMZ);

        infrastructure.ajouterSousReseau(admin);
        infrastructure.ajouterSousReseau(tech);
        infrastructure.ajouterSousReseau(wifi);
        infrastructure.ajouterSousReseau(dmz);

        // Creation d'un equipement pour tester
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        InterfaceReseau eth0 = new InterfaceReseau("eth0", ipRouteur);
        eth0.activer();

        Equipement routeur = new Equipement("R1_EDGE", "Routeur");
        routeur.ajouterInterface(eth0);
        infrastructure.ajouterEquipement(routeur);

        // Test de la methode estReseauPrive()
        System.out.println("\n=== Test reseaux prives ===");
        System.out.println("192.168.1.0/24 est prive ? " + 
            CalculateurReseau.estReseauPrive("192.168.1.0", 24));
        System.out.println("172.16.0.0/16 est prive ? " + 
            CalculateurReseau.estReseauPrive("172.16.0.0", 16));
        System.out.println("10.0.0.0/8 est prive ? " + 
            CalculateurReseau.estReseauPrive("10.0.0.0", 8));
        System.out.println("8.8.8.0/24 est prive ? " + 
            CalculateurReseau.estReseauPrive("8.8.8.0", 24));

        System.out.println("\n=== Infrastructure complete ===\n");
        System.out.println("\n=== Test reseaux prives (travail supplementaire) ===");
System.out.println("192.168.1.0/24 est prive ? " + 
    CalculateurReseau.estReseauPrive("192.168.1.0", 24));
System.out.println("172.16.0.0/16 est prive ? " + 
    CalculateurReseau.estReseauPrive("172.16.0.0", 16));
System.out.println("10.0.0.0/8 est prive ? " + 
    CalculateurReseau.estReseauPrive("10.0.0.0", 8));
System.out.println("8.8.8.0/24 est prive ? " + 
    CalculateurReseau.estReseauPrive("8.8.8.0", 24));
System.out.println("192.168.50.0/28 est prive ? " + 
    CalculateurReseau.estReseauPrive("192.168.50.0", 28));
System.out.println("172.15.0.0/16 est prive ? " + 
    CalculateurReseau.estReseauPrive("172.15.0.0", 16));
System.out.println("172.32.0.0/16 est prive ? " + 
    CalculateurReseau.estReseauPrive("172.32.0.0", 16));
        infrastructure.afficher();
    }
}