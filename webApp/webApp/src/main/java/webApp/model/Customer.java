package webApp.model;


import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "test")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 3, max = 50)
	@Column(name = "fname")
	private String firstName;

	@Size(min = 3, max = 50)
	@Column(name = "lname")
	private String lastName;


	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	



}
