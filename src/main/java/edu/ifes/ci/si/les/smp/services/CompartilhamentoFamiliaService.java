package edu.ifes.ci.si.les.smp.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.model.Familia;
import edu.ifes.ci.si.les.smp.model.GrupoSenha;
import edu.ifes.ci.si.les.smp.model.Senha;
import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.repositories.AssinaturaRepository;
import edu.ifes.ci.si.les.smp.repositories.CompartilhamentoFamiliaRepository;
import edu.ifes.ci.si.les.smp.repositories.FamiliaRepository;
import edu.ifes.ci.si.les.smp.repositories.GrupoSenhaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;
import edu.ifes.ci.si.les.smp.util.Compartilhar;

/*
 * FABIO
 * */

@Service
public class CompartilhamentoFamiliaService {

    @Autowired
    private CompartilhamentoFamiliaRepository repository;
    
    @Autowired
    private AssinaturaRepository assinaturaRepository;
    
    @Autowired
    private FamiliaRepository familiaRepository;
    
    @Autowired
    private GrupoSenhaRepository grupoSenhaRepository;

    public CompartilhamentoFamilia findById(String id) {
        try {
            CompartilhamentoFamilia obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + CompartilhamentoFamilia.class.getName());
        }
    }

    public Collection<CompartilhamentoFamilia> findAll() { 
        return repository.findAll();
    }

    public CompartilhamentoFamilia insert(CompartilhamentoFamilia obj) {
    	obj.setIdCompFamilia(null);
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da CompartilhamentoFamilia não foi(foram) preenchido(s)");
        }
    }
     
    public CompartilhamentoFamilia update(CompartilhamentoFamilia obj) {
    	findById(obj.getIdCompFamilia());
    	try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da CompartilhamentoFamilia não foi(foram) preenchido(s)");
        }
    }

    public void delete(String id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um CompartilhamentoFamilia que possui assinante!");
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CompartilhamentoFamilia share(Compartilhar obj) {
        try {
        	String idUser = obj.getIdUser();
        	String email = obj.getEmailFamiliar();
    		String cpf = obj.getCpfFamiliar();
    		String idGrupo = obj.getIdGrupo();
    		String idFamiliar = obj.getIdFamilia();
            Integer objA = assinaturaRepository.findPlanoAssinatura(idUser);
            if(objA >= 1) {
            	Integer objS = familiaRepository.findFamiliarUser(email, cpf, idUser);
            	if(objS >= 1) {
            		
            		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar calendar = Calendar.getInstance();
                    Date dateObj = calendar.getTime();
                    /*
                    System.out.println("========================================================");
                    Familia familia = familiaRepository.findById(idFamiliar).get();
                    GrupoSenha grupo = grupoSenhaRepository.findById(idGrupo).get();
                    System.out.println(idFamiliar);
                    System.out.println(idGrupo);
                    System.out.println("========================================================");
                    */
                    Familia familia = new Familia();
                    familia.setIdFamilia(idFamiliar);
                    GrupoSenha grupo = new GrupoSenha();
                    grupo.setIdGrupo(idGrupo);
            		CompartilhamentoFamilia com = new CompartilhamentoFamilia(null, dateObj, familia, grupo);
            		System.out.println(com);
            		return repository.save(com);
            	}else {
            		throw new BusinessRuleException("A famila tem que ser comfirmada");
            	}
            }else {
            	throw new BusinessRuleException("Recurso indisponivel para essa assinatura");
            }
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: , Tipo: " + Exportacao.class.getName());
        }
    }
    
    public Collection<?> relatorioCompatilhamento(String id, String dataInicial, String dataFinal) {
        try {
        	Collection<?> obj = repository.relatorioCompatilhamento(id, dataInicial, dataFinal);
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
        }
    }
    
}
