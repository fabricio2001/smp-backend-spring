package edu.ifes.ci.si.les.smp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.smp.model.Exportacao;

@Repository
public interface ExportacaoRepository extends JpaRepository<Exportacao, String> {
    
}
