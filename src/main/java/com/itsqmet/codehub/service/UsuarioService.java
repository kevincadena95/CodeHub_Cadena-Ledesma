package com.itsqmet.codehub.service;

import com.itsqmet.codehub.model.Usuario;
import com.itsqmet.codehub.repository.UsuarioRepository;
import com.itsqmet.codehub.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<String> registrar(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            return Optional.of("El username ya está en uso");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return Optional.of("El email ya está registrado");
        }

        String passwordHasheado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordHasheado);

        usuarioRepository.save(usuario);
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
