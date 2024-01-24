package com.ecommerce.api;

import com.ecommerce.dao.ArticleDAO;
import com.ecommerce.entity.Article;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("articles")
public class ArticleApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Article> articles = ArticleDAO.findAll();
        if (articles != null) {
            return Response.status(Response.Status.CREATED).entity(articles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Articles non trouvés").build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Article article = ArticleDAO.findById(id);
        if (article != null) {
            return Response.status(Response.Status.FOUND).entity(article).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Article non trouvé").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Article article) {
        ArticleDAO.save(article);
        return Response.status(Response.Status.CREATED).entity(article).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Integer id) {
        Article article = ArticleDAO.findById(id);
        if (article != null) {
            ArticleDAO.delete(id);
            return Response.ok().entity("Article supprimé !").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("L'article à supprimer n'existe pas !").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Article articleToUpdate, @PathParam("id") Integer id) {
        Article article = ArticleDAO.findById(id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("article non trouvée").build();
        } else {
            if (!articleToUpdate.getId().equals(article.getId())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("id article non reconnu").build();
            } else {
                ArticleDAO.update(articleToUpdate);
                return Response.ok(articleToUpdate).build();
            }
        }
    }
}
