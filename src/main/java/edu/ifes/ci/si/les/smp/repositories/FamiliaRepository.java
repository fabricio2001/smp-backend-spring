package edu.ifes.ci.si.les.smp.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, String> {

	//FABIO
	@Transactional(readOnly = true)
    @Query(value = "SELECT COUNT(1) FROM FAMILIA AS fa WHERE fa.EMAIL_FAMILIA  = ?1 AND fa.CPF_FAMILIA = ?2 AND fa.USUARIO_ID = ?3", nativeQuery = true)
	public Integer findFamiliarUser(String email, String cpf, String id);
	
}
