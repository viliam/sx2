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

package sk.wlio.sx2.gui;

import sk.wlio.sx2.beans.reservedwords.enums.ReservedWordEnum;
import sk.wlio.sx2.gui.komponenty.CislovanieRiadkov;
import sk.wlio.sx2.gui.akcie.KlavesnicaListener;
import sk.wlio.sx2.gui.komponenty.SxDocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class FrmSx extends JFrame {

    JTextPane txtEditor = new JTextPane();
    JTextArea txtOutput = new JTextArea();
    Font aktualnyFont = new java.awt.Font("Lucida Console", 0, 16);
    CislovanieRiadkov cislovanieRiadkov;

    public static final FrmSx instatcia = new FrmSx();

    private FrmSx() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        initComponents();
        createLayout();
        addKeyListener();

        this.setVisible( true);
    }

    private void initComponents() {
        StyledDocument styledDoc = txtEditor.getStyledDocument();
        if (styledDoc instanceof AbstractDocument) {
            AbstractDocument  doc = (AbstractDocument)styledDoc;
            doc.setDocumentFilter( new SxDocumentFilter(ReservedWordEnum.getZakazaneSlova(), txtEditor));
        }
        txtEditor.setFont( aktualnyFont);
        txtOutput.setFont( aktualnyFont);

        cislovanieRiadkov = new CislovanieRiadkov( aktualnyFont);
        cislovanieRiadkov.setPreferredHeight(1800);
    }

    private void createLayout() {
        JScrollPane paneEditor = new JScrollPane(txtEditor);
        JScrollPane paneOutput = new JScrollPane( txtOutput);
        paneEditor.setRowHeaderView( cislovanieRiadkov);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(paneEditor);
        splitPane.setRightComponent( paneOutput);
        splitPane.setDividerLocation( this.getHeight() *2/3);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        this.add( splitPane);
    }

    private void addKeyListener() {
        KlavesnicaListener kl = new KlavesnicaListener();
        this.addKeyListener( kl);
        txtEditor.addKeyListener( kl);
        txtOutput.addKeyListener( kl);
    }

    public JTextPane getTxtEditor() {
        return txtEditor;
    }

    public JTextArea getTxtOutput() {
        return txtOutput;
    }

    public static void main(String[] args) {
        FrmSx frm = FrmSx.instatcia;
    }
}
