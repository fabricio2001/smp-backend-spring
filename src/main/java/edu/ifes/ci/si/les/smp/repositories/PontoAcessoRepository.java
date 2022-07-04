package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.PontoAcesso;

@Repository
public interface PontoAcessoRepository extends JpaRepository<PontoAcesso, String> {
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM PONTO_ACESSO WHERE usuario_id = ?1 AND novo_dispositivo_ponto_acesso = ?2", nativeQuery = true)
	public Integer findPontoAcessoExist(String usuario, String endereco);
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM PONTO_ACESSO WHERE usuario_id = ?1", nativeQuery = true)
	public Integer findCountPontoAcesso(String usuario);
}
