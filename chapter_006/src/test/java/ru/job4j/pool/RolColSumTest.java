package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class RolColSumTest {

    @Test
    public  void thenSerialSum() {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};

        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[0].getRowSum(), is(3));
        assertThat(sums[0].getColSum(), is(4));
    }

    @Test
    public  void thenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};

        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[0].getRowSum(), is(3));
        assertThat(sums[0].getColSum(), is(4));
    }
}
