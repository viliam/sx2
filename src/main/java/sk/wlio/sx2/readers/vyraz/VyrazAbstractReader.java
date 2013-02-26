package sk.wlio.sx2.readers.vyraz;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.symbol.enums.SymbolEnum;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.IVyraz;
import sk.wlio.sx2.rozhrania.TextReader;

public abstract class VyrazAbstractReader implements TextReader<IVyraz> {

    public IVyraz citaj(TextContext tC)  {
        IVyraz o1 = citajPrvyVyraz( tC);

        skontrolujTyp( o1.getVyrazTyp(), tC);

        if (tC.jeKoniec())  return o1;

        IVyraz v = citajZlozenyVyraz( tC, o1);
        if (v != null) return v;

        if ( jeKorektnyKoniecVyrazu( tC) )
            return o1;

        throw SxException.create(SxExTyp.CAKAL_OPERATOR, tC);
    }

    protected abstract void skontrolujTyp(Enums.VyrazTyp vyrazTyp, TextContext tC);

    protected abstract IVyraz citajZlozenyVyraz(TextContext tC, IVyraz o1);

    protected abstract IVyraz citajPrvyVyraz(TextContext tC);

    private boolean jeKorektnyKoniecVyrazu(  TextContext tC) {
        return tC.jePrefixOperator()
             || tC.jePrefixCiarka()
             || tC.jePrefixZatvorkaZatvorena();
    }

}
