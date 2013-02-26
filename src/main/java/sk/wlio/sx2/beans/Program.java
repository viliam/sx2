package sk.wlio.sx2.beans;

import sk.wlio.sx2.beans.instruction.DeclarationCommand;
import sk.wlio.sx2.beans.instruction.DeclarationVariable;

import java.util.HashMap;
import java.util.Map;

public class Program extends SlovoAbstract {

    final Map<String, DeclarationVariable> mapPremenna = new HashMap<String, DeclarationVariable>();
    final Map<String, DeclarationCommand> mapPrikaz = new HashMap<String, DeclarationCommand>();

    public Program(Pozicia pozicia) {
        super(pozicia);
    }

    public void pridajPremennu( DeclarationVariable declarationVariable) {
        mapPremenna.put( declarationVariable.getNazov().getObsah(), declarationVariable);
    }

    public void pridajPrikaz( DeclarationCommand declarationCommand) {
        mapPrikaz.put( declarationCommand.getNazov().getObsah(), declarationCommand);
    }

    public Map<String, DeclarationCommand> getMapPrikaz() {
        return mapPrikaz;
    }

    public Map<String, DeclarationVariable> getMapPremenna() {
        return mapPremenna;
    }
}
