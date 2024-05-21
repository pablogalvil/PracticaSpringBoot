package practicaSpringBoot.usuario.model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	private String nombre;
	private String contrasenia;
	private int peso;
	private int altura;
	private int edad;
	private char sexo;

	// Getters y Setters
}
