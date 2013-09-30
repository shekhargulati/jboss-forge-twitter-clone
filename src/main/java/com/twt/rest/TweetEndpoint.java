package com.twt.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.twt.model.Tweet;

/**
 * 
 */
@Stateless
@Path("/tweets")
public class TweetEndpoint
{
   @PersistenceContext(unitName = "forge-default")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Tweet entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(TweetEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Tweet entity = em.find(Tweet.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Tweet> findByIdQuery = em.createQuery("SELECT DISTINCT t FROM Tweet t LEFT JOIN FETCH t.postedBy WHERE t.id = :entityId ORDER BY t.id", Tweet.class);
      findByIdQuery.setParameter("entityId", id);
      Tweet entity = findByIdQuery.getSingleResult();
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Tweet> listAll()
   {
      final List<Tweet> results = em.createQuery("SELECT DISTINCT t FROM Tweet t LEFT JOIN FETCH t.postedBy ORDER BY t.id", Tweet.class).getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Tweet entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}