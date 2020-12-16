package ru.job4j.tictactoe;

public class FigureO implements Figure {
    private static final int TYPE = 1;

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return "O";
    }


}
