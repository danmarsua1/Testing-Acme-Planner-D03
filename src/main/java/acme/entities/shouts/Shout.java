package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;
	
	@NotBlank
	@Size(min = 5, max = 25)
	private String				author;
	
	@NotBlank
	@Column(length = 100)
	private String				text;
	
	@URL
	private String				info;

}
