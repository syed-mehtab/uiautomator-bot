package in.BBAT.TestRunner.Listener;


public interface IDeviceConnectionListener  {

	public void deviceDisconnected(IAndroidDevice device);

	public void deviceConnected(IAndroidDevice device);

	public void deviceChanged(IAndroidDevice device, int changeMask);
	
}
