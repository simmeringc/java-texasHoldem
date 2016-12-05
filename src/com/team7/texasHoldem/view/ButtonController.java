package com.team7.texasHoldem.view;

import com.team7.texasHoldem.game.*;

/**
 * Created by Matt on 12/4/16.
 */
public class ButtonController {

    public static void resetAllButtons () {
        MainWindow.dealButton.setEnabled(true);
        MainWindow.foldButton.setEnabled(false);
        MainWindow.raiseButton.setEnabled(false);
        MainWindow.callButton.setEnabled(false);
    }

    public static void setNewGamePressedButtons() {
        MainWindow.dealButton.setEnabled(true);
        MainWindow.foldButton.setEnabled(false);
        MainWindow.raiseButton.setEnabled(false);
        MainWindow.callButton.setEnabled(false);
    }
}
