package in.BBAT.data.model.Entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.TableGenerator;

@Entity
public class TestDeviceRunEntity extends AbstractEntity {

	@Id
	@TableGenerator(name = "DeviceRun", table = "ID_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize=1)
	@GeneratedValue(generator = "DeviceRun")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestDeviceRunEntity(TestRunEntity runEntity,TestDeviceEntity deviceEntity) {
		this(deviceEntity);
		this.testRun = runEntity;
	}

	public TestDeviceRunEntity() {
		// TODO Auto-generated constructor stub
	}


	public TestDeviceRunEntity(TestDeviceEntity deviceEntity) {
		this.device = deviceEntity;
	}


	@OneToOne
	private TestDeviceEntity device;

	private String status;

	private Timestamp startTime;

	private Timestamp endTime;

	@OneToMany(mappedBy="testDeviceRun", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@OrderColumn
	private List<TestRunInfoEntity> testRunInfos;

	@ManyToOne
	private TestRunEntity testRun;

	public TestDeviceEntity getDevice() {
		return device;
	}

	public void setDevice(TestDeviceEntity device) {
		this.device = device;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TestRunEntity getTestRun() {
		return testRun;
	}

	public void setTestRun(TestRunEntity testRun) {
		this.testRun = testRun;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public List<? extends AbstractEntity> getChildren() {
		// TODO Auto-generated method stub
		return getTestRunInfos();
	}

	public List<TestRunInfoEntity> getTestRunInfos() {
		return testRunInfos;
	}

	public void setTestRunInfos(List<TestRunInfoEntity> testRunInfos) {
		this.testRunInfos = testRunInfos;
	}
}
