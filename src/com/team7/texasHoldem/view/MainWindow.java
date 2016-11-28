/**
 * Created by Conner on 11/27/16.
 */

package com.team7.texasHoldem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class MainWindow {


    JFrame frame;
    JLabel urlFormLabel;
    JPanel inputPanel, logPanel;
    JFXPanel jfxWebviewPanel;
    JTextField urlForm;
    JButton goButton, showCacheButton, clearCacheButton, helpButton;
    JScrollPane scroll;
    JComboBox<String> replacementAlgorithmsCb;
    JComboBox<Integer> cacheSizeCb;

    SystemLog systemLog = new SystemLog();

    String[] replacementAlgorithms = {"LRU","MRU","RR"};
    Integer[] cacheSize = {1,2,3,4,5};
    ComboBoxParameters cbParams = new ComboBoxParameters(cacheSize, replacementAlgorithms);


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

        //Using Event Dispatch Thread for modifying a Swing components
        jfxWebviewPanel = new JFXPanel();
        //Default scene
        Platform.runLater(() -> { //Interactions with JFXPanel should take place on the JavaFX Application Thread
            WebView webView = new WebView();
            webView.setMaxSize(600.0, 400.0);
            jfxWebviewPanel.setScene(new Scene(webView));
            try {
                webView.getEngine().load("");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        });

        //Instantiate inputPanel interface
        replacementAlgorithmsCb = new JComboBox<>(cbParams.getReplacementAlgorithms());
        replacementAlgorithmsCb.setVisible(true);

        cacheSizeCb = new JComboBox<>(cbParams.getCacheSizeRange());
        cacheSizeCb.setSelectedItem(1);
        cacheSizeCb.setVisible(true);
        cacheSizeCb.addActionListener(new cacheSizeCbListener());

        urlFormLabel = new JLabel("Enter a URL:");
        urlForm = new JTextField(12);
        urlForm.addKeyListener(new URLFormListener());

        goButton = new JButton("Go");
        goButton.addActionListener(new GoButtonListener());

        showCacheButton = new JButton("Show Cache");
        showCacheButton.addActionListener(new ShowCacheButtonListener());

        clearCacheButton = new JButton("Clear Cache");
        clearCacheButton.addActionListener(new ClearCacheButtonListener());

        helpButton = new JButton("Help");
        helpButton.addActionListener(new HelpButtonListener());

        //Append inputPanel interface
        inputPanel.add(urlFormLabel);
        inputPanel.add(urlForm);
        inputPanel.add(replacementAlgorithmsCb);
        inputPanel.add(cacheSizeCb);
        inputPanel.add(goButton);
        inputPanel.add(showCacheButton);
        inputPanel.add(clearCacheButton);
        inputPanel.add(helpButton);

        //Instantiate logPanel interface
        scroll = new JScrollPane(systemLog.getSystemLogTextArea());

        //Append logPanel interface
        logPanel.add(scroll);

        //Add panels to frame
        frame.getContentPane().add(BorderLayout.SOUTH, inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, logPanel);
        frame.getContentPane().add(BorderLayout.NORTH, jfxWebviewPanel);


        //Frame parameters
        frame.setSize(809, 601);
        frame.setTitle("Local Web Cacher - TTD Exercise");
        frame.setResizable(false);
        frame.setVisible(true);
    }

    class URLFormListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode()==KeyEvent.VK_ENTER){
                goButton.doClick();
            }
        }
        @Override
        public void keyReleased(KeyEvent event) {
            //Abstract method stub
        }
        @Override
        public void keyTyped(KeyEvent event) {
            //Abstract method stub
        }
    }

    class GoButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            systemLog.buttonPressed();
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


