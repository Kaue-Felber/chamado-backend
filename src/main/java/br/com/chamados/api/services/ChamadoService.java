package br.com.chamados.api.services;

import br.com.chamados.api.entities.Chamado;
import br.com.chamados.api.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public List<Chamado> listarChamados() {
        return chamadoRepository.findAll();
    }

    public List<Chamado> buscarUltimosChamados(int quantidade) {
        Pageable limit = PageRequest.of(0, quantidade);
        return chamadoRepository.findAllByOrderByIdChamadoDesc(limit);
    }

    public List<Chamado> listarChamadosPorAtendente(Long idAtendente, int quantidade) {
        Pageable limit = PageRequest.of(0, quantidade);
        return chamadoRepository.findByIdAtendenteChamado(idAtendente,limit);
    }

    public Chamado buscarPorId(Long id) {
        return chamadoRepository.findById(id).orElse(null);
    }

    public Chamado salvarChamado(Chamado chamado) {
        // Aqui você pode validar o chamado, ou aplicar regras
        return chamadoRepository.save(chamado);
    }

    public void deletarChamado(Long id) {
        chamadoRepository.deleteById(id);
    }
}