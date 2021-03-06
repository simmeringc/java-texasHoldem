package com.team7.texasHoldem.view;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.text.SimpleDateFormat;

public class SystemLog {

    private static JTextArea systemLogTextArea;

    public SystemLog() {
        systemLogTextArea = new JTextArea(8, 65);
        systemLogTextArea.setLineWrap(true);
        systemLogTextArea.setText("Welcome to Java Texas Hold Em\'");
        DefaultCaret caret = (DefaultCaret) systemLogTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public JTextArea getSystemLogTextArea() {
        return systemLogTextArea;
    }

    public String timeStamp() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
    
    public void dealCards() {
    	systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " Howdy partner, the cards have been dealt. Ante-up 25 chips to start the round city slicker, no cheatin ya hear!");
    }
    public void buttonPressed() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " button pressed");
    }
    public void newDeck() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " finished deck, shuffling new deck");
    }
    public void fold(String s) {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " " + s + " The game is finishing");
    }
    public void raise(String s) {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " " + s + " Raised 25");
    }
    public void flop() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " flop");
    }
    public void turn() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " turn");
    }
    public void river() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " river");
    }
    public void winner(int playerNumber) {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " player" + playerNumber + " is the winner!");
    }
}

