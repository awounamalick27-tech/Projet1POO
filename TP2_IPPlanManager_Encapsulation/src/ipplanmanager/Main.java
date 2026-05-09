package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== TP2 : Encapsulation =====");

        // Test des validations à la création
        System.out.println("\n--- Test creation avec valeurs invalides ---");
        AdresseIP ipInvalide = new AdresseIP("");
        AdresseIP ipNull = new AdresseIP(null);
        InterfaceReseau interfaceInvalide = new InterfaceReseau("", ipInvalide);
        Equipement equipementInvalide = new Equipement("", "", interfaceInvalide);
        ReseauIP reseauInvalide = new ReseauIP("", 55, "");

        // Test des setters (modification avec valeurs invalides)
        System.out.println("\n--- Test setters avec valeurs invalides ---");
        AdresseIP ipTest = new AdresseIP("10.0.0.1");
        ipTest.setValeur("");
        ipTest.setValeur(null);
        
        ReseauIP reseauTest = new ReseauIP("10.0.0.0", 24, "Test");
        reseauTest.setAdresseReseau("");
        reseauTest.setMasqueCidr(99);
        reseauTest.setDescription("");
        
        InterfaceReseau interfaceTest = new InterfaceReseau("eth1", ipTest);
        interfaceTest.setNom("");
        
        Equipement equipementTest = new Equipement("Test", "Routeur", interfaceTest);
        equipementTest.setNom("");
        equipementTest.setType("");

        // Test de la methode estAdresseLocale()
        System.out.println("\n--- Test methode estAdresseLocale() ---");
        AdresseIP ipLocale = new AdresseIP("192.168.1.1");
        AdresseIP ipNonLocale = new AdresseIP("10.0.0.1");
        System.out.println("192.168.1.1 est locale ? " + ipLocale.estAdresseLocale());
        System.out.println("10.0.0.1 est locale ? " + ipNonLocale.estAdresseLocale());

        // Creation d'equipements supplementaires (partie 13)
        System.out.println("\n--- Equipements supplementaires ---");
        
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");
        InterfaceReseau interfaceSwitch = new InterfaceReseau("eth0", ipSwitch);
        interfaceSwitch.activer();
        Equipement switchEquip = new Equipement("SW1", "Switch", interfaceSwitch);
        switchEquip.afficher();
        
        System.out.println();
        
        AdresseIP ipWifi = new AdresseIP("192.168.1.3");
        InterfaceReseau interfaceWifi = new InterfaceReseau("wlan0", ipWifi);
        interfaceWifi.activer();
        Equipement pointAcces = new Equipement("AP1", "Point d'acces WiFi", interfaceWifi);
        pointAcces.afficher();

        System.out.println("\n--- Affichage des objets du sujet ---");
        
        // Objets du sujet
        AdresseIP ip1 = new AdresseIP("192.168.1.1");
        AdresseIP ip2 = new AdresseIP("");
        AdresseIP ip3 = new AdresseIP(null);

        InterfaceReseau interface1 = new InterfaceReseau("eth0", ip1);
        InterfaceReseau interface2 = new InterfaceReseau("", ip2);

        interface1.activer();

        Equipement routeur = new Equipement("R1EDGE", "Routeur", interface1);
        Equipement serveur = new Equipement("", "", interface2);

        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Reseau principal");
        ReseauIP reseau2 = new ReseauIP("", 55, "");

        System.out.println();
        System.out.println("----- Reseau 1 -----");
        reseau1.afficher();

        System.out.println();
        System.out.println("----- Reseau 2 -----");
        reseau2.afficher();

        System.out.println();
        System.out.println("----- Equipement 1 -----");
        routeur.afficher();

        System.out.println();
        System.out.println("----- Equipement 2 -----");
        serveur.afficher();
    }
}