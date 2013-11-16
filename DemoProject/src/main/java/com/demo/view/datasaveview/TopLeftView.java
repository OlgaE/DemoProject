package com.demo.view.datasaveview;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TopLeftView extends CustomComponent {

	private VerticalLayout vLayout;
	private Label welcomeLabel;
	private Label loginLabel;
	private Label nameLabel;
	private Label surnameLabel;
	private Label registrationOkLabel;
	
	private TextField loginField;
	private TextField nameField;
	private TextField surnameField;
	
	private Button saveButton;

	
	public TextField getLoginField() {
		return loginField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public TextField getSurnameField() {
		return surnameField;
	}

	public Button getSaveButton() {
		return saveButton;
	}
	
	public Label getRegistrationOkLabel() {
		return registrationOkLabel;
	}

	public VerticalLayout getLayout(){
		return vLayout;
	}
	
	public TopLeftView(){
		
		// Main layout for the top left part of the page:
		vLayout = new VerticalLayout();
		
		// Welcome message:
		welcomeLabel = new Label("New User Registration:");
		//welcomeLabel.setStyleName("welcomestyle");
		vLayout.addComponent(welcomeLabel);
		
		loginLabel = new Label("Login:");
		vLayout.addComponent(loginLabel);
		loginField = new TextField();
		vLayout.addComponent(loginField);
		
		nameLabel = new Label("Name:");
		vLayout.addComponent(nameLabel);
		nameField = new TextField();
		vLayout.addComponent(nameField);
		
		surnameLabel = new Label("Surname:");
		vLayout.addComponent(surnameLabel);
		surnameField = new TextField();
		vLayout.addComponent(surnameField);
		
		saveButton = new Button("Save");
		vLayout.addComponent(saveButton);
		
		registrationOkLabel = new Label();
		vLayout.addComponent(registrationOkLabel);

	}
}
