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
		file = new JMenu("파일");
		add(file);

		newFile = new JMenuItem("새로만들기");
		newFile.setName("newFile");
		file.add(newFile);
		openFile = new JMenuItem("열기");
		openFile.setName("openFile");
		file.add(openFile);

		save = new JMenu("저장");
		file.add(save);
		saveFile = new JMenuItem("저장");
		saveFile.setName("saveFile");
		save.add(saveFile);
		saveAs = new JMenuItem("다른 이름으로 저장");
		saveAs.setName("saveAs");
		save.add(saveAs);
		javaFile = new JMenuItem(".java 파일 생성");
		javaFile.setName("javaFile");
		save.add(javaFile);
		file.addSeparator();
		close = new JMenuItem("닫기");
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
