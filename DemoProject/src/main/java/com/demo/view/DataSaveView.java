package com.demo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.user.User;
import com.demo.user.service.UserService;
import com.demo.view.save.BottomView;
import com.demo.view.save.TopLeftView;
import com.demo.view.save.TopRightView;
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
	private String loginName;
	private String userName;
	private String userSurname;
	private String nameToFind;

	static ApplicationContext context;
	static UserService userManager;

	// @Autowired
	// @Qualifier("user")
	private User user = new User();
	
	static {
		context = new ClassPathXmlApplicationContext("spring.xml");
		userManager = (UserService) context.getBean("userServiceImpl");
	}

	public DataSaveView() {
		setSizeFull();

		VerticalSplitPanel vPanel = new VerticalSplitPanel();
		vPanel.setSplitPosition(60);
		vPanel.setStyleName(Reindeer.LAYOUT_BLUE);
		setCompositionRoot(vPanel);

		HorizontalSplitPanel hPanel = new HorizontalSplitPanel();
		vPanel.addComponent(hPanel);

		TopLeftView topLeftView = new TopLeftView();
		VerticalLayout vLayoutTopLeft = topLeftView.getLayout();
		
		TopRightView topRightView = new TopRightView();
		VerticalLayout vLayoutTopRight = topRightView.getLayout();
		
		BottomView bottomView = new BottomView();
		VerticalLayout vLayoutBottom = bottomView.getLayout();

		hPanel.addComponent(vLayoutTopLeft);
		hPanel.addComponent(vLayoutTopRight);
		vPanel.addComponent(vLayoutBottom);

		final TextField loginField = topLeftView.getLoginField();
		loginField.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				loginName = (String) loginField.getValue();
				user.setLoginName(loginName);
			}
		});
		loginField.setImmediate(true);
		
		final TextField nameField = topLeftView.getNameField();
		nameField.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				userName = (String) nameField.getValue();
				user.setName(userName);
			}
		});
		nameField.setImmediate(true);

		final TextField surnameField = topLeftView.getSurnameField();
		surnameField.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				userSurname = surnameField.getValue();
				user.setSurname(userSurname);
			}
		});
		surnameField.setImmediate(true);

		final TextField findUserTextField = topRightView.getFindUserTextField();
		findUserTextField.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				nameToFind = findUserTextField.getValue();
			}
		});
		findUserTextField.setImmediate(true);
		
		final Button saveButton = topLeftView.getSaveButton();
		final Label registrationOkLabel=topLeftView.getRegistrationOkLabel();
		saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				userManager.insertUser(user);
				loginField.setValue("");
				nameField.setValue("");
				surnameField.setValue("");
				
				registrationOkLabel.setCaption("Registration success)");
			}
		});

		final Button backToMenuButton = bottomView.getBackToMenuButton();
		backToMenuButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(MainView.NAME);
			}
		});

		final Button logoutButton = bottomView.getLogoutButton();
		logoutButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				// "Logout" the user
				getSession().setAttribute("user", null);

				// Refresh this view, should redirect to login view
				getUI().getNavigator().navigateTo("");
			}
		});

		final Button findUserButton = topRightView.getFindUserButton();
		final Label showUserLabel = topRightView.getShowUserLabel();
		findUserButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
				User foundUser = userManager.getUser(nameToFind);
				if(foundUser != null){
					showUserLabel.setCaption(foundUser.getName() + " " + foundUser.getSurname());
				} else {
					showUserLabel.setCaption("User not found.");
				}
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {


	}
}
