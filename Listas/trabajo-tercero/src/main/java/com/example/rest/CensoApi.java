package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.HashMap;
import com.example.controls.dao.FamilaDao;
import com.example.controls.dao.GeneradorDao;
import com.example.controls.dao.services.FamiliaServices;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.tda.stack.Stack;
import com.example.models.Familia;
import com.example.models.Generador;

@Path("person")
public class CensoApi {

    // listar familias
    @Path("/list/familia")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        FamilaDao fd = new FamilaDao();
        map.put("msg", "Ok");
        map.put("data", fd.listAll().toArray());
        if (fd.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    // enumeradores de identificacion
    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        FamiliaServices ps = new FamiliaServices();
        map.put("msg", "Ok");
        map.put("data", ps.getTipos());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    // editar familia
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        FamilaDao fd = new FamilaDao();
        try {
            fd.setFamilia(fd.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", fd.getFamilia());
        if (fd.getFamilia().getId() == null) {
            map.put("data", "No existe la persona con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    // guardar
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        try {
            FamilaDao fd = new FamilaDao();
            fd.getFamilia().setApellido(map.get(("apellido")).toString());
            fd.getFamilia().setNombre(map.get(("nombre")).toString());
            fd.getFamilia().setDireccion(map.get(("direccion")).toString());
            fd.getFamilia().setTelefono(map.get(("fono")).toString());
            fd.getFamilia().setTipo(fd.getTipoIdentificacion(map.get("tipo").toString()));

            fd.getFamilia().setIdentificacion(map.get("identificacion").toString());

            fd.save();
            fd.update();
            res.put("msf", "OK");
            res.put("msg", "Persona registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    // actualizar y guardar la modificacion
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();
        try {
            FamilaDao fd = new FamilaDao();
            fd.setFamilia(fd.get(Integer.parseInt(map.get("id").toString())));
            fd.getFamilia().setApellido(map.get(("apellido")).toString());
            fd.getFamilia().setNombre(map.get(("nombre")).toString());
            fd.getFamilia().setDireccion(map.get(("direccion")).toString());
            fd.getFamilia().setTelefono(map.get(("fono")).toString());
            fd.getFamilia().setTipo(fd.getTipoIdentificacion(map.get("tipo").toString()));

            // fd.getPersona().setIdentificacion(map.get("identificacion").toString());

            fd.update();
            res.put("msf", "OK");
            res.put("msg", "Persona registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    // guardar generador
    @Path("/save/generador/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveGenerador(@PathParam("id") Integer id, HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            GeneradorDao gd = new GeneradorDao();
            FamilaDao fd = new FamilaDao();

            // Obtener la familia por el ID
            Familia familia = fd.get(id);
            if (familia == null) {
                res.put("msg", "No existe la familia con ese identificador");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Crear el generador y configurar sus propiedades
            Generador generador = new Generador();
            generador.setUso(map.get("uso").toString());
            generador.setConsumo(Float.parseFloat(map.get("consumo").toString()));
            generador.setPotencia(Float.parseFloat(map.get("potencia").toString()));
            generador.setCoste(Float.parseFloat(map.get("coste").toString()));

            // Agregar el generador a la lista de generadores de la familia
            if (familia.getGeneradores() == null) {
                familia.setGeneradores(new Stack<Generador>(10));
            }
            familia.getGeneradores().push(generador);

            // Guardar el generador
            gd.setGenerador(generador);
            gd.save();

            // Aquí actualizamos la familia y la guardamos nuevamente
            fd.setFamilia(familia);
            fd.update(); // Asegúrate de que la actualización está guardando correctamente

            res.put("msg", "Generador registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en saveGenerador: " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update/generador")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGenerador(HashMap map) {
        HashMap res = new HashMap<>();
        try {
            GeneradorDao fd = new GeneradorDao();
            fd.setGenerador(fd.get(Integer.parseInt(map.get("id").toString())));
            fd.getGenerador().setUso(map.get("uso").toString());
            fd.getGenerador().setConsumo(Float.parseFloat(map.get("consumo").toString()));
            fd.getGenerador().setPotencia(Float.parseFloat(map.get("potencia").toString()));
            fd.getGenerador().setCoste(Float.parseFloat(map.get("coste").toString()));

            // fd.getPersona().setIdentificacion(map.get("identificacion").toString());

            fd.update();
            res.put("msf", "OK");
            res.put("msg", "Persona registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    // modificar generador
    @Path("/getGeneradores/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneradores(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        FamilaDao fd = new FamilaDao();
        try {
            // Obtener la familia por el ID
            Familia familia = fd.get(id);
            if (familia == null) {
                res.put("msg", "No existe la familia con ese identificador");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Obtener la lista de generadores de la familia
            Stack<Generador> generadores = familia.getGeneradores();
            if (generadores == null || generadores.getSize() == 0) {
                res.put("msg", "La familia no tiene generadores registrados");
                res.put("data", new Object[] {});
            } else {
                res.put("msg", "Ok");
                res.put("data", generadores.toArray());
            }
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
        return Response.ok(res).build();
    }

    @Path("/list/order/{algoritmo}/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ordenarFamilias(@PathParam("algoritmo") String algoritmo, @PathParam("attribute") String attribute,
            @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        map.put("msg", "Ok");

        try {
            LinkedList lista = fs.order(type, attribute, algoritmo);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/search/{method}/{attribute}/{value}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFamilies(@PathParam("method") String method,
            @PathParam("attribute") String attribute,
            @PathParam("value") String value) {
        HashMap<String, Object> res = new HashMap<>();
        FamilaDao fd = new FamilaDao();

        try {
            switch (method.toLowerCase()) {
                case "binary":
                    Familia result = fd.searchByBinary(value, attribute);
                    res.put("msg", "Ok");
                    res.put("data", result != null ? result : "No encontrado");
                    break;
                case "linearbinary":
                    LinkedList<Familia> results = fd.searchByLinearBinary(value, attribute);
                    res.put("msg", "Ok");
                    res.put("data", results.toArray());
                    break;
                default:
                    res.put("msg", "Método de búsqueda no reconocido: " + method);
                    return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

        return Response.ok(res).build();
    }

}