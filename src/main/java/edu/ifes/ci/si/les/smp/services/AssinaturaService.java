package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.Assinatura;
import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.model.Senha;
import edu.ifes.ci.si.les.smp.repositories.AssinaturaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

/*
 * LUCAS
 * */

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository repository;

    public Assinatura findById(String id) {
        try {
            Assinatura obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Assinatura.class.getName());
        }
    }

    public Collection<Assinatura> findAll() { 
        return repository.findAll();
    }

    public Assinatura insert(Assinatura obj) {
    	obj.setIdAssinatura(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Assinatura não foi(foram) preenchido(s)");
        }
    }
     
    public Assinatura update(Assinatura obj) {
    	findById(obj.getIdAssinatura());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Assinatura não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Assinatura que possui assinante!");
        }
    }
    
    public Assinatura assinatura(String id, Assinatura obj) {
        try {
            Integer objA = repository.findMac(id);
            if(objA > 0) {
            	String user = obj.getUsuario().getIdUsuario();
       
            	Integer objB = repository.findStatusPagamentoAssinatura(user);
            	System.out.println(objB);
            	if(objB == 0) {
            		obj.setIdAssinatura(null);
            		return repository.save(obj);
            	}else {
                	throw new BusinessRuleException("O usuario possui um pagamento predente");
                }
        	}else {
            	throw new BusinessRuleException("Endereco não cadastrado");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Exportacao.class.getName());
        }
    }
    
    public Collection<?> relatorioPagamento(String id, String dataInicial, String dataFinal) {
        try {
        	Collection<?> obj = repository.relatorioPagamento(id, dataInicial, dataFinal);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }
    
}
