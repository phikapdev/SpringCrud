package com.phikapdev.springcrud.controllers;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.phikapdev.springcrud.models.entity.Cliente;
import com.phikapdev.springcrud.models.services.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    
    // Listar Clientes
    @GetMapping("/listar")
    public String listar(Model model){

        model.addAttribute("titulo", "Listado");
        model.addAttribute("clientes", clienteService.findAll());
        return "listar";
    }

    // Mostrar Formulario
    @GetMapping("/form")
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario");
        return "formulario";
    }

     // Crear Cliente
     @PostMapping("/form")
     public String guardar(@Valid Cliente cliente, 
                            BindingResult result, 
                            Model model, 
                            SessionStatus status) {
 
         if(result.hasErrors()){
             model.addAttribute("titulo", "Formulario de Cliente");
             return "formulario";
         }
 
         clienteService.save(cliente);
         status.setComplete();
         return "redirect:listar";
     } 

    // Actualizar Cliente
    @GetMapping("/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, 
                         Map<String, Object> model){

        Cliente cliente = null;

        if(id>0){
            cliente = clienteService.findById(id);
        }else{
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", " Editar Cliente");

        return "formulario";
    }

    // Eliminar Cliente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){

        if(id > 0){
            clienteService.delete(id);
        }

        return "redirect:/listar";
    }

}
