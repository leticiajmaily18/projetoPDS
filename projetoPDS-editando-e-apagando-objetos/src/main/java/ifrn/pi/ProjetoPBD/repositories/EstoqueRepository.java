package ifrn.pi.ProjetoPBD.repositories;

import ifrn.pi.ProjetoPBD.modelos.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByQuantidade(Integer quantidade);  // Método para buscar produtos com quantidade específica
}
