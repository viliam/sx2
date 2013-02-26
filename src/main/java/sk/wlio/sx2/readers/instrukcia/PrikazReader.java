package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.Parameters;
import sk.wlio.sx2.beans.instruction.Command;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class PrikazReader implements TextReader<Command> {
    
    public Command citaj(TextContext tC)  {
        Slovo nazovPrikaz = Readers.slovo().citaj( tC);
        Parameters parameters = Readers.parametre().citaj(tC);
        return new Command(nazovPrikaz, parameters);
    }
}
