package ipplanmanager;

public class RecommandationMargeAdresse implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int demandes = vlan.getReseauAssocie().getNombreHotesDemandes();
            int capacite = vlan.getReseauAssocie().getCapacite();
            int marge = capacite - demandes;
            
            if (marge < 10 && marge >= 0) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " ne dispose que de " + marge + 
                    " adresses disponibles. Prevoir une marge plus confortable pour l'evolution."
                );
            }
        }
        return null;
    }
}