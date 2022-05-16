package ru.saubulprojects.tinkoffapp.model;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users",
	   uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "email_constraint"),
			   				@UniqueConstraint(columnNames = "username", name = "username_constraint")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Необходимо ввести имя.")
	private String name;
	
	@NotBlank(message = "Необходимо ввести электронную почту.")
	@Email
	private String email;
	
	@NotBlank(message = "Необходимо ввести имя пользователя.")
	private String username;
	
	@NotBlank(message = "Необходимо ввести пароль.")
	private String password;
	
	@ManyToMany
	@JoinTable(name = "users_roles",
			   joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="user_id_fk"))},
			   inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "role_id_fk"))})
	private Collection<Role> roles;
	
	public User(String name, String email, String username, String password, Collection<Role> roles) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
}
