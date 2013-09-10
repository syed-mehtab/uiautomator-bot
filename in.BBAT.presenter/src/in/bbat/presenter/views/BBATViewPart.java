package in.bbat.presenter.views;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.ViewPart;

public abstract class BBATViewPart extends ViewPart {

	public void addMenuManager(Viewer viewer){

		final MenuManager menuManager = new MenuManager();
		getSite().registerContextMenu(menuManager, viewer);
		menuManager.setRemoveAllWhenShown(true);
		viewer.getControl().setMenu(
				menuManager.createContextMenu(viewer.getControl()));
	
	}
}