package dmit2015.resource;


import common.validator.BeanValidator;
import dmit2015.entity.TodoItem;
import dmit2015.repository.TodoItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

/**
 * * Web API with CRUD methods for managing TodoItem.
 *
 *  URI						    Http Method     Request Body		                        Description
 * 	----------------------      -----------		------------------------------------------- ------------------------------------------
 *	/restapi/TodoItems			POST			{"name":"Demo DMIT2015 assignment 1",       Create a new TodoItem
 *                                         	    "complete":false}
 * 	/restapi/TodoItems/{id}		GET			                                                Find one TodoItem with a id value
 * 	/restapi/TodoItems		    GET			                                                Find all TodoItem
 * 	/restapi/TodoItems/{id}      PUT             {"id":5,                                    Update the TodoItem
 * 	                                            "name":"Submitted DMIT2015 assignment 7",
 *                                              "complete":true}
 * 	/restapi/TodoItems/{id}		DELETE			                                            Remove the TodoItem
 *

 curl -i -X GET http://localhost:8080/restapi/TodoItems

 curl -i -X GET http://localhost:8080/restapi/TodoItems/1

 curl -i -X POST http://localhost:8080/restapi/TodoItems \
 -d '{"name":"Finish DMIT2015 Assignment 1","complete":false}' \
 -H 'Content-Type:application/json'

 curl -i -X GET http://localhost:8080/restapi/TodoItems/4

 curl -i -X PUT http://localhost:8080/restapi/TodoItems/4 \
 -d '{"id":4,"name":"Demo DMIT2015 Assignment 1","complete":true}' \
 -H 'Content-Type:application/json'

 curl -i -X GET http://localhost:8080/restapi/TodoItems/4

 curl -i -X DELETE http://localhost:8080/restapi/TodoItems/4

 curl -i -X GET http://localhost:8080/restapi/TodoItems/4

 *
 */

@ApplicationScoped
@Path("TodoItems")                    // All methods of this class are associated this URL path
@Consumes(MediaType.APPLICATION_JSON)    // All methods this class accept only JSON format data
@Produces(MediaType.APPLICATION_JSON)    // All methods returns data that has been converted to JSON format
public class TodoItemResource {

    @Inject
    private TodoItemRepository _todoItemRepository;

    @GET    // This method only accepts HTTP GET requests.
    public Response listTodoItems() {
        return Response.ok(_todoItemRepository.findAll()).build();
    }

    @Path("{id}")
    @GET    // This method only accepts HTTP GET requests.
    public Response findTodoItemById(@PathParam("id") Long todoItemId) {
        TodoItem existingTodoItem = _todoItemRepository.findOptionalById(todoItemId).orElseThrow(NotFoundException::new);

        return Response.ok(existingTodoItem).build();
    }

    @POST    // This method only accepts HTTP POST requests.
    public Response addTodoItem(TodoItem newTodoItem, @Context UriInfo uriInfo) {

        String errorMessage = BeanValidator.validateBean(TodoItem.class, newTodoItem);
        if (errorMessage != null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .build();
        }

        try {
            // Persist the new TodoItem into the database
            _todoItemRepository.add(newTodoItem);
        } catch (Exception ex) {
            // Return a HTTP status of "500 Internal Server Error" containing the exception message
            return Response.
                    serverError()
                    .entity(ex.getMessage())
                    .build();
        }

        // userInfo is injected via @Context parameter to this method
        URI location = uriInfo.getAbsolutePathBuilder()
                .path(newTodoItem.getId() + "")
                .build();

        // Set the location path of the new entity with its identifier
        // Returns an HTTP status of "201 Created" if the TodoItem was successfully persisted
        return Response
                .created(location)
                .build();
    }

    @PUT            // This method only accepts HTTP PUT requests.
    @Path("{id}")    // This method accepts a path parameter and gives it a name of id
    public Response updateTodoItem(@PathParam("id") Long id, TodoItem updatedTodoItem) {
        if (!id.equals(updatedTodoItem.getId())) {
            throw new BadRequestException();
        }

        String errorMessage = BeanValidator.validateBean(TodoItem.class, updatedTodoItem);
        if (errorMessage != null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .build();
        }

        TodoItem existingTodoItem = _todoItemRepository
                .findOptionalById(id)
                .orElseThrow(NotFoundException::new);
        // TODO: copy properties from the updated entity to the existing entity such as copy the version property shown below
        existingTodoItem.setName(updatedTodoItem.getName());
        existingTodoItem.setComplete(updatedTodoItem.isComplete());
        existingTodoItem.setVersion(updatedTodoItem.getVersion());

        try {
            _todoItemRepository.update(existingTodoItem);
        } catch (OptimisticLockException ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("The data you are trying to update has changed since your last read request.")
                    .build();
        } catch (Exception ex) {
            // Return an HTTP status of "500 Internal Server Error" containing the exception message
            return Response.
                    serverError()
                    .entity(ex.getMessage())
                    .build();
        }

        // Returns an HTTP status "200 OK" and include in the body of the response the object that was updated
        return Response.ok(existingTodoItem).build();
    }

    @DELETE            // This method only accepts HTTP DELETE requests.
    @Path("{id}")    // This method accepts a path parameter and gives it a name of id
    public Response delete(@PathParam("id") Long id) {

        TodoItem existingTodoItem = _todoItemRepository
                .findOptionalById(id)
                .orElseThrow(NotFoundException::new);

        try {
            _todoItemRepository.delete(existingTodoItem);    // Removes the TodoItem from being persisted
        } catch (Exception ex) {
            // Return a HTTP status of "500 Internal Server Error" containing the exception message
            return Response
                    .serverError()
                    .encoding(ex.getMessage())
                    .build();
        }

        // Returns an HTTP status "204 No Content" if the TodoItem was successfully deleted
        return Response.noContent().build();
    }

}

