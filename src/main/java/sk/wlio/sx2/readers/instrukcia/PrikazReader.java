package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.Parametre;
import sk.wlio.sx2.beans.instrukcia.Prikaz;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class PrikazReader implements TextReader<Prikaz> {
    
    public Prikaz citaj(TextContext tC)  {
        Slovo nazovPrikaz = Readers.slovo().citaj( tC);
        Parametre parametre  = Readers.parametre().citaj(tC);
        return new Prikaz(nazovPrikaz, parametre);
    }
}
