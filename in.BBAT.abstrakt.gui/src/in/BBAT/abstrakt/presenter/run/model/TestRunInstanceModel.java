package in.BBAT.abstrakt.presenter.run.model;

import java.util.List;

import org.eclipse.swt.graphics.Image;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.gui.model.IGUITreeNode;
import in.bbat.data.model.Entities.IBBATEntity;

/**
 * 
 * @author syed Mehtab
 *
 */
public class TestRunInstanceModel extends AbstractTreeModel {

	protected TestRunInstanceModel(IBBATEntity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
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
	protected List<IGUITreeNode> produceChildren(List<IBBATEntity> childEntties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IGUITreeNode produceParent(IBBATEntity childEntties) {
		// TODO Auto-generated method stub
		return new TestRunModel(childEntties);
	}

	@Override
	protected IGUITreeNode getChild(IBBATEntity childEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
