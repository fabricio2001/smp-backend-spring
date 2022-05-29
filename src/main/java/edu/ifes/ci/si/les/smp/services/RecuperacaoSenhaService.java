package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.repositories.RecuperacaoSenhaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private RecuperacaoSenhaRepository repository;

    public RecuperacaoSenha findById(String id) {
        try {
            RecuperacaoSenha obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + RecuperacaoSenha.class.getName());
        }
    }

    public Collection<RecuperacaoSenha> findAll() { 
        return repository.findAll();
    }

    public RecuperacaoSenha insert(RecuperacaoSenha obj) {
    	obj.setIdRecuperacaoSenha(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da RecuperacaoSenha não foi(foram) preenchido(s)");
        }
    }
     
    public RecuperacaoSenha update(RecuperacaoSenha obj) {
    	findById(obj.getIdRecuperacaoSenha());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da RecuperacaoSenha não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um RecuperacaoSenha que possui assinante!");
        }
    }
    
}
