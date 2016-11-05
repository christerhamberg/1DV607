package controller;

import model.MemberFacadeInterface;
import view.UIInterface;

public interface UseCaseInterface {
	
	public void runUseCase (UIInterface ui, MemberFacadeInterface memFacade);

}
