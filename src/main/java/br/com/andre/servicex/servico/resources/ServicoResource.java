package br.com.andre.servicex.servico.resources;

import br.com.andre.servicex.servico.domain.Servico;
import br.com.andre.servicex.servico.domain.ServicoDTO;
import br.com.andre.servicex.servico.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/servicos")
public class ServicoResource {


    @Autowired
    private ServicoService servicoService;


    @Operation(summary = "Cria um novo serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso")
    })
    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody ServicoDTO servicoDTO) {
        Servico servico = servicoService.fromDTOService(servicoDTO);
        servico = servicoService.criarServico(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(servico.getIdServico()).toUri();
        return  ResponseEntity.created(uri).build();
    }


    @Operation(summary = "Lista todos os serviços")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços")
    })
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServico();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }






    @Operation(summary = "Busca um serviço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/{idServico}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer idServico) {
        Servico servico = servicoService.buscarServico(idServico);
        return ResponseEntity.ok().body(servico);
    }




    @Operation(summary = "Deleta um serviço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping("/{idServico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer idServico) {
        servicoService.deletarServico(idServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






    @Operation(summary = "Atualiza um serviço existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping("/{idServico}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Integer idServico, @RequestBody ServicoDTO servicoDTO) {
        Servico servico = servicoService.fromDTOService(servicoDTO);
        servico.setIdServico(idServico);
        servicoService.atualizarServico(servico);
        return ResponseEntity.noContent().build();
    }


}
