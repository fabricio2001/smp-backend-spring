package edu.ifes.ci.si.les.smp.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Exportacao;

@Repository
public interface ExportacaoRepository extends JpaRepository<Exportacao, String> {
    
	//FABRICIO
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM exportacao AS ex INNER JOIN assinatura AS ass ON ass.PLANO_ASSINATURA_ID  = 2 AND ass.usuario_id = ?1", nativeQuery = true)
	public Integer findAssinatura(String usuario);
	
	//FABRICIO
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(gs.id_grupo) FROM exportacao AS ex INNER JOIN grupo_senha AS gs ON gs.usuario_id = ?1", nativeQuery = true)
	public Integer findCountGrupoSenha(String usuario);
}
