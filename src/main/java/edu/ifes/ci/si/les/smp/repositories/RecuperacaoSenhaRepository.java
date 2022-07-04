package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;

@Repository
public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, String> {
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM recuperacao_senha AS rs INNER JOIN usuario AS us ON rs.usuario_id = us.id_usuario AND us.email_usuario = ?1 INNER JOIN ponto_acesso AS pa ON us.id_usuario = pa.usuario_id AND pa.novo_dispositivo_ponto_acesso = ?2", nativeQuery = true)
	public Integer findEmailExist(String email, String endereco);
}
