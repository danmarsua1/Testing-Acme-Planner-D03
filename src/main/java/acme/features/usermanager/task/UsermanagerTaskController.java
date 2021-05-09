
package acme.features.usermanager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Usermanager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/usermanager/task/")
public class UsermanagerTaskController extends AbstractController<Usermanager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private UsermanagerTaskListMineService	listMineService;

	@Autowired
	private UsermanagerTaskShowService		showService;

	@Autowired
	private UsermanagerTaskCreateService		createService;

	@Autowired
	private UsermanagerTaskUpdateService		updateService;

	@Autowired
	private UsermanagerTaskDeleteService		deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
