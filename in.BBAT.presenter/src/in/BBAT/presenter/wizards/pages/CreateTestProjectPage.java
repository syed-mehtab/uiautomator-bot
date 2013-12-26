package in.BBAT.presenter.wizards.pages;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.presenter.pkg.model.TestProjectManager;
import in.BBAT.abstrakt.presenter.pkg.model.TestProjectModel;
import in.bbat.logger.BBATLogger;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;



public class CreateTestProjectPage extends WizardPage {

	private static final Logger LOG = BBATLogger.getLogger(CreateTestProjectPage.class.getName());

	public CreateTestProjectPage(String pageName) {
		super(pageName);
		setTitle(pageName);
	}

	private NameAndDescriptionComponent nameDescComp;

	private boolean nameValid;
	private boolean descValid;

	private Spinner sp;
	

	//	private AbstractProjectTree parent;

	@Override
	public void createControl(Composite parent) {
		//		getContainer().getShell().setSize(500, 500);
		parent.setLayout(new GridLayout(1,false));
		createUpperArea(parent);
		nameDescComp = new NameAndDescriptionComponent(parent);
		nameDescComp.getNameText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String className = "[\\p{L}_$][\\p{L}\\p{N}_$]*";
				boolean matches = ((Text)e.getSource()).getText().matches(className);

				if(!matches){
					setMessage("Not a valid name",WizardPage.ERROR);
					nameValid= false;
				}
				if(isDuplicate( ((Text)e.getSource()).getText())){
					setMessage("Name already exists",WizardPage.ERROR);
					nameValid= false;
				}
				else{
					setMessage("Wizard to create Test Project", WizardPage.INFORMATION);
					nameValid = true;
				}
				pageComplete();
			}
		});

		nameDescComp.getDescText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if(!((Text)e.getSource()).getText().isEmpty())	
				{
					descValid = true;
				}
				else
					descValid = false;

				pageComplete();
			}
		});

		setPageComplete(false);

		setControl(parent);
	}

	public void pageComplete(){
		setPageComplete(nameValid && descValid);
	}

	public String getDeskription(){
		return nameDescComp.getDescription();
	}

	public String getProjName(){
		return nameDescComp.getName();
	}

	public String getApiLevel(){
		return sp.getText();
	}
	private boolean isDuplicate(String enteredName){
		try {
			List<TestProjectModel> children = TestProjectManager.getInstance().getTestProjects();
			for(AbstractTreeModel model : children){
				if(model.getName().equalsIgnoreCase(enteredName)){
					return true;
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return false;
	}

	protected void createUpperArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		comp.setLayout(new GridLayout(2, false));

		Label nameLabel = new Label(comp, SWT.NULL);
		nameLabel.setText("API Level :");
		 sp = new Spinner(comp, SWT.BORDER|SWT.READ_ONLY);
		sp.setIncrement(1);
		sp.setMinimum(16);
		sp.setMaximum(22);

		/*GridData gd = new GridData(GridData.FILL_BOTH);
		gd.grabExcessVerticalSpace = true;
		sp.setLayoutData(gd);*/
	}
}
