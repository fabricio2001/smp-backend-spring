package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, String> {

	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM familia AS fam INNER JOIN assinatura AS ass ON fam.usuario_id = ass.usuario_id AND ass.status_assinatura = 1 AND fam.usuario_id = ?1", nativeQuery = true)
	public Integer findAssinatura(String usuario);
	
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM familia AS fam INNER JOIN usuario AS us ON fam.usuario_id = us.id_usuario AND fam.confirma_familia = 1 AND us.id_usuario = ?1", nativeQuery = true)
	public Integer findAssociatedFamily(String usuario);
}
