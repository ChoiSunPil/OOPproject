package model;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import controller.AttributeEventHandler;
import controller.EditorEventHandler;

public class MyFrame extends JFrame {

	public MyFrame() {
		setTitle("My Simple GUI Builder");
		setSize(1000, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel leftPane = new JPanel();
		AttributePane attributePane = new AttributePane();
		leftPane.add(attributePane);
		MyComponent first = new MyComponent();
		MyComponent component = new MyComponent(first, attributePane);
		EditorPane editorPane = new EditorPane();
		editorPane.add(component);
		EditorEventHandler edihandler = new EditorEventHandler(attributePane, component);
		AttributeEventHandler atrhandler = new AttributeEventHandler(attributePane, component);
		attributePane.change.addActionListener(atrhandler);
		editorPane.addMouseListener(edihandler);
		editorPane.addMouseMotionListener(edihandler);
		MyMenuBar mymenu = new MyMenuBar(edihandler);
		setJMenuBar(mymenu);
		MyToolBar mytool = new MyToolBar(edihandler);
		add(mytool, BorderLayout.NORTH);

		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, editorPane);
		add(sp, BorderLayout.CENTER);
		sp.setDividerLocation((int) getSize().getWidth() / 4);
		sp.setDividerSize(15);

		Border border = BorderFactory.createLineBorder(Color.black);
		Border bdAttribute = BorderFactory.createTitledBorder(border, "Attribute Pane");
		Border bdEditor = BorderFactory.createTitledBorder(border, "Editor Pane");
		leftPane.setBorder(bdAttribute);
		editorPane.setBorder(bdEditor);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		MyFrame mf = new MyFrame();
	}
}
