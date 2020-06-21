package model;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.EditorEventHandler;
import controller.MenuToolEventHandler;

public class MyMenuBar extends JMenuBar {
	private JMenu file, save;
	private JMenuItem newFile, openFile, saveFile, saveAs, javaFile, close;

	public MyMenuBar(EditorEventHandler edihandler) {
		super();
		file = new JMenu("����");
		add(file);

		newFile = new JMenuItem("���θ����");
		newFile.setName("newFile");
		file.add(newFile);
		openFile = new JMenuItem("����");
		openFile.setName("openFile");
		file.add(openFile);

		save = new JMenu("����");
		file.add(save);
		saveFile = new JMenuItem("����");
		saveFile.setName("saveFile");
		save.add(saveFile);
		saveAs = new JMenuItem("�ٸ� �̸����� ����");
		saveAs.setName("saveAs");
		save.add(saveAs);
		javaFile = new JMenuItem(".java ���� ����");
		javaFile.setName("javaFile");
		save.add(javaFile);
		file.addSeparator();
		close = new JMenuItem("�ݱ�");
		close.setName("close");
		file.add(close);

		MenuToolEventHandler menutoolhandler = new MenuToolEventHandler(edihandler);
		newFile.addActionListener(menutoolhandler);
		openFile.addActionListener(menutoolhandler);
		saveFile.addActionListener(menutoolhandler);
		saveAs.addActionListener(menutoolhandler);
		javaFile.addActionListener(menutoolhandler);
		close.addActionListener(menutoolhandler);
	}

}
