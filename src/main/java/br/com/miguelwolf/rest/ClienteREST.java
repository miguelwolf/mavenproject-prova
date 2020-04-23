/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.miguelwolf.rest;

import br.com.miguelwolf.controller.ClienteDAO;
import br.com.miguelwolf.model.Cidade;
import br.com.miguelwolf.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Miguel Wolf
 */
@Path("cliente")
public class ClienteREST {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAll() {
        return ClienteDAO.getmListCliente();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{codigo}")
    public Cliente getByCode(@PathParam("codigo") int codigo) {
        return ClienteDAO.getCliente(codigo);
    }

    @POST
    @Path("/{codigo}/{nome}/{codCidade}/{nomeCidade}")
    public void save(@PathParam("codigo") int codigo, @PathParam("nome") String nome, @PathParam("codCidade") int codCidade, @PathParam("nomeCidade") String nomeCidade) {
        ClienteDAO.persistir(new Cliente(codigo, nome, new Cidade(codCidade, nomeCidade)));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClienteDAO.persistir(mapper.readValue(json, Cliente.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClienteDAO.atualizar(mapper.readValue(json, Cliente.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DELETE
    @Path("/{codigo}")
    public void deleteByCode(@PathParam("codigo") int codigo) {
        ClienteDAO.deletar(new Cliente(codigo));
    }
}
