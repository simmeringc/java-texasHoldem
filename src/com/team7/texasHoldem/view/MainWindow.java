package com.team7.texasHoldem.view;
import com.team7.texasHoldem.game.Card;
import com.team7.texasHoldem.game.Ranker;
import com.team7.texasHoldem.game.Deck;
import com.team7.texasHoldem.game.Game;
import com.team7.texasHoldem.game.Player;

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
import java.awt.event.*;

public class MainWindow {

    JFrame frame;
    JPanel inputPanel, logPanel, topCardPanel, centerCardPanel, bottomCardPanel, cardPanel, controlPanel, NWPanel, NEPanel , SWPanel, SEPanel, CPanel;
    JButton dealButton, anteButton, foldButton, raiseButton, callButton;
    JScrollPane scroll;
    JTextPane NWcardPane, NEcardPane, SEcardPane, SWcardPane, CcardPane, NWChipPane, NEChipPane, SWChipPane , SEChipPane, CPot;

    SystemLog systemLog = new SystemLog();
    //Ranker ranker = new Ranker();

    Player player1 = new Player(1);
    Player player2 = new Player(2);
    Player player3 = new Player(3);
    Player player4 = new Player(4);
    Game game = new Game(systemLog, player1, player2, player3, player4);
    Deck deck = game.getDeck();

    public static void main(String[] args) {
        //Take the menu bar off the JFrame
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        //Set the name of the application menu item
        System.setProperty("apple.awt.application.name", "Texas Hold'em");

        MainWindow mainWindow = new MainWindow();
        mainWindow.buildGUI();
        mainWindow.initializeSound();
        mainWindow.drawGameInit();
    }

    public void initializeSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("./../music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex){
            ex.printStackTrace();
        }
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
        dealButton.addActionListener(new NewGameButtonListener());

        anteButton = new JButton("Ante");
        anteButton.addActionListener(new AnteButtonListener());

        foldButton = new JButton("Fold");
        foldButton.addActionListener(new FoldButtonListener());

        raiseButton = new JButton("Raise 25");
        raiseButton.addActionListener(new RaiseButtonListener());

        callButton = new JButton("Call");
        callButton.addActionListener(new CallButtonListener());

        //Append inputPanel interface
        //TODO: 

        inputPanel.add(dealButton);
        inputPanel.add(anteButton);
        inputPanel.add(foldButton);
        inputPanel.add(raiseButton);
        inputPanel.add(callButton);

        //Instantiate logPanel interface
        scroll = new JScrollPane(systemLog.getSystemLogTextArea());

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
        logPanel.add(scroll);

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

    private void appendtoPane(JTextPane tp, String msg, java.awt.Color c) {
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

    public void initChips() {
        NWChipPane.setText(player4.resetChips());
        NEChipPane.setText(player3.resetChips());
        SEChipPane.setText(player2.resetChips());
        SWChipPane.setText(player1.resetChips());
        CPot.setText(game.resetPot());
    }

    public void drawChips() {
        NWChipPane.setText(player4.getChips());
        NEChipPane.setText(player3.getChips());
        SEChipPane.setText(player2.getChips());
        SWChipPane.setText(player1.getChips());
        CPot.setText(game.getPot());
    }

    private void drawCardsNW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        NWcardPane.setText("");
        appendtoPane(NWcardPane, rank1 + suit1, color1);
        appendtoPane(NWcardPane, rank2 + suit2, color2);
    }

    private void drawCardsNE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        NEcardPane.setText("");
        appendtoPane(NEcardPane, rank1 + suit1, color1);
        appendtoPane(NEcardPane, rank2 + suit2, color2);
    }

    private void drawCardsSE(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        SEcardPane.setText("");
        appendtoPane(SEcardPane, rank1 + suit1, color1);
        appendtoPane(SEcardPane, rank2 + suit2, color2);
    }

    private void drawCardsSW(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        SWcardPane.setText("");

        appendtoPane(SWcardPane, rank1 + suit1, color1);
        appendtoPane(SWcardPane, rank2 + suit2, color2);
    }
    private void drawCardsCC(String rank1, String suit1, java.awt.Color color1) {
        CcardPane.setText("");
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
    }
    private void drawCardsCC(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2) {
        CcardPane.setText("");
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
        appendtoPane(CcardPane, rank2 + suit2, color2);
    }
    private void drawCardsCC(String rank1, String suit1, java.awt.Color color1, String rank2, String suit2, java.awt.Color color2, String rank3, String suit3, java.awt.Color color3) {
        CcardPane.setText("");
        appendtoPane(CcardPane, "\n" + rank1 + suit1, color1);
        appendtoPane(CcardPane, rank2 + suit2, color2);
        appendtoPane(CcardPane, rank3 + suit3, color3);
    }

    public void revealHands() {
        for (Player player : game.getPlayers()) {
            Card[] cards = player.getCards();
            String card1Rank = cards[0].getRank();
            String card1Suit = cards[0].getSuit();
            java.awt.Color card1Color = cards[0].getColor();
            String card2Rank = cards[1].getRank();
            String card2Suit = cards[1].getSuit();
            java.awt.Color card2Color = cards[1].getColor();
            if (player.getPlayerNumber() == 2) {
                drawCardsNW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
            if (player.getPlayerNumber() == 3) {
                drawCardsNE(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
            if (player.getPlayerNumber() == 4) {
                drawCardsSE(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
        }
    }

    public void drawWinner(Player winner) {
        for (Player player : game.getPlayers()) {
            Card[] cards = player.getCards();
            String card1Rank = cards[0].getRank();
            String card1Suit = cards[0].getSuit();
            java.awt.Color card1Color = Color.GREEN;
            String card2Rank = cards[1].getRank();
            String card2Suit = cards[1].getSuit();
            java.awt.Color card2Color = Color.GREEN;
            if (winner.getPlayerNumber() == 1) {
                drawCardsNW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
            if (winner.getPlayerNumber() == 2) {
                drawCardsNW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
            if (winner.getPlayerNumber() == 3) {
                drawCardsNE(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
            if (winner.getPlayerNumber() == 4) {
                drawCardsSE(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            }
        }
    }

	class NewGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            setEditableCards(true);
            game.setRound(0);
            systemLog.dealCards();
            game.deal();
            Card[] cards = player1.getCards();
            String card1Rank = cards[0].getRank();
            String card1Suit = cards[0].getSuit();
            java.awt.Color card1Color = cards[0].getColor();
            String card2Rank = cards[1].getRank();
            String card2Suit = cards[1].getSuit();
            java.awt.Color card2Color = cards[1].getColor();
            drawCardsSW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            drawCardsNW("?","",Color.BLACK,"?","",Color.RED);
            drawCardsNE("?","",Color.BLACK,"?","",Color.RED);
            drawCardsSE("?","",Color.BLACK,"?","",Color.RED);
            drawCardsCC("?","",Color.BLACK,"?","",Color.RED,"?","",Color.BLACK);
            initChips();
            anteButton.setEnabled(true);
            foldButton.setEnabled(false);
            raiseButton.setEnabled(false);
            callButton.setEnabled(false);
        }
    }


    class AnteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
            for (Player player : game.getActivePlayers()) {
                player.removeChips(25);
                game.increasePot(25);
                drawChips();
            }
            anteButton.setEnabled(false);
            foldButton.setEnabled(true);
            raiseButton.setEnabled(true);
            callButton.setEnabled(true);
        }
    }

    class FoldButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.fold("Player 1");
            game.removeActivePlayer(player1);
            Card[] cards = player1.getCards();
            String card1Rank = cards[0].getRank();
            String card1Suit = cards[0].getSuit();
            java.awt.Color card1Color = Color.GRAY;
            String card2Rank = cards[1].getRank();
            String card2Suit = cards[1].getSuit();
            java.awt.Color card2Color = Color.GRAY;
            System.out.println(card1Rank + card1Suit + card2Rank + card2Suit);
            drawCardsSW(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
            anteButton.setEnabled(false);
            foldButton.setEnabled(false);
            raiseButton.setEnabled(false);
            callButton.setEnabled(false);
        }
    }

    class RaiseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            systemLog.raise("Player1");
            player1.removeChips(25);
            game.increasePot(25);
            drawChips();
            game.increasePot(25);
            drawChips();
            anteButton.setEnabled(false);
            raiseButton.setEnabled(false);
            callButton.setEnabled(true);
        }
    }
    class CallButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (game.getRound() == 0) {
                game.callFlop();
                Card[] cards = game.getTableCards();
                systemLog.flop();
                game.setRound(1);
                String card1Rank = cards[0].getRank();
                String card1Suit = cards[0].getSuit();
                java.awt.Color card1Color = cards[0].getColor();
                drawCardsCC(card1Rank, card1Suit, card1Color);

                return;
            }
            if (game.getRound() == 1) {
                game.betTurn();
                Card[] cards = game.getTableCards();
                systemLog.turn();
                game.setRound(2);
                String card1Rank = cards[0].getRank();
                String card1Suit = cards[0].getSuit();
                java.awt.Color card1Color = cards[0].getColor();
                System.out.println(game.getTableCards());
                String card2Rank = cards[1].getRank();
                String card2Suit = cards[1].getSuit();
                java.awt.Color card2Color = cards[1].getColor();
                System.out.println(card1Rank+ card1Suit + card1Color + card2Rank+ card2Suit + card2Color);
                drawCardsCC(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color);
                return;
            }
            if (game.getRound() == 2) {
                game.betRiver();
                Card[] cards = game.getTableCards();
                systemLog.river();
                game.setRound(3);
                String card1Rank = cards[0].getRank();
                String card1Suit = cards[0].getSuit();
                java.awt.Color card1Color = cards[0].getColor();
                String card2Rank = cards[1].getRank();
                String card2Suit = cards[1].getSuit();
                java.awt.Color card2Color = cards[1].getColor();
                String card3Rank = cards[2].getRank();
                String card3Suit = cards[2].getSuit();
                java.awt.Color card3Color = cards[2].getColor();
                drawCardsCC(card1Rank, card1Suit, card1Color, card2Rank, card2Suit, card2Color, card3Rank, card3Suit, card3Color);
                revealHands();
                Player winner = game.getWinner();
                systemLog.winner(winner.getPlayerNumber());
                drawWinner(winner);
                anteButton.setEnabled(true);
                foldButton.setEnabled(false);
                raiseButton.setEnabled(false);
                callButton.setEnabled(false);
                return;
            }
        }
    }
}


