package sk.wlio.sx2.exception;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Pozicia;

public class SxException extends RuntimeException {

    private final SxExTyp typ;
    private final Pozicia pozicia;

    public static SxException create( SxExTyp typ, TextContext tC)  {
        return create(typ, null,  tC);
    }

    public static SxException create( SxExTyp typ,  String message, TextContext tC) {
        Pozicia inx = tC.getPozicia();

        if (message == null) {
            if (tC.jeKoniec())
                return new SxException(typ, inx);

            message= urobMessage(typ, inx, tC);
        }

        return new SxException( typ, message, inx);
    }

    private static String urobMessage(SxExTyp typ, Pozicia pozicia, TextContext tC) {
        int riadok = pozicia.getY();
        int stlpec = pozicia.getX();

        String aktualnyRiadok = tC.getRiadok(riadok);
        char znak = ( aktualnyRiadok.length() < stlpec) ?
                aktualnyRiadok.charAt(stlpec) : ' ';

        return typ + "  : " + aktualnyRiadok + "    \n znak = " + znak;

    }

    public static SxException create( SxExTyp typ, Pozicia pozicia)   {
        return new SxException(typ, pozicia);
    }

    private SxException(SxExTyp typ, Pozicia pozicia) {
        this( typ, typ.toString(), pozicia);
    }

    private  SxException( SxExTyp typ, String message, Pozicia pozicia )  {
        super(message);
        this.typ = typ;
        this.pozicia = pozicia;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "  \n" 
                + " riadok = " + pozicia.getY() + " ,   stlpec = " + pozicia.getX();
    }

    public SxExTyp getTyp() {
        return typ;
    }


}