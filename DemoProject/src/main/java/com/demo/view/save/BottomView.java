package com.demo.view.save;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BottomView extends CustomComponent {

	private VerticalLayout vLayout;
	private Button backToMenuButton;
	private Button logoutButton;
	
	public Button getBackToMenuButton() {
		return backToMenuButton;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public VerticalLayout getLayout(){
		return vLayout;
	}
	
	public BottomView(){
		
		// Main layout for the top right part of the page:
		vLayout = new VerticalLayout();
		
		backToMenuButton = new Button("Main Menu");
		vLayout.addComponent(backToMenuButton);
		
		logoutButton = new Button("Logout");
		vLayout.addComponent(logoutButton);
	}
}
