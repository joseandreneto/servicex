package br.com.andre.servicex.categoria.respositores;

import br.com.andre.servicex.categoria.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository  extends JpaRepository<Servico, Integer> {
}
