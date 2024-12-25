/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.registro2.CRUD.controller;

import com.registro2.CRUD.model.Persona;
import com.registro2.CRUD.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author danie
 */
@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String listarPersonas(Model model) {
        model.addAttribute("personas", personaService.listarTodas());
        return "persona-list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "persona-form";
    }

    @PostMapping
    public String guardarPersona(Persona persona) {
        personaService.guardar(persona);
        return "redirect:/personas";

    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable Long id, Model model) {
        Persona persona = personaService.obtenerPorId(id);
        if (persona == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada");
        }
        model.addAttribute("persona", persona);
        return "persona-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        personaService.eliminar(id);
        return "redirect:/personas";
    }
    
    //error en las ids y en como la vista recibia los datos del controlador
    
}
