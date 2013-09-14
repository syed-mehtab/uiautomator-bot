package in.BBAT.presenter.developer.handlers.testcaseBrowser;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import in.BBAT.presenter.developer.handlers.BBATHandler;
import in.bbat.presenter.views.developer.TestCaseBrowserView;

public abstract class AbstractTestCaseBrowserHandler extends BBATHandler {

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
		TestCaseBrowserView view  = (TestCaseBrowserView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestCaseBrowserView.ID);
		ISelection selection = view.getSelectedElements();
		List<?> list = null;
		if(selection instanceof IStructuredSelection)
		{
			list = ((IStructuredSelection) selection).toList();
		}
		return list;
	}

}
