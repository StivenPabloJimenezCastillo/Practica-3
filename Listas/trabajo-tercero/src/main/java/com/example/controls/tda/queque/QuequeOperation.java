package com.example.controls.tda.queque;

import com.example.controls.tda.list.LinkedList;
import com.example.controls.exception.ListEmptyException;
import com.example.controls.exception.OverFlowException;

public class QuequeOperation <E> extends LinkedList<E>{
    private Integer top;

    public QuequeOperation(Integer top) {
        this.top = top;
    }

    public Boolean verify(){
        return getSize().intValue() <= top.intValue();
    }

    public void queque(E dato) throws Exception{
        if (verify()) {
            add(dato, getSize());
        } else {
            throw new OverFlowException("Cola llena");
        }
    }

    public E dequeque() throws Exception{
        if (isEmpty()) {
            throw new ListEmptyException("Cola vacia");
        } else {
            return deleteFirst();
        }
    }

    public void push(E dato) throws Exception{
        if (verify()) {
            add(dato, 0);
        } else {
            throw new OverFlowException("Cola llena");
        }
    }


    
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
