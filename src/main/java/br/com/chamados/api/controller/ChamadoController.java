package br.com.chamados.api.controller;

import br.com.chamados.api.entities.Chamado;
import br.com.chamados.api.services.ChamadoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping
    public List<Chamado> listar() {
        return chamadoService.listarChamados();
    }

    @GetMapping("/ultimos/{qtd}")
    public List<Chamado> getUltimosChamados(@PathVariable int qtd) {
        return chamadoService.buscarUltimosChamados(qtd);
    }

    @GetMapping("/atendente/{qtd}")
    public ResponseEntity<?> listarChamados(HttpServletRequest request,@PathVariable int qtd) {
        Long idAtendente = (Long) request.getAttribute("idUsuario");
        List<Chamado> chamados = chamadoService.listarChamadosPorAtendente(idAtendente,qtd);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public Chamado buscarPorId(@PathVariable Long id) {
        return chamadoService.buscarPorId(id);
    }

    @PostMapping
    public Chamado salvar(@RequestBody Chamado chamado) {
        return chamadoService.salvarChamado(chamado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        chamadoService.deletarChamado(id);
    }
}
