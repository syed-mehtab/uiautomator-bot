package in.BBAT.presenter.DND.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.presenter.pkg.model.AbstractProjectTree;
import in.BBAT.abstrakt.presenter.run.model.TestRunCaseModel;
import in.BBAT.presenter.internal.DeviceTestRun;
import in.BBAT.presenter.views.BBATViewPart;
import in.BBAT.presenter.views.tester.TestRunnerView;
import in.bbat.logger.BBATLogger;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;

public class TestDeviceRunCaseDropListener extends TestRunDropListener{

	private static final Logger LOG = BBATLogger.getLogger(TestDeviceRunCaseDropListener.class.getName());
	private DeviceTestRun devTestRun;

	public TestDeviceRunCaseDropListener(Viewer viewer,DeviceTestRun devTestRun) {
		super(viewer);
		this.devTestRun = devTestRun;
	}

	@Override
	public boolean performDrop(Object data) {	

		Object testObj=null;
		List<TestRunCaseModel> tempList = new ArrayList<TestRunCaseModel>();

		if(data instanceof ISelection){
			/*Multiple selection is allowed
			 */
			Iterator<IStructuredSelection> iterator = ((IStructuredSelection) data).iterator();
			while(iterator.hasNext())
			{
				testObj = iterator.next();
				if(testObj instanceof AbstractProjectTree){
					addToTestCaseList((AbstractTreeModel) testObj, tempList);
				}
			}
		}

		((DeviceTestRun) devTestRun).addTestCases(tempList);

		TestRunnerView.refreshView();
		return true;
	}
}
