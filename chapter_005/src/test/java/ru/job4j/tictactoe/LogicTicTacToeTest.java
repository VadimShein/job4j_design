package ru.job4j.tictactoe;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;

public class LogicTicTacToeTest {

    @Test
    public void whenAddOneFigure() {
        Field field = new FieldTicTacToe();
        field.addFigure(0, 0, new FigureX());
        Assert.assertThat(field.getFiguresCount(), is(1));
    }

    @Test
    public void whenWinXVertical() {
        Field field = new FieldTicTacToe();
        String[] turns = new String[]{"00", "01", "10", "11", "20"};
        Position position = new PositionOfTest(turns);
        LogicTicTacToe logic = new LogicTicTacToe(field, position);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        logic.run();
        Assert.assertThat(out.toString().endsWith("winner is: X\r\n"), is(true));
    }

    @Test
    public void whenWinOHorizontal() {
        Field field = new FieldTicTacToe();
        String[] turns = new String[]{"00", "10", "02", "11", "20", "12"};
        Position position = new PositionOfTest(turns);
        LogicTicTacToe logic = new LogicTicTacToe(field, position);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        logic.run();
        Assert.assertThat(out.toString().endsWith("winner is: O\r\n"), is(true));
    }

    @Test
    public void whenWinXDiagonal() {
        Field field = new FieldTicTacToe();
        String[] turns = new String[]{"00", "10", "11", "01", "22", "12"};
        Position position = new PositionOfTest(turns);
        LogicTicTacToe logic = new LogicTicTacToe(field, position);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        logic.run();
        Assert.assertThat(out.toString().endsWith("winner is: X\r\n"), is(true));
    }

    @Test
    public void whenAllTurnsAndNotWin() {
        Field field = new FieldTicTacToe();
        String[] turns = new String[]{"00", "01", "02", "10", "12", "11", "20", "22", "21"};
        Position position = new PositionOfTest(turns);
        LogicTicTacToe logic = new LogicTicTacToe(field, position);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        logic.run();
        Assert.assertThat(out.toString().endsWith("no more moves available\r\n"), is(true));
    }
}
