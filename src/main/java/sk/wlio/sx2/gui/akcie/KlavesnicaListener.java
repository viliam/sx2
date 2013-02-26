package sk.wlio.sx2.gui.akcie;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KlavesnicaListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        Akcia akcia = null;
        switch ( e.getKeyCode() ) {
            case KeyEvent.VK_F5 : akcia = new Kontrola();
        }

        if (akcia == null) return;

        akcia.vykonaj();
    }
}
