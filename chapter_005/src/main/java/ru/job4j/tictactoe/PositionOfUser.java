package ru.job4j.tictactoe;

import java.util.Scanner;

public class PositionOfUser implements Position {
    public int[] getPosition() {
        Scanner keyboard = new Scanner(System.in);
        String line = keyboard.nextLine();

        int[] position = new int[2];
        try {
            String[] stringArray = line.split("");
            for (int i = 0; i < 2; i++) {
                position[i] = Integer.parseInt(stringArray[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("incorrect string format!");
        }
        return position;
    }
}
