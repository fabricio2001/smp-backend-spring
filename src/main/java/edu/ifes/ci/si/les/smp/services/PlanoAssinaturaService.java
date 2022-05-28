package edu.ifes.ci.si.les.smp.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.smp.model.PlanoAssinatura;
import edu.ifes.ci.si.les.smp.repositories.PlanoAssinaturaRepository;
import edu.ifes.ci.si.les.smp.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.smp.services.exceptions.ObjectNotFoundException;

@Service
public class PlanoAssinaturaService {

    @Autowired
    private PlanoAssinaturaRepository repository;

    public PlanoAssinatura findById(String id) {
        try {
            PlanoAssinatura obj = repository.findById(id).get();
            return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + PlanoAssinatura.class.getName());
        }
    }

    public Collection<PlanoAssinatura> findAll() {
        return repository.findAll();
    }
        
    
}
