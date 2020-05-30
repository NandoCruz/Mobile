package com.example.p04_gasapp_ads_noite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void deveCalcularValorTotalGasto() {
        MainActivity t1 = new MainActivity();
        assertEquals(24.62,
                t1.valorTotal(60, 1.5, 3.899),
                0.01);
    }
}