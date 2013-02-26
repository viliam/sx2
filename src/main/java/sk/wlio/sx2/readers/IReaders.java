package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public interface IReaders {
    
    public TextReader<Slovo> slovo();
    public TextReader<Cislo> cislo();
    public TextReader<Operator> opAritm();
    public TextReader<Operator> opBool();
    public TextReader<Operator> opPorovnanie();
    public TextReader<Operator> opPriradenia();
    public TextReader<IVyraz> vyraz();
    public TextReader<IVyraz> vrzAritm();
    public TextReader<IVyraz> vrzBool();
    public TextReader<IVyraz> vrzJednduchy();
    public TextReader<IVyraz> vrzVzatvorke();
    public TextReader<IVyraz> vrzAritmVzatvorke();
    public TextReader<Zatvorka> zatvorka();
    public TextReader<Ciarka> ciarka();

    public TextReader<DatovyTyp> datovyTyp();

    public TextReader<Instrukcia> instrukcia();
    public TextReader<Assignment> priradenie();
    public TextReader<Block> blok();
    public TextReader<Premenna> premenna();
    public TextReader<DeclarationVariable> dekPremennej();
    public TextReader<Command> prikaz();
    public TextReader<DeclarationCommand> dekPrikaz();
    public TextReader<Program> dekTrieda();
    public TextReader<Parameters> parametre();
    public TextReader<DeclarationParameter> dekParameter();
    public TextReader<Return> vrat();

    public TextReader<InstrukciaSlovo> instrukciaSlovo();


}
