package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


//Serializable transforma os objetos em sequencia de bytes e isto por sua vez permite gravar os objtos em arquivo e trafegado em rede
public class Seller implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;

	private Departament departament;

	public Seller() {

	}

	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Departament departament) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.departament = departament;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
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
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	
	// to string
	@Override
	public String toString() {
		return String.format("Seller [id=%s, name=%s, email=%s, birthDate=%s, baseSalary=%s, departament=%s]", id, name,
				email, birthDate, baseSalary, departament);
	}
	
	

}
