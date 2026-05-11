package ipplanmanager;

import java.util.ArrayList;

public class ValidateurPlanAdressage {

    // Vérifie les adresses IP de tous les résultats
    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) throws AdresseIPInvalideException {
        for (ResultatVLSM resultat : resultats) {
            CalculateurReseau.verifierAdresseIP(resultat.getAdresseReseau());
        }
    }

    // Vérifie qu'il n'y a pas de chevauchement entre les sous-réseaux
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                boolean conflit = CalculateurReseau.reseauxSeChevauchent(
                    r1.getAdresseReseau(), r1.getCidr(),
                    r2.getAdresseReseau(), r2.getCidr()
                );
                if (conflit) {
                    throw new ChevauchementReseauException(
                        "Chevauchement detecte entre " + r1.getNomBesoin() + " et " + r2.getNomBesoin()
                    );
                }
            }
        }
    }

    public void afficherValidationReussie() {
        System.out.println("Validation terminee : aucun conflit critique detecte.");
    }
}