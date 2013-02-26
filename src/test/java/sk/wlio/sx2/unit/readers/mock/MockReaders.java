package sk.wlio.sx2.unit.readers.mock;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.readers.IReaders;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.TextReader;

public class MockReaders implements IReaders {

    StringBuffer postupVolania;

    protected MockReader<Cislo> mCislo;
    protected MockReader<Slovo> mSlovo;

    protected MockReader<Operator> mOpAritm;
    protected MockReader<Operator> mOpBool;
    protected MockReader<Operator> mOpPorovnania;
    protected MockReader<Operator> mOpPriradenia;

    protected MockReader<IVyraz> mVyraz;
    protected MockReader<IVyraz> mVrzArtim;
    protected MockReader<IVyraz> mVrzBool;
    protected MockReader<IVyraz> mVrzJednoduchy;
    protected MockReader<IVyraz> mVrzZatvorka;
    protected MockReader<IVyraz> mVrzAritmVzatvorke;
    protected MockReader<IVyraz> mVrzBoolVzatvorke;

    protected MockReader<Zatvorka> mZatvorka;
    protected MockReader<Ciarka> mCiarka;

    protected MockReader<DatovyTyp> mDatovyTyp;
    protected MockReader<InstrukciaSlovo> mInstrukciaSlovo;

    protected MockReader<Instrukcia> mInstrukcia;
    protected MockReader<Assignment> mPriradenie;
    protected MockReader<Block> mBlok;
    protected MockReader<Premenna> mPremenna;
    protected MockReader<DeclarationVariable> mDekPremennej;
    protected MockReader<Command> mPrikaz;
    protected MockReader<DeclarationCommand> mDekPrikaz;
    protected MockReader<Program> mDekTrieda;
    protected MockReader<Parameters> mParametre;
    protected MockReader<DeclarationParameter> mDekParameter;
    protected MockReader<Return> mVrat;


    public MockReaders(StringBuffer postupVolania) {
        this.postupVolania = postupVolania;

        mCislo = new MockReader<Cislo>("cislo", postupVolania);
        mSlovo= new MockReader<Slovo>("slovo", postupVolania);

        mOpAritm = new MockReader<Operator>("opAritm", postupVolania);
        mOpBool= new MockReader<Operator>("opBool", postupVolania);
        mOpPorovnania= new MockReader<Operator>("opPorovnania", postupVolania);
        mOpPriradenia= new MockReader<Operator>("opPriradenia", postupVolania);

        mVyraz= new MockReader<IVyraz>("vyraz", postupVolania);
        mVrzArtim= new MockReader<IVyraz>("vrzAritm", postupVolania);
        mVrzBool= new MockReader<IVyraz>("vrzBool", postupVolania);
        mVrzJednoduchy = new MockReader<IVyraz>("vrzJednoduchy", postupVolania);
        mVrzZatvorka = new MockReader<IVyraz>("vrzZatvorka", postupVolania);
        mVrzAritmVzatvorke= new MockReader<IVyraz>("vrzAritmVzatvorke", postupVolania);
        mVrzBoolVzatvorke= new MockReader<IVyraz>("vrzBoolVzatvorke", postupVolania);

        mZatvorka = new MockReader<Zatvorka>("zatvorka", postupVolania);
        mCiarka= new MockReader<Ciarka>("ciarka", postupVolania);

        mInstrukciaSlovo= new MockReader<InstrukciaSlovo>("instrukciaSlovo", postupVolania);
        mDatovyTyp= new MockReader<DatovyTyp>("datovyTyp", postupVolania);

        mInstrukcia= new MockReader<Instrukcia>("instruction", postupVolania);
        mPriradenie= new MockReader<Assignment>("priradenie", postupVolania);
        mBlok= new MockReader<Block>("blok", postupVolania);
        mPremenna =new MockReader<Premenna>("premenna", postupVolania);
        mDekPremennej = new MockReader<DeclarationVariable>("dekPremennej", postupVolania);
        mPrikaz= new MockReader<Command>("prikaz", postupVolania);
        mDekPrikaz= new MockReader<DeclarationCommand>("dekPrikaz", postupVolania);
        mParametre= new MockReader<Parameters>("parametre", postupVolania);
        mDekParameter= new MockReader<DeclarationParameter>("dekParameter", postupVolania);
        mVrat=new MockReader<Return>("vrat", postupVolania);

    }

    public MockReader<Slovo> slovo() {  return  mSlovo; }
    public MockReader<Cislo> cislo() {  return   mCislo; }

    public MockReader<Operator> opAritm() {  return mOpAritm; }
    public MockReader<Operator> opBool() {  return  mOpBool; }
    public MockReader<Operator> opPorovnanie() {  return mOpPorovnania; }
    public MockReader<Operator> opPriradenia() {  return mOpPriradenia; }

    public MockReader<IVyraz> vyraz() {  return   mVyraz; }
    public MockReader<IVyraz> vrzAritm() {  return mVrzArtim; }
    public MockReader<IVyraz> vrzBool() {  return mVrzBool; }
    public MockReader<IVyraz> vrzJednduchy() {  return mVrzJednoduchy; }
    public MockReader<IVyraz> vrzVzatvorke() {  return mVrzZatvorka; }
    public MockReader<IVyraz> vrzAritmVzatvorke() {  return mVrzAritmVzatvorke; }
    public MockReader<IVyraz> vrzBoolVzatvorke() {  return mVrzBoolVzatvorke; }

    public MockReader<Zatvorka> zatvorka() {  return mZatvorka; }
    public MockReader<Ciarka> ciarka  () {  return mCiarka; }

    public MockReader<InstrukciaSlovo> instrukciaSlovo() {  return mInstrukciaSlovo; }
    public MockReader<DatovyTyp> datovyTyp() {  return mDatovyTyp; }

    public MockReader<Instrukcia> instrukcia() {  return mInstrukcia; }
    public MockReader<Assignment> priradenie() {  return mPriradenie; }
    public MockReader<Block> blok() {  return mBlok; }
    public MockReader<Premenna> premenna() {  return mPremenna; }
    public MockReader<DeclarationVariable> dekPremennej() {  return mDekPremennej; }
    public MockReader<Command> prikaz() {  return mPrikaz; }
    public MockReader<DeclarationCommand> dekPrikaz () {  return mDekPrikaz; }

    public TextReader<Program> dekTrieda() {
        return mDekTrieda;
    }

    public MockReader<Parameters> parametre() {  return mParametre;}
    public MockReader<DeclarationParameter> dekParameter() {  return mDekParameter; }
    public MockReader<Return> vrat() {  return mVrat; }

}
