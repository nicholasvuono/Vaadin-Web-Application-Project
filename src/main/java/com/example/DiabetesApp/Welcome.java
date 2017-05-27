package com.example.DiabetesApp;

import com.example.DiabetesApp.NavigationUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class Welcome extends VerticalLayout implements View{

	public Welcome(){
		
		setSizeFull();
		setSpacing(true);
		
		Panel panel = new Panel();
		VerticalLayout panelLayout = new VerticalLayout();
		
		addComponent(panel);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
		panel.setWidth("360px");
		panel.setHeight("460px");
		
		panelLayout.setMargin(true);
		panelLayout.setSpacing(true);
		
		Label title = new Label(String.format("<font size = \"8\" color=\"#ff1a1a\">SafeSugar"), ContentMode.HTML);
		title.setWidthUndefined();
		
		 Image image = new Image("", new ExternalResource("http://media.istockphoto.com/vectors/blood-drop-icon-modern-minimal-flat-design-style-vector-illustration-vector-id482397038?s=235x235"));
		
		Button button = new Button("Enter", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				NavigationUI.navigator.addView(NavigationUI.DASHBOARD, new Dashboard());
				getUI().getNavigator().navigateTo(NavigationUI.DASHBOARD);
			}
		});
			    	
		
		Label description = new Label("a way to simply visualize blood sugar data");
		description.setWidthUndefined();
		
		panelLayout.addComponent(title);
		panelLayout.addComponent(image);
		panelLayout.addComponent(button);
		panelLayout.addComponent(description);
		
		panelLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		panelLayout.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
		
		panel.setContent(panelLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		Notification.show("","WELCOME!", Notification.Type.HUMANIZED_MESSAGE);
	}

}
