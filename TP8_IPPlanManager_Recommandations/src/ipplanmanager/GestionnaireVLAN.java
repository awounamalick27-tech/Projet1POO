package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;

    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }

    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) {
            return;
        }
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est deja utilise.");
            }
        }
        vlans.add(vlan);
    }

    public void afficherTousLesVLANs() {
        for (VLAN vlan : vlans) {
            vlan.afficher();
            System.out.println();
        }
    }

    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }

    public int obtenirNombreVLANs() {
        return vlans.size();
    }
    public ArrayList<VLAN> getVlans() {
    return vlans;
}
}