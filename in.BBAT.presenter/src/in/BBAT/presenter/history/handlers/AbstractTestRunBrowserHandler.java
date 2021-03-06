package in.BBAT.presenter.history.handlers;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import in.BBAT.presenter.developer.handlers.BBATHandler;
import in.BBAT.presenter.views.developer.TestCaseBrowserView;
import in.BBAT.presenter.views.history.TestRunHistoryView;
import in.bbat.logger.BBATLogger;

public abstract class AbstractTestRunBrowserHandler extends BBATHandler {

	private static final Logger LOG = BBATLogger.getLogger(AbstractTestRunBrowserHandler.class.getName());
	@Override
	public Object run(ExecutionEvent event) {
		return run(event, getSelectedList());
	}

	@Override
	public boolean isEnabled() {

		List<?> list = getSelectedList();

		return isEnabled(list);
	}

	private List<?> getSelectedList() {
		TestRunHistoryView view  = (TestRunHistoryView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestRunHistoryView.ID);
		ISelection selection = view.getSelectedElements();
		List<?> list = null;
		if(selection instanceof IStructuredSelection)
		{
			list = ((IStructuredSelection) selection).toList();
		}
		return list;
	}

}
