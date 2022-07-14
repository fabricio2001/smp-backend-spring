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

import edu.ifes.ci.si.les.smp.model.Exportacao;
import edu.ifes.ci.si.les.smp.services.ExportacaoService;
import edu.ifes.ci.si.les.smp.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/exportacao")
public class ExportacaoController {

    @Autowired
    private ExportacaoService service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Exportacao>> findAll() {
        Collection<Exportacao> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Exportacao> find(@PathVariable String id) {
        Exportacao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Exportacao> insert(@Valid @RequestBody Exportacao obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Exportacao> update(@Valid @RequestBody Exportacao obj, BindingResult br) {
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
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public ResponseEntity<Exportacao> export(@PathVariable String id, @Valid @RequestBody Exportacao obj, BindingResult br) {
    	Exportacao objE = service.export(id, obj);
        return ResponseEntity.ok().body(objE);
    }
}
