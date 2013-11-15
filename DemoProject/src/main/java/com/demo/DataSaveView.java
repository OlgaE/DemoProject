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
    
    //@Autowired
    //@Qualifier("user")
    private User user = new User();
    
    Label welcomeMessage = new Label();
    Label nameLabel = new Label("Name:");
    Label surnameLabel = new Label("Surname:");
    TextField nameField = new TextField();
    TextField surnameField = new TextField();
    Button saveButton = new Button("Save");   
    Button backToMenuButton = new Button("Main Menu");
    Button logoutButton = new Button("Logout");
    Button showDbButton = new Button("Show database entry:");
    Label showDbLabel = new Label();

    public DataSaveView() {
    	setSizeFull();
    	
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        final UserService userManager = (UserService) ctx.getBean("userServiceImpl");
    	
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
    	vLayoutTopLeft.addComponent(nameLabel);
    	vLayoutTopLeft.addComponent(nameField);
    	vLayoutTopLeft.addComponent(surnameLabel);
    	vLayoutTopLeft.addComponent(surnameField);
    	vLayoutTopLeft.addComponent(saveButton);
    	vLayoutTopRight.addComponent(showDbButton);
    	vLayoutTopRight.addComponent(showDbLabel);
    	vLayoutBottom.addComponent(backToMenuButton);
    	vLayoutBottom.addComponent(logoutButton);
    	
    	hPanel.addComponent(vLayoutTopLeft);
    	hPanel.addComponent(vLayoutTopRight);
    	vPanel.addComponent(vLayoutBottom);
    	
    	nameField.addValueChangeListener(new Property.ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				userName = nameField.getValue();
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
    	
    	saveButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				userManager.insertUser(user);
				
				nameField.setValue("");
				surnameField.setValue("");
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
    	
    	showDbButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// !!!!
				User dbUser = userManager.getUser(user.getSurname());
				showDbLabel.setCaption(dbUser.getName() + " " + dbUser.getSurname());
				System.out.println("You entered (" + userSurname + "): " + dbUser.getName() + " " + dbUser.getSurname());
			}
		});
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
        welcomeMessage.setValue("Type your name below:");
    }
}
