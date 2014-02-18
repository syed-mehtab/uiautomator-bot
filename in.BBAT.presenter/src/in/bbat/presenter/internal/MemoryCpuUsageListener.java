package in.bbat.presenter.internal;

import in.BBAT.TestRunner.Listener.ICpuUsageListener;
import in.BBAT.TestRunner.Listener.IMemoryUsageListener;
import in.BBAT.abstrakt.presenter.device.model.AndroidDevice;
import in.BBAT.abstrakt.presenter.pkg.model.TestProjectModel;
import in.BBAT.abstrakt.presenter.run.model.TestRunInstanceModel;

public class MemoryCpuUsageListener implements IMemoryUsageListener,ICpuUsageListener {

	private TestRunInstanceModel model;
	private AndroidDevice device ;


	public MemoryCpuUsageListener(TestRunInstanceModel model, AndroidDevice device ) {
		this.model=model;
		this.device=device;
	}

	@Override
	public void cpuUsage(int percentUsage, long time) {
		model.saveMemoryUsage(percentUsage, time);

	}

	@Override
	public void memoryUsage(int percentUsage, long time) {
		model.saveCpuUsage(percentUsage, time);
	}

	@Override
	public String getPackageName() {
		return ((TestProjectModel)model.getParent().getParent()).getApkPackageName();
	}

}
