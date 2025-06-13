package br.com.chamados.api.repository;

import br.com.chamados.api.entities.Chamado;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findAllByOrderByIdChamadoDesc(Pageable pageable);
    List<Chamado> findByIdAtendenteChamado(Long idAtendenteChamado, Pageable pageable);
}
