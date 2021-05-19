package lesson2.Big;

import java.util.LinkedList;

public class BigLinkedList {
    private LinkedList<Object> linkedList;
    private int mediate = 0;
    private int linkedCapacity = 0;

    public BigLinkedList(int arrayDimension){
        if (arrayDimension > 0) {
            linkedCapacity = arrayDimension;
            linkedList = new LinkedList();
            mediate = arrayDimension / 2;
            fillLinkedList();
        }
    }

    public long calculateAccessToMediate(int repeatCount){
        long timeStart = System.currentTimeMillis();

        while (repeatCount-- > 0) {
            Object item = linkedList.get(mediate);
        }

        long timeEnd = System.currentTimeMillis();

        return timeEnd - timeStart;
    }

    public long removeHalfOfItems(){
        long timeStart = System.currentTimeMillis();

        // Так как удаляем половину из середины, то остается половина по краям. Находим первый индекс как первую из деления на четыре части.
        int indexToRemove = linkedCapacity / 4;

        // За середину можно принять медиану.
        int countToRemove = mediate;
        while (countToRemove-- > 0) {
            linkedList.remove(indexToRemove);
        }

        long timeEnd = System.currentTimeMillis();

        return timeEnd - timeStart;
    }

    private void fillLinkedList(){
        for (int i = 0; i < linkedCapacity; i++){
            linkedList.add(new Object());
        }
    }
}
