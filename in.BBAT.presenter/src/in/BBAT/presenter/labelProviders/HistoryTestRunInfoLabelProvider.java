package in.BBAT.presenter.labelProviders;

import in.BBAT.abstrakt.presenter.run.model.TestRunInstanceModel;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class HistoryTestRunInfoLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof TestRunInstanceModel){
			switch (columnIndex) {
			case 0:
				return ((TestRunInstanceModel) element).getTestCaseEntity().getSuite().getTestProject().getName();
			case 1:
				return ((TestRunInstanceModel) element).getTestCaseEntity().getSuite().getName();
			case 2:
				return ((TestRunInstanceModel) element).getTestCaseEntity().getName();
			case 3:
				return ((TestRunInstanceModel) element).getStatus();

			default:
				break;
			}
		}
		return "";
	}

}
