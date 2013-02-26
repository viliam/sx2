package sk.wlio.sx2.gui;

import sk.wlio.sx2.beans.rezervovaneslova.enums.RezervovaneSlovoEnum;
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
            doc.setDocumentFilter( new SxDocumentFilter(RezervovaneSlovoEnum.getZakazaneSlova(), txtEditor));
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
