package com.unitec.localizaamigos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoUsuario extends MongoRepository<Usuario, String> {
}
