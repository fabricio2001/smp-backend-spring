package edu.ifes.ci.si.les.smp.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.model.Usuario;
import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.repositories.RecuperacaoSenhaRepository;
import edu.ifes.ci.si.les.smp.repositories.UsuarioRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.smp.util.Reset;

/*
 * EDUARDO
 * */
@Service
public class RecuperacaoSenhaService {

    @Autowired
    private RecuperacaoSenhaRepository repository;
    @Autowired
    private UsuarioRepository repositoryUser;

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
    
    public RecuperacaoSenha reset(Reset objR) {
        try {
        	String email = objR.getEmail();
        	String endereco = objR.getEndereco();
            Integer objA = repository.findEmailExist(email);
            if(objA > 0) {
            	Integer objB = repository.findEmailEnderecoExist(email, endereco);
            	if(objB > 0) {
            	SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar calendar = Calendar.getInstance();

                Date dateObj = calendar.getTime();
            	
            	Usuario user = repositoryUser.findById("1").get();
        		RecuperacaoSenha obj = new RecuperacaoSenha(null, dateObj, true, user);
        		
        		return repository.save(obj);
            	}else {
            		throw new BusinessRuleException("Ponto de acesso não cadastrado");
            	}
            }else {
            	throw new BusinessRuleException("Email não cadastrado");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: , Tipo: " + RecuperacaoSenha.class.getName());
        }
    }
}
