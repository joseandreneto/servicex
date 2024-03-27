package br.com.andre.servicex.servico.domain;

import br.com.andre.servicex.categoria.domain.Categoria;
import br.com.andre.servicex.ordemServico.domain.OrdemServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICOS")
public class Servico {
    @Id
    @GeneratedValue
    @Column(name = "ID_SERVICO")
    private Integer idServico;
    @Column(name = "NOME_SERVICO")
    private String nomeServico;
    @Column(name = "VALOR_SERVICO")
    private Double valor;
    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "idCategoria")
    private List<Categoria> categoria;
    @OneToMany
    private List<OrdemServico> ordemServico;
}