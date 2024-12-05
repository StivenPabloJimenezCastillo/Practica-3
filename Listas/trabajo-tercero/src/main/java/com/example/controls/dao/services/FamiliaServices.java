package com.example.controls.dao.services;

import com.example.controls.dao.FamilaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Familia;
import com.example.models.enumerador.TipoIdentificacion;

public class FamiliaServices {
    private FamilaDao obj;

    public FamiliaServices() {
        obj = new FamilaDao();
    }
    
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Familia getFamilia() {
        return obj.getFamilia();
    }

    public void setFamilia(Familia familia) {
        obj.setFamilia(familia);
    }


    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return obj.getTipoIdentificacion(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }
    
    public Familia get(Integer id) throws Exception{
        return obj.get(id);
    }

    public LinkedList order(Integer type_order, String atributo, String algoritmo){
        return obj.order(type_order, atributo, algoritmo);
    }
}
