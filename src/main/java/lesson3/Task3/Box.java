package lesson3.Task3;

import lesson3.Task3.Fruits.Fruit;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public Box(){

    }

    public Box(T fruit){
        fruits.add(fruit);
    }

    public Box(Collection<T> baseFruits){
        fruits = new ArrayList<>(baseFruits);
    }

    public void add(T fruit){
        fruits.add(fruit);
    }

    public float getWeight(){
        int size = fruits.size();
        float weight = 0.0f;

        if (size > 0) {
            T fruit = fruits.get(0);
            weight = fruit.getWeight();
        }

        return size * weight;
    }

    public boolean compare(Box<? extends Fruit> anotherBox){
        if (anotherBox == null){
            return false;
        }

        return this.getWeight() - anotherBox.getWeight() < 0.001f;
    }

    public ArrayList<T> moveAllInto(Box<T> anotherBox){
        if (anotherBox == null || anotherBox.fruits.size() == 0) {
            return fruits;
        }

        int anotherFruitsSize = anotherBox.fruits.size();
        int fullSize = (int)((fruits.size() + anotherFruitsSize) * 1.5) + 1;
        ArrayList<T> resultedFruits = new ArrayList<T>(fullSize);

        resultedFruits.addAll(fruits);
        resultedFruits.addAll(anotherBox.fruits);

        fruits = resultedFruits;

        return fruits;
    }
}
