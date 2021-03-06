package in.BBAT.abstrakt.presenter.run.model;

import in.BBAT.data.model.Entities.TestRunEntity;
import in.BBAT.dataMine.manager.RunMineManager;

import java.util.ArrayList;
import java.util.List;


public class TestRunManager {


	private static TestRunManager instance;

	private TestRunManager()
	{
	}

	public static TestRunManager getInstance(){
		if(instance == null)
		{
			instance= new TestRunManager();
		}
		return instance;
	}


	public List<TestRunModel> getTestRuns(){
		List<TestRunModel> models = new ArrayList<TestRunModel>();
		for(TestRunEntity entity: RunMineManager.getAllTestRuns()){
			TestRunModel model = new TestRunModel(entity);
			models.add(model);
		}
		return models;
	}

}
