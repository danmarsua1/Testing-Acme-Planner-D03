
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//Tasks
	@Query("select count(t) from Task t where t.isPrivate=false")
	Integer numberPublicTasks();
	
	@Query("select count(t) from Task t where t.isPrivate=true")
	Integer numberPrivateTasks();
	
	@Query("select count(t) from Task t where CURRENT_TIMESTAMP >= t.executionEnd")
	Integer numberFinishedTasks();
	
	@Query("select count(t) from Task t where CURRENT_TIMESTAMP < t.executionEnd")
	Integer numberNotFinishedTasks();
	
	//Execution periods
	@Query("select min (t.executionStart) from Task t")
	Date minExecutionStart();
	
	@Query("select max (t.executionStart) from Task t")
	Date maxExecutionStart();
	
	@Query("select min (t.executionEnd) from Task t")
	Date minExecutionEnd();
	
	@Query("select max (t.executionEnd) from Task t")
	Date maxExecutionEnd();
	
	@Query("select from_unixtime(avg(UNIX_TIMESTAMP(t.executionStart))) from Task t")
	Date averageExecutionStart();
	
	@Query("select from_unixtime(avg(UNIX_TIMESTAMP(t.executionEnd))) from Task t")
	Date averageExecutionEnd();
	
	@Query("select stddev(UNIX_TIMESTAMP(t.executionStart)) from Task t")
	Double stddevExecutionStart();
	
	@Query("select stddev(UNIX_TIMESTAMP(t.executionEnd)) from Task t")
	Double stddevExecutionEnd();
	
	//Workload
	@Query("select min (t.workLoad) from Task t")
	Integer minWorkload();
	
	@Query("select max (t.workLoad) from Task t")
	Integer maxWorkload();
	
	@Query("select avg (t.workLoad) from Task t")
	Double averageWorkload();
	
	@Query("select stddev (t.workLoad) from Task t")
	Double stddevWorkload();

}
