package com.tp.ap.controladores;

import com.tp.ap.entidades.Marca;
import com.tp.ap.entidades.Tecnico;
import com.tp.ap.servicios.TecnicoServicio;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tecnico")
@CrossOrigin(origins = "http://localhost:4200")
public class TecnicoControlador {

    @Autowired
    TecnicoServicio tecnicoServicio;

    @GetMapping("/lista")
    public String listarTecnicos(Model model) {
        List<Tecnico> tecnicos = tecnicoServicio.list();
        model.addAttribute("tecnicos", tecnicos);
        return "lista_tecnicos"; // Devuelve el nombre de la vista HTML
    }

    @GetMapping("/editar/{id}")
    public String editarTecnico(@PathVariable("id") int id, Model model) {
        Optional<Tecnico> tecnico = tecnicoServicio.getOne(id);
        if (tecnico.isPresent()) {
            model.addAttribute("tecnico", tecnico.get());
            return "editar_tecnico"; // Nombre de la vista HTML para la edición
        } else {
            // Manejar el caso en el que el tecnico no existe
            return "redirect:/tecnico/lista";
        }
    }

    @RequestMapping(value = "/guardar/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String guardarTecnico(@PathVariable("id") int id, @ModelAttribute("tecnico") Tecnico tecnico) {
        // Verifica si el tecnico existe
        if (tecnicoServicio.existsById(id)) {
            // Actualiza los detalles del tecnico
            Tecnico tecnicoExistente = tecnicoServicio.getOne(id).get();
            tecnicoExistente.setNombre(tecnico.getNombre());
            tecnicoExistente.setApellido(tecnico.getApellido());
            tecnicoExistente.setDni(tecnico.getDni());
            tecnicoServicio.save(tecnicoExistente);
        }
        // Redirige a la página de lista de tecnicos después de la actualización
        return "redirect:/tecnico/lista";
    }

    @RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String eliminarTecnico(@PathVariable("id") int id) {
        // Verifica si el tecnico existe
        if (tecnicoServicio.existsById(id)) {
            // Elimina el tecnico por su ID
            tecnicoServicio.delete(id);
        }
        // Redirige a la página de lista de tecnicos después de la eliminación
        return "redirect:/tecnico/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        // Crea un objeto Tecnico vacío para el formulario
        Tecnico tecnico = new Tecnico();
        model.addAttribute("tecnico", tecnico);
        return "formulario_tecnico"; // Nombre de la vista HTML para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearTecnico(@ModelAttribute("tecnico") Tecnico tecnico) {
        // Guarda el nuevo tecnico en la base de datos
        tecnicoServicio.save(tecnico);
        // Redirige a la página de lista de tecnicos después de la creación
        return "redirect:/tecnico/lista";
    }
}

