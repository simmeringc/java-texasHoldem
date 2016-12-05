package com.team7.texasHoldem.view;

import com.team7.texasHoldem.game.*;
import com.team7.texasHoldem.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Matt on 12/4/16.
 */
public class NewGameButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        Runner.initializeGame();
        ButtonController.setNewGamePressedButtons();
    }
}