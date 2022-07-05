package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.PontoAcesso;
import edu.ifes.ci.si.les.smp.model.Usuario;
import edu.ifes.ci.si.les.smp.model.PontoAcesso;
import edu.ifes.ci.si.les.smp.repositories.PontoAcessoRepository;
import edu.ifes.ci.si.les.smp.repositories.UsuarioRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

/*
 * ENZO
 * */

@Service
public class PontoAcessoService {

    @Autowired
    private PontoAcessoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PontoAcesso export(String id, PontoAcesso obj) {
        try {
        	//validando regra de negocio so que n esta salvando
        	Usuario user = new Usuario();
        	user.setIdUsuario(id);
        	//Usuario objU = usuarioRepository.findById(id).get();
        	obj.setUsuario(user);
        	String endereco = obj.getNovoDispositivoPontoAcesso();
            Integer objA = repository.findPontoAcessoExist(id, endereco);
            if(objA == 0) {
            	Integer objC = repository.findCountPontoAcesso(id);
            	System.out.println(objC);
            	if(objC < 3) {
            		obj.setIdPontoAcesso(null);
            		return repository.save(obj);
            	}else {
            		throw new BusinessRuleException("O usuario ja possui 3 ponto de acesso cadastrado");
            	}
            }else {
            	throw new BusinessRuleException("Ponto de acesso ja cadastrado");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + PontoAcesso.class.getName());
        }
    }
    
}
