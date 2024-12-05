package com.example.controls.tda.list.metodosOrdenacion;

import com.example.controls.tda.list.LinkedList;
import com.example.models.Familia;

public class QuickSort<E> {

   
    public LinkedList<E> quickSort(LinkedList<E> list, String attribute, Integer order) throws Exception {
        // Convertir la lista enlazada a un array para mejorar el acceso
        E[] array = (E[]) new Object[list.getSize()];
        for (int i = 0; i < list.getSize(); i++) {
            array[i] = list.get(i);
        }

        // Llamar al método QuickSort para arrays
        quickSort(array, 0, array.length - 1, attribute, order);

        // Convertir de vuelta a LinkedList
        LinkedList<E> sortedList = new LinkedList<>();
        for (E element : array) {
            sortedList.add(element);
        }

        return sortedList;
    }

    private void quickSort(E[] array, int low, int high, String attribute, Integer order) throws Exception {
        if (low < high) {
            int pivotIndex = partition(array, low, high, attribute, order);
            quickSort(array, low, pivotIndex - 1, attribute, order);
            quickSort(array, pivotIndex + 1, high, attribute, order);
        }
    }

    private int partition(E[] array, int low, int high, String attribute, Integer order) throws Exception {
        E pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(array[j], pivot, attribute, order)) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
        return obj.getClass().getMethod("get" + capitalize(attribute)).invoke(obj);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static void main(String[] args) {
        LinkedList<Familia> list = new LinkedList<>();
        list.add(new Familia(1, "Pedro", "Armijos", "casa", "0989883012", null, null, null));
        list.add(new Familia(2, "Juan", "Briseño", "casa", "0989883012", null, null, null));
        list.add(new Familia(3, "Maria", "Calva", "casa", "0989883012", null, null, null));
        list.add(new Familia(4, "Jose", "Guaman", "casa", "0989883012", null, null, null));

        QuickSort<Familia> quickSort = new QuickSort<>();
        LinkedList<Familia> sortedList = null;

        try {
            sortedList = quickSort.quickSort(list, "apellido", 0); // Orden ascendente por "apellido"
            System.out.println(sortedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}