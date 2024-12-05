package com.example.views;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.controls.tda.list.LinkedList;
import com.example.controls.tda.list.metodosOrdenacion.QuickSort;


public class pruebaRendimientoBusqueda {
        public static void main(String[] args) throws Exception {
            int[] datos = {10000, 20000, 25000};
    
            System.out.printf("%-15s %-15s %-15s %-15s\n", "Tamaño", "Secuencial (ms)", "Binaria (ms)", "Resultado");
            System.out.println("---------------------------------------------------------------");
    
            for (int numdatos : datos) {
                LinkedList<Integer> listaNumeros = new LinkedList<>();
                Random random = new Random();
    
                // Generar la lista de números aleatorios
                for (int i = 0; i < numdatos; i++) {
                    listaNumeros.add(random.nextInt(100000));
                }
    
                
                listaNumeros.add(99);
                
    
                // Clonar la lista para la búsqueda binaria
                LinkedList<Integer> binaryList = cloneList(listaNumeros);
                sortList(binaryList);
                // Búsqueda secuencial
                long startSequential = System.nanoTime();
                LinkedList<Integer> sequentialResult = listaNumeros.linealBinarySearchNumber(listaNumeros, 99);
                long endSequential = System.nanoTime();
                long sequentialTime = TimeUnit.MILLISECONDS.convert(endSequential - startSequential, TimeUnit.NANOSECONDS);
    
                // Ordenar la lista para la búsqueda binaria
                
    
                // Búsqueda binaria
                long startBinary = System.nanoTime();
                LinkedList<Integer> binaryResult = binaryList.linealBinarySearchNumber(binaryList, 99);
                long endBinary = System.nanoTime();
                long binaryTime = TimeUnit.MILLISECONDS.convert(endBinary - startBinary, TimeUnit.NANOSECONDS);
    
                String result = (sequentialResult != null && binaryResult != null) ? "Encontrado" : "No encontrado";
    
                System.out.printf("%-15d %d ms      %d ms        %-15s\n", numdatos,binaryTime ,sequentialTime, result);
            }
        }
    
        private static LinkedList<Integer> cloneList(LinkedList<Integer> original) throws Exception {
            LinkedList<Integer> clone = new LinkedList<>();
            for (int i = 0; i < original.getSize(); i++) {
                clone.add(original.get(i));
            }
            return clone;
        }
    
        private static void sortList(LinkedList<Integer> list) throws Exception {
            QuickSort sorter = new QuickSort();
            //sorter.quickSort(list, 0, list.getSize() - 1);
            sorter.quickSort(list, "", 0);
        }
    }
    