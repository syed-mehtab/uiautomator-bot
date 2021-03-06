package in.BBAT.presenter.history.handlers;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import in.BBAT.presenter.developer.handlers.BBATHandler;
import in.BBAT.presenter.views.history.TestRunHistoryView;
import in.BBAT.presenter.views.history.TestRunInfoView;
import in.bbat.logger.BBATLogger;

public abstract class AbstractTestRunInfoHandler extends BBATHandler {

	private static final Logger LOG = BBATLogger.getLogger(AbstractTestRunInfoHandler.class.getName());
	@Override
	public Object run(ExecutionEvent event) {
		return run(event, getSelectedList());
	}

	@Override
	public boolean isEnabled() {
		List<?> list =null;
		try{
			list = getSelectedList();
		}catch (Exception e) {
		}

		return isEnabled(list);
	}

	private List<?> getSelectedList() {
		TestRunInfoView view  = (TestRunInfoView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestRunInfoView.ID);
		ISelection selection = view.getSelectedElements();
		List<?> list = null;
		if(selection instanceof IStructuredSelection)
		{
			list = ((IStructuredSelection) selection).toList();
		}
		return list;
	}
}
