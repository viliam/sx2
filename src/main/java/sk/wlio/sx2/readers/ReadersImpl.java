package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.readers.instrukcia.*;
import sk.wlio.sx2.readers.symbol.*;
import sk.wlio.sx2.readers.vyraz.*;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazAritmVzatvorkeReader;
import sk.wlio.sx2.readers.vyraz.zatvorka.VyrazVzatvorkeReader;
import sk.wlio.sx2.readers.zakazaneslova.DatovyTypReader;
import sk.wlio.sx2.readers.zakazaneslova.InstrukciaSlovoReader;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class ReadersImpl implements  IReaders {
    
    public TextReader<Slovo> slovo() {  return  new SlovoReader(); }
    public TextReader<Cislo> cislo() {  return   new CisloReader(); }
    public TextReader<Operator> opAritm() {  return   new OperatorAritmReader(); }
    public TextReader<Operator> opBool() {  return   new OperatorBoolReader(); }
    public TextReader<Operator> opPorovnanie() {  return   new OperatorPorovnanieReader(); }
    public TextReader<Operator> opPriradenia() {  return   new OperatorPriradenieReader(); }
    public TextReader<IVyraz> vyraz() {  return   new VyrazReader(); }
    public TextReader<IVyraz> vrzAritm() {  return   new VyrazAritmReader(); }
    public TextReader<IVyraz> vrzBool() {  return   new VyrazBoolReader(); }
    public TextReader<IVyraz> vrzJednduchy() {  return   new VyrazJednoduchyReader(); }
    public TextReader<IVyraz> vrzVzatvorke() {  return   new VyrazVzatvorkeReader(); }
    public TextReader<IVyraz> vrzAritmVzatvorke() {  return   new VyrazAritmVzatvorkeReader(); }
    public TextReader<Zatvorka> zatvorka() {  return   new ZatvorkaReader(); }
    public TextReader<Ciarka> ciarka  () {  return   new CiarkaReader(); }

    public TextReader<DatovyTyp> datovyTyp() {  return   new DatovyTypReader(); }
    public TextReader<InstrukciaSlovo> instrukciaSlovo() { return new InstrukciaSlovoReader(); }

    public TextReader<Instrukcia> instrukcia() {  return   new InstrukciaReader(); }
    public TextReader<Priradenie> priradenie() {  return   new PriradenieReader(); }
    public TextReader<Blok> blok() {  return   new BlokReader(); }
    public TextReader<Premenna> premenna() {  return   new PremennaReader(); }
    public TextReader<DeklaraciaPremennej> dekPremennej() {  return   new DeklaraciaPremennejReader(); }
    public TextReader<Prikaz> prikaz() {  return   new PrikazReader(); }
    public TextReader<DeklaraciaPrikaz> dekPrikaz () {  return   new DeklaraciaPrikazReader(); }
    public TextReader<Program> dekTrieda() {   return new ProgramReader();  }

    public TextReader<Parametre> parametre() {  return   new ParametreReader(); }
    public TextReader<DeklaraciaParameter> dekParameter() {  return   new DeklaraciaParameterReader(); }
    public TextReader<Vrat> vrat() {  return   new VratReader(); }
}
