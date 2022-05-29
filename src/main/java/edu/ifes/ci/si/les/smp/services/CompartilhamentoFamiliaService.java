package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.repositories.CompartilhamentoFamiliaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class CompartilhamentoFamiliaService {

    @Autowired
    private CompartilhamentoFamiliaRepository repository;

    public CompartilhamentoFamilia findById(String id) {
        try {
            CompartilhamentoFamilia obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + CompartilhamentoFamilia.class.getName());
        }
    }

    public Collection<CompartilhamentoFamilia> findAll() { 
        return repository.findAll();
    }

    public CompartilhamentoFamilia insert(CompartilhamentoFamilia obj) {
    	obj.setIdCompFamilia(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da CompartilhamentoFamilia não foi(foram) preenchido(s)");
        }
    }
     
    public CompartilhamentoFamilia update(CompartilhamentoFamilia obj) {
    	findById(obj.getIdCompFamilia());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da CompartilhamentoFamilia não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um CompartilhamentoFamilia que possui assinante!");
        }
    }
    
}
