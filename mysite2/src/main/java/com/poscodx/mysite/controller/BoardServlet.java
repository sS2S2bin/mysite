package com.poscodx.mysite.controller;

import java.util.Map;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.controller.action.board.BoardMainAction;
import com.poscodx.mysite.controller.action.board.boarddeleteAction;
import com.poscodx.mysite.controller.action.board.modifyAction;
import com.poscodx.mysite.controller.action.board.modifyformAction;
import com.poscodx.mysite.controller.action.board.replyAction;
import com.poscodx.mysite.controller.action.board.viewAction;
import com.poscodx.mysite.controller.action.board.writeAction;
import com.poscodx.mysite.controller.action.board.writeformAction;
import com.poscodx.mysite.controller.action.guestbook.GuestbookMainAction;
import com.poscodx.mysite.controller.action.guestbook.deleteAction;
import com.poscodx.mysite.controller.action.guestbook.deleteformAction;
import com.poscodx.mysite.controller.action.guestbook.insertAction;

public class BoardServlet extends ActionServlet {
	
	
	private static final long serialVersionUID = 1L;
	private Map<String, Action> mapAction = Map.of(
			"writeform", new writeformAction(),
			"view", new viewAction(),
			"modifyform", new modifyformAction(),
			"write", new writeAction(),
			"delete", new boarddeleteAction(),
			"modify", new modifyAction(),
			"reply", new replyAction()
			);
	
	
	@Override
	protected Action getAction(String actionName) {
		return  mapAction.getOrDefault(actionName, new BoardMainAction()); //mapAction.get(actionName);

	}

}
