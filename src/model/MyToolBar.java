package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.EditorEventHandler;
import controller.MenuToolEventHandler;

public class MyToolBar extends JToolBar {
	private JButton newFile, openFile, saveFile, saveAs, javaFile, close, plus, delete;

	public MyToolBar(EditorEventHandler edihandler) {
		super();
		newFile = new JButton(new ImageIcon("newFile.png"));
		newFile.setName("newFile");
		add(newFile);
		openFile = new JButton(new ImageIcon("openFile.png"));
		openFile.setName("openFile");
		add(openFile);
		saveFile = new JButton(new ImageIcon("saveFile.png"));
		saveFile.setName("saveFile");
		add(saveFile);
		saveAs = new JButton(new ImageIcon("saveas.png"));
		saveAs.setName("saveAs");
		add(saveAs);
		javaFile = new JButton(new ImageIcon("javaFile.png"));
		javaFile.setName("javaFile");
		add(javaFile);
		close = new JButton(new ImageIcon("close.png"));
		close.setName("close");
		add(close);

		addSeparator();
		plus = new JButton(new ImageIcon("plus.png"));
		plus.setName("plus");
		add(plus);

		delete = new JButton(new ImageIcon("delete.png"));
		delete.setName("delete");
		add(delete);
		plus.addActionListener(edihandler);
		delete.addActionListener(edihandler);

		MenuToolEventHandler menutoolhandler = new MenuToolEventHandler(edihandler);
		newFile.addActionListener(menutoolhandler);
		openFile.addActionListener(menutoolhandler);
		saveFile.addActionListener(menutoolhandler);
		saveAs.addActionListener(menutoolhandler);
		javaFile.addActionListener(menutoolhandler);
		close.addActionListener(menutoolhandler);
		setFloatable(false);

	}

}