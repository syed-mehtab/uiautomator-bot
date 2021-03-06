package in.BBAT.presenter.wizards.pages;

import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.abstrakt.presenter.pkg.model.TestProjectManager;
import in.BBAT.abstrakt.presenter.pkg.model.TestProjectModel;
import in.BBAT.utils.AndroidXmlParser;
import in.bbat.configuration.BBATProperties;
import in.bbat.logger.BBATLogger;
import in.bbat.utility.AndroidSdkUtility;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.android.xml.AndroidXPathFactory;



public class CreateTestProjectPage extends WizardPage {

	private static final Logger LOG = BBATLogger.getLogger(CreateTestProjectPage.class.getName());

	public CreateTestProjectPage(String pageName) {
		super(pageName);
		setTitle(pageName);
	}

	private NameAndDescriptionComponent nameDescComp;

	private boolean nameValid;
	private boolean descValid;
	private boolean apkPackageNameValid = true;
	private boolean validApiLevel =true ;

	private Spinner sp;

	private Text uiAutoJarTextPath;

	private Text androidTextPath;

	private TestProjectModel project;

	private Button browseDirButton;

	private Text apkPackageNameText;

	public CreateTestProjectPage(String pageName,TestProjectModel project) {
		this(pageName);
		this.project =project;

	}
	//	private AbstractProjectTree parent;

	@Override
	public void createControl(Composite parent) {
		//		getContainer().getShell().setSize(500, 500);
		parent.setLayout(new GridLayout(1,false));
		createUpperArea(parent);
		if(project!=null){
			nameDescComp = new NameAndDescriptionComponent(parent,project.getName(),project.getDescription(),false);
		}
		else
		{
			nameDescComp = new NameAndDescriptionComponent(parent,"","",true);
		}
		nameDescComp.getNameText().addModifyListener( new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String className = "[\\p{L}_$][\\p{L}\\p{N}_$]*";

				boolean matches = ((Text)e.getSource()).getText().trim().matches(className);

				if(JavaElementValidator.isReserverWord(((Text)e.getSource()).getText().trim())){
					setMessage("You cannot use java reserved keyword",WizardPage.ERROR);
					nameValid= false;
					pageComplete();
					return;
				}

				if(!matches){
					setMessage("Not a valid name",WizardPage.ERROR);
					nameValid= false;
					pageComplete();
					return;
				}
				if(isDuplicate(((Text)e.getSource()).getText().trim())){
					setMessage("Name already exists",WizardPage.ERROR);
					nameValid= false;
					pageComplete();
					return;
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
				if(!((Text)e.getSource()).getText().trim().isEmpty())	
				{
					descValid = true;
				}
				else
					descValid = false;

				pageComplete();
			}
		});

		createLowerArea(parent);
		setPageComplete(false);

		setControl(parent);
	}

	private void createLowerArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		comp.setLayout(new GridLayout(4, false));

		Label nameLabel = new Label(comp, SWT.NULL);
		nameLabel.setText("APK Package Name :");

		apkPackageNameText = new Text(comp, SWT.BORDER);
		apkPackageNameText.setLayoutData(new GridData(GridData.FILL_BOTH));
		apkPackageNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				/*if(!((Text)e.getSource()).getText().trim().isEmpty())	
				{
					apkPackageNameValid = true;
				}
				else
					apkPackageNameValid = false;*/

				pageComplete();
			}
		});
		apkPackageNameText.setMessage("Enter package name");

		if(project!=null){
			apkPackageNameText.setText(project.getApkPackageName());
			apkPackageNameText.setEditable(false);
		}
		Button infoButton = new Button(comp, SWT.PUSH);
		infoButton.setText("?");

		infoButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info","Pakage is required to trace the memory and cpu usage." );
			}
		});


		browseDirButton = new Button(comp, SWT.PUSH);
		GridData gdzipFilePathButton = new GridData(GridData.FILL);
		browseDirButton.setLayoutData(gdzipFilePathButton);
		browseDirButton.setText("Select APK ");
		addListnerForBrowseButton();

	}

	private void addListnerForBrowseButton() {

		browseDirButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog exportDialog = new FileDialog(getShell(),SWT.SAVE);
				exportDialog.setFilterExtensions(new String[] {"*.apk"});
				String destinationPath = exportDialog.open();
				if (destinationPath != null) {

					apkPackageNameText.setText(	(new AndroidXmlParser().getIntents(destinationPath)));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	public void pageComplete(){
		setPageComplete(nameValid && descValid && apkPackageNameValid && validApiLevel);
	}

	public String getDeskription(){
		return nameDescComp.getDescription();
	}

	public String getProjName(){
		return nameDescComp.getName();
	}

	public String getPackageName(){
		return apkPackageNameText.getText().trim();
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
		if(AndroidSdkUtility.minimumApiLevel()>=16){
			sp.setMinimum(AndroidSdkUtility.minimumApiLevel());
		}
		else
			sp.setMinimum(16);

		if(AndroidSdkUtility.maximumApiLevel()<16)
			sp.setMaximum(16);
		else
			sp.setMaximum(AndroidSdkUtility.maximumApiLevel());

		if(AndroidSdkUtility.maximumApiLevel()>16){
			if(project==null)
				sp.setSelection(AndroidSdkUtility.maximumApiLevel());
			else {
				sp.setSelection(project.getApiLevel());	
				sp.setEnabled(false);	
			}
		}

		sp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!AndroidSdkUtility.isPlatformPresent(Integer.valueOf(sp.getText())))
				{
					setMessage("Could not find android-"+sp.getText()+" in SDK",WizardPage.ERROR);
					validApiLevel = false;
					pageComplete();
					return;
				}

				uiAutoJarTextPath.setText(BBATProperties.getInstance().getAndroid_UiAutomatorPath(Integer.valueOf(sp.getText())));
				androidTextPath.setText(BBATProperties.getInstance().getAndroid_AndroidJarPath((Integer.valueOf(sp.getText()))));

				validApiLevel = true;
				setMessage(null);
				pageComplete();

			}

		});
		Label uiUatoJar = new Label(comp, SWT.NULL);
		uiUatoJar.setText("Uiautomator Jar Path :");

		uiAutoJarTextPath = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		uiAutoJarTextPath.setText(BBATProperties.getInstance().getAndroid_UiAutomatorPath(Integer.valueOf(sp.getText())));
		uiAutoJarTextPath.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label androidJar = new Label(comp, SWT.NULL);
		androidJar.setText("Android Jar Path :");

		androidTextPath = new Text(comp, SWT.BORDER|SWT.READ_ONLY);
		androidTextPath.setText(BBATProperties.getInstance().getAndroid_AndroidJarPath((Integer.valueOf(sp.getText()))));
		androidTextPath.setLayoutData(new GridData(GridData.FILL_BOTH));
		/*GridData gd = new GridData(GridData.FILL_BOTH);
		gd.grabExcessVerticalSpace = true;
		sp.setLayoutData(gd);*/
	}
}
