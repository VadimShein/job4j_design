package ru.job4j.tictactoe;


public class LogicTicTacToe {
    private Field field;
    private static final int MIN_FIGURES = 5;
    private static final int MAX_FIGURES = 9;
    private Position position;

    public LogicTicTacToe(Field field, Position position) {
        this.field = field;
        this.position = position;
    }

    private boolean checkWin(int startX, int startY, int deltaX, int deltaY, Figure figure) {
        boolean result = true;
        for (int index = 0; index != field.getFigures().length; index++) {
            Figure cell = field.getFigures()[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (cell != figure) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinner(Figure figure) {
        if (field.getFiguresCount() < MIN_FIGURES) {
            return false;
        }
        return this.checkWin(0, 0, 1, 0, figure)
                || checkWin(0, 1, 1, 0, figure)
                || checkWin(0, 2, 1, 0, figure)
                || checkWin(0, 0, 0, 1, figure)
                || checkWin(1, 0, 0, 1, figure)
                || checkWin(2, 0, 0, 1, figure)
                || checkWin(0, 0, 1, 1, figure)
                || checkWin(field.getFigures().length - 1, 0, -1, 1, figure);
    }

    private int[] getPosition() {
        return position.getPosition();
    }

    public void run() {
        boolean win = false;
        int[] position;
        Figure figure;
        Figure figureX = new FigureX();
        Figure figureO = new FigureO();
        figure = figureX;

        System.out.println("game is starting...");
        while (!win) {
            if (field.getFiguresCount() == MAX_FIGURES) {
                break;
            }
            field.printField();
            System.out.println("enter position for: " + figure.getName());

            position = getPosition();
            boolean isAdd = field.addFigure(position[0], position[1], figure);
            win = isWinner(figure);

            if (isAdd & !win) {
                if (figure.getType() == 1) {
                    figure = figureX;
                } else {
                    figure = figureO;
                }
            }
        }
        field.printField();
        if (win) {
            System.out.println("winner is: " + figure.getName());
        } else {
            System.out.println("no more moves available");
        }

    }

    public static void main(String[] args) {
        Field field = new FieldTicTacToe();
        Position position = new PositionOfUser();
        LogicTicTacToe logic = new LogicTicTacToe(field, position);
        logic.run();
    }
}
