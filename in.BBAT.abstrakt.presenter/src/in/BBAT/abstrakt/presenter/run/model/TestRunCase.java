package in.BBAT.abstrakt.presenter.run.model;

import org.eclipse.swt.graphics.Image;

import in.BBAT.abstrakt.presenter.pkg.model.TestCaseModel;

public class TestRunCase {

	private TestCaseModel testcase;
	private String status;

	public TestRunCase(TestCaseModel testcase){
		this.testcase = testcase;
	}

	public TestCaseModel getTestcase() {
		return testcase;
	}

	public void setTestcase(TestCaseModel testcase) {
		this.testcase = testcase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Image getImage(){
		return null;
	}
	
	public String getLabel(){
		return testcase.getName();
	}
}
