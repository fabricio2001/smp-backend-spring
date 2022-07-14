package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;

@Repository
public interface CompartilhamentoFamiliaRepository extends JpaRepository<CompartilhamentoFamilia, String> {

}
