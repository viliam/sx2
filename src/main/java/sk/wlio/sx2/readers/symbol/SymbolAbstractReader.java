package sk.wlio.sx2.readers.symbol;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.ISlovo;
import sk.wlio.sx2.rozhrania.TextReader;

//Template metod
public abstract class SymbolAbstractReader<E extends ISlovo> implements TextReader<E> {

    public E citaj(TextContext tC)  {
        try {
            Pozicia poz = tC.najdiNasledujuciZnak();
            String sSymbol = odkusniSymbol(tC);
            SymbolEnum eSymbol = SymbolEnum.makeSymbol(sSymbol);
            return create( poz, eSymbol);
        } catch (SxException ex) {
            throw SxException.create( ex.getTyp(), tC);
        }

    }

    protected abstract String[] getSymbols();
    //protected abstract E makeSymbol(String s, Bod pozicia);
    protected abstract E create(Pozicia pozicia, SymbolEnum oEnum) ;
    protected abstract SxExTyp getExceptionTyp();


    protected String odkusniSymbol(  TextContext tC)  {
        String[] symbols = getSymbols();

        int najdeneInx = -1, dlzka =0;   //hladam najdlhsi zhodny prefix
        String s = tC.vratKoniecRiadka();
        for (int i=0 ; i<symbols.length; i++)
            if ( s.startsWith( symbols[i]) && dlzka < symbols[i].length()) {
                    dlzka = symbols[i].length() ;
                    najdeneInx = i;
                }

        if (najdeneInx!= -1) {
            tC.pripocitajXPoziciu(symbols[najdeneInx].length());
            return symbols[najdeneInx];
        }

        throw SxException.create( getExceptionTyp(), tC);
    }

//    public boolean jePrfx( char a) {
//        String[] symbols  = getSymbols();
//        for (int i=0; i < symbols.length; i++)
//                if ( symbols[i].charAt(0) == a  ) return true;
//        return false;
//    }

}
