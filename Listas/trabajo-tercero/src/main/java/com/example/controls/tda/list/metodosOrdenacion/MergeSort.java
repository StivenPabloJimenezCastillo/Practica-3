package com.example.controls.tda.list.metodosOrdenacion;

import com.example.controls.tda.list.LinkedList;
import com.example.models.Familia;

public class MergeSort<E> {

    public LinkedList<E> mergeSort(LinkedList<E> list, String attribute, Integer order) throws Exception {
        if (list.getSize() <= 1) {
            return list;
        }
        int middle = list.getSize() / 2;
        LinkedList<E> left = new LinkedList<>();
        LinkedList<E> right = new LinkedList<>();

        for (int i = 0; i < middle; i++) {
            left.add(list.get(i));
        }
        for (int i = middle; i < list.getSize(); i++) {
            right.add(list.get(i));
        }
        left = mergeSort(left, attribute, order);
        right = mergeSort(right, attribute, order);
        return merge(left, right, attribute, order);
    }

    private LinkedList<E> merge(LinkedList<E> left, LinkedList<E> right, String attribute, Integer order)
            throws Exception {
        LinkedList<E> resultado = new LinkedList<>();
        int i = 0, j = 0;
        while (i < left.getSize() && j < right.getSize()) {
            E leftElement = left.get(i);
            E rightElement = right.get(j);

            if (compare(leftElement, rightElement, attribute, order)) {
                resultado.add(leftElement);
                i++;
            } else {
                resultado.add(rightElement);
                j++;
            }
        }
        while (i < left.getSize()) {
            resultado.add(left.get(i));
            i++;
        }
        while (j < right.getSize()) {
            resultado.add(right.get(j));
            j++;
        }
        return resultado;
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

        MergeSort<Familia> mergeSort = new MergeSort<>();
        LinkedList<Familia> listaOrdenada = null;
        try {
            listaOrdenada = mergeSort.mergeSort(list, "apellido", 0);
            System.out.println(listaOrdenada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
/**public static void main(String[] args) {
    // Crear una lista de 100 números aleatorios entre 0 y 1000
    LinkedList<Integer> listaNumeros = new LinkedList<>();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
        listaNumeros.add(random.nextInt(1000));
    }

    System.out.println("Lista original: " + listaNumeros);

    // Ordenar la lista usando MergeSort
    MergeSort<Integer> mergeSort = new MergeSort<>();
    LinkedList<Integer> listaOrdenada = null;
    try {
        listaOrdenada = mergeSort.mergeSort(listaNumeros, "", 0); // Orden ascendente
        System.out.println("Lista ordenada: " + listaOrdenada);
    } catch (Exception e) {
        e.printStackTrace();
    }
} */    

}