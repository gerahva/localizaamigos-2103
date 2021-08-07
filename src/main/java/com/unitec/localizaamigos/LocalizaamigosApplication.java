package com.unitec.localizaamigos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class LocalizaamigosApplication implements  CommandLineRunner{

	@Autowired RepoUsuario repoUsuario;
	public static void main(String[] args) {
		SpringApplication.run(LocalizaamigosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//quitarRegistros("rapidclimate@gmail.com");
	}


	public void quitarRegistros(String email){
	Usuario usuario=	repoUsuario.findById(email).get();
	ArrayList<Localizacion> locas=new ArrayList<>();

     usuario.setLocalizacion(locas);
     repoUsuario.save(usuario);


	}
}
