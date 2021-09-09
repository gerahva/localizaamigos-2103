package com.unitec.localizaamigos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Un controlador en arquitectura estilo REST es aquel  que encapsula todas
als operaciones que van a ser requeridas por el cliente(Android) tales  como
Guardar un usuario(registro), buscarlo, borrarlo, o actualizarlo
 */
@RestController
@RequestMapping("/api")
public class ControladorUsuario {
   //Esta anotacion es la inyeccion d dependencias de nuestro repo de mongo
   @Autowired RepoUsuario repoUsuario;

   //A cntinuacion siguen los metodos  de acceso a la base datos
   //Empezamos con buscar Todos los usuarios que estan en la base de datos.
   @GetMapping("/usuario")
    public List<Usuario> buscarTodos(){

       return repoUsuario.findAll();
   }

   //aYUDA DEL PUNTO 7
   @PostMapping("/usuario")
   public Estatus registrar(@RequestBody String json)throws  Exception{
       //Esta clase sirve para mapear un objeto json a Java y vice-versa
      ObjectMapper objectMapper=new ObjectMapper();

     Usuario usuario= objectMapper.readValue(json, Usuario.class);
      repoUsuario.save(usuario);
       System.out.println("LLego el objeto que es el usuaio a regustrar"+json);

      Estatus estatus=new Estatus();
      estatus.setMensaje("Registro realizado con exito, todo bien!!");
      estatus.setSuccess(true);
      return estatus;
   }

}
