package lesson2.Big;

import java.util.ArrayList;

public class BigArrayList {
    private ArrayList<Object> arrayList;
    private int mediate = 0;
    private int arrayCapacity = 0;

    public BigArrayList(int arrayDimension){
        if (arrayDimension > 0) {
            arrayCapacity = arrayDimension;
            arrayList = new ArrayList<>(arrayDimension);
            mediate = arrayDimension / 2;
            fillArrayList();
        }
    }

    //Здесь бы отлично подошли делегаты из C# вместо дублирования кода
    public long calculateAccessToMediate(int repeatCount){
        long timeStart = System.currentTimeMillis();

        while (repeatCount-- > 0) {
            Object item = arrayList.get(mediate);
        }

        long timeEnd = System.currentTimeMillis();

        return timeEnd - timeStart;
    }

    public long removeHalfOfItems(){
        long timeStart = System.currentTimeMillis();

        // Так как удаляем половину из середины, то остается половина по краям. Находим первый индекс как первую из деления на четыре части.
        int indexToRemove = arrayCapacity / 4;

        // За середину можно принять медиану.
        int countToRemove = mediate;
        while (countToRemove-- > 0) {
            arrayList.remove(indexToRemove);
        }

        long timeEnd = System.currentTimeMillis();

        return timeEnd - timeStart;
    }

    private void fillArrayList(){
        for (int i = 0; i < arrayCapacity; i++){
            arrayList.add(new Object());
        }
    }
}
