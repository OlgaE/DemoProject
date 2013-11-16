package com.demo.view.save;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TopRightView extends CustomComponent {

	private VerticalLayout vLayout;
	private Label findUserLabel;
	private TextField findUserTextField;
	private Button findUserButton;
	private Label showUserLabel;

	public TextField getFindUserTextField() {
		return findUserTextField;
	}

	public Button getFindUserButton() {
		return findUserButton;
	}

	public Label getShowUserLabel() {
		return showUserLabel;
	}

	public VerticalLayout getLayout(){
		return vLayout;
	}
	
	public TopRightView(){
		
		// Main layout for the top right part of the page:
		vLayout = new VerticalLayout();
		
		findUserLabel = new Label("Enter login name to find a user:");
		vLayout.addComponent(findUserLabel);
		findUserTextField = new TextField();
		vLayout.addComponent(findUserTextField);
		
		findUserButton = new Button("Find");
		vLayout.addComponent(findUserButton);
		showUserLabel = new Label();
		vLayout.addComponent(showUserLabel);
	}
}
