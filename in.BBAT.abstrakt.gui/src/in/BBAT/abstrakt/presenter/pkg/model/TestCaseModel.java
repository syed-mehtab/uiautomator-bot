package in.BBAT.abstrakt.presenter.pkg.model;

import java.util.List;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.gui.model.IGUITreeNode;
import in.BBAT.data.model.Entities.AbstractEntity;
import in.BBAT.data.model.Entities.TestCaseEntity;
import in.BBAT.data.model.Entities.TestProjectEntity;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author Syed Mehtab
 *
 */
public class TestCaseModel extends AbstractTreeModel {

	private String scriptPath;

	protected TestCaseModel(AbstractEntity entity) {
		super(entity);

	}

	public TestCaseModel(String testCaseName){
		this(new TestCaseEntity());
		setName(testCaseName);
		createTestCaseTemplate();
		
	}

	/**
	 * this method will create the template.
	 */
	private void createTestCaseTemplate() {
		// TODO Auto-generated method stub

	}



	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	@Override
	public String getLabel() {
		return null;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	protected List<IGUITreeNode> produceChildren(List<AbstractEntity> childEntties) {
		return null;
	}

	@Override
	protected IGUITreeNode produceParent(AbstractEntity childEntties) {
		return new TestProjectModel((TestProjectEntity) childEntties);
	}

	@Override
	protected IGUITreeNode getChild(AbstractEntity childEntity) {
		return null;
	}

}
