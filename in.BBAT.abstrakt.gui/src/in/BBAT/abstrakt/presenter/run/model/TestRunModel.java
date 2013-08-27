package in.BBAT.abstrakt.presenter.run.model;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.gui.model.IGUITreeNode;
import in.BBAT.data.model.Entities.AbstractEntity;
import in.BBAT.data.model.Entities.TestRunEntity;

import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author syed Mehtab
 *
 */
public class TestRunModel extends AbstractTreeModel {

	protected TestRunModel(AbstractEntity entity) {
		super(entity);
	}

	public TestRunModel() {
		super(new TestRunEntity());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IGUITreeNode produceParent(AbstractEntity childEntties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IGUITreeNode getChild(AbstractEntity childEntity) {
		return new TestRunInstanceModel(childEntity);
	}

	
}
