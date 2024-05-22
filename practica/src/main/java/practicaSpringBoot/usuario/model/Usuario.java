package practicaSpringBoot.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private long idUsuario;
	private String nombre;
	private String contrasenia;
	private int peso;
	private int altura;
	private int edad;
	private char sexo;
	@Column(name = "tipoconsulta")
	private String tipoConsulta;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(long idUsuario, String nombre, String contrasenia, int peso, int altura, int edad, char sexo,
			String tipoConsulta) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.peso = peso;
		this.altura = altura;
		this.edad = edad;
		this.sexo = sexo;
		this.tipoConsulta = tipoConsulta;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

}
