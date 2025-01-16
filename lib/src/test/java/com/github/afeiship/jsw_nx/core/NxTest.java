package com.github.afeiship.jsw_nx.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.afeiship.jswork_nx.core.Nx;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class NxTest {

    @Test
    public void testLog() {
        // 捕获 System.out 输出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String message = "This is a log message";
        Nx.log(message);

        String expected = "🌈 [jsw-nx-log]: " + message + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testError() {
        // 捕获 System.err 输出
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        String message = "This is an error message";
        Nx.error(message);

        String expected = "🍎 [jsw-nx-error]: " + message + System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }
}
