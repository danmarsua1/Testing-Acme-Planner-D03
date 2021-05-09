
package acme.forms;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;
	
	//Tasks
	Integer						numberPublicTasks;
	
	Integer						numberPrivateTasks;

	Integer						numberFinishedTasks;
	
	Integer						numberNotFinishedTasks;
	
	//Execution periods
	Date						minExecutionStart;
	
	Date						maxExecutionStart;
	
	Date						minExecutionEnd;
	
	Date						maxExecutionEnd;

	Date						averageExecutionStart;

	Date						averageExecutionEnd;

	Double						stddevExecutionStart;

	Double						stddevExecutionEnd;
	
	//Workloads
	Integer						minWorkload;
	
	Integer						maxWorkload;

	Double						averageWorkload;
	
	Double						stddevWorkload;

}
