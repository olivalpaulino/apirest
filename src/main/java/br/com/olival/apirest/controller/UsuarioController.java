package br.com.olival.apirest.controller;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.olival.apirest.entity.Usuario;
import br.com.olival.apirest.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
    public List<String> getUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<String> nomes = new ArrayList<>();
		
		for(int i=0; i<usuarios.size(); i++) {
			nomes.add(usuarios.get(i).getNome());
		}
		
        return nomes;
    }
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
	    Optional<Usuario> usuario = usuarioRepository.findById(id);

	    if (usuario.isPresent()) {
	        usuarioRepository.deleteById(id);
	        return ResponseEntity.ok("Usuário com ID " + id + " foi deletado com sucesso!");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
	    Usuario novoUsuario = usuarioRepository.save(usuario);

	    return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
	    Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

	    if (usuarioExistente.isPresent()) {
	        Usuario usuarioAtualizado = usuarioExistente.get();
	        usuarioAtualizado.setNome(usuario.getNome());
	        usuarioAtualizado.setSexo(usuario.getSexo());
	        usuarioAtualizado.setLogin(usuario.getLogin());
	        usuarioAtualizado.setSenha(usuario.getSenha());
	        // adicione outras atualizações que você desejar aqui

	        Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);
	        return ResponseEntity.ok(usuarioSalvo);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}



}
