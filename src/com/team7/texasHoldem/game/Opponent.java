package com.team7.texasHoldem.game;

import java.util.Random;

/**
* Created by Matt on 12/3/16.
*/
public class Opponent extends Player {
    private String[] choices = {"fold", "call", "raise"};
    private Random random = new Random();

    public int getChoice() {
        if (chips > 0) {
            int choiceIndex = random.nextInt(3);
            String choice = choices[choiceIndex];
            if (choice.equals("call")) {
                System.out.println("Raising opponent");

                return 1;
            } else if (choice.equals("raise")){
                System.out.println("Raising opponent");

                return 2 ;
            } else { // fold
                System.out.println("Folding opponent");

                return -1;
            }
        } else {
            System.out.println("Folding opponent");

            return -1;
        }
    }
}
