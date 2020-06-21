package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.AttributePane;
import model.EditorPane;
import model.MyComponent;

public class EditorEventHandler extends MouseAdapter implements MouseMotionListener, ActionListener {
	MyComponent first;
	AttributePane attributePane;
	private MyComponent current;
	private MyComponent component;
	private static boolean check = false;

	public EditorEventHandler() {
		first = null;
		current = null;
	}

	public EditorEventHandler(JPanel attributePane, MyComponent component) {
		first = component;
		current = null;
		this.attributePane = (AttributePane) attributePane;
	}

	public void mousePressed(MouseEvent e) {
		if (check) {
			MyComponent.num++;
			EditorPane pane = (EditorPane) e.getSource();
			component = new MyComponent(first, attributePane);
			component.setLocation(e.getX(), e.getY());
			component.setVar("MyComponent" + MyComponent.num);
			component.setText("(" + component.getType() + ") " + component.getVar());
			pane.add(component);

		}
	} // 눌러질때

	public void mouseReleased(MouseEvent e) {
		if (check) {
			EditorPane pane = (EditorPane) e.getSource();
			int width = e.getX() - component.getX();
			int height = e.getY() - component.getY();
			if (width < 10)
				width = 10;
			if (height < 10)
				height = 10;
			component.setSize(width, height);
			pane.repaint();
			if (first.next == null) {
				first.next = component;
			} else {
				current = first.next;
				while (current.next != null) {
					current = current.next;
				}
				current.next = component;
			}

			check = false;
		}
	} // 때질때

	public void mouseDragged(MouseEvent e) {
		if (check) {
			EditorPane pane = (EditorPane) e.getSource();
			int width = e.getX() - component.getX();
			int height = e.getY() - component.getY();
			if (width < 10)
				width = 10;
			if (height < 10)
				height = 10;
			component.setSize(width, height);
			pane.repaint();
		}
	} // 드래깅할때

	public void mouseMoved(MouseEvent e) {
	} // 움직일때

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		MyComponent previous = null;
		switch (button.getName()) {
		case "plus":
			check = true;
			break;
		case "delete":
			if (first.next != null) {
				current = first.next;
				while (!current.select) {
					if (current.next != null) {
						previous = current;
						current = current.next;
					} else
						break;
				}
				if (current.select) {
					EditorPane pane = (EditorPane) first.next.getParent();
					if (current.next == null) {
						if (current != first.next)
							previous.next = null;
						else
							first.next = null;
					} else if (current == first.next) {
						first.next = current.next;
					} else {
						previous.next = current.next;
					}
					pane.remove(current);
					pane.repaint();
					attributePane.setPosxText(null);
					attributePane.setPosyText(null);
					attributePane.setWidthText(null);
					attributePane.setHeightText(null);
					attributePane.ctype.setSelectedIndex(0);
					attributePane.setVarText(null);
					attributePane.setValueText(null);
				}
				break;
			}
		}
	}

	public void setAttributePaneNull() {
		attributePane.setPosxText(null);
		attributePane.setPosyText(null);
		attributePane.setWidthText(null);
		attributePane.setHeightText(null);
		attributePane.ctype.setSelectedIndex(0);
		attributePane.setVarText(null);
		attributePane.setValueText(null);
	}
}