package in.BBAT.abstrakt.presenter.pkg.model;

import in.BBAT.abstrakt.gui.BBATImageManager;
import in.BBAT.abstrakt.gui.model.AbstractTreeModel;
import in.BBAT.data.model.Entities.AbstractEntity;
import in.BBAT.data.model.Entities.TestProjectEntity;
import in.BBAT.data.model.Entities.TestRunEntity;
import in.BBAT.data.model.Entities.TestSuiteEntity;
import in.BBAT.dataMine.manager.JaxbExportImport;
import in.BBAT.dataMine.manager.ProjectMineManager;
import in.bbat.utility.FileUtils;
import in.bbat.utility.ZipFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author Syed Mehtab
 *
 */
public class TestProjectModel extends AbstractProjectTree {

	private IFolder folder ;
	private BBATProject project;

	protected TestProjectModel(TestProjectEntity entity) throws Exception {
		super(null,entity, entity.getName(),false);
	}

	protected TestProjectModel(TestProjectEntity entity,boolean createResource) throws Exception {
		super(null,entity, entity.getName(),createResource);
	}

	public TestProjectModel(String projectName,int apiLevel) throws Exception {
		this(new TestProjectEntity(projectName,apiLevel),true);
	}

	@Override
	public String getLabel() {
		return getName();
	}

	public String getName() {
		return ((TestProjectEntity)getEntity()).getName();
	}

	public Image getImage() {
		return BBATImageManager.getInstance().getImage(BBATImageManager.PROJECT_GIF_16);
	}

	@Override
	protected AbstractProjectTree getChild(AbstractEntity childEntity) throws Exception {
		return new TestSuiteModel(this,(TestSuiteEntity) childEntity);
	}

	@Override
	public void setName(String name) {
		((TestProjectEntity)getEntity()).setName(name);
	}

	@Override
	public void createResource() {
		File f = new File(getPath());
		f.mkdirs();
	}

	public void setDescription(String description) {
		((TestProjectEntity)getEntity()).setDescription(description);		
	}

	public String getDescription()
	{
		return ((TestProjectEntity)getEntity()).getDescription();
	}

	@Override
	public void linkToProject() {

		project = new BBATProject(getName(),getApiLevel());
		project.linkPackage(getResourcePath());
	}

	@Override
	public void deLinkFromProject() {
		project.deleteProject();
	}

	@Override
	public void deleteResource() throws Exception {
		super.deleteResource();
		TestProjectManager.getInstance().getTestProjects().remove(this);
	}

	public static TestProjectModel create(String name, String description,int apiLevel, String apkPkgname, boolean createHelperSuite) throws Exception{
		TestProjectModel model = new TestProjectModel(name,apiLevel);
		model.setDescription(description);
		model.setApiLevel(apiLevel);
		model.setApkPackageName(apkPkgname);
		model.save();
		if(createHelperSuite)
			createHelperSuite(model);
		TestProjectManager.getInstance().getTestProjects().add(model);
		return model;
	}

	private static void createHelperSuite(TestProjectModel model) throws Exception {
		TestSuiteModel helperSuite = TestSuiteModel.create(model, TestSuiteModel.BBAT_UTILITY, "This suite will have all the helper and utility classes. \n" +
				"This can also be used for creating pameterized test cases. The classes in this suite cannot be run as testcases. ", true);
		TestCaseModel.createHelper(helperSuite);
	}

	public void setApkPackageName(String apkPkgname) {
		((TestProjectEntity)getEntity()).setApkPackageName(apkPkgname);		
	}

	public String getApkPackageName() {
		return ((TestProjectEntity)getEntity()).getApkPackageName();		
	}


	public void setApiLevel(int apiLevel) {
		((TestProjectEntity)getEntity()).setApiLevel(apiLevel);		
	}

	public int getApiLevel(){
		return ((TestProjectEntity)getEntity()).getApiLevel();		
	}

	public void export(String dirPath) throws Exception{
		//create temporary file
		String tempFilePath ="temp"+System.currentTimeMillis()+Path.SEPARATOR+"project";
		File tempFile = new File(tempFilePath);
		tempFile.mkdirs();

		//export xml
		JaxbExportImport exp = new JaxbExportImport();
		exp.export(tempFile.getAbsolutePath(), (TestProjectEntity) getEntity());

		//expport the project and scripts
		File proj = new File(tempFile.getAbsolutePath()+Path.SEPARATOR+getName());
		proj.mkdirs();
		FileUtils.copyFolder(new File(getResourcePath()), proj);

		//zip exported artifacts
		ZipFiles.zipDirectory(tempFile, dirPath+Path.SEPARATOR+getName()+".dat");

		//delete temporary file
		FileUtils.delete(tempFile);

	}

	@Override
	public BBATProject getProject() {
		return project;		
	}

	@Override
	public List<TestRunEntity> getRefTestRunEntities() {
		List<TestRunEntity> testRuns = ProjectMineManager.getAllRunsContainingTestProject((TestProjectEntity) getEntity());
		return testRuns;
	}

	public List<String> getLibraryScriptpaths() throws Exception {

		List<String> scriptPaths = new ArrayList<String>();
		for (AbstractTreeModel suiteModel : getChildren()) {
			if(((TestSuiteModel)suiteModel).isHelper()){
				scriptPaths.addAll(((TestSuiteModel)suiteModel).getScriptPaths());
			}
		}		
		return scriptPaths;
	}

}
