package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.EditorPane;
import model.MyComponent;

public class MenuToolEventHandler implements ActionListener {
	private EditorEventHandler edihandler;

	public MenuToolEventHandler(EditorEventHandler edihandler) {
		this.edihandler = edihandler;
	}

	public void actionPerformed(ActionEvent e) {
		AbstractButton abs = (AbstractButton) e.getSource();
		switch (abs.getName()) {
		case "newFile":
			newFile(1);
			break;
		case "openFile":
			newFile(2);
			openFile();
			break;
		case "saveFile":
			saveFile();
			break;
		case "saveAs":
			saveAs();
			break;
		case "javaFile":
			javaFile();
			break;
		case "close":
			close();
			break;
		}
	}

	private void newFile(int i) {
		if (edihandler.first.next == null && edihandler.first.getFileName() == null)
			MyComponent.num = 0;
		else {
			JDialog md = new JDialog();
			JLabel label = new JLabel("편집중인 내용이 있습니다. 저장하시겠습니까?");
			JPanel pane = new JPanel();
			JButton save = new JButton("저장");
			JButton nosave = new JButton("저장 안함");
			JButton cancel = new JButton("취소");
			pane.add(save);
			pane.add(nosave);
			if (i == 1)
				pane.add(cancel);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveFile();
					EditorPane pane = (EditorPane) edihandler.first.getParent();
					pane.removeAll();
					pane.add(edihandler.first);
					pane.repaint();
					edihandler.first.next = null;
					edihandler.first.setFileName(null);
					edihandler.setAttributePaneNull();
					md.dispose();
				}
			});
			nosave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
					MyComponent.num = 0;
					EditorPane pane = (EditorPane) edihandler.first.getParent();
					pane.removeAll();
					pane.add(edihandler.first);
					pane.repaint();
					edihandler.first.next = null;
					edihandler.first.setFileName(null);
					edihandler.setAttributePaneNull();
					md.dispose();

				}
			});
			if (i == 1)
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						md.dispose();
					}
				});

			md.setLayout(new GridLayout(2, 1));
			label.setHorizontalAlignment(SwingConstants.CENTER);

			md.add(label);
			md.add(pane);
			md.setLocation(325, 275);
			md.setSize(350, 150);
			md.setModal(true);
			md.setResizable(false);
			md.setVisible(true);
		}
	}

	private void openFile() {
		JFileChooser fs = new JFileChooser();
		fs.setDialogTitle("열기");
		fs.setFileFilter(new FileTypeFilter(".json", "JSON File"));
		int result = fs.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File fi = fs.getSelectedFile();
				BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
				String s = "";
				String line = "";
				while ((line = br.readLine()) != null) {
					s += line;
				}
				ParseJSONFile pjf = new ParseJSONFile(edihandler, s);
				edihandler.first.setFileName(fi.getPath());
				if (br != null)
					br.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	private void saveFile() {
		if (edihandler.first.getFileName() != null) {
			System.out.println(edihandler.first.getFileName());
			CreateJSONObject jsonFile = new CreateJSONObject(edihandler);
			String content = jsonFile.getJSONInfo();
			FileWriter fw;
			try {
				fw = new FileWriter(edihandler.first.getFileName());
				fw.write(content);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			saveAs();
		}
	}

	private void saveAs() {
		CreateJSONObject jsonFile = new CreateJSONObject(edihandler);
		JFileChooser fs = new JFileChooser();
		fs.setDialogTitle("다른 이름으로 저장");
		fs.setFileFilter(new FileTypeFilter(".json", "JSON File"));
		int result = fs.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String content = jsonFile.getJSONInfo();
			File fi = fs.getSelectedFile();
			FileWriter fw;
			try {
				fw = new FileWriter(fi.getPath());
				fw.write(content);
				fw.flush();
				fw.close();
				edihandler.first.setFileName(fi.getPath());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}

	private void javaFile() {
		CreateJavaFile cjf = new CreateJavaFile(edihandler.first);
		cjf.SaveJavaFile();
	}

	private void close() {
		if (edihandler.first.next == null && edihandler.first.getFileName() == null)
			System.exit(0);
		else {
			JDialog md = new JDialog();
			JLabel label = new JLabel("편집중인 내용이 있습니다. 저장하시겠습니까?");
			JPanel pane = new JPanel();
			JButton save = new JButton("저장");
			JButton nosave = new JButton("저장 안함");
			JButton cancel = new JButton("취소");
			pane.add(save);
			pane.add(nosave);
			pane.add(cancel);
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveFile();
					System.exit(0);
				}
			});
			nosave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					md.dispose();
				}
			});

			md.setLayout(new GridLayout(2, 1));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			;
			md.add(label);
			md.add(pane);
			md.setLocation(325, 275);
			md.setSize(350, 150);
			md.setModal(true);
			md.setResizable(false);
			md.setVisible(true);
		}
	}
}
