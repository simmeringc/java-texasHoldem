/**
 * Created by Conner on 11/27/16.
 */

package com.team7.texasHoldem.view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class MainWindow {


	JFrame frame2;
    JFrame frame;
    JPanel inputPanel, logPanel, topCardPanel, centerCardPanel, bottomCardPanel, rightCardPanel, cardPanel;
//    JFXPanel jfxWebviewPanel;
    JButton dealButton, showCacheButton, clearCacheButton, helpButton;
    JScrollPane scroll;
    JTextPane cardPane, cardPane2, cardPane3, cardPane4, cardPane5;
    

    SystemLog systemLog = new SystemLog();

    public static void main(String[] args) {
        //Take the menu bar off the JFrame
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        //Set the name of the application menu item
        System.setProperty("apple.awt.application.name", "Local Web Cacher");

        new MainWindow().buildGUI();
    }

    public void buildGUI() {

        //Create frame
    	frame = new JFrame();
    	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create panels
        inputPanel = new JPanel();
        logPanel = new JPanel();
        
        cardPanel = new JPanel();
        
        topCardPanel = new JPanel();
        bottomCardPanel = new JPanel();
        centerCardPanel = new JPanel();

//        //Using Event Dispatch Thread for modifying a Swing components
//        jfxWebviewPanel = new JFXPanel();
//        //Default scene
//        Platform.runLater(() -> { //Interactions with JFXPanel should take place on the JavaFX Application Thread
//            WebView webView = new WebView();
//            webView.setMaxSize(600.0, 400.0);
//            jfxWebviewPanel.setScene(new Scene(webView));
//            try {
//                webView.getEngine().load("");
//            } catch (Exception e) {
//                e.printStackTrace(System.out);
//            }
//        });

        //Instantiate inputPanel interface

        dealButton = new JButton("New Game");
        dealButton.addActionListener(new dealButtonListener());

        showCacheButton = new JButton("Fold");
        showCacheButton.addActionListener(new ShowCacheButtonListener());

        clearCacheButton = new JButton("fuck");
        clearCacheButton.addActionListener(new ClearCacheButtonListener());

        helpButton = new JButton("Help");
        helpButton.addActionListener(new HelpButtonListener());

        //Append inputPanel interface
        //TODO: 
        
        inputPanel.add(dealButton);
        inputPanel.add(showCacheButton);
        inputPanel.add(clearCacheButton);
        inputPanel.add(helpButton);

        //Instantiate logPanel interface
        scroll = new JScrollPane(systemLog.getSystemLogTextArea());


        Font font1 = new Font("SansSerif", Font.BOLD, 70);
        cardPane = new JTextPane();
        cardPane2 = new JTextPane();
        cardPane3 = new JTextPane();
        cardPane4 = new JTextPane();
        cardPane5 = new JTextPane();
        
        cardPanel.setLayout(new BorderLayout());
        cardPanel.add(topCardPanel, BorderLayout.NORTH);
        topCardPanel.setLayout(new BorderLayout());
        topCardPanel.add(cardPane, BorderLayout.EAST);
        topCardPanel.add(cardPane2, BorderLayout.WEST);
        
        cardPanel.add(bottomCardPanel, BorderLayout.SOUTH);
        bottomCardPanel.setLayout(new BorderLayout());
        bottomCardPanel.add(cardPane3, BorderLayout.EAST);
        bottomCardPanel.add(cardPane4, BorderLayout.WEST);
        
        cardPanel.add(centerCardPanel, BorderLayout.CENTER);
        centerCardPanel.setLayout(new BorderLayout());
        centerCardPanel.add(cardPane5, BorderLayout.CENTER);


        StyledDocument doc = cardPane5.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        //bottomCardPanel.add(cardPane2);
        //rightCardPanel.add(cardPane3);

        cardPane.setFont(font1);
        cardPane2.setFont(font1);
        cardPane3.setFont(font1);
        cardPane4.setFont(font1);
        cardPane5.setFont(font1);
        
        appendtoPane(cardPane, "10\u2660 ", Color.BLACK);
        appendtoPane(cardPane, "5\u2665 ", Color.RED);

        appendtoPane(cardPane2, "9\u2663 ", Color.BLACK);
        appendtoPane(cardPane2, "A\u2666 ", Color.RED);
        
        appendtoPane(cardPane3, "4\u2660 ", Color.BLACK);
        appendtoPane(cardPane3, "2\u2665 ", Color.RED);

        appendtoPane(cardPane4, "8\u2663 ", Color.BLACK);
        appendtoPane(cardPane4, "2\u2666 ", Color.RED);
        
        appendtoPane(cardPane5, "\n\nQ", Color.BLACK);
        
        cardPane.setEditable(false);
        cardPane2.setEditable(false);
        cardPane3.setEditable(false);
        cardPane4.setEditable(false);
        cardPane5.setEditable(false);
        
        //Append logPanel interface
        logPanel.add(scroll);
        

        //Add panels to frame
        //frame.getContentPane().add(BorderLayout.CENTER, bottomCardPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, cardPanel);
        //frame.getContentPane().add(BorderLayout.CENTER, logPanel);
        //frame.getContentPane().add(BorderLayout.NORTH, topCardPanel);
       // frame.getContentPane().add(BorderLayout.NORTH, jfxWebviewPanel);


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

	class dealButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            systemLog.dealCards();
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


