package sk.wlio.sx2.unit.readers.mock;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
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
    protected MockReader<Priradenie> mPriradenie;
    protected MockReader<Blok> mBlok;
    protected MockReader<Premenna> mPremenna;
    protected MockReader<DeklaraciaPremennej> mDekPremennej;
    protected MockReader<Prikaz> mPrikaz;
    protected MockReader<DeklaraciaPrikaz> mDekPrikaz;
    protected MockReader<Program> mDekTrieda;
    protected MockReader<Parametre> mParametre;
    protected MockReader<DeklaraciaParameter> mDekParameter;
    protected MockReader<Vrat> mVrat;


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

        mInstrukcia= new MockReader<Instrukcia>("instrukcia", postupVolania);
        mPriradenie= new MockReader<Priradenie>("priradenie", postupVolania);
        mBlok= new MockReader<Blok>("blok", postupVolania);
        mPremenna =new MockReader<Premenna>("premenna", postupVolania);
        mDekPremennej = new MockReader<DeklaraciaPremennej>("dekPremennej", postupVolania);
        mPrikaz= new MockReader<Prikaz>("prikaz", postupVolania);
        mDekPrikaz= new MockReader<DeklaraciaPrikaz>("dekPrikaz", postupVolania);
        mParametre= new MockReader<Parametre>("parametre", postupVolania);
        mDekParameter= new MockReader<DeklaraciaParameter>("dekParameter", postupVolania);
        mVrat=new MockReader<Vrat>("vrat", postupVolania);

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
    public MockReader<Priradenie> priradenie() {  return mPriradenie; }
    public MockReader<Blok> blok() {  return mBlok; }
    public MockReader<Premenna> premenna() {  return mPremenna; }
    public MockReader<DeklaraciaPremennej> dekPremennej() {  return mDekPremennej; }
    public MockReader<Prikaz> prikaz() {  return mPrikaz; }
    public MockReader<DeklaraciaPrikaz> dekPrikaz () {  return mDekPrikaz; }

    public TextReader<Program> dekTrieda() {
        return mDekTrieda;
    }

    public MockReader<Parametre> parametre() {  return mParametre;}
    public MockReader<DeklaraciaParameter> dekParameter() {  return mDekParameter; }
    public MockReader<Vrat> vrat() {  return mVrat; }

}
