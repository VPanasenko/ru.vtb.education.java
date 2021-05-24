package lesson4;

public class SomeMath {
    public static final int size = 100000000;

    private float[] arr = new float[size];

    public SomeMath(){
        for(int i=0; i< size; i++){
            arr[i] = 1.0f;
        }
    }

    public float[] getArr() {
        return arr;
    }

    public long refillAndCalculateTime(float[] inArray){
        long totalTime = 0;

        if (inArray != null) {
            for (int i = 0; i < inArray.length; i++) {
                long start = System.currentTimeMillis();
                // См. комментарий к методу ниже.
                // arr[i] = calculateNewValue(i, arr[i]);
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                long operationLength = System.currentTimeMillis() - start;
                totalTime += operationLength;
            }
        }

        return totalTime;
    }

    // Можно ли было считать передачу значений в отдельный метод как удовлетвроение п. 4 из условий задания?
    // Или требовалось как реализовано выше?
    //    private float calculateNewValue(int index, float value){
    //        return (float)(value * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
    //    }


}
