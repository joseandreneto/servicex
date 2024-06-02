package br.com.andre.servicex.categoria.resources;

import br.com.andre.servicex.categoria.domain.Categoria;
import br.com.andre.servicex.categoria.repositores.CategoriaRepository;
import br.com.andre.servicex.categoria.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {
    @InjectMocks
    private CategoriaService categoriaService;


    @Mock
    private CategoriaRepository categoriaRepository;


    @Test
    public void testCriarCategoria_success(){
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("New Category");


        when(categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);


        Categoria result = categoriaService.criarCategoria(categoria);


        assertNotNull(result);
        assertEquals("New Category", result.getNomeCategoria());
        verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testAtualizarCategoria_success() {
        Integer categoriaId = 1;


        Categoria categoriaExistente = new Categoria();
        categoriaExistente.setIdCategoria(categoriaId);
        categoriaExistente.setNomeCategoria("Old Category");


        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setIdCategoria(categoriaId);
        categoriaAtualizada.setNomeCategoria("Updated Category");


        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaExistente));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaExistente);


        Categoria result = categoriaService.atualizarCategoria(categoriaAtualizada);


        assertNotNull(result);
        assertEquals("Updated Category", result.getNomeCategoria());
        verify(categoriaRepository).findById(categoriaId);
        verify(categoriaRepository).save(categoriaExistente);
    }
}
