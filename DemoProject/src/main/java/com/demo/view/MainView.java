package com.demo.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "";

    Label welcomeMessage = new Label();
    Button enterDataButton = new Button("Enter data and save", new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			getUI().getNavigator().navigateTo(DataSaveView.NAME);
		}
	});
    
    Button doSomwethingButton = new Button("Do something else)", new Button.ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			//getUI().getNavigator().navigateTo(DataSaveView.NAME);
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

    public MainView() {
    	setSizeFull();
    	
        VerticalLayout vLayout = new VerticalLayout(welcomeMessage, enterDataButton, doSomwethingButton, logoutButton);
        vLayout.setSpacing(true);
        vLayout.setMargin(new MarginInfo(true, true, true, false));
        vLayout.setSizeUndefined();
        vLayout.setComponentAlignment(welcomeMessage, Alignment.MIDDLE_CENTER);
        vLayout.setComponentAlignment(enterDataButton, Alignment.MIDDLE_CENTER);
        vLayout.setComponentAlignment(doSomwethingButton, Alignment.MIDDLE_CENTER);
        vLayout.setComponentAlignment(logoutButton, Alignment.MIDDLE_CENTER);
        
        VerticalLayout viewLayout = new VerticalLayout(vLayout);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(vLayout, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        addComponent(viewLayout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Get the user name from the session:
        String username = String.valueOf(getSession().getAttribute("user"));
        welcomeMessage.setValue("Hello, you are logged in as \"" + username + "\"");
        welcomeMessage.setStyleName("welcomestyle");
    }
}
