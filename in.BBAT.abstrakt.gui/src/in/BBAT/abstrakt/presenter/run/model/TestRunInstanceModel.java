package in.BBAT.abstrakt.presenter.run.model;

import java.util.List;

import org.eclipse.swt.graphics.Image;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.gui.model.IGUITreeNode;
import in.BBAT.data.model.Entities.AbstractEntity;
import in.BBAT.data.model.Entities.TestProjectEntity;
import in.BBAT.data.model.Entities.TestRunInfoEntity;

/**
 * 
 * @author syed Mehtab
 *
 */
public class TestRunInstanceModel extends AbstractTreeModel {

	protected TestRunInstanceModel(AbstractEntity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	public TestRunInstanceModel() {
		super(new TestRunInfoEntity());
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<IGUITreeNode> produceChildren(List<AbstractEntity> childEntties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IGUITreeNode produceParent(AbstractEntity childEntties) {
		// TODO Auto-generated method stub
		return new TestRunModel(childEntties);
	}

	@Override
	protected IGUITreeNode getChild(AbstractEntity childEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
