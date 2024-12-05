package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.controls.dao.services.FamiliaServices;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Familia;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap<String, Object> mapa = new HashMap<>();
        FamiliaServices personaServices = new FamiliaServices();

        try {
            // Obtener la lista original
            LinkedList<Familia> originalList = personaServices.listAll();
            if (originalList.isEmpty()) {
                mapa.put("msg", "La lista está vacía.");
                return Response.ok(mapa).build();
            }

            System.out.println(personaServices.listAll().toString());
            System.out.println(personaServices.order(0, "nombre", "merge"));
            // Agregar los resultados al mapa de respuesta
            mapa.put("msg", "Operación exitosa");

        } catch (Exception e) {
            mapa.put("msg", "Error durante la operación");
            mapa.put("error", e.getMessage());
        }

        return Response.ok(mapa).build();
    }
}