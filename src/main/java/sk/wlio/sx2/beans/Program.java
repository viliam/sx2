package sk.wlio.sx2.beans;

import sk.wlio.sx2.beans.instrukcia.DeklaraciaPremennej;
import sk.wlio.sx2.beans.instrukcia.DeklaraciaPrikaz;

import java.util.HashMap;
import java.util.Map;

public class Program extends SlovoAbstract {

    final Map<String, DeklaraciaPremennej> mapPremenna = new HashMap<String, DeklaraciaPremennej>();
    final Map<String, DeklaraciaPrikaz> mapPrikaz = new HashMap<String, DeklaraciaPrikaz>();

    public Program(Pozicia pozicia) {
        super(pozicia);
    }

    public void pridajPremennu( DeklaraciaPremennej deklaraciaPremennej) {
        mapPremenna.put( deklaraciaPremennej.getNazov().getObsah(), deklaraciaPremennej);
    }

    public void pridajPrikaz( DeklaraciaPrikaz deklaraciaPrikaz) {
        mapPrikaz.put( deklaraciaPrikaz.getNazov().getObsah(), deklaraciaPrikaz);
    }

    public Map<String, DeklaraciaPrikaz> getMapPrikaz() {
        return mapPrikaz;
    }

    public Map<String, DeklaraciaPremennej> getMapPremenna() {
        return mapPremenna;
    }
}
