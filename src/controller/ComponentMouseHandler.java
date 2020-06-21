package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import model.AttributePane;
import model.MyComponent;

public class ComponentMouseHandler implements MouseListener, MouseMotionListener {
	private int clickedX, clickedY;
	private boolean move;
	private MyComponent current, first;
	private AttributePane attributePane;

	public ComponentMouseHandler(AttributePane attributePane, MyComponent component) {
		first = component;
		current = null;
		move = false;
		this.attributePane = attributePane;
	}

	public void mouseDragged(MouseEvent e) {
		int x, y, width, height;
		if (current.select) {
			MyComponent label = (MyComponent) e.getSource();
			while (true) {
				if (label.getVar().equals(current.getVar())) {
					if (move) {
						x = e.getLocationOnScreen().x - label.getParent().getLocationOnScreen().x - clickedX;
						y = e.getLocationOnScreen().y - label.getParent().getLocationOnScreen().y - clickedY;
						label.setLocation(x, y);
						label.getParent().repaint();
						attributePane.setPosxText(Integer.toString(x));
						attributePane.setPosyText(Integer.toString(y));
						break;
					} else {
						width = e.getX();
						height = e.getY();
						if (width < 10)
							width = 10;
						if (height < 10)
							height = 10;
						label.setSize(width, height);
						label.getParent().repaint();
						attributePane.setWidthText(Integer.toString(width));
						attributePane.setHeightText(Integer.toString(height));
						break;
					}
				} else
					current = current.next;
			}
		}

	}

	public void mouseMoved(MouseEvent e) {
		current = first.next;
		MyComponent label = (MyComponent) e.getSource();

		while (!(label.getVar().equals(current.getVar())))
			current = current.next;
		if (current.select) {
			if (label.getWidth() - 5 <= e.getX() && label.getHeight() - 5 <= e.getY()) {
				{
					Border border = BorderFactory.createLineBorder(Color.red);
					label.setBorder(border);
				}
			} else {
				Border border = BorderFactory.createLineBorder(Color.black);
				label.setBorder(border);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		current = first.next;
		while (current != null) {
			current.select = false;
			current.setBackground(null);
			current = current.next;
		}
		current = first.next;
		MyComponent label = (MyComponent) e.getSource();
		label.setBackground(Color.lightGray);
		while (true) {
			if (label.getVar().equals(current.getVar())) {
				current.select = true;
				break;
			} else {
				current = current.next;
			}
		}
		int n = 0;
		switch (current.getType()) {
		case "JLabel":
			n = 0;
			break;
		case "JTextArea":
			n = 1;
			break;
		case "JButton":
			n = 2;
			break;
		case "JTextField":
			n = 3;
			break;
		case "JRadioButton":
			n = 4;
			break;
		}

		attributePane.setPosxText(Integer.toString(label.getX()));
		attributePane.setPosyText(Integer.toString(label.getY()));
		attributePane.setWidthText(Integer.toString(label.getWidth()));
		attributePane.setHeightText(Integer.toString(label.getHeight()));
		attributePane.ctype.setSelectedIndex(n);
		attributePane.setVarText(label.getVar());
		attributePane.setValueText(label.getContents());
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		MyComponent label = (MyComponent) e.getSource();
		Border border = BorderFactory.createLineBorder(Color.black);
		label.setBorder(border);
	}

	public void mousePressed(MouseEvent e) {
		current = first.next;
		MyComponent label = (MyComponent) e.getSource();
		while (!(label.getVar().equals(current.getVar())))
			current = current.next;
		if (current.select) {
			clickedX = e.getX();
			clickedY = e.getY();
		}
		if (clickedX >= label.getWidth() - 5 && clickedY >= label.getHeight() - 5)
			move = false;
		else
			move = true;
	}

	public void mouseReleased(MouseEvent e) {
		current = first.next;
		MyComponent label = (MyComponent) e.getSource();
		while (!(label.getVar().equals(current.getVar())))
			current = current.next;
		current.setLocation(label.getX(), label.getY());
		current.setSize(label.getWidth(), label.getHeight());
	}
}
