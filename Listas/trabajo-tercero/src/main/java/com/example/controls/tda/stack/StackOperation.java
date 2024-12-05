package com.example.controls.tda.stack;
import com.example.controls.exception.ListEmptyException;
import com.example.controls.exception.OverFlowException;
import com.example.controls.tda.list.LinkedList;

public class StackOperation <E> extends LinkedList<E> {
    private Integer top;
    
    public StackOperation(Integer top) {
    this.top = top;
    }

    public Boolean verify(){
        return getSize().intValue() <= top.intValue();
    }

    public void push(E dato) throws Exception{
        if (verify()) {
            add(dato, 0);
        } else{
            throw new OverFlowException("Pila llena");
        }
    }

    public E pop() throws ListEmptyException{
        if (isEmpty()) {
            throw new ListEmptyException("Pila vasia");
        } else {
            return deleteFirst();
        }
    }

    public Integer getTop(){
        return top;
    }

    public void setTop(Integer top){
        this.top = top;
    }
    
    public E[] toArray() {
        // Crear un arreglo del tipo genérico E con el tamaño actual de la pila
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[getSize()];

        // Llenar el arreglo con los elementos de la pila usando el método get
        for (int i = 0; i < getSize(); i++) {
            array[i] = get(i); // Llamar al método get de la clase LinkedList
        }

        return array;
    }

    
}
