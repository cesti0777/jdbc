package probono.view;

import probono.service.ProbonoProjectController;

public class RunningStartView {
	
	public static void main(String [] args){
		System.out.println("***** ��� ����Ǵ� ���� Project �˻� *****");
		ProbonoProjectController.getAllProbonoProjects(); // ��Ű�� �͸� �ؾ���.
			
		
		//��� ��� ����ڵ� �˻�
		System.out.println("\n***** ��� ��� ����� �˻� *****");
		ProbonoProjectController.getAllActivists();
		
		
		//Ư�� ���κ��� ���� �˻�
		System.out.println("\n***** Ư�� ���κ��� ���� �˻� *****");
		ProbonoProjectController.getProbono("schweitzer");
		
		
		//���κ��� id�� ���κ��� ���� ����	
		//test data - id : schweitzer,  ���� : �ֿϵ������
		System.out.println("\n***** Ư�� ���κ��� ���� ������ �� �˻� *****");
		ProbonoProjectController.updateProbono("schweitzer", "���������");
		ProbonoProjectController.getProbono("schweitzer");
		
	}
	
}








