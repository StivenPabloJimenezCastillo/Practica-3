package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.tda.list.metodosOrdenacion.MergeSort;
import com.example.controls.tda.list.metodosOrdenacion.QuickSort;
import com.example.controls.tda.list.metodosOrdenacion.ShellSort;
import com.example.models.Familia;
import com.example.models.enumerador.TipoIdentificacion;
import com.example.controls.Validacion.Cedula;

public class FamilaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList listAll;

    public FamilaDao() {
        super(Familia.class);
    }

    public Familia getFamilia() {
        if (familia == null) {
            familia = new Familia();
        }
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }
    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        familia.setId(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true;
    }
    /**public Boolean save() throws Exception {
        if (!Cedula.validadorDeCedula(familia.getIdentificacion())) {
            throw new Exception("Cedula invalida");

        }
        Integer id = getListAll().getSize() + 1;
        familia.setId(id);
        this.persist(this.familia);
        this.listAll = listAll();
        return true;
    }*/

    public Boolean update() throws Exception {
        this.merge(getFamilia(), getFamilia().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return TipoIdentificacion.valueOf(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return TipoIdentificacion.values();
    }

    public LinkedList order(Integer type_order, String atributo, String algoritmo) {
        LinkedList listita = getListAll();
        if (!listita.isEmpty()) {
            try {
                switch (algoritmo.toLowerCase()) {
                    case "merge":
                        MergeSort<Familia> mergeSort = new MergeSort<>();
                        listita = mergeSort.mergeSort(listita, atributo, type_order);
                        break;

                    case "quick":
                        QuickSort<Familia> quickSort = new QuickSort<>();
                        listita = quickSort.quickSort(listita, atributo, type_order);
                        break;

                    case "shell":
                        ShellSort<Familia> shellSort = new ShellSort<>();
                        listita = shellSort.shellSort(listita, atributo, type_order);
                        break;

                    default:
                        throw new IllegalArgumentException("Algoritmo no reconocido: " + algoritmo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listita;
    }

    public Familia searchByBinary(String data, String attribute) throws Exception {
        LinkedList<Familia> list = getListAll();
        return LinkedList.busquedaBinaria(list, data, attribute, 0); 
    }
    
    public LinkedList<Familia> searchByLinearBinary(String data, String attribute) throws Exception {
        LinkedList<Familia> list = getListAll();
        return list.linearBinarySearch(data, attribute);
    }
}
