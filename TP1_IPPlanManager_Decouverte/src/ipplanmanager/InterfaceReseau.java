package ipplanmanager;

public class InterfaceReseau {
    String nom;
    AdresseIP adresseIP;
    boolean active;

    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.active = false;
    }

    public void activer() {
        active = true;
    }

    public void desactiver() {
        active = false;
    }

    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        } else {
            System.out.println("Adresse IP : non configuree");
        }
        if (active) {
            System.out.println("Etat : active");
        } else {
            System.out.println("Etat : inactive");
        }
    }
}