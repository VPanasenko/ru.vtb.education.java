package test.lesson3;

import lesson3.MainApp3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test
    public void Task3() {
        boolean task3Result = MainApp3.Task3Compared();
        Assertions.assertEquals(true, task3Result);
    }
}
