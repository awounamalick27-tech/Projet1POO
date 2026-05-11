package ipplanmanager;

public class RecommandationAdministration implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getNom().toUpperCase().contains("ADMIN")) {
            return new Recommandation(
                "Accès restreint au VLAN Administration",
                "ÉLEVÉE",
                "Le VLAN " + vlan.getNom() + " doit être accessible uniquement aux administrateurs réseau."
            );
        }
        return null;
    }
}