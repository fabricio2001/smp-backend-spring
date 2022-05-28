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

import edu.ifes.ci.si.les.smp.model.PlanoAssinatura;
import edu.ifes.ci.si.les.smp.services.PlanoAssinaturaService;
import edu.ifes.ci.si.les.smp.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/planoassinatura")
public class PlanoAssinaturaController {

    @Autowired
    private PlanoAssinaturaService service;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<PlanoAssinatura>> findAll() {
        Collection<PlanoAssinatura> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }
}
