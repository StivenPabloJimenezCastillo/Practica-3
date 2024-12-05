package com.example.controls.tda.list;

import java.util.Iterator;
import com.example.controls.exception.ListEmptyException;
import com.example.controls.tda.list.metodosOrdenacion.QuickSort;
import com.example.models.Familia;

import java.lang.reflect.Method;

public class LinkedList<E> implements Iterable<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = header;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E data = current.getInfo();
                current = current.getNext();
                return data;
            }
        };
    }

    public E get(int index) throws IndexOutOfBoundsException {
        Node<E> node = getNode(index); // Usa getNode para obtener el nodo
        return node.getInfo(); // Devuelve la información del nodo
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    private void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            last = help; // También establece `last` si es el primer nodo
        } else {
            help = new Node<>(dato, header);
            header = help;
        }
        this.size++; // Incrementa el tamaño solo una vez aquí
    }

    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(info);
            header = help;
            last = help;
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
        }
        this.size++; // Incrementa el tamaño solo una vez aquí
    }

    public void add(E info) {
        addLast(info);
    }

    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    private Node<E> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites");
        }

        Node<E> current = header;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private E getFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        }
        return last.getInfo();
    }

    public E getLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista Vacia");
        }
        return last.getInfo();
    }

    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, list empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    /*** END BYPOSITION */
    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List data" + "\n");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" ->");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }

    public Node<E> getHeader() {
        return header;
    }

    E[] matrix = null;

    /*
     * public E[] toArray() {
     * if (!isEmpty()) {
     * Class clazz = header.getInfo().getClass();
     * matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
     * Node<E> aux = header;
     * for (int i = 0; i < this.size; i++) {
     * matrix[i] = aux.getInfo();
     * aux = aux.getNext();
     * }
     * 
     * }
     * return matrix;
     * }
     **/

    public E[] toArray() {
        if (isEmpty()) {
            return (E[]) new Object[0]; // Retorna un array vacío si la lista está vacía
        }

        E[] matrix = (E[]) new Object[size];
        Node<E> aux = header;
        int i = 0;

        while (aux != null && i < size) {
            matrix[i++] = aux.getInfo();
            aux = aux.getNext();
        }
        return matrix;
    }

    public LinkedList<E> toList(E[] matrix) {
        reset();
        for (int i = 0; i < matrix.length; i++) {
            this.add(matrix[i]);
        }
        return this;
    }

    public void update(E data, Integer post) throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (post == 0) {
            header.setInfo(data);
        } else if (post == (size - 1)) {
            last.setInfo(data);
        } else {
            Node<E> search = header;
            Integer cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(data);
        }

    }

    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E element = header.getInfo();
            Node<E> aux = header.getNext();
            header = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E delete(Integer post) throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Node<E> preview = getNode(post - 1);
            Node<E> actually = getNode(post);
            E element = preview.getInfo();
            Node<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    }

    private Object exist_attribute(E a, String attribute) throws Exception {
        Method method = null;
        attribute = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        attribute = "get" + attribute;
        for (Method aux : a.getClass().getMethods()) {
            if (aux.getName().contains(attribute)) {
                method = aux;
                break;
            }
        }
        if (method == null) {
            for (Method aux : a.getClass().getSuperclass().getMethods()) {
                if (aux.getName().contains(attribute)) {
                    method = aux;
                    break;
                }
            }
        }
        if (method != null) {
            return method.invoke(a);
        }

        return null;
    }

    public static <E> E busquedaBinaria(LinkedList<E> list, String data, String attribute, Integer order)
            throws Exception {
        QuickSort<E> sorter = new QuickSort<>();
        list = sorter.quickSort(list, attribute, order);
        Integer left = 0;
        Integer right = list.getSize() - 1;

        while (left <= right) {
            Integer mid = (left + right) / 2;
            E midElement = list.get(mid);
            String midValue = list.exist_attribute(midElement, attribute).toString().toLowerCase();
            Integer comparison = midValue.compareTo(data.toLowerCase());

            if (comparison == 0) {
                return midElement;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static Integer busquedaBinariaNumeros(LinkedList<Integer> list, Integer numero) throws Exception {
        QuickSort<Integer> sorter = new QuickSort<>();
        list = sorter.quickSort(list, null, 0);

        Integer left = 0;
        Integer right = list.getSize() - 1;

        while (left <= right) {
            Integer mid = (left + right) / 2;
            Integer midValue = list.get(mid);

            if (midValue.equals(numero)) {
                return midValue;
            } else if (midValue < numero) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public LinkedList<E> linearBinarySearch(String data, String attribute) throws Exception {
        QuickSort<E> sorter = new QuickSort<>();
        LinkedList<E> sortedList = sorter.quickSort(this, attribute, 0); 
    
        LinkedList<E> resultList = new LinkedList<>();
        int left = 0;
        int right = sortedList.getSize() - 1;
    
        while (left <= right) {
            int mid = (left + right) / 2;
    
            E midElement = sortedList.get(mid);
            String midValue = sortedList.exist_attribute(midElement, attribute).toString().toLowerCase();
            int comparison = midValue.compareTo(data.toLowerCase());
    
            if (comparison == 0) {
                for (int i = mid; i < sortedList.getSize(); i++) {
                    E element = sortedList.get(i);
                    String elementValue = sortedList.exist_attribute(element, attribute).toString().toLowerCase();
                    if (elementValue.equals(data.toLowerCase())) {
                        resultList.add(element);
                    } else {
                        break;
                    }
                }
                for (int i = mid - 1; i >= 0; i--) {
                    E element = sortedList.get(i);
                    String elementValue = sortedList.exist_attribute(element, attribute).toString().toLowerCase();
                    if (elementValue.equals(data.toLowerCase())) {
                        resultList.add(element);
                    } else {
                        break;
                    }
                }
                break;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    
        return resultList;
    }    

    public LinkedList<Integer> linealBinarySearchNumber(LinkedList<Integer> list, int target) {
        for (int i = 0; i < list.getSize(); i++) {
            if (list.get(i).equals(target)) {
                LinkedList<Integer> result = new LinkedList<>();
                result.add(list.get(i));
                return result; // Devuelve la lista con el número encontrado
            }
        }
        return null; // Retorna null si no encuentra el número
    }
    
    public static void main(String[] args) {
        LinkedList<Familia> list = new LinkedList<>();
        list.add(new Familia(1, "Pedro", "Armijos", "casa", "0989883012", null, "11500996681", null));
        list.add(new Familia(2, "Juan", "Briseño", "casa", "0989883012", null, "11500996684", null));
        list.add(new Familia(3, "Maria", "Calva", "casa", "0989883012", null, "11500996683", null));
        list.add(new Familia(4, "Juan", "Guaman", "casa", "0989883012", null, "11500996682", null));

        System.out.println("Lista de Familias: ");
        System.out.println(list);

        try {
            LinkedList<Familia> familiasCoincidentes = list.linearBinarySearch("Juan", "nombre");
            if (!familiasCoincidentes.isEmpty()) {
                System.out.println("Familias encontradas:");
                System.out.println(familiasCoincidentes);
            } else {
                System.out.println("No se encontraron familias con el criterio especificado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**try {
            String buscarApellido = "11500996681";
            Familia familiaEncontrada = busquedaBinaria(list, buscarApellido, "cedula", 0);
            if (familiaEncontrada != null) {
                System.out.println("\nFamilia encontrada: " + familiaEncontrada);
            } else {
                System.out.println("\nFamilia no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
