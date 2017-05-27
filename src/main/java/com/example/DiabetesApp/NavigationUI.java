package com.example.DiabetesApp;

import javax.servlet.annotation.WebServlet;
import com.example.DiabetesApp.Welcome;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("valo")
@SuppressWarnings("serial")
public class NavigationUI extends UI {
	
	public static Navigator navigator;
	public static final String WELCOME = "welcome";
	public static final String DASHBOARD = "dashboard";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	final VerticalLayout layout = new VerticalLayout();
    	layout.setMargin(true);
    	layout.setSpacing(true);
    	setContent(layout);
    	
    	ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
    	
    	navigator = new Navigator(UI.getCurrent(), viewDisplay);
    	navigator.addView("", new Welcome());
    }

    @WebServlet(urlPatterns = "/*", name = "NavigationUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigationUI.class, productionMode = false)
    public static class NavigationUIServlet extends VaadinServlet {
    }
}
