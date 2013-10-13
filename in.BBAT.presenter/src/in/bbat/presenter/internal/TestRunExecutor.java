package in.bbat.presenter.internal;


import in.BBAT.abstrakt.presenter.run.model.TestRunCase;
import in.BBAT.abstrakt.presenter.run.model.TestRunModel;
import in.BBAT.testRunner.runner.UiAutoTestCaseJar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestRunExecutor {

	private Set<DeviceTestRun> deviceTestRuns;

	public TestRunExecutor(Set<DeviceTestRun> deviceTestRuns) {
		this.deviceTestRuns = deviceTestRuns;
	}

	public void run() {
		TestRunModel testRun = new TestRunModel();
		testRun.setStartTime(new Timestamp(System.currentTimeMillis()));
		testRun.save();
		UiAutoTestCaseJar jar = new UiAutoTestCaseJar(getTestScriptPaths());

		for (DeviceTestRun device : deviceTestRuns) {
			device.setTestRun(testRun);
			device.setTestRunCases(TestRunExecutionManager.getInstance().getTestRunCases());
			device.createTab();
			device.execute(jar);
		}

		testRun.setEndtiTime(new Timestamp(System.currentTimeMillis()));
		testRun.update();
	}
	
	public List<String> getTestScriptPaths() {
		List<String> testScriptPaths = new ArrayList<String>();
		for (TestRunCase testRunCase : TestRunExecutionManager.getInstance().getTestRunCases()) {
			if(!testScriptPaths.contains(testRunCase.getTestcase().getTestScriptPath()))
				testScriptPaths.add(testRunCase.getTestcase().getTestScriptPath());
		}
		return testScriptPaths;
	}
}
