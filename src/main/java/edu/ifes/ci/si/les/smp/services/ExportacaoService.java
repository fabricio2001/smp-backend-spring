package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.repositories.ExportacaoRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

/*
 * FABRICIO
 * */

@Service
public class ExportacaoService {

    @Autowired
    private ExportacaoRepository repository;

    public Exportacao findById(String id) {
        try {
            Exportacao obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Exportacao.class.getName());
        }
    }

    public Collection<Exportacao> findAll() { 
        return repository.findAll();
    }

    public Exportacao insert(Exportacao obj) {
    	obj.setIdExportacao(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Exportacao não foi(foram) preenchido(s)");
        }
    }
     
    public Exportacao update(Exportacao obj) {
    	findById(obj.getIdExportacao());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Exportacao não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Exportacao que possui assinante!");
        }
    }
    
    public Exportacao export(String id, Exportacao obj) {
        try {
            Integer objA = repository.findAssinatura(id);
            if(objA > 0) {
            	Integer objC = repository.findCountGrupoSenha(id);
            	if(objC > 1) {
            		obj.setIdExportacao(null);
            		return repository.save(obj);
            	}else {
            		throw new BusinessRuleException("O usuario tem que possui 2 grupos cadastrado");
            	}
            }else {
            	throw new BusinessRuleException("Recurso indisponivel para essa assinatura");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Exportacao.class.getName());
        }
    }
    
}
