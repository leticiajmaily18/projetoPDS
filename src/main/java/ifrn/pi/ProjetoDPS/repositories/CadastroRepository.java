package ifrn.pi.ProjetoDPS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ifrn.pi.ProjetoDPS.models.Funcionario;

public interface CadastroRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByEmail(String email);
}
