package practicaSpringBoot.usuario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import practicaSpringBoot.usuario.model.Usuario;
import practicaSpringBoot.usuario.model.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioR;

	@GetMapping("/")
	public String listaUsuarios(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		// Crear objeto Pageable
		Pageable pageable = PageRequest.of(page, size);

		// Obtener página de usuarios
		Page<Usuario> usuarioPage = usuarioR.findAll(pageable);

		// Añadir atributos al modelo
		model.addAttribute("usuarioPage", usuarioPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);

		// Cargar la vista Thymeleaf
		return "listaUsuarios";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model model) {
		Optional<Usuario> optionalUsuario = usuarioR.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			model.addAttribute("usuario", usuario);
			return "formularioUsuario";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/crear")
	public String showCreateForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "formularioUsuario";
	}

	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("usuario") Usuario usuario) {
		usuarioR.save(usuario);
		return "redirect:/";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		usuarioR.deleteById(id);
		return "redirect:/";
	}

}
