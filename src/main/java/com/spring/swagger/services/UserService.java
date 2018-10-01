package com.spring.swagger.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.swagger.models.User;
import com.spring.swagger.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * Nuestro servicio se compone de un API (/users/) formado por operaciones sobre
 * usuarios: Buscar todos (/find/all): devuelve un listado con todos los
 * usuarios del sistema. Buscar usuario (/find/{id}): devuelve la información
 * relativa a un usuario (id). Crear usuario (/add): crea un usuario en el
 * sistema. Actualizar usuario (/update/{id}): actualiza la información relativa
 * a un usuario (id). Borrar usuario (/delete/{id}): elimina la información
 * relativa a un usuario (id).
 */
 
@Path("/users")
@Api(
    value = "API-REST PARA USUARIOS", produces = MediaType.APPLICATION_JSON,
    description = "Recursos de usuario."
)
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@POST
    @Path("/login")
    @ApiOperation(
        value = "Login de usuario.", response = User.class,
        httpMethod = "POST", consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        notes = "Servicio que permite el ingreso de usuario al sistema"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                code = 200, message = "Hola Mundo. Ingreso exitoso",
                response = User.class
            ),
            @ApiResponse(code = 400, message = "El usuario no spudo ingresar")
        }
    )
	public Response loginUser(User user){
		user = userRepository.getOne(user.getEmail());
		if (user != null) {
			return Response.ok(User.class).build();
		}else 
			return Response.status(Status.BAD_REQUEST).entity(User.class).build();
	}


	/***************************************************************************
	 * 
	 * 
	 * **********************************************************************/
	@GET
    @Path("/find/all")
    @ApiOperation(
            value = "Devuelve todos los usuarios",
            notes = "Devuelve todos los usuarios del sistema"
    )
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista devuelta con exito"),
            @ApiResponse(code = 401, message = "No estas autorizado para ver este producto"),
            @ApiResponse(code = 403, message = "Se prohíbe acceder al recurso al que intentabas llegar"),
            @ApiResponse(code = 404, message = "El recurso que intentabas alcanzar no se encuentra"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public Response findAll() {
		List<User> userList = userRepository.findAll();
		Response resp = Response
				.ok(userList)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS")
				.build();
		return resp;
    }
 
    @GET
    @Path("/find/{email}")
    @ApiOperation(
            value = "Busca un usuario por email",
            notes = "Devuelve los datos relativos a un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario devuelto con exito"),
            @ApiResponse(code = 401, message = "No estas autorizado para ver este producto"),
            @ApiResponse(code = 403, message = "Se prohíbe acceder al recurso al que intentabas llegar"),
            @ApiResponse(code = 404, message = "El recurso que intentabas alcanzar no se encuentra")
    })
    public Response findById(
            @ApiParam(value = "Email del usuario a buscar", required = true)
            @PathParam("email") String email) {
    	User user = userRepository.getOne(email);
    	if (user != null) {
    		return Response.ok(user).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity(user).build();
		}
		
    }
 
    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Añade un nuevo usuario",
            notes = "Crea un nuevo usuario a partir de un email y password"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario agregado con exito"),
            @ApiResponse(code = 401, message = "No estas autorizado para ver este producto"),
            @ApiResponse(code = 403, message = "Se prohíbe acceder al recurso al que intentabas llegar"),
            @ApiResponse(code = 404, message = "El recurso que intentabas alcanzar no se encuentra"),
            @ApiResponse(code = 500, message = "Error del servidor"),
            @ApiResponse(code = 400, message = "El usuario ya existe")
    })
    public Response addUser(
            @ApiParam(value = "Datos del nuevo usuario", required = true)
            User newUser) {
    	Boolean band = userRepository.existsById(newUser.getEmail());
		if (!band) {
			newUser = userRepository.save(newUser);
			return Response
					.ok(newUser)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.allow("OPTIONS")
					.build();
		}else {
			Response resp = Response.status(Status.BAD_REQUEST).entity(newUser).build();
			return resp;
		}
		
    }
 
   

	@PUT
    @Path("/update/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Actualiza los datos de un uginsuario",
            notes = "Actualiza los datos del usuario que se corresponda con el email. El usuario debe existir"
    )
    public Response updateUser(@PathParam("id") int id,
                               @ApiParam(value = "Datos del usuario a actualizar", required = true)
                               User userToUpdate) {
    	User newUser = userRepository.save(userToUpdate);
		return Response.ok(newUser).build(); 
    }
 
    @DELETE
    @Path("/delete/{email}")
    @ApiOperation(
            value = "Elimina un usuario",
            notes = "Elimina los datos del usuario que se corresponda con el email. El usuario debe existir"
    )
    public Response removeUser(
            @ApiParam(value = "ID del usuario a eliminar", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("email") String email) {
        userRepository.deleteById(email);  
        return Response.noContent().build();
    }
	
}
