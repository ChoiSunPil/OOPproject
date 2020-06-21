package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AttributePane;
import model.MyComponent;

public class AttributeEventHandler implements ActionListener {
	private MyComponent component;
	private AttributePane attributePane;

	public AttributeEventHandler(AttributePane attributePane, MyComponent component) {
		super();
		this.component = component;
		this.attributePane = attributePane;
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		MyComponent current;
		String type;
		current = component.next;
		if (obj == attributePane.change) {
			while (current != null) {
				if (current.select) {
					int x = Integer.parseInt(attributePane.getPosxText());
					int y = Integer.parseInt(attributePane.getPosyText());
					int width = Integer.parseInt(attributePane.getWidthText());
					int height = Integer.parseInt(attributePane.getHeightText());
					if (width < 10) {
						width = 10;
						attributePane.setWidthText("10");
					}
					if (height < 10) {
						height = 10;
						attributePane.setHeightText("10");
					}
					type = attributePane.ctype.getSelectedItem().toString();
					current.setLocation(x, y);
					current.setSize(width, height);
					current.setContents(attributePane.getValueText());
					current.setVar(attributePane.getVarText());
					current.setType(type);
					current.setText("(" + current.getType() + ") " + current.getVar());

					break;
				} else
					current = current.next;
			}
		}
	}
}
