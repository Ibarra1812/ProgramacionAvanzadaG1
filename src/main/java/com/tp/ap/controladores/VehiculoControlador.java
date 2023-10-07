package com.tp.ap.controladores;

import com.tp.ap.entidades.*;
import com.tp.ap.servicios.MarcaServicio;
import com.tp.ap.servicios.ModeloServicio;
import com.tp.ap.servicios.TecnicoServicio;
import com.tp.ap.servicios.VehiculoServicio;
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
@RequestMapping("/vehiculo")
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculoControlador {

    @Autowired
    VehiculoServicio vehiculoServicio;

    @Autowired
    MarcaServicio marcaServicio;

    @Autowired
    ModeloServicio modeloServicio;

    @Autowired
    TecnicoServicio tecnicoServicio;

    @GetMapping("/lista")
    public String listarVehiculos(Model model) {
        List<Vehiculo> vehiculos = vehiculoServicio.list();
        model.addAttribute("vehiculos", vehiculos);
        return "lista_vehiculos"; // Devuelve el nombre de la vista HTML
    }

    @GetMapping("/editar/{id}")
    public String editarVehiculo(@PathVariable("id") int id, Model model) {
        Optional<Vehiculo> vehiculo = vehiculoServicio.getOne(id);
        if (vehiculo.isPresent()) {
            // Obtén la lista completa de vehículos
            List<Marca> marcas = marcaServicio.list();
            List<Modelo> modelos = modeloServicio.list();
            List<Tecnico> tecnicos = tecnicoServicio.list();

            model.addAttribute("vehiculo", vehiculo.get());
            model.addAttribute("marcas", marcas);
            model.addAttribute("modelos", modelos);
            model.addAttribute("tecnicos", tecnicos);
            return "editar_vehiculo"; // Nombre de la vista HTML para la edición
        } else {
            // Manejar el caso en el que el vehiculo no existe
            return "redirect:/vehiculo/lista";
        }
    }

    @RequestMapping(value = "/guardar/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String guardarVehiculo(@PathVariable("id") int id, @ModelAttribute("vehiculo") Vehiculo vehiculo) {
        // Verifica si el vehiculo existe
        if (vehiculoServicio.existsById(id)) {
            // Actualiza los detalles del vehiculo
            Vehiculo vehiculoExistente = vehiculoServicio.getOne(id).get();
            vehiculoExistente.setPatente(vehiculo.getPatente());
            vehiculoExistente.setMarca(vehiculo.getMarca());
            vehiculoExistente.setModelo(vehiculo.getModelo());
            vehiculoExistente.setTecnico(vehiculo.getTecnico());
            vehiculoServicio.save(vehiculoExistente);
        }
        // Redirige a la página de lista de vehiculos después de la actualización
        return "redirect:/vehiculo/lista";
    }

    @RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String eliminarVehiculo(@PathVariable("id") int id) {
        // Verifica si el vehiculo existe
        if (vehiculoServicio.existsById(id)) {
            // Elimina el vehiculo por su ID
            vehiculoServicio.delete(id);
        }
        // Redirige a la página de lista de vehiculos después de la eliminación
        return "redirect:/vehiculo/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        // Crea un objeto Vehiculo vacío para el formulario
        Vehiculo vehiculo = new Vehiculo();
        // Obtiene la lista de marcas, modelos y tecnicos, y los agrega al modelo
        List<Marca> marcas = marcaServicio.list();
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("marcas", marcas);
        List<Modelo> modelos = modeloServicio.list();
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("modelos", modelos);
        List<Tecnico> tecnicos = tecnicoServicio.list();
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("tecnicos", tecnicos);
        return "formulario_vehiculo"; // Nombre de la vista HTML para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        // Guarda el nuevo vehiculo en la base de datos
        vehiculoServicio.save(vehiculo);
        // Redirige a la página de lista de vehiculos después de la creación
        return "redirect:/vehiculo/lista";
    }
}

