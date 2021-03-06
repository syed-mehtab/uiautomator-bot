package in.BBAT.presenter.perstpectives;

import in.bbat.logger.BBATLogger;

import org.apache.log4j.Logger;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.PlatformUI;

public class DeveloperPerspective implements IPerspectiveFactory {

	private static final Logger LOG = BBATLogger.getLogger(DeveloperPerspective.class.getName());
	public static final String ID = "in.BBAT.presenter.perspective.developer";
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
//		layout.setFixed(true);
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().addPerspectiveListener(new BBATPerspectiveListener());
	}

}
