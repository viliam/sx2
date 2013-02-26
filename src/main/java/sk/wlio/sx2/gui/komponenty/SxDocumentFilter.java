package sk.wlio.sx2.gui.komponenty;

import sk.wlio.sx2.TextUtils;
import sk.wlio.sx2.beans.symbol.enums.SymbolsEnum;
import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


public class SxDocumentFilter extends DocumentFilter {

    private String[] rezervovaneSlova;
    private JTextPane jTextPane;
    //private AttributeSet pouzivanyFont;

    private boolean suZmeny = false;
    public boolean getZmeny() {
        boolean b = suZmeny;
        suZmeny = false;
        return b;
    }
    public void zrusZmeny() { suZmeny = false; }

    public SxDocumentFilter(String[] rezervovaneSlova, JTextPane jTextPane) {
        this.rezervovaneSlova = rezervovaneSlova;
        this.jTextPane = jTextPane;
    }

    @Override
    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)  throws BadLocationException {
        suZmeny = true;
        if (str.equals("\t")) {
            super.insertString(fb, offs, "    ", a);
            return;
        }

        int inx = 0;
        setNormalStyle(a);
        //prejdem cely string, a budem ho vkladat po castiach (splitnem ho podla zakzanych znakov)
        final int length = str.length();
        while ( inx < length)  {
            int zac = inx;
             //zapametam si, kde zacina slovo, aby som mohol skotrolovat, ci nahodou necitam zakazany znak
            int inxNasledujuci = TextUtils.posunNasledujuciZnakVriadku(str, inx);
            if (inxNasledujuci>= length) {  //neobsahuje dalsie slovo => odstrihnem odstavajuce medzery a vlozim ich
                super.insertString(fb, offs+zac, str.substring(zac), a);
                return;
            }

            char b = str.charAt(inxNasledujuci);
            String s;   //musim skontrolova, ci nahodou sa nezacina zakazanym znakom
            if (jeRiadiaciZnak(b)) {
                inx = inxNasledujuci+1;
                s = b + "";
            }  else {
                inx = TextUtils.najdiKoniecSlova(str, inxNasledujuci);
                s = str.substring( inxNasledujuci, inx );
            }

            while (s.startsWith("\n")) {
                s = s.substring(1);
                super.insertString(fb,offs+zac,"\n", a);
                zac++;
            }

            if ( RezervovaneSlovoEnum.jeZakazaneSlovo(s)) {
                setHighLightStyle(a);
                super.insertString(fb,offs + zac, str.substring( zac, inx) ,  a);
                setNormalStyle(a);
            } else
                super.insertString(fb,offs+zac, str.substring( zac, inx ) ,  a);
        }
    }

    private static boolean jeRiadiaciZnak(char b) {
        return SymbolsEnum.jeSymbol( b);
    }

    private void setHighLightStyle(AttributeSet a) {
        StyleConstants.setForeground( (SimpleAttributeSet) a, new Color(0,0,250));
        StyleConstants.setBold((SimpleAttributeSet) a ,true);
    }

    private void setNormalStyle(AttributeSet a) {
        StyleConstants.setForeground( (SimpleAttributeSet) a, Color.BLACK);
        StyleConstants.setBold((SimpleAttributeSet) a ,false);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document document = fb.getDocument();
        int inx = najdiZaciatokSlova(offset, document);   //presuniem pozornost na zaciatok slova
        super.remove( fb, offset, length);
        int inxKon = najdiKoniecSlova(offset+length, document);
        String str = document.getText(inx, inxKon - inx );
        super.remove( fb, inx, inxKon - inx);
        this.insertString(fb, inx,  str, new SimpleAttributeSet()  );

        jTextPane.setCaretPosition(offset );
    }

    @Override
    public void replace(FilterBypass fb, int offs,
                    int length,   String str, AttributeSet a) throws BadLocationException {

            suZmeny = true;
            if (str.equals("\t")) {
                super.replace(fb, offs, length, "    ", a);
                return;
            }
            Document document = fb.getDocument();
            int inxZac = najdiZaciatokSlova(offs, document);
            int inxKon = najdiKoniecSlova(offs+length, document);
            String s1 = ( offs <= inxZac)? ""  : document.getText( inxZac, offs-inxZac),
                   s2 = ( inxKon <= offs +length) ? "" : document.getText( offs+length, inxKon - offs -length),
                   s =  ( s1+ str + s2);
            super.remove(fb, inxZac, inxKon - inxZac);
            this.insertString(fb, inxZac, s,a);

            jTextPane.setCaretPosition(offs + str.length() );
    }

    public static int najdiZaciatokSlova( int offs , Document doc) {
        if (offs <= 0) return 0;

        int inx =  offs-1;

        while ( inx>=0 )
            try {
                String s = doc.getText(inx,1);
                char a = s.charAt(0);
                if (jePrazdnyZnak(s) || jeRiadiaciZnak(a)  )
                    return (inx+1< doc.getLength()) ? inx+1 : inx;
                else inx --;
            } catch ( BadLocationException exx ) {
                exx.printStackTrace() ;
            }

        return 0;
    }

    private static boolean jePrazdnyZnak(String s) {
        return s.equals("\n") || s.equals(" ");
    }

    private int najdiKoniecSlova( int offs , Document doc) {
        int inx = offs;

        while ( inx < doc.getLength() )
            try {
                String s = doc.getText(inx,1);
                char a = s.charAt(0);
                if (jePrazdnyZnak(s) || jeRiadiaciZnak(a)  )
                    return inx; //(inx-1>0) ? inx-1 : inx;
                else inx++;
            } catch ( BadLocationException exx ) {  exx.printStackTrace() ;   }
        return doc.getLength();
    }

}