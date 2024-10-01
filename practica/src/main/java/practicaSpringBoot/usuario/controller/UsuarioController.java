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

	/**
	 * Funcion que abre el menú principal, que muestra una tabla con todos los
	 * usuarios en la base de datos, separados en páginas de 10 en 10.
	 * 
	 * @param model
	 * @param page  pagina en la que estaba antes de cambiar o 0 predeterminado.
	 * @param size  predeterminado 10, este valor no cambia.
	 * @return listado html.
	 */
	@GetMapping("/")
	public String listaUsuarios(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		// Creamos un objeto pageable a partir de los valores introducidos en la funcion
		// (predeterminados 0 y 10 respectivamente), que separará los elementos en
		// páginas.
		Pageable pageable = PageRequest.of(page, size);

		// Buscamos a los usuarios que pertenezcan a dicha página.
		Page<Usuario> usuarioPage = usuarioR.findAll(pageable);

		// Los añadimos al modelo.
		model.addAttribute("usuarioPage", usuarioPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);

		// Cargamos la vista Thymeleaf
		return "listaUsuarios";
	}

	/**
	 * Funcion que abre el formulario para editar un usuario.
	 * 
	 * @param id    conseguido a partir del id del usuario al que se ha clicado para
	 *              editar.
	 * @param model
	 * @return formulario html.
	 */
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model model) {
		// Encontramos al usuario a partir del id.
		Optional<Usuario> optionalUsuario = usuarioR.findById(id);
		// Si existe, lo guardamos en un usuario y devolvemos el formulario html con
		// dicho usuario.
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			model.addAttribute("usuario", usuario);
			return "formularioUsuario";
			// Si no existe se redirige a la página principal.
		} else {
			return "redirect:/";
		}
	}

	/**
	 * Funcion que abre el formulario html vacio para crear un nuevo usuario.
	 * 
	 * @param model
	 * @return formulario html.
	 */
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "formularioUsuario";
	}

	/**
	 * Funcion que se abre al guardar los cambios en las funciones de crear y
	 * editar, la cual guarda los cambios en la base de datos.
	 * 
	 * @param usuario cogido a partir del formulario.
	 * @return redireccion a la página principal.
	 */
	@PostMapping("/actualizar")
	public String actualizar(@ModelAttribute("usuario") Usuario usuario) {
		usuarioR.save(usuario);
		return "redirect:/";
	}

	/**
	 * Funcion que elimina al usuario del id especificado.
	 * 
	 * @param id del usuario al cual se ha presionado en eliminar.
	 * @return redireccion a la página principal.
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		usuarioR.deleteById(id);
		return "redirect:/";
	}

	/**
	 * Funcion que muestra una vista detallada con los datos de un usuario
	 * 
	 * @param id
	 * @param model
	 * @return vista html detalle
	 */
	@GetMapping("/detalle/{id}")
	public String getUsuarioById(@PathVariable("id") Integer id, Model model) {
		Optional<Usuario> usuarioOptional = usuarioR.findById(id);
		if (usuarioOptional.isPresent()) {
			model.addAttribute("usuario", usuarioOptional.get());
			return "detalle";
		} else {
			return "redirect:/";
		}
	}

}
