package lesson4;

import java.util.Arrays;
import java.util.concurrent.*;

public class MainApp {
    public static final String template = "Time of refilling %1$s items in %2$s thread is %3$s ms";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long oneThreadTime = OneThread();
        System.out.println(String.format(template, SomeMath.size, "main", oneThreadTime));
        int threadCount = 16;
        long multipleThreadsTime = MultipleThreads(threadCount);
        System.out.println(String.format(template, SomeMath.size, "all", multipleThreadsTime));
    }

    public static long OneThread(){
        SomeMath someMath = new SomeMath();
        return someMath.refillAndCalculateTime(someMath.getArr());
    }

    public static long MultipleThreads(int threadCount) throws ExecutionException, InterruptedException {
        long totalTime = 0;

        if (threadCount < 0 || threadCount > SomeMath.size)
            throw new IllegalArgumentException("Число потоков меньше 0 или больше числа элементов в классе SomeMath");
        ExecutorService fixed = Executors.newFixedThreadPool(threadCount);
        SomeMath someMath = new SomeMath();
        int portionSize = SomeMath.size / threadCount;
        for (int i = 0; i < threadCount; i++) {
            final int threadStartIndex = i * portionSize;
            final int threadEndIndex = (i + 1) < threadCount ? ((i + 1) * portionSize - 1) : SomeMath.size;
            float[] threadArr = Arrays.copyOfRange(someMath.getArr(), threadStartIndex, threadEndIndex);
            Future<Long> threadTime = fixed.submit(() ->{
                {
                    return someMath.refillAndCalculateTime(threadArr);
                }
            });

            totalTime += threadTime.get();

            // Из-за нулевого индекса первый элемент теряется. Для всех вариантов кроме последних добавляем 1.
            int itemsRefilled = threadEndIndex - threadStartIndex + 1;
            if ((i + 1) == threadCount)
                itemsRefilled--;

            System.out.println(String.format(template, itemsRefilled, i + 1, threadTime.get()));
        }

        return totalTime;
    }
}
