package com.team7.texasHoldem.view;

import com.team7.texasHoldem.game.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Matt on 12/4/16.
 */
public class FoldButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("Folding player");
        Game.player.setChoice(-1);
    }
}