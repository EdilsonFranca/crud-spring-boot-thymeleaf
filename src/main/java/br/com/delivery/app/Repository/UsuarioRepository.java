package br.com.delivery.app.Repository;

import org.springframework.data.repository.CrudRepository;

import br.com.delivery.app.Model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}