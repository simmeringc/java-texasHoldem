package com.team7.texasHoldem.view;
import com.team7.texasHoldem.game.*;

import javax.swing.*;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.util.ArrayList;

public class MainWindow {

    public static JFrame frame;
    public static JPanel inputPanel, logPanel, topCardPanel, centerCardPanel, bottomCardPanel, cardPanel, controlPanel, NWPanel, NEPanel , SWPanel, SEPanel, CPanel;
    public static JButton dealButton, foldButton, raiseButton, callButton;
    public static JScrollPane scroll;
    public static JTextPane NWcardPane, NEcardPane, SEcardPane, SWcardPane, CcardPane, NWChipPane, NEChipPane, SWChipPane , SEChipPane, CPot;

    public static void initialize() {
        //Take the menu bar off the JFrame
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        //Set the name of the application menu item
        System.setProperty("apple.awt.application.name", "Texas Hold'em");

        MainWindow mainWindow = new MainWindow();
        mainWindow.buildGUI();
        mainWindow.initializeSound();
        mainWindow.drawFlopInit();

    }

    public static void initializeSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(MainWindow.class.getResource("./../music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void buildGUI() {

        //Create frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create panels
        inputPanel = new JPanel();
        logPanel = new JPanel();

        cardPanel = new JPanel();

        controlPanel = new JPanel();
        topCardPanel = new JPanel();
        bottomCardPanel = new JPanel();
        centerCardPanel = new JPanel();

        dealButton = new JButton("New Game");
        dealButton.addActionListener(new NewGameButtonListener());

        foldButton = new JButton("Fold");
        foldButton.addActionListener(new FoldButtonListener());

        raiseButton = new JButton("Raise");
        raiseButton.addActionListener(new RaiseButtonListener());

        callButton = new JButton("Call");
        callButton.addActionListener(new CallButtonListener());

        //Append inputPanel interface
        //TODO: 

        inputPanel.add(dealButton);
        inputPanel.add(foldButton);
        inputPanel.add(raiseButton);
        inputPanel.add(callButton);

        //Instantiate logPanel interface
//        scroll = new JScrollPane(systemLog.getSystemLogTextArea());

        NWPanel = new JPanel();
        NEPanel = new JPanel();
        SWPanel = new JPanel();
        SEPanel = new JPanel();
        CPanel = new JPanel();

        NWcardPane = new JTextPane();
        NEcardPane = new JTextPane();
        SEcardPane = new JTextPane();
        SWcardPane = new JTextPane();
        CcardPane = new JTextPane();

        NWChipPane = new JTextPane();
        NEChipPane = new JTextPane();
        SWChipPane = new JTextPane();
        SEChipPane = new JTextPane();
        CPot = new JTextPane();

        NWPanel.add(NWcardPane);
        NWPanel.add(NWChipPane);

        NEPanel.add(NEChipPane);
        NEPanel.add(NEcardPane);

        SWPanel.add(SWcardPane);
        SWPanel.add(SWChipPane);

        SEPanel.add(SEChipPane);
        SEPanel.add(SEcardPane);

        CPanel.add(CcardPane);
        CPanel.add(CPot);

        cardPanel.setLayout(new BorderLayout());
        cardPanel.add(topCardPanel, BorderLayout.NORTH);
        cardPanel.add(centerCardPanel, BorderLayout.CENTER);
        cardPanel.add(bottomCardPanel, BorderLayout.SOUTH);

        topCardPanel.setLayout(new BorderLayout());
        topCardPanel.add(NWPanel, BorderLayout.WEST);
        topCardPanel.add(NEPanel, BorderLayout.EAST);

        bottomCardPanel.setLayout(new BorderLayout());
        bottomCardPanel.add(SWPanel, BorderLayout.WEST);
        bottomCardPanel.add(SEPanel, BorderLayout.EAST);

        centerCardPanel.setLayout(new BorderLayout());
        centerCardPanel.add(CPanel, BorderLayout.CENTER);

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(logPanel, BorderLayout.NORTH);
        controlPanel.add(inputPanel, BorderLayout.SOUTH);

        StyledDocument doc = CcardPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        //Playing Field
        Font font1 = new Font("SansSerif", Font.BOLD, 70);
        Font font2 = new Font("SansSerif", Font.BOLD, 40);

        //Card Font
        NWcardPane.setFont(font1);
        NEcardPane.setFont(font1);
        SEcardPane.setFont(font1);
        SWcardPane.setFont(font1);
        CcardPane.setFont(font1);

        //Money Font
        NWChipPane.setFont(font2);
        NEChipPane.setFont(font2);
        SWChipPane.setFont(font2);
        SEChipPane.setFont(font2);
        CPot.setFont(font2);

        //Append logPanel interface
//        logPanel.add(scroll);

        //Add panels to frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER, cardPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, controlPanel);

        //Frame parameters
        frame.setSize(809, 601);
        frame.setTitle("Texas Hold'em");

        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void appendtoPane(JTextPane tp, String msg, java.awt.Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);

    }

    public static void setEditableCards(Boolean bool) {
        NWcardPane.setEditable(bool);
        NEcardPane.setEditable(bool);
        SEcardPane.setEditable(bool);
        SWcardPane.setEditable(bool);
        CcardPane.setEditable(bool);
    }

    public static void setCards(ArrayList<Card> playerCards) {
        MainWindow.setEditableCards(true);
        String card1Rank = playerCards.get(0).getRank();
        String card1Suit = playerCards.get(0).getSuit();
        Color card1Color = playerCards.get(0).getColor();
        String card2Rank = playerCards.get(1).getRank();
        String card2Suit = playerCards.get(1).getSuit();
        Color card2Color = playerCards.get(1).getColor();
        MainWindow.drawCardsSW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
        MainWindow.drawCardsNW("?","", Color.BLACK,"?","",Color.RED);
        MainWindow.drawCardsNE("?","",Color.BLACK,"?","",Color.RED);
        MainWindow.drawCardsSE("?","",Color.BLACK,"?","",Color.RED);
        MainWindow.drawFlopCardsCC("?","",Color.BLACK,"?","",Color.RED,"?","",Color.BLACK);
        MainWindow.setEditableCards(false);
    }


    public static void drawFlopInit() {
        appendtoPane(CcardPane, "\n0", Color.BLACK);
        appendtoPane(CcardPane, "0", Color.RED);
        appendtoPane(CcardPane, "0", Color.BLACK);
    }

    public static void drawCardsNW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        NWcardPane.setText("");
        appendtoPane(NWcardPane, rank1 + suit1, color1);
        appendtoPane(NWcardPane, rank2 + suit2, color2);
    }

    public static void drawCardsNE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        NEcardPane.setText("");
        appendtoPane(NEcardPane, rank1 + suit1, color1);
        appendtoPane(NEcardPane, rank2 + suit2, color2);
    }

    public static void drawCardsSE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        SEcardPane.setText("");
        appendtoPane(SEcardPane, rank1 + suit1, color1);
        appendtoPane(SEcardPane, rank2 + suit2, color2);
    }

    public static void drawCardsSW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        SWcardPane.setText("");

        appendtoPane(SWcardPane, rank1 + suit1, color1);
        appendtoPane(SWcardPane, rank2 + suit2, color2);
    }

    public static void drawFlopCardsCC(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2, String rank3, String suit3, java.awt.Color color3) {
        CcardPane.setText("");
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
        appendtoPane(CcardPane, rank2 + suit2, color2);
        appendtoPane(CcardPane, rank3 + suit3, color3);
    }

    public static void drawChips(String playerChips, String opponent1Chips, String opponent2Chips, String opponent3Chips, String pot) {
        SWChipPane.setText(playerChips);
        NWChipPane.setText(opponent1Chips);
        NEChipPane.setText(opponent2Chips);
        SEChipPane.setText(opponent3Chips);
        CPot.setText(pot);
    }
}