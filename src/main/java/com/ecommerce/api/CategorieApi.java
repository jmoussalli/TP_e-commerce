package com.ecommerce.api;

import com.ecommerce.dao.ArticleDAO;
import com.ecommerce.dao.CategorieDAO;
import com.ecommerce.entity.Article;
import com.ecommerce.entity.Categorie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("categories")
public class CategorieApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Categorie> categories = CategorieDAO.findAll();
        if (categories != null) {
            return Response.status(Response.Status.CREATED).entity(categories).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Categories non trouvés").build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.findById(id);
        if (categorie != null) {
            return Response.status(Response.Status.FOUND).entity(categorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Categorie non trouvé").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Categorie categorie) {
        CategorieDAO.save(categorie);
        return Response.status(Response.Status.CREATED).entity(categorie).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.findById(id);
        if (categorie != null) {
            CategorieDAO.delete(id);
            return Response.ok().entity("Categorie supprimé !").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'categorie à supprimer n'existe pas !").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Categorie categorieToUpdate, @PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.findById(id);
        if (categorie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("categorie non trouvée").build();
        } else {
            if (!categorieToUpdate.getId().equals(categorie.getId())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("id categorie non reconnu").build();
            } else {
                CategorieDAO.update(categorieToUpdate);
                return Response.ok(categorieToUpdate).build();
            }
        }
    }

    @PUT
    @Path("/{id}/addArticle")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArticle(Article articleToAdd, @PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.findById(id);
        if (categorie == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("categorie non trouvée").build();
        } else {
            if (ArticleDAO.findById(articleToAdd.getId()) == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("L'article n'existe pas").build();
            } else {
                CategorieDAO.addArticle(articleToAdd);
                return Response.ok(articleToAdd).entity("Article ajouté à la catégorie").build();
            }
        }
    }

}
