package edu.ifes.ci.si.les.smp.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.model.RecuperacaoSenha;
import edu.ifes.ci.si.les.smp.services.RecuperacaoSenhaService;
import edu.ifes.ci.si.les.smp.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/recuperacaosenha")
public class RecuperacaoSenhaController {

    @Autowired
    private RecuperacaoSenhaService service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<RecuperacaoSenha>> findAll() {
        Collection<RecuperacaoSenha> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RecuperacaoSenha> find(@PathVariable String id) {
        RecuperacaoSenha obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RecuperacaoSenha> insert(@Valid @RequestBody RecuperacaoSenha obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RecuperacaoSenha> update(@Valid @RequestBody RecuperacaoSenha obj, BindingResult br) {
    	if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
