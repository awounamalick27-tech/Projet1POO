package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {

    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        
        // Trier les besoins du plus grand au plus petit
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });
        
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        int adresseCourante = CalculateurReseau.convertirIpEnEntier(adresseDepart);
        
        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String adresseReseau = CalculateurReseau.convertirEntierEnIp(adresseCourante);
            
            // Calcul des adresses utilisables (travail supplémentaire)
            String premiere = CalculateurReseau.calculerPremiereAdresseUtilisable(adresseReseau);
            String derniere = CalculateurReseau.calculerDerniereAdresseUtilisable(adresseReseau, cidr);
            
            ResultatVLSM resultat = new ResultatVLSM(besoin.getNom(), adresseReseau, cidr, masque, capacite, premiere, derniere);
            resultats.add(resultat);
            
            int tailleBloc = CalculateurReseau.calculerTailleBloc(cidr);
            adresseCourante = adresseCourante + tailleBloc;
        }
        
        return resultats;
    }
}