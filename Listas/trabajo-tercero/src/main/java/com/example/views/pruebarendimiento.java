package com.example.views;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.controls.tda.list.metodosOrdenacion.MergeSort;
import com.example.controls.tda.list.metodosOrdenacion.QuickSort;
import com.example.controls.tda.list.metodosOrdenacion.ShellSort;
import com.example.controls.tda.list.LinkedList;

public class pruebarendimiento {
    public static void main(String[] args) throws Exception {
        // Crear una lista con 10,000 números aleatorios
        LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 25000; i++) {
            list.add(random.nextInt(100000)); // Números aleatorios entre 0 y 100,000
        }

        // Usar la misma lista para cada algoritmo
        LinkedList<Integer> mergeList = cloneList(list);
        LinkedList<Integer> quickList = cloneList(list);
        LinkedList<Integer> shellList = cloneList(list);

        // Instancias de algoritmos
        MergeSort<Integer> mergeSort = new MergeSort<>();
        QuickSort<Integer> quickSort = new QuickSort<>();
        ShellSort<Integer> shellSort = new ShellSort<>();

        // Medir tiempo de ejecución de MergeSort
        long startMerge = System.nanoTime();
        mergeSort.mergeSort(mergeList, "", 0); // Orden ascendente
        long endMerge = System.nanoTime();
        long mergeTime = TimeUnit.MILLISECONDS.convert(endMerge - startMerge, TimeUnit.NANOSECONDS);

        // Medir tiempo de ejecución de QuickSort
        long startQuick = System.nanoTime();
        quickSort.quickSort(quickList, "", 0); // Orden ascendente
        long endQuick = System.nanoTime();
        long quickTime = TimeUnit.MILLISECONDS.convert(endQuick - startQuick, TimeUnit.NANOSECONDS);

        // Medir tiempo de ejecución de ShellSort
        long startShell = System.nanoTime();
        shellSort.shellSort(shellList, "", 0); // Orden ascendente
        long endShell = System.nanoTime();
        long shellTime = TimeUnit.MILLISECONDS.convert(endShell - startShell, TimeUnit.NANOSECONDS);

        System.out.printf(" MergeSort         | %d ms\n", mergeTime);
        System.out.printf(" QuickSort         | %d ms\n", quickTime);
        System.out.printf(" ShellSort         | %d ms\n", shellTime);
    }


    private static LinkedList<Integer> cloneList(LinkedList<Integer> original) throws Exception {
        LinkedList<Integer> clone = new LinkedList<>();
        for (int i = 0; i < original.getSize(); i++) {
            clone.add(original.get(i));
        }
        return clone;
    }
}
