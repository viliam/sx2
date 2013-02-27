/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.wlio.sx2.gui.komponenty;


import javax.swing.*;
import java.awt.*;

public class CislovanieRiadkov extends JComponent {

    private Font font;
    private int sirkaFontu = -1;
    private int vyskaFontu = -1;

    public CislovanieRiadkov( Font font) {
        this.font = font;
    }

    public void setPreferredHeight(int ph) {
        int i=  (sirkaFontu== -1)? 25 : sirkaFontu;
//System.out.println(sirkaFontu);
        setPreferredSize(new Dimension(i, ph));
    }

    protected void paintComponent(Graphics g) {
        sirkaFontu = g.getFontMetrics(font).charWidth('0');
        Rectangle zobrazene = g.getClipBounds();

        g.setColor(new Color(205, 219, 237));
        g.fillRect(zobrazene.x, zobrazene.y, zobrazene.width, zobrazene.height);
        g.setFont( font.deriveFont( Font.BOLD, font.getSize()) );
        g.setColor(Color.black);


        int end = 0, start = 0, i=0;  //i je cislo riadku a vyskaFontu vyska fontu

        start = (i =(zobrazene.y / (vyskaFontu = g.getFontMetrics().getHeight()))) * vyskaFontu;
        end = (((zobrazene.y + zobrazene.height) / vyskaFontu) + 1)* vyskaFontu;
        for (int j = start; j<end; j+=vyskaFontu) {
            g.drawString(Integer.toString(i),0,j);
            i++;
        }
    }

}