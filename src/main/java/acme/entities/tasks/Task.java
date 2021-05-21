package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------
	
	@NotBlank
	@Column(length = 80)
	private String				title;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				executionStart;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				executionEnd;
	
	@NotNull
	private int workLoad;
	
	@NotBlank
	@Column(length = 500)
	private String				description;
	
	@URL
	private String				link;
	
	private Boolean isPrivate;
	
	//Aux methods--------------------------
	
	@PreUpdate
	@PrePersist
	private void calculateWorkLoad() {

		final Date start = this.executionStart;
		final Date end = this.executionEnd;
		
	    final int MILLI_TO_HOUR = 1000 * 60 * 60;
	    this.workLoad = Math.abs((int) (start.getTime() - end.getTime()) / MILLI_TO_HOUR);
	}

}
