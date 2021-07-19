package test.lesson6;

import lesson6.MainApp6;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TasksTest {
    // https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void Task5Test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method task5Method = MainApp6.class.getDeclaredMethod("Task5");

        task5Method.setAccessible(true);
        task5Method.invoke(MainApp6.class);
        task5Method.setAccessible(false);

        Assertions.assertEquals("Сумма четных чисел от 100 до 200 равна 7650", outContent.toString().trim());
    }

}
