package com.example.DiabetesApp;

import java.io.IOException;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Image;

@SuppressWarnings("serial")
public class Dashboard extends AbsoluteLayout implements View{

	public Dashboard(){
		
		setWidth("1400px");
		setHeight("1000px");
		//addComponent(new Menu(), "left: 0px; top: 0px;");
		
		Label title = new Label(String.format("<font size = \"7.0\" color=\"#ff1a1a\">Dashboard"), ContentMode.HTML);
		addComponent(title, "left: -2px; top: 0px;");
		
		Label description = new Label("visualize your data");
		addComponent(description, "left: 245x; top: 36px;");
		
		Label webaddress = new Label(String.format("<font size = \"4.0\" color=\"Black\">Link:"), ContentMode.HTML);
		addComponent(webaddress, "left: 0px; top: 105px;");
		
		TextField url = new TextField();
		addComponent(url, "left: 45px; top: 95px;");
		
		Panel panel = new Panel();
		AbsoluteLayout panelLayout = new AbsoluteLayout();
		addComponent(panel, "left: 0px; top: 160px;");
		panel.setWidth("500");
		panel.setHeight("350");
		
		Panel panel2 = new Panel();
		AbsoluteLayout panelLayout2 = new AbsoluteLayout();
		addComponent(panel2,  "left: 525px; top: 160px;");
		panel2.setWidth("750");
		panel2.setHeight("350");
		
		Panel panel3 = new Panel();
		AbsoluteLayout panelLayout3 = new AbsoluteLayout();
		addComponent(panel3,  "left: 0px; top: 535px;");
		panel3.setWidth("1275");
		panel3.setHeight("440");
		
		Grid grid = new Grid();
		grid.setWidth("1275");
		
		Button button = new Button("Visualize", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					Gather.getInformation(url.getValue());
				} catch (IOException e) {
				}
				Gather.getAverages();
				Gather.createObject();
				grid.setContainerDataSource(new BeanItemContainer<>(BloodSugar.class, Gather.objects));
				
				Image p = new Image("Average Blood Sugar Evaluation", new ExternalResource(Create.EvaluationChart()));
				panelLayout.addComponent(p, "left: 50px; top: 50px");
				panel.setContent(panelLayout);
				
				Label mgdl = new Label(String.format("<font size = \"7.0\" color=\"#b2b2b0\">A1C: " + Create.mgdl()), ContentMode.HTML);
				panelLayout2.addComponent(mgdl, "left: 490px; top: 10px;");
				
				Label grade = new Label(String.format("<font size = \"7.0\" color=\"#b2b2b0\">Grade: " + Create.grade()), ContentMode.HTML);
				panelLayout2.addComponent(grade, "left: 240px; top: 10px;");
				
				Image b = new Image("Average Daily Blood Sugar", new ExternalResource(Create.DailyAverageChart()));
				panelLayout2.addComponent(b, "left: 30px; top: 155px");
				panel2.setContent(panelLayout2);
				
				Gather.dates.clear();
				Gather.readings.clear();
				Gather.objects.clear();
				Gather.averages.clear();
			}
		});
		
		grid.setContainerDataSource(new BeanItemContainer<>(BloodSugar.class, Gather.objects));
		grid.setColumnOrder("date","morning","noon","evening");
		grid.getColumn("date");
		grid.getColumn("morning");
		grid.getColumn("noon");
		grid.getColumn("evening");
		grid.setHeightMode(HeightMode.ROW);
		grid.setHeightByRows(10);
		grid.setSelectionMode(SelectionMode.NONE);
		
		addComponent(button, "left: 250px; top: 95px;");
		panelLayout3.addComponent(grid);
		panel3.setContent(panelLayout3);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("","DASHBOARD!", Notification.Type.HUMANIZED_MESSAGE);
	}

}
