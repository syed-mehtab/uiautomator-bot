package in.BBAT.presenter.internal;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import in.BBAT.TestRunner.Listener.IUiAutomatorListener;
import in.BBAT.abstrakt.presenter.run.model.AutomatorLogModel;
import in.BBAT.abstrakt.presenter.run.model.TestRunInstanceModel;
import in.BBAT.presenter.views.tester.AutomatorLogView;
import in.bbat.logger.BBATLogger;

public class UIAutomatorOutputListener implements IUiAutomatorListener {

	private static final Logger LOG = BBATLogger.getLogger(UIAutomatorOutputListener.class.getName());
	private TestRunInstanceModel runInstance;

	public UIAutomatorOutputListener(TestRunInstanceModel runInstance) {
		this.runInstance = runInstance;
	}

	@Override
	public void processLine(String line) {
		runInstance.saveAutoLog(line);
		
//		runInstance.addAutoLog(log);


	/*	Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				IViewPart autoLogView;
				try {
					autoLogView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(AutomatorLogView.ID);
					if(autoLogView!= null){
						((AutomatorLogView)autoLogView).refresh();
					}
				} catch (Exception e) {
					LOG.error(e);
				}

			}
		});*/

	}

}
