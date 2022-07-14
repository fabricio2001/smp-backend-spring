package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.Pergunta;
import edu.ifes.ci.si.les.smp.repositories.PerguntaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository repository;

    public Pergunta findById(String id) {
        try {
            Pergunta obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pergunta.class.getName());
        }
    }

    public Collection<Pergunta> findAll() { 
        return repository.findAll();
    }

    public Pergunta insert(Pergunta obj) {
    	obj.setIdPergunta(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Pergunta não foi(foram) preenchido(s)");
        }
    }
     
    public Pergunta update(Pergunta obj) {
    	findById(obj.getIdPergunta());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Pergunta não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Pergunta que possui assinante!");
        }
    }
    
}
