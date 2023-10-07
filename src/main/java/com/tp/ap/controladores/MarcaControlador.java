package com.tp.ap.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tp.ap.entidades.Marca;
import com.tp.ap.servicios.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/marca")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaControlador {

    @Autowired
    MarcaServicio marcaServicio;

    @GetMapping("/lista")
    public String listarMarcas(Model model) {
        List<Marca> marcas = marcaServicio.list();
        model.addAttribute("marcas", marcas);
        return "lista_marcas"; // Devuelve el nombre de la vista HTML
    }

    @GetMapping("/editar/{id}")
    public String editarMarca(@PathVariable("id") int id, Model model) {
        Optional<Marca> marca = marcaServicio.getOne(id);
        if (marca.isPresent()) {
            model.addAttribute("marca", marca.get());
            return "editar_marca"; // Nombre de la vista HTML para la edición
        } else {
            // Manejar el caso en el que la marca no existe
            return "redirect:/marca/lista";
        }
    }

    @RequestMapping(value = "/guardar/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String guardarMarca(@PathVariable("id") int id, @ModelAttribute("marca") Marca marca) {
        // Verifica si la marca existe
        if (marcaServicio.existsById(id)) {
            // Actualiza los detalles de la marca
            Marca marcaExistente = marcaServicio.getOne(id).get();
            marcaExistente.setNombre(marca.getNombre());
            marcaExistente.setDescripcion(marca.getDescripcion());
            marcaServicio.save(marcaExistente);
        }
        // Redirige a la página de lista de marcas después de la actualización
        return "redirect:/marca/lista";
    }

    @RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String eliminarMarca(@PathVariable("id") int id) {
        // Verifica si la marca existe
        if (marcaServicio.existsById(id)) {
            // Elimina la marca por su ID
            marcaServicio.delete(id);
        }
        // Redirige a la página de lista de marcas después de la eliminación
        return "redirect:/marca/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        // Crea un objeto Marca vacío para el formulario
        Marca marca = new Marca();
        model.addAttribute("marca", marca);
        return "formulario_marca"; // Nombre de la vista HTML para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearMarca(@ModelAttribute("marca") Marca marca) {
        // Guarda la nueva marca en la base de datos
        marcaServicio.save(marca);
        // Redirige a la página de lista de marcas después de la creación
        return "redirect:/marca/lista";
    }
}
