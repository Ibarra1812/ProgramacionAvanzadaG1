package com.tp.ap.controladores;

import com.tp.ap.entidades.Marca;
import com.tp.ap.entidades.Modelo;
import com.tp.ap.servicios.ModeloServicio;
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
@RequestMapping("/modelo")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloControlador {

    @Autowired
    ModeloServicio modeloServicio;

    @GetMapping("/lista")
    public String listarModelos(Model model) {
        List<Modelo> modelos = modeloServicio.list();
        model.addAttribute("modelos", modelos);
        return "lista_modelos"; // Devuelve el nombre de la vista HTML
    }

    @GetMapping("/editar/{id}")
    public String editarModelo(@PathVariable("id") int id, Model model) {
        Optional<Modelo> modelo = modeloServicio.getOne(id);
        if (modelo.isPresent()) {
            model.addAttribute("modelo", modelo.get());
            return "editar_modelo"; // Nombre de la vista HTML para la edición
        } else {
            // Manejar el caso en el que el modelo no existe
            return "redirect:/modelo/lista";
        }
    }

    @RequestMapping(value = "/guardar/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String guardarModelo(@PathVariable("id") int id, @ModelAttribute("modelo") Modelo modelo) {
        // Verifica si el modelo existe
        if (modeloServicio.existsById(id)) {
            // Actualiza los detalles del modelo
            Modelo modeloExistente = modeloServicio.getOne(id).get();
            modeloExistente.setNombre(modelo.getNombre());
            modeloExistente.setAno(modelo.getAno());
            modeloServicio.save(modeloExistente);
        }
        // Redirige a la página de lista de modelos después de la actualización
        return "redirect:/modelo/lista";
    }

    @RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String eliminarModelo(@PathVariable("id") int id) {
        // Verifica si el modelo existe
        if (modeloServicio.existsById(id)) {
            // Elimina el modelo por su ID
            modeloServicio.delete(id);
        }
        // Redirige a la página de lista de modelos después de la eliminación
        return "redirect:/modelo/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        // Crea un objeto Modelo vacío para el formulario
        Modelo modelo = new Modelo();
        model.addAttribute("modelo", modelo);
        return "formulario_modelo"; // Nombre de la vista HTML para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearModelo(@ModelAttribute("modelo") Modelo modelo) {
        // Guarda el nuevo modelo en la base de datos
        modeloServicio.save(modelo);
        // Redirige a la página de lista de modelos después de la creación
        return "redirect:/modelo/lista";
    }
}
