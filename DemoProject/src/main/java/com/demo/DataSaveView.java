package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.user.User;
import com.demo.user.service.UserService;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class DataSaveView extends CustomComponent implements View {

	public static final String NAME = "datasaving";
	private String userName;
	private String userSurname;
	private String nameToFind;
	private String loginName;

	static ApplicationContext context;
	static UserService userManager;

	// @Autowired
	// @Qualifier("user")
	private User user = new User();

	Label welcomeMessage = new Label();
	Label loginLabel = new Label("Login:");
	Label nameLabel = new Label("Name:");
	Label surnameLabel = new Label("Surname:");
	Label registrationOk = new Label();
	
	TextField loginField = new TextField();
	TextField nameField = new TextField();
	TextField surnameField = new TextField();
	
	Button saveButton = new Button("Save");
	Button backToMenuButton = new Button("Main Menu");
	Button logoutButton = new Button("Logout");
	
	Label findUserLabel = new Label("Enter login to find a user:");
	TextField findUserTextField = new TextField();
	Button findUserButton = new Button("Find");
	Label showUserLabel = new Label();

	static {
		context = new ClassPathXmlApplicationContext("spring.xml");
		userManager = (UserService) context.getBean("userServiceImpl");
	}

	public DataSaveView() {
		setSizeFull();

		VerticalSplitPanel vPanel = new VerticalSplitPanel();
		setCompositionRoot(vPanel);
		vPanel.setSplitPosition(60);
		vPanel.setStyleName(Reindeer.LAYOUT_BLUE);

		HorizontalSplitPanel hPanel = new HorizontalSplitPanel();
		vPanel.addComponent(hPanel);

		final VerticalLayout vLayoutTopLeft = new VerticalLayout();
		final VerticalLayout vLayoutTopRight = new VerticalLayout();
		final VerticalLayout vLayoutBottom = new VerticalLayout();

		vLayoutTopLeft.addComponent(welcomeMessage);
		vLayoutTopLeft.addComponent(loginLabel);
		vLayoutTopLeft.addComponent(loginField);
		vLayoutTopLeft.addComponent(nameLabel);
		vLayoutTopLeft.addComponent(nameField);
		vLayoutTopLeft.addComponent(surnameLabel);
		vLayoutTopLeft.addComponent(surnameField);
		vLayoutTopLeft.addComponent(saveButton);
		vLayoutTopLeft.addComponent(registrationOk);

		vLayoutTopRight.addComponent(findUserLabel);
		vLayoutTopRight.addComponent(findUserTextField);
		vLayoutTopRight.addComponent(findUserButton);
		vLayoutTopRight.addComponent(showUserLabel);

		vLayoutBottom.addComponent(backToMenuButton);
		vLayoutBottom.addComponent(logoutButton);

		hPanel.addComponent(vLayoutTopLeft);
		hPanel.addComponent(vLayoutTopRight);
		vPanel.addComponent(vLayoutBottom);

	}

	@Override
	public void enter(ViewChangeEvent event) {

		welcomeMessage.setValue("User registration:");

		loginField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				loginName = (String) loginField.getValue();
				user.setLoginName(loginName);
			}
		});
		loginField.setImmediate(true);
		
		nameField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				userName = (String) nameField.getValue();
				user.setName(userName);
			}
		});
		nameField.setImmediate(true);

		surnameField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				userSurname = surnameField.getValue();
				user.setSurname(userSurname);
			}
		});
		surnameField.setImmediate(true);

		findUserTextField.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				nameToFind = findUserTextField.getValue();
			}
		});
		
		saveButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				userManager.insertUser(user);
				loginField.setValue("");
				nameField.setValue("");
				surnameField.setValue("");
				
				registrationOk.setCaption("Registration success)");
			}
		});

		backToMenuButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(MainView.NAME);
			}
		});

		logoutButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				// "Logout" the user
				getSession().setAttribute("user", null);

				// Refresh this view, should redirect to login view
				getUI().getNavigator().navigateTo("");
			}
		});

		findUserButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				
				User foundUser = userManager.getUser(nameToFind);
				showUserLabel.setCaption(foundUser.getName() + " "
						+ foundUser.getSurname());
				System.out.println("You entered (" + userSurname + "): "
						+ foundUser.getName() + " " + foundUser.getSurname());
			}
		});
	}
}
