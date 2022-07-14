package edu.ifes.ci.si.les.smp.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;

@Repository
public interface CompartilhamentoFamiliaRepository extends JpaRepository<CompartilhamentoFamilia, String> {

	//Lucas
	@Transactional(readOnly = true)
	@Query(value = "SELECT cf.DATA_COMP_FAMILIA, fa.NOME_FAMILIA, gs.NOME_GRUPO  "
			+ "FROM COMPARTILHAMENTO_FAMILIA AS cf INNER JOIN FAMILIA  AS fa "
			+ "ON cf.FAMILIA_ID = fa.ID_FAMILIA INNER JOIN GRUPO_SENHA  AS gs "
			+ "ON cf.GRUPO_SENHA_ID = gs.ID_GRUPO "
			+ "AND gs.USUARIO_ID = ?1 "
			+ "AND cf.DATA_COMP_FAMILIA  >= ?2 "
			+ "AND cf.DATA_COMP_FAMILIA  <= ?3", nativeQuery = true)
	public Collection<?> relatorioCompatilhamento(String id, String dataInicial, String dataFinal);
}
