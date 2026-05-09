package ipplanmanager;

public class Equipement {
    String nom;
    String type;
    InterfaceReseau interfacePrincipale;

    public Equipement(String nom, String type, InterfaceReseau interfacePrincipale) {
        this.nom = nom;
        this.type = type;
        this.interfacePrincipale = interfacePrincipale;
    }

    public void afficher() {
        System.out.println("Nom de l'equipement : " + nom);
        System.out.println("Type d'equipement : " + type);
        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
        } else {
            System.out.println("Aucune interface reseau configuree.");
        }
    }
}