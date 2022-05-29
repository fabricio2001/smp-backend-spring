package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.GrupoSenha;
import edu.ifes.ci.si.les.smp.repositories.GrupoSenhaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class GrupoSenhaService {

    @Autowired
    private GrupoSenhaRepository repository;

    public GrupoSenha findById(String id) {
        try {
            GrupoSenha obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + GrupoSenha.class.getName());
        }
    }

    public Collection<GrupoSenha> findAll() { 
        return repository.findAll();
    }

    public GrupoSenha insert(GrupoSenha obj) {
    	obj.setIdGrupo(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da GrupoSenha não foi(foram) preenchido(s)");
        }
    }
     
    public GrupoSenha update(GrupoSenha obj) {
    	findById(obj.getIdGrupo());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da GrupoSenha não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um GrupoSenha que possui assinante!");
        }
    }
    
}
