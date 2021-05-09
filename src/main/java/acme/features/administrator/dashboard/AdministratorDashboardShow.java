
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShow implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		Boolean res = false;
		final Principal principal = request.getPrincipal();
		if (principal.hasRole(Administrator.class)) {
			res = true;
		}

		return res;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberPublicTasks", "numberPrivateTasks", "numberFinishedTasks", "numberNotFinishedTasks", 
			"minExecutionStart", "maxExecutionStart", "minExecutionEnd", "maxExecutionEnd", "averageExecutionStart", "averageExecutionEnd",
			"stddevExecutionStart", "stddevExecutionEnd",
			"minWorkload", "maxWorkload", "averageWorkload", "stddevWorkload");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {

		assert request != null;

		final Dashboard res = new Dashboard();
		
		//Tasks
		final Integer numberPublicTasks = this.repository.numberPublicTasks();
		res.setNumberPublicTasks(numberPublicTasks);
		
		final Integer numberPrivateTasks = this.repository.numberPrivateTasks();
		res.setNumberPrivateTasks(numberPrivateTasks);
		
		final Integer numberFinishedTasks = this.repository.numberFinishedTasks();
		res.setNumberFinishedTasks(numberFinishedTasks);
		
		final Integer numberNotFinishedTasks = this.repository.numberNotFinishedTasks();
		res.setNumberNotFinishedTasks(numberNotFinishedTasks);
		
		//Execution periods
		final Date minExecutionStart = this.repository.minExecutionStart();
		res.setMinExecutionStart(minExecutionStart);
		
		final Date maxExecutionStart = this.repository.maxExecutionStart();
		res.setMaxExecutionStart(maxExecutionStart);
		
		final Date minExecutionEnd = this.repository.minExecutionEnd();
		res.setMinExecutionEnd(minExecutionEnd);
		
		final Date maxExecutionEnd = this.repository.maxExecutionEnd();
		res.setMaxExecutionEnd(maxExecutionEnd);
		
		final Date averageExecutionStart = this.repository.averageExecutionStart();
		res.setAverageExecutionStart(averageExecutionStart);
		
		final Date averageExecutionEnd = this.repository.averageExecutionEnd();
		res.setAverageExecutionEnd(averageExecutionEnd);
		
		final Double stddevExecutionStart = this.repository.stddevExecutionStart();
		res.setStddevExecutionStart(stddevExecutionStart);
		
		final Double stddevExecutionEnd = this.repository.stddevExecutionEnd();
		res.setStddevExecutionEnd(stddevExecutionEnd);
		
		//Workloads
		final Integer minWorkload = this.repository.minWorkload();
		res.setMinWorkload(minWorkload);
		
		final Integer maxWorkload = this.repository.maxWorkload();
		res.setMaxWorkload(maxWorkload);
		
		final Double averageWorkload = this.repository.averageWorkload();
		res.setAverageWorkload(averageWorkload);
		
		final Double stddevWorkload = this.repository.stddevWorkload();
		res.setStddevWorkload(stddevWorkload);

		return res;
	}


}
