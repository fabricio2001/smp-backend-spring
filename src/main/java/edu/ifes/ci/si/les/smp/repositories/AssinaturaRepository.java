package edu.ifes.ci.si.les.smp.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Assinatura;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, String>{
	
	//LUCAS
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM ASSINATURA AS ass INNER JOIN usuario AS us ON ass.id_assinatura = us.id_usuario INNER JOIN ponto_acesso AS pa ON us.id_usuario = pa.usuario_id AND pa.novo_dispositivo_ponto_acesso = ?1", nativeQuery = true)
	public Integer findMac(String mac);
	
	//LUCAS
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM ASSINATURA AS ass WHERE ass.CONFIRMA_PAGAMENTO_ASSINATURA = 0 AND ass.USUARIO_ID = ?1", nativeQuery = true)
	public Integer findStatusPagamentoAssinatura(String id);
	
	//FABIO
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM ASSINATURA WHERE usuario_id = ?1 AND PLANO_ASSINATURA_ID = 2", nativeQuery = true)
	public Integer findPlanoAssinatura(String usuario);
	
	//Fabricio
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM ASSINATURA WHERE USUARIO_ID = ?1 "
			+ "AND DATA_ASSINATURA  >= ?2 "
			+ "AND DATA_ASSINATURA  <= ?3", nativeQuery = true)
	public Collection<?> relatorioPagamento(String id, String dataInicial, String dataFinal);
}
