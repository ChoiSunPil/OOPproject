package model;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import controller.ComponentMouseHandler;

public class MyComponent extends JLabel {
	public MyComponent next;
	MyComponent first;
	private String var, type, contents;
	public boolean select;
	public static int num = 0;
	private String fileName;

	public MyComponent() {
		super();
		fileName = null;
		next = null;
		var = null;
		type = "JLabel";
		select = false;
		setOpaque(true);
		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
	}

	public MyComponent(MyComponent first, AttributePane attributePane) {
		super();
		fileName = null;
		next = null;
		var = null;
		type = "JLabel";
		select = false;
		this.first = first;
		setOpaque(true);
		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
		ComponentMouseHandler cmh = new ComponentMouseHandler(attributePane, this.first);
		this.addMouseListener(cmh);
		this.addMouseMotionListener(cmh);
	}

	public void setVar(String str) {
		var = str;
	}

	public void setType(String str) {
		type = str;
	}

	public void setContents(String str) {
		contents = str;
	}

	public void setFileName(String str) {
		fileName = str;
	}

	public String getVar() {
		return var;
	}

	public String getType() {
		return type;
	}

	public String getContents() {
		return contents;
	}

	public String getFileName() {
		return fileName;
	}
}
