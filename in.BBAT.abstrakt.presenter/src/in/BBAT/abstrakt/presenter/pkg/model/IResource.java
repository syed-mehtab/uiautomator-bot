package in.BBAT.abstrakt.presenter.pkg.model;

public interface IResource {

	String getPath();
	void createResource() throws Exception;
	void deleteResource() throws Exception;
	void linkToProject();
	void deLinkFromProject();
}
