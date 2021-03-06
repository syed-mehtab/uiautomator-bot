package in.BBAT.presenter.developer.handlers.testcaseBrowser;

import in.BBAT.abstrakt.presenter.pkg.model.TestCaseModel;
import in.bbat.logger.BBATLogger;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

public class EditScriptHandler extends AbstractTestCaseBrowserHandler {

	private static final Logger LOG = BBATLogger.getLogger(EditScriptHandler.class.getName());
	@Override
	public Object run(ExecutionEvent event, List<?> selectedObjects) {

		LOG.info("Edit script");
		try {
			((TestCaseModel) selectedObjects.get(0)).openEditor();
		} catch (Exception e) {
			LOG.error(e);
		}
		return null;
	}


	@Override
	public boolean isEnabled(List<?> object) {
		if(object==null){
			return false;
		}
		if(!object.isEmpty())
		{
			if(object.size()==1)
			{
				if(object.get(0) instanceof TestCaseModel)
				{
					return true;
				}
			}
		}
		return false;
	}
}
