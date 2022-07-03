package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.model.Familia;
import edu.ifes.ci.si.les.smp.repositories.FamiliaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class FamiliaService {

    @Autowired
    private FamiliaRepository repository;

    public Familia findById(String id) {
        try {
            Familia obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Familia.class.getName());
        }
    }

    public Collection<Familia> findAll() { 
        return repository.findAll();
    }

    public Familia insert(Familia obj) {
    	obj.setIdFamilia(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Familia não foi(foram) preenchido(s)");
        }
    }
     
    public Familia update(Familia obj) {
    	findById(obj.getIdFamilia());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Familia não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Familia que possui assinante!");
        }
    }
    
    public Familia share(String id, Familia obj) {
        try {
            Integer objA = repository.findAssinatura(id);
            if(objA > 0) {
            	Integer objS = repository.findAssociatedFamily(id);
            	if(objS > 1) {
            		obj.setIdFamilia(id);
            		return repository.save(obj);
            	}else {
            		throw new BusinessRuleException("A famila tem que ser comfirmada");
            	}
            }else {
            	throw new BusinessRuleException("Recurso indisponivel para essa assinatura");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Exportacao.class.getName());
        }
    }
    
}
