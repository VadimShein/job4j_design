package ru.job4j.tictactoe;

public class FigureX implements Figure {
    private static final int TYPE = 0;

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return "X";
    }

}
