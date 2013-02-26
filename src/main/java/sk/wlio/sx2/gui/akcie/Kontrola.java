package sk.wlio.sx2.gui.akcie;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.gui.FrmSx;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;

public class Kontrola implements Akcia {

    public void vykonaj() {
        FrmSx frm = FrmSx.instatcia;

        String text = frm.getTxtEditor().getText();
        TextContext tC = new TextContext( text);
        try {
            Readers.blok().citaj( tC);
        } catch (SxException e) {
            frm.getTxtOutput().setText( e.toString());
        }
    }
}
