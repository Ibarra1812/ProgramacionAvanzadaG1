package com.tp.ap.controladores;

import com.tp.ap.entidades.Cliente;
import com.tp.ap.entidades.Marca;
import com.tp.ap.entidades.Vehiculo;
import com.tp.ap.servicios.ClienteServicio;
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
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteControlador {

    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    VehiculoServicio vehiculoServicio;

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteServicio.list();
        model.addAttribute("clientes", clientes);
        return "lista_clientes"; // Devuelve el nombre de la vista HTML
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") int id, Model model) {
        Optional<Cliente> cliente = clienteServicio.getOne(id);
        if (cliente.isPresent()) {
            // Obtén la lista completa de clientes
            List<Vehiculo> vehiculos = vehiculoServicio.list();

            model.addAttribute("cliente", cliente.get());
            model.addAttribute("vehiculos", vehiculos);
            return "editar_cliente"; // Nombre de la vista HTML para la edición
        } else {
            // Manejar el caso en el que el cliente no existe
            return "redirect:/cliente/lista";
        }
    }

    @RequestMapping(value = "/guardar/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String guardarCliente(@PathVariable("id") int id, @ModelAttribute("cliente") Cliente cliente) {
        // Verifica si el cliente existe
        if (clienteServicio.existsById(id)) {
            // Actualiza los detalles de la marca
            Cliente clienteExistente = clienteServicio.getOne(id).get();
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDni(cliente.getDni());
            clienteExistente.setVehiculo(cliente.getVehiculo());
            clienteExistente.setDireccion(cliente.getDireccion());
            clienteExistente.setTelefono(cliente.getTelefono());
            clienteServicio.save(clienteExistente);
        }
        // Redirige a la página de lista de clientes después de la actualización
        return "redirect:/cliente/lista";
    }

    @RequestMapping(value = "/eliminar/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String eliminarCliente(@PathVariable("id") int id) {
        // Verifica si el cliente existe
        if (clienteServicio.existsById(id)) {
            // Elimina el cliente por su ID
            clienteServicio.delete(id);
        }
        // Redirige a la página de lista de clientes después de la eliminación
        return "redirect:/cliente/lista";
    }


    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        // Crea un objeto Cliente vacío para el formulario
        Cliente cliente = new Cliente();
        // Obtiene la lista de vehículos y agrega al modelo
        List<Vehiculo> vehiculos = vehiculoServicio.list();
        model.addAttribute("cliente", cliente);
        model.addAttribute("vehiculos", vehiculos);
        return "formulario_cliente"; // Nombre de la vista HTML para el formulario de creación
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
        // Guarda el nuevo cliente en la base de datos
        clienteServicio.save(cliente);
        // Redirige a la página de lista de clientes después de la creación
        return "redirect:/cliente/lista";
    }
}
