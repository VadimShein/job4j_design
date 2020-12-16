package ru.job4j.tictactoe;

public class PositionOfTest implements Position {
    private final String[] turns;
    private int count = 0;

    public PositionOfTest(String[] turns) {
        this.turns = turns;
    }

    public int[] getPosition() {
        int[] position = new int[2];
        String line = turns[count++];
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
