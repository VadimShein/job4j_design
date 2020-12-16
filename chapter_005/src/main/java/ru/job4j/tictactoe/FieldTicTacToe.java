package ru.job4j.tictactoe;


public class FieldTicTacToe implements Field {
    private final int sizeOfField = 3;
    private final Figure[][] figures = new Figure[sizeOfField][sizeOfField];
    private int figuresCount = 0;

    public Figure[][] getFigures() {
        return figures;
    }

    public int getFiguresCount() {
        return figuresCount;
    }

    public boolean addFigure(int x, int y, Figure figure) {
        boolean rsl = true;
        if (figures[x][y] == null) {
            figures[x][y] = figure;
            figuresCount++;
        } else {
            System.out.println("position is busy...");
            rsl = false;
        }
        return rsl;
    }

    public void printField() {
        System.out.print(" ");
        for (int i = 0; i < sizeOfField; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        int line = 0;
        for (Figure[] figure : figures) {
            System.out.print(line);
            line++;
            for (Figure fig : figure) {
                if (fig != null) {
                    System.out.print("|" + fig.getName());
                } else {
                    System.out.print("| ");
                }
            }
            System.out.print("|" + "\n");
        }
    }
}
