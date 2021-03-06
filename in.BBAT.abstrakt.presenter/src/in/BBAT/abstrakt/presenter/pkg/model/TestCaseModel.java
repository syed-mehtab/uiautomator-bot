package in.BBAT.abstrakt.presenter.pkg.model;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import in.BBAT.abstrakt.gui.Activator;
import in.BBAT.abstrakt.gui.BBATImageManager;
import in.BBAT.abstrakt.presenter.run.model.TestRunModel;
import in.BBAT.data.model.Entities.TestCaseEntity;
import in.BBAT.data.model.Entities.TestProjectEntity;
import in.BBAT.data.model.Entities.TestRunEntity;
import in.BBAT.data.model.Entities.TestSuiteEntity;
import in.BBAT.dataMine.manager.ProjectMineManager;
import in.BBAT.dataMine.manager.TestCaseMineManager;
import in.bbat.logger.BBATLogger;
import in.bbat.utility.BBATPluginUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * 
 * @author Syed Mehtab
 *
 */
public class TestCaseModel extends AbstractProjectTree{

	public final static String JAVA = ".java";

	final static String UTILITY_TEMPLATE ="utility.ftl";
	final static String API_16_UTILITY_TEMPLATE ="ApiLevel16_utility.ftl";

	final static String TESTCASE_TEMPLATE ="testcase.ftl";

	final static String LIBRARY_TEMPLATE ="libraryClass.ftl";

	public final static String BBATUTILITY_CLASS_NAME ="BBATUtility";

	private static final Logger LOG = BBATLogger.getLogger(TestCaseModel.class.getName());
	public IFile testCaseFile;


	private String testScriptPath;

	protected TestCaseModel(TestSuiteModel parent ,TestCaseEntity entity) throws Exception {
		super(parent,entity,entity.getName()+JAVA,false);

	}

	protected TestCaseModel(TestSuiteModel parent ,TestCaseEntity entity,boolean createResource) throws Exception {
		super(parent,entity,entity.getName()+JAVA,createResource);
	}

	public TestCaseModel(TestSuiteModel parent,String testCaseName) throws Exception{
		this(parent,new TestCaseEntity((TestSuiteEntity)parent.getEntity(),testCaseName),true);

	}

	@Override
	public String getLabel() {
		return getName();
	}

	@Override
	public String getName() {
		return ((TestCaseEntity)getEntity()).getName();
	}
	@Override
	public Image getImage() {
		if(getProject().hasErrors(getIFile())){
			return BBATImageManager.getInstance().getImage(BBATImageManager.TESTCASE_ERROR_GIF_8);
		}
		if(isHelper()){
			return BBATImageManager.getInstance().getImage(BBATImageManager.LIBRARY_CLASS_GIF_16);
		}
		return BBATImageManager.getInstance().getImage(BBATImageManager.TESTCASE_GIF_16);

	}

	@Override
	public void createResource() throws IOException {
		File newFile = new File(getPath());
		newFile.createNewFile();
	}


	@Override
	public String getDescription() {
		return ((TestCaseEntity)getEntity()).getDescription();
	}

	@Override
	public void setDescription(String description) {
		((TestCaseEntity)getEntity()).setDescription(description);
	}

	@Override
	public void linkToProject() {
		testCaseFile = getProject().linkScript(getTestScriptPath());
	}

	@Override
	public void deLinkFromProject() {
		getProject().deleteLink(getIFile());
	}

	public IFile getIFile(){
		return testCaseFile;
	}

	public String getTestScriptPath() {
		return getResourcePath();
	}

	public String getCompleteScriptName(){
		return getParent().getParent().getName()+"."+getParent().getName()+"."+getName();
	}

	public void createContents(boolean isLibraryCase) {

		String template = TESTCASE_TEMPLATE;
		if(isLibraryCase)
		{
			template = LIBRARY_TEMPLATE;
		}
		fillContents(template);
	}

	private void fillContents(String template) {

		Configuration cfgFtl = new Configuration();
		try {
			cfgFtl.setDirectoryForTemplateLoading(new File(BBATPluginUtility.getInstance().getPluginDir(Activator.PLUGIN_ID)+Path.SEPARATOR+"lib"+Path.SEPARATOR));
			Template testCaseTemplate = cfgFtl.getTemplate(template);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("package_name", getParent().getParent().getName()+"."+getParent().getName());
			data.put("testCase_name", getName());
			data.put("description", getDescription());
			data.put("utility_import", getParent().getParent().getName()+"."+TestSuiteModel.BBAT_UTILITY+"."+TestCaseModel.BBATUTILITY_CLASS_NAME);
			Writer fileWriter = new FileWriter(new File(getTestScriptPath()));
			try {
				testCaseTemplate.process(data, fileWriter);
			} finally {
				fileWriter.close();
			}

		} catch (IOException e1) {
			LOG.error(e1);
		} catch (TemplateException e) {
			LOG.error(e);
		}
		getProject().refresh();
	}

	public void createHelperContents()
	{

		if(getApiLevel()==16)
			fillContents(API_16_UTILITY_TEMPLATE);
		else
			fillContents(UTILITY_TEMPLATE);
	}

	public void openEditor() throws Exception{
		IEditorInput input=null;
		input = new FileEditorInput(getIFile());
		try
		{
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, "org.eclipse.jdt.ui.CompilationUnitEditor");
		} catch (PartInitException e)
		{
			LOG.error(e);
			throw new Exception("Failled to open java editor");
		}
	}

	@Override
	public void save() {
		super.save();
		createContents(isHelper());
	}

	public static TestCaseModel create(TestSuiteModel suite,String name, String description) throws Exception{
		TestCaseModel newTestCase = new TestCaseModel(suite, name);
		newTestCase.setDescription(description);
		newTestCase.save();
		suite.addChild(newTestCase);
		return newTestCase;
	}

	public static  TestCaseModel createHelper(TestSuiteModel suite) throws Exception{
		TestCaseModel testCase = create(suite, BBATUTILITY_CLASS_NAME, "Utility for screenshots ");
		testCase.createHelperContents();
		return testCase;
	}

	@Override
	public BBATProject getProject() {
		return ((TestSuiteModel)getParent()).getProject();
	}

	@Override
	public void deleteResource() throws Exception {
		super.deleteResource();
		getParent().removeChild(this);
	}

	@Override
	public List<TestRunEntity> getRefTestRunEntities() {
		List<TestRunEntity> testRuns = TestCaseMineManager.getAllRunsContainingTestCase((TestCaseEntity) getEntity());
		return testRuns;
	}

	public boolean hasErrors(){
		return getProject().hasErrors(testCaseFile);
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isHelper() {
		return ((TestSuiteModel)getParent()).isHelper();
	}

	@Override
	public int getApiLevel() {
		return ((TestSuiteModel)getParent()).getApiLevel();
	}
}

