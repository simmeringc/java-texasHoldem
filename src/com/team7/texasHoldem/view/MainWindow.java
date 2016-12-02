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
    JPanel inputPanel, logPanel, topCardPanel, centerCardPanel, bottomCardPanel, cardPanel, controlPanel;
//    JFXPanel jfxWebviewPanel;
    JButton dealButton, showCacheButton, clearCacheButton, helpButton;
    JScrollPane scroll;
    JTextPane NWcardPane, NEcardPane, SEcardPane, SWcardPane, CcardPane;
    

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
        
        controlPanel = new JPanel();
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

        
        //TODO: Button functions. New Game should be disabled when game has started
        //		Raise should be disabled when not enough money
        //		Call should be disabled when blind is above previous bet
        dealButton = new JButton("New Game");
        dealButton.addActionListener(new dealButtonListener());

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
        
        //Unicode suit definitions
        
        String spade = "\u2660";
        String heart = "\u2665";
        String diamond = "\u2666";
        String club = "\u2663";
        
        appendtoPane(NWcardPane, "10" + spade, Color.BLACK);
        appendtoPane(NWcardPane, "5" + heart, Color.RED);

        appendtoPane(NEcardPane, "9" + club, Color.BLACK);
        appendtoPane(NEcardPane, "A" + diamond, Color.RED);
        
        appendtoPane(SEcardPane, "4" + spade, Color.BLACK);
        appendtoPane(SEcardPane, "2" + heart, Color.RED);

        appendtoPane(SWcardPane, "8" + club, Color.BLACK);
        appendtoPane(SWcardPane, "2" + diamond, Color.RED);
        
        appendtoPane(CcardPane, "\n10" + club + " ", Color.BLACK);
        appendtoPane(CcardPane, "A" + heart + " ", Color.RED);
        appendtoPane(CcardPane, "Q" + spade + " ", Color.BLACK);

        
        NWcardPane.setEditable(false);
        NEcardPane.setEditable(false);
        SEcardPane.setEditable(false);
        SWcardPane.setEditable(false);
        CcardPane.setEditable(false);
        
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


