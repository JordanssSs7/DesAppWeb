package com.tecsup.service;

import com.tecsup.model.Detalle;
import com.tecsup.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleService {
    @Autowired
    private DetalleRepository repo;

    public List<Detalle> listar() { return repo.findAll(); }
    public Detalle guardar(Detalle d) { return repo.save(d); }
    public Detalle obtener(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
}