package br.com.erudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.services.PersonServices;

@RestController
public class PersonController {

    @Autowired
    private PersonServices service;

    // private PersonServices service = new PersonServices();

}
