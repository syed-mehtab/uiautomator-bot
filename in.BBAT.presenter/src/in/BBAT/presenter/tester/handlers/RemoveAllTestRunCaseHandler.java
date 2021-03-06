package in.BBAT.presenter.tester.handlers;

import in.BBAT.presenter.internal.TestRunExecutionManager;
import in.BBAT.presenter.views.BBATViewPart;
import in.BBAT.presenter.views.tester.AutomatorLogView;
import in.BBAT.presenter.views.tester.DeviceLogView;
import in.BBAT.presenter.views.tester.ExecutionView;
import in.BBAT.presenter.views.tester.ScreenShotView;
import in.BBAT.presenter.views.tester.TestLogView;
import in.BBAT.presenter.views.tester.TestRunnerView;
import in.bbat.logger.BBATLogger;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.PlatformUI;

public class RemoveAllTestRunCaseHandler extends AbstractTestRunnerHandler {

	private static final Logger LOG = BBATLogger.getLogger(RemoveAllTestRunCaseHandler.class.getName());
	@Override
	public Object run(ExecutionEvent event, List<?> selectedObjects) {

		LOG.info("Remove all test runs");
		TestRunExecutionManager.getInstance().clearTestRunCases();
		TestRunExecutionManager.getInstance().clearTestDevices();
		BBATViewPart.hideView(AutomatorLogView.ID);
		BBATViewPart.hideView(ScreenShotView.ID);
		BBATViewPart.hideView(DeviceLogView.ID);
		BBATViewPart.hideView(TestLogView.ID);
		BBATViewPart.hideView(ExecutionView.ID);
		TestRunnerView view  = (TestRunnerView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(TestRunnerView.ID);
		try {
//			view.clearRunViewerInput();
			view.clearDeviceRunItem();
			view.refresh();
		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	@Override
	public boolean isEnabled(List<?> object) {
		if(TestRunExecutionManager.getInstance().getTestRunCases().isEmpty())
			return false;
		if(TestRunExecutionManager.getInstance().isExecuting())
			return false;
		return true;
	}

}
