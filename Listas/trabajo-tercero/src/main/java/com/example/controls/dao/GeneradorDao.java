package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Generador;

public class GeneradorDao extends AdapterDao<Generador> {
    private Generador generador;
    private LinkedList listAll;

    public GeneradorDao() {
        super(Generador.class);
    }

    public Generador getGenerador() {
        if (generador == null) {
            generador = new Generador();
        }
        return generador;
    }

    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        generador.setId(id);
        this.persist(this.generador);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getGenerador(), getGenerador().getId() - 1);
        this.listAll = listAll();
        return true;
    }
}
