package in.BBAT.abstrakt.presenter.run.model;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.gui.model.IGUITreeNode;
import in.BBAT.abstrakt.presenter.pkg.model.TestCaseModel;
import in.BBAT.data.model.Entities.AbstractEntity;
import in.BBAT.data.model.Entities.TestCaseEntity;
import in.BBAT.data.model.Entities.TestRunEntity;
import in.BBAT.data.model.Entities.TestRunInfoEntity;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author syed Mehtab
 *
 */
public class TestRunInstanceModel extends AbstractTreeModel {

	private TestCaseModel testCaseModel ;
	protected TestRunInstanceModel(TestRunModel parent,TestRunInfoEntity entity) {
		super(parent,entity);
	}

	public TestRunInstanceModel(TestRunModel parent,TestCaseModel testCase,String status) {
		super(parent,new TestRunInfoEntity((TestCaseEntity) testCase.getEntity(),status));
		this.setTestCaseModel(testCase);
	}
	@Override
	public String getLabel() {
		return getName();
	}

	@Override
	public Image getImage() {
		return null;
	}

	protected IGUITreeNode produceParent(AbstractEntity childEntties) {
		return new TestRunModel((TestRunEntity) childEntties);
	}

	@Override
	protected AbstractTreeModel getChild(AbstractEntity childEntity) {
		return null;
	}

	public String getName(){
		return getTestCaseModel().getName();
	}

	public TestCaseModel getTestCaseModel() {
		return testCaseModel;
	}

	public void setTestCaseModel(TestCaseModel testCaseModel) {
		this.testCaseModel = testCaseModel;
	}

	public String getStatus() {
		return ((TestRunInfoEntity)getEntity()).getVerdict();
	}

	public void setStatus(String status){
		((TestRunInfoEntity)getEntity()).setVerdict(status);
	}

}
