package com.example.tlaitinen.ohutlevymuokkaaja;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void kerroin_isCorrect()
    {
        assertEquals(KerroinLaskija.getKerroinY(1.0), 0.75, 0.02); // check values
    }
}