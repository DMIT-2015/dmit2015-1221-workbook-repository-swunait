package dmit2015.resource;

import dmit2015.dto.TodoItemDto;
import dmit2015.entity.TodoItem;
import dmit2015.repository.TodoItemRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * * Web API with CRUD methods for managing TodoItem.
 *
 *  URI						        Http Method     Request Body		                            Description
 * 	----------------------          -----------		-------------------------------------------     ------------------------------------------
 *	/restapi/TodoItemsDto		    POST	      	{"name":"Demo DMIT2015 assignment 1",           Create a new TodoItem
*                                         	        "complete":false}
 * 	/restapi/TodoItemsDto/{id}		GET			                                                    Find one TodoItem with a id value
 * 	/restapi/TodoItemsDto		    GET			                                                    Find all TodoItem
 * 	/restapi/TodoItemsDto/{id}       PUT             {"id":1,
 * 	                                                "name":"Demo DMIT2015 assignment 1",            Update the TodoItem
 *                                                  "complete":true}
 *
* 	/restapi/TodoItemsDto/{id}		DELETE			                                                Remove the TodoItem
 *

 curl -i -X POST http://localhost:8080/restapi/TodoItemsDto \
 -d '{"name":"Demo DMIT2015 Assignment 1","complete":false}' \
 -H 'Content-Type:application/json'

 curl -i -X GET http://localhost:8080/restapi/TodoItemsDto

 curl -i -X GET http://localhost:8080/restapi/TodoItemsDto/1

 curl -i -X POST http://localhost:8080/restapi/TodoItemsDto/1 \
 -d '{"name":"Demo DMIT2015 Assignment 1","complete":true}' \
 -H 'Content-Type:application/json'

 curl -i -X GET http://localhost:8080/restapi/TodoItemsDto/1

 curl -i -X DELETE http://localhost:8080/restapi/TodoItemsDto/1

 curl -i -X GET http://localhost:8080/restapi/TodoItemsDto/1
 *
 */

@ApplicationScoped
// This is a CDI-managed bean that is created only once during the life cycle of the application
@Path("TodoItemsDto")	        // All methods of this class are associated this URL path
@Consumes(MediaType.APPLICATION_JSON)	// All methods this class accept only JSON format data
@Produces(MediaType.APPLICATION_JSON)	// All methods returns data that has been converted to JSON format
public class TodoItemDtoResource {

    @Inject
    private JsonWebToken _callerPrincipal;
    @Context
    private UriInfo uriInfo;

    @Inject
    private TodoItemRepository todoItemRepository;

    @RolesAllowed({"Sales","IT"})
    @POST   // POST: restapi/TodoItemsDto
    public Response postTodoItem(@Valid TodoItemDto dto) {
        if (dto == null) {
            throw new BadRequestException();
        }

        String errorMessage = validateBean(dto);
        if (errorMessage != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

        TodoItem newTodoItem = mapFromDto(dto);
        todoItemRepository.add(newTodoItem);

        URI todoItemsUri = uriInfo.getAbsolutePathBuilder().path(newTodoItem.getId().toString()).build();
        return Response.created(todoItemsUri).build();
    }

    @GET    // GET: restapi/TodoItemsDto/5
    @Path("{id}")
    public Response getTodoItem(@PathParam("id") Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findOptionalById(id);

        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }
        TodoItem existingTodoItem = optionalTodoItem.get();
        TodoItemDto dto = mapToDto(existingTodoItem);

        return Response.ok(dto).build();
    }

    @GET    // GET: restapi/TodoItemsDto
    public Response getTodoItems() {
        return Response.ok(todoItemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList())).build();
    }

    @PUT    // PUT: restapi/TodoItemsDto/5
    @Path("{id}")
    public Response updateTodoItem(@PathParam("id") Long id, TodoItemDto dto) {
        if (!id.equals(dto.id())) {
            throw new BadRequestException();
        }

        Optional<TodoItem> optionalTodoItem = todoItemRepository.findOptionalById(id);
        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }

        String errorMessage = validateBean(dto);
        if (errorMessage != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

        todoItemRepository.update(mapFromDto(dto));

        return Response.ok(dto).build();
    }

    @RolesAllowed({"IT"})
    @DELETE // DELETE: restapi/TodoItemsDto/5
    @Path("{id}")
    public Response deleteTodoItem(@PathParam("id") Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findOptionalById(id);

        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }

        todoItemRepository.deleteById(id);

        return Response.noContent().build();
    }

    private String validateBean(TodoItemDto dto) {
        String errorMessage = null;
        var constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(dto);
        if (constraintViolations.size() > 0) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            for (ConstraintViolation<TodoItemDto> singleConstraintViolation : constraintViolations) {
                jsonObjectBuilder.add(singleConstraintViolation.getPropertyPath().toString(), singleConstraintViolation.getMessage());
            }
            errorMessage = jsonObjectBuilder.build().toString();
        }
        return errorMessage;
    }

    private TodoItemDto mapToDto(TodoItem todoItem) {
        return new TodoItemDto(todoItem.getId(), todoItem.getName(), todoItem.isComplete());
    }

    private TodoItem mapFromDto(TodoItemDto dto) {
        return new TodoItem(dto.id(), dto.name(), dto.complete());
    }

}
