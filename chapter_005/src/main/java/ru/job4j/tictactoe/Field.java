package ru.job4j.tictactoe;

public interface Field {
    Figure[][] getFigures();
    int getFiguresCount();
    boolean addFigure(int x, int y, Figure figure);
    void printField();
}
