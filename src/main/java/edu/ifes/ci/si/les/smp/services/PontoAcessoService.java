package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.PontoAcesso;
import edu.ifes.ci.si.les.smp.repositories.PontoAcessoRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class PontoAcessoService {

    @Autowired
    private PontoAcessoRepository repository;

    public PontoAcesso findById(String id) {
        try {
            PontoAcesso obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + PontoAcesso.class.getName());
        }
    }

    public Collection<PontoAcesso> findAll() { 
        return repository.findAll();
    }

    public PontoAcesso insert(PontoAcesso obj) {
    	obj.setIdPontoAcesso(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da PontoAcesso não foi(foram) preenchido(s)");
        }
    }
     
    public PontoAcesso update(PontoAcesso obj) {
    	findById(obj.getIdPontoAcesso());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da PontoAcesso não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um PontoAcesso que possui assinante!");
        }
    }
    
}
