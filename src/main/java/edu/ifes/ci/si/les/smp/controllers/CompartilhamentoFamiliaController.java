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

import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.model.CompartilhamentoFamilia;
import edu.ifes.ci.si.les.smp.services.CompartilhamentoFamiliaService;
import edu.ifes.ci.si.les.smp.services.exceptions.ConstraintException;
import edu.ifes.ci.si.les.smp.util.Compartilhar;

@RestController
@RequestMapping(value = "/compartilhamentofamilia")
public class CompartilhamentoFamiliaController {

    @Autowired
    private CompartilhamentoFamiliaService service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CompartilhamentoFamilia>> findAll() {
        Collection<CompartilhamentoFamilia> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CompartilhamentoFamilia> find(@PathVariable String id) {
        CompartilhamentoFamilia obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CompartilhamentoFamilia> insert(@Valid @RequestBody CompartilhamentoFamilia obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CompartilhamentoFamilia> update(@Valid @RequestBody CompartilhamentoFamilia obj, BindingResult br) {
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
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<CompartilhamentoFamilia> share(@Valid @RequestBody Compartilhar obj, BindingResult br) {
    	CompartilhamentoFamilia objF = service.share(obj);
        return ResponseEntity.ok().body(objF);
    }
    
    @RequestMapping(value = "relatorio/compartilhamento/{id}/{dataInicial}/{dataFinal}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> relatorioCompatilhamento(@PathVariable String id, @PathVariable String dataInicial, @PathVariable String dataFinal) {
    	Collection<?> obj = service.relatorioCompatilhamento(id, dataInicial, dataFinal);
        return ResponseEntity.ok().body(obj);
    }
}
