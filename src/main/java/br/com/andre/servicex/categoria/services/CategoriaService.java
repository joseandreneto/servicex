package br.com.andre.servicex.categoria.services;

import br.com.andre.servicex.categoria.domain.Categoria;
import br.com.andre.servicex.categoria.respositores.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(Categoria categoria) { return categoriaRepository.save(categoria); }

    public List<Categoria> listarCategoria() { return categoriaRepository.findAll(); }

    public Optional<Categoria> buscarCategoria(Integer idCategoria) { return categoriaRepository.findById(idCategoria); }

    public Categoria atualizarCategoria(Categoria categoria) { return categoriaRepository.save(categoria); }

    public void deletarCategoria(Integer idCategoria) { categoriaRepository.deleteById(idCategoria); }
}