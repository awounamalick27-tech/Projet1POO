package ipplanmanager;

public class CalculateurReseau {

    // Calcule le nombre d'hôtes max dans un réseau
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) {
            return 0;
        }
        int bitsHotes = 32 - cidr;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    // Détermine la classe de l'adresse IP (A, B, C)
    public static String obtenirClasseReseau(String adresseIP) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);

        if (premierOctet >= 1 && premierOctet <= 126) {
            return "Classe A";
        }
        if (premierOctet >= 128 && premierOctet <= 191) {
            return "Classe B";
        }
        if (premierOctet >= 192 && premierOctet <= 223) {
            return "Classe C";
        }
        return "Classe inconnue";
    }

    // Convertit un CIDR en masque décimal
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 8: return "255.0.0.0";
            case 16: return "255.255.0.0";
            case 24: return "255.255.255.0";
            case 25: return "255.255.255.128";
            case 26: return "255.255.255.192";
            case 27: return "255.255.255.224";
            case 28: return "255.255.255.240";
            case 29: return "255.255.255.248";
            case 30: return "255.255.255.252";
            default: return "Masque non disponible";
        }
    }

    // Travail supplémentaire : vérifie si l'adresse appartient à une plage privée
    public static boolean estReseauPrive(String adresseIP, int cidr) {
        String[] parties = adresseIP.split("\\.");
        int premierOctet = Integer.parseInt(parties[0]);

        // Plage 10.0.0.0/8
        if (premierOctet == 10) {
            return true;
        }

        // Plage 172.16.0.0/12 (172.16.x.x à 172.31.x.x)
        if (premierOctet == 172) {
            int deuxiemeOctet = Integer.parseInt(parties[1]);
            if (deuxiemeOctet >= 16 && deuxiemeOctet <= 31) {
                return true;
            }
        }

        // Plage 192.168.0.0/16
        if (premierOctet == 192 && Integer.parseInt(parties[1]) == 168) {
            return true;
        }

        return false;
    }
}