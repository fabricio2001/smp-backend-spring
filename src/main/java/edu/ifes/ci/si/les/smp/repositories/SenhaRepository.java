package edu.ifes.ci.si.les.smp.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Senha;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, String> {
	
	//Eduardo
	@Transactional(readOnly = true)
    @Query(value = "SELECT se.* FROM SENHA AS se INNER JOIN GRUPO_SENHA AS gs ON "
    		+ "se.GRUPO_SENHA_ID  = gs.ID_GRUPO AND "
    		+ "gs.USUARIO_ID  = ?1 AND "
    		+ "gs.NOME_GRUPO = ?2", nativeQuery = true)
	public Collection<?> relatorioGrupo(String id, String grupo);
	
	//Enzo
	@Transactional(readOnly = true)
    @Query(value = "SELECT se.* FROM SENHA AS se INNER JOIN GRUPO_SENHA AS gs ON "
    		+ "se.GRUPO_SENHA_ID  = gs.ID_GRUPO AND "
    		+ "gs.USUARIO_ID  = ?1 AND "
    		+ "se.EMAIL_SENHA = ?2", nativeQuery = true)
	public Collection<?> relatorioEmail(String id, String email);
	
	//Fábio
	@Transactional(readOnly = true)
	@Query(value = "SELECT se.EMAIL_SENHA, COUNT(se.EMAIL_SENHA) FROM SENHA AS se "
    		+ "INNER JOIN GRUPO_SENHA AS gs ON se.GRUPO_SENHA_ID = gs.ID_GRUPO AND "
    		+ "gs.USUARIO_ID = ?1 GROUP BY se.EMAIL_SENHA ", nativeQuery = true)
	public Collection<?> relatorioRepeticaoEmail(String id);
	
}
