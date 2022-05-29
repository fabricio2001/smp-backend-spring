package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.PerguntaRecuperacaoCadastro;
import edu.ifes.ci.si.les.smp.repositories.PerguntaRecuperacaoCadastroRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class PerguntaRecuperacaoCadastroService {

    @Autowired
    private PerguntaRecuperacaoCadastroRepository repository;

    public PerguntaRecuperacaoCadastro findById(String id) {
        try {
            PerguntaRecuperacaoCadastro obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + PerguntaRecuperacaoCadastro.class.getName());
        }
    }

    public Collection<PerguntaRecuperacaoCadastro> findAll() { 
        return repository.findAll();
    }

    public PerguntaRecuperacaoCadastro insert(PerguntaRecuperacaoCadastro obj) {
    	obj.setIdPerguntaRecuperacao(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da PerguntaRecuperacaoCadastro não foi(foram) preenchido(s)");
        }
    }
     
    public PerguntaRecuperacaoCadastro update(PerguntaRecuperacaoCadastro obj) {
    	findById(obj.getIdPerguntaRecuperacao());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da PerguntaRecuperacaoCadastro não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um PerguntaRecuperacaoCadastro que possui assinante!");
        }
    }
    
}
