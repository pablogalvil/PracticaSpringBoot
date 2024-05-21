package practicaSpringBoot.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import practicaSpringBoot.usuario.model.Usuario;
import practicaSpringBoot.usuario.model.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioR;

	@GetMapping("/")
	public String ListaProductos(Model model) {
		// Cargo todos los productos
		List<Usuario> usuarios = usuarioR.findAll();

		// Asocio al atributo productos la lista de productos cargada de BD
		model.addAttribute("usuarios", usuarios);

		// Cargamos la vista thymeleaf(el html)
		return "listaUsuarios";
	}
}
