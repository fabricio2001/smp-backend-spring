package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Assinatura;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, String>{
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM ASSINATURA AS ass INNER JOIN usuario AS us ON ass.id_assinatura = us.id_usuario INNER JOIN ponto_acesso AS pa ON us.id_usuario = pa.usuario_id AND pa.novo_dispositivo_ponto_acesso = ?1", nativeQuery = true)
	public Integer findMac(String mac);
}
