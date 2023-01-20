package model.entities;

import java.io.Serializable;
import java.util.Objects;

// Serializable transforma os objetos em sequencia de bytes e isto por sua vez permite gravar os objtos em arquivo e trafegado em rede
public class Departament implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public Departament() {
	}

	public Departament(Integer id, String name) {
	
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	// hasCode para permitir comparar os objetos pelo seu conteúdo e não pela referencia
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departament other = (Departament) obj;
		return Objects.equals(id, other.id);
	}

	// metodo to string
	@Override
	public String toString() {
		return String.format("Departament [id=%s, name=%s]", id, name);
	}

	
}
