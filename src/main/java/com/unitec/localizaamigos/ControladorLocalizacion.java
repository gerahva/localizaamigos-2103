package com.unitec.localizaamigos;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ControladorLocalizacion {
    @Autowired RepoUsuario repoUsuario;

   @PostMapping("/localizacion")
    public Estatus guardarLocalizacion(@RequestBody String json)throws  Exception{
       //Verificar que el modelo Usuario en el bck end ya tenga la localizacion
       //Primero leemos el objeto json que es de tipo usuario
       Usuario usuarioLLegado=new ObjectMapper().readValue(json, Usuario.class);
       //Primero checamos que ese usuario ya este registrado, buscandolo en la BD
       Estatus estatus=new Estatus();
             if(repoUsuario.findById(usuarioLLegado.getEmail()).isPresent()){
                //Si se cumple esta condicion esta registrado

         Usuario usuarioGuardado=        repoUsuario.findById(usuarioLLegado.getEmail()).get();
         //Primero obtenemos el historial de localizaciones
          //Primero checamos que exista el arraylist
                 ArrayList<Localizacion> locaGuardadas=new ArrayList<>();
          if(usuarioGuardado.getLocalizacion()!=null)locaGuardadas=usuarioGuardado.getLocalizacion();


           locaGuardadas.add(usuarioLLegado.getLocalizacion().get(0));
           //Agregamos el mensaje del estatus

                 estatus.setSuccess(true);
                 estatus.setMensaje("Localizacion agregada con exito");

             }else{
                //Si llega aqui, no esta registrado.
                 estatus.setSuccess(false);
                 estatus.setMensaje("ESte usuario no esta registrado");
             }
       return estatus;
   }

   /*
   El siguiente servicio se utiliza para verficiar el historial de usuario que tiene localizacion guardada
   y que esten registrados. Este serrvicio REST nos va a sevir para localizar en el mapa a todos los
   usuarios que previamente esten registrados y que ademas ya tengan minimo una localzaicion, ya que son
   los que van a aparecer en el mapa.
    */
    @GetMapping("/localizaciones")
    public ArrayList<Usuario> buscarTodas(){
        //Solamente obtenemos los usuarios registros
           ArrayList<Usuario> usuariosRegistrados=new ArrayList<>();

           for (Usuario usuario: repoUsuario.findAll()){
               if(usuario.getLocalizacion()!=null){
                   usuariosRegistrados.add(usuario);
               }
           }
           return usuariosRegistrados;
    }

}
