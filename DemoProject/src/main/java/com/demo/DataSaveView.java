package com.demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DataSaveView extends VerticalLayout implements View {

    public static final String NAME = "datasaving";

    /*VerticalLayout vLayout = new VerticalLayout();*/
    
    Label welcomeMessage = new Label();
    
    Button backToMenuButton = new Button("Main menu", new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(MainView.NAME);
		}
	});
    
    Button logoutButton = new Button("Logout", new Button.ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(NAME);
        }
    });

    public DataSaveView() {
    	VerticalLayout vLayout = new VerticalLayout();
    	vLayout.addComponent(welcomeMessage);
    	vLayout.addComponent(backToMenuButton);
    	vLayout.addComponent(logoutButton);
    	addComponent(vLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        welcomeMessage.setValue("This is for the data.");
    }
}
