package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.Senha;
import edu.ifes.ci.si.les.smp.repositories.SenhaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository repository;

    public Senha findById(String id) {
        try {
            Senha obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }

    public Collection<Senha> findAll() { 
        return repository.findAll();
    }

    public Senha insert(Senha obj) {
    	obj.setIdSenha(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Senha não foi(foram) preenchido(s)");
        }
    }
     
    public Senha update(Senha obj) {
    	findById(obj.getIdSenha());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Senha não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Senha que possui assinante!");
        }
    }
    
    public Collection<?> relatorioGrupo(String id, String grupo) {
        try {
        	Collection<?> obj = repository.relatorioGrupo(id, grupo);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }
    
    public Collection<?> relatorioEmail(String id, String email) {
        try {
        	Collection<?> obj = repository.relatorioEmail(id, email);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }
    
    public Collection<?> relatorioRepeticaoEmail(String id) {
        try {
        	Collection<?> obj = repository.relatorioRepeticaoEmail(id);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }
    
}
