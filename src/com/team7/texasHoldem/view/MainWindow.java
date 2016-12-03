package com.team7.texasHoldem.view;
//import com.sun.tools.corba.se.idl.Arguments;
import com.team7.texasHoldem.game.Card;
import com.team7.texasHoldem.game.Deck;
import com.team7.texasHoldem.game.Game;
import com.team7.texasHoldem.game.Player;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;

public class MainWindow {

    JFrame frame;
    JPanel inputPanel, logPanel, topCardPanel, centerCardPanel, bottomCardPanel, cardPanel, controlPanel;
    JButton dealButton, showCacheButton, clearCacheButton, helpButton;
    JScrollPane scroll;
    JTextPane NWcardPane, NEcardPane, SEcardPane, SWcardPane, CcardPane;


    SystemLog systemLog = new SystemLog();

    Player player1 = new Player();
    Player player2 = new Player();
    Player player3 = new Player();
    Player player4 = new Player();
    Game game = new Game(player1, player2, player3, player4);
    Deck deck = game.getDeck();

    public static void main(String[] args) {
        //Take the menu bar off the JFrame
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        //Set the name of the application menu item
        System.setProperty("apple.awt.application.name", "Texas Hold'em");

        MainWindow mainWindow = new MainWindow();
        mainWindow.buildGUI();
        mainWindow.drawGameInit();
    }

    public void buildGUI() {

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

        //TODO: Button functions. New Game should be disabled when game has started
        //		Raise should be disabled when not enough money
        //		Call should be disabled when blind is above previous bet
        dealButton = new JButton("New Game");
        dealButton.addActionListener(new newGameButtonListener());

        showCacheButton = new JButton("Fold");
        showCacheButton.addActionListener(new ShowCacheButtonListener());

        clearCacheButton = new JButton("Raise");
        clearCacheButton.addActionListener(new ClearCacheButtonListener());

        helpButton = new JButton("Call");
        helpButton.addActionListener(new HelpButtonListener());

        //Append inputPanel interface
        //TODO: 

        inputPanel.add(dealButton);
        inputPanel.add(showCacheButton);
        inputPanel.add(clearCacheButton);
        inputPanel.add(helpButton);

        //Instantiate logPanel interface
        scroll = new JScrollPane(systemLog.getSystemLogTextArea());

        //Playing Field
        Font font1 = new Font("SansSerif", Font.BOLD, 70);
        NWcardPane = new JTextPane();
        NEcardPane = new JTextPane();
        SEcardPane = new JTextPane();
        SWcardPane = new JTextPane();
        CcardPane = new JTextPane();

        cardPanel.setLayout(new BorderLayout());
        cardPanel.add(topCardPanel, BorderLayout.NORTH);
        topCardPanel.setLayout(new BorderLayout());
        topCardPanel.add(NWcardPane, BorderLayout.EAST);
        topCardPanel.add(NEcardPane, BorderLayout.WEST);

        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(logPanel, BorderLayout.NORTH);
        controlPanel.add(inputPanel, BorderLayout.SOUTH);

        cardPanel.add(bottomCardPanel, BorderLayout.SOUTH);
        bottomCardPanel.setLayout(new BorderLayout());
        bottomCardPanel.add(SEcardPane, BorderLayout.EAST);
        bottomCardPanel.add(SWcardPane, BorderLayout.WEST);

        cardPanel.add(centerCardPanel, BorderLayout.CENTER);
        centerCardPanel.setLayout(new BorderLayout());
        centerCardPanel.add(CcardPane, BorderLayout.CENTER);


        StyledDocument doc = CcardPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        //Large Font
        NWcardPane.setFont(font1);
        NEcardPane.setFont(font1);
        SEcardPane.setFont(font1);
        SWcardPane.setFont(font1);
        CcardPane.setFont(font1);

        //Append logPanel interface
        logPanel.add(scroll);

        //Add panels to frame
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER, cardPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, controlPanel);

        //Frame parameters
        frame.setSize(809, 601);
        frame.setTitle("Local Web Cacher - TTD Exercise");

        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void appendtoPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);

    }
    private void setEditableCards(Boolean bool) {
        NWcardPane.setEditable(bool);
        NEcardPane.setEditable(bool);
        SEcardPane.setEditable(bool);
        SWcardPane.setEditable(bool);
        CcardPane.setEditable(bool);
    }

    private void drawGameInit() {
        appendtoPane(NWcardPane, "0", Color.BLACK);
        appendtoPane(NWcardPane, "0", Color.RED);

        appendtoPane(NEcardPane, "0", Color.BLACK);
        appendtoPane(NEcardPane, "0", Color.RED);

        appendtoPane(SEcardPane, "0", Color.BLACK);
        appendtoPane(SEcardPane, "0", Color.RED);

        appendtoPane(SWcardPane, "0", Color.BLACK);
        appendtoPane(SWcardPane, "0", Color.RED);

        appendtoPane(CcardPane, "\n0", Color.BLACK);
        appendtoPane(CcardPane, "0", Color.RED);
        appendtoPane(CcardPane, "0", Color.BLACK);
    }

    private void drawCardsNW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        appendtoPane(NWcardPane, rank1 + suit1, color1);
        appendtoPane(NWcardPane, rank2 + suit2, color2);
    }

    private void drawCardsNE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        appendtoPane(NEcardPane, rank1 + suit1, color1);
        appendtoPane(NEcardPane, rank2 + suit2, color2);
    }

    private void drawCardsSE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        appendtoPane(SEcardPane, rank1 + suit1, color1);
        appendtoPane(SEcardPane, rank2 + suit2, color2);
    }

    private void drawCardsSW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        System.out.println(rank1+suit1+color1+rank2+suit2+color2);
        appendtoPane(SWcardPane, rank1 + suit1, color1);
        appendtoPane(SWcardPane, rank2 + suit2, color2);
    }
    private void drawCardsCC1(String rank1, String suit1, java.awt.Color color1) {
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
    }
    private void drawCardsCC2(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
        appendtoPane(CcardPane, rank2 + suit2, color2);
    }
    private void drawCardsCC3(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2, String rank3, String suit3, java.awt.Color color3) {
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
        appendtoPane(CcardPane, rank2 + suit2, color2);
        appendtoPane(CcardPane, rank3 + suit3, color3);
    }

	class newGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            setEditableCards(true);
            systemLog.dealCards();
            game.deal();
            Card[] cards = player1.getCards();
            String card1Rank = cards[0].getRank();
            String card1Suit = cards[0].getSuit();
            player1.printCards();
            java.awt.Color card1Color = cards[0].getColor();
            String card2Rank = cards[1].getRank();
            String card2Suit = cards[1].getSuit();
            java.awt.Color card2Color = cards[1].getColor();
            drawCardsSW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            setEditableCards(false);
        }
    }

    class ShowCacheButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
        }
    }

    class cacheSizeCbListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
        }
    }

    class ClearCacheButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
        }
    }
    class HelpButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
        }
    }
}


