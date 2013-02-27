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
