package com.example.controls.tda.list.metodosOrdenacion;

import com.example.controls.tda.list.LinkedList;
import com.example.models.Familia;
import java.lang.reflect.Method;

public class ShellSort<E> {

    public LinkedList<E> shellSort(LinkedList<E> list, String attribute, int order) throws Exception {
        int size = list.getSize();
        int gap = size / 2;

        while (gap > 0) {
            for (int i = gap; i < size; i++) {
                E currentElement = list.get(i);
                int j = i;

                while (j >= gap && compare(list.get(j - gap), currentElement, attribute, order)) {
                    list.update(list.get(j - gap), j);
                    j -= gap;
                }

                list.update(currentElement, j);
            }
            gap /= 2;
        }

        return list;
    }

    private boolean compare(E a, E b, String attribute, Integer order) throws Exception {
        Object valueA = (a instanceof Integer || a instanceof Double || a instanceof Float) ? a
                : getAttributeValue(a, attribute);
        Object valueB = (b instanceof Integer || b instanceof Double || b instanceof Float) ? b
                : getAttributeValue(b, attribute);

        if (valueA instanceof Comparable && valueB instanceof Comparable) {
            Comparable comparableA = (Comparable) valueA;
            Comparable comparableB = (Comparable) valueB;

            return order == 0 ? comparableA.compareTo(comparableB) <= 0 : comparableA.compareTo(comparableB) > 0;
        }
        throw new IllegalArgumentException("Los atributos no son comparables.");
    }

    private Object getAttributeValue(E obj, String attribute) throws Exception {
        Method method = obj.getClass().getMethod("get" + capitalize(attribute));
        return method.invoke(obj);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void main(String[] args) {
        try {
            LinkedList<Familia> list = new LinkedList<>();
            list.add(new Familia(1, "Pedro", "Armijos", "casa", "0989883012", null, null, null));
            list.add(new Familia(2, "Juan", "Briseño", "casa", "0989883012", null, null, null));
            list.add(new Familia(3, "Maria", "Calva", "casa", "0989883012", null, null, null));
            list.add(new Familia(4, "Jose", "Guaman", "casa", "0989883012", null, null, null));

            ShellSort<Familia> shellSort = new ShellSort<>();
            LinkedList<Familia> sortedList = shellSort.shellSort(list, "apellido", 1); // Orden ascendente
            System.out.println(sortedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
