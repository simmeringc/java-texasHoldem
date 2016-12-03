package com.team7.texasHoldem.view;

import javax.swing.*;
import java.awt.Font;

import javax.swing.text.DefaultCaret;
import java.text.SimpleDateFormat;
import java.util.*;

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
    
    public void dealCards(){
    	systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + "Cards have been Dealt");

    	
    }
    public void buttonPressed() {
        systemLogTextArea.setText(systemLogTextArea.getText() + "\n" + timeStamp() + " button pressed");
    }
}

