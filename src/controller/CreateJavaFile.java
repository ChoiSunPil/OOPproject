package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.MyComponent;

class CreateJavaFile {
	private MyComponent first, current;
	private String source;

	CreateJavaFile(MyComponent first) {
		this.first = first;
		current = null;
		source = null;
	}

	public void CreateJavaSource(String fileName){
		String name;
		int idx = fileName.lastIndexOf(".");
		name = fileName.substring(0,idx);
		String startSource = "import javax.swing.*;\r\n"
				+ "import javax.swing.event.*;\r\n"
				+ "import javax.swing.border.*\r\n;"
				+ "import java.awt.*;\r\n"
				+ "import java.awt.event.*;\r\n"
				+ "public class "+name+" extends JFrame {\r\n"
				+ "	public static void main(String[] args) {\r\n"
				+ "		try {\r\n"
				+ "			UIManager.setLookAndFeel(\"com.sun.java.swing.plaf.windows.WindowsLookAndFeel\");\r\n"
						+ "		} \r\n"
						+ "catch (Exception e) {}\r\n"
						+ name +" mf = new "+name+"();\r\n"
						+ "}\r\n"
		+ name+"(){\r\n"
				+ "setTitle(\"component\");\r\n"
				+ "setSize(727,606);\r\n"
				+ "setLayout(new BorderLayout());\r\n"
				+ "setDefaultCloseOperation(EXIT_ON_CLOSE);\r\n"
				+ "JPanel pane = new JPanel();\r\n"
				+ "pane.setLayout(null);\r\n";
		String componentSource = "";
		current = first.next;
		while(current != null)
		{
			componentSource += current.getType()+" "+current.getVar()+" = new "+current.getType()+"();\r\n"
			+current.getVar()+".setLocation("+Integer.toString(current.getX())+", "+Integer.toString(current.getY())+");\r\n"
			+current.getVar()+".setSize("+Integer.toString(current.getWidth())+","+Integer.toString(current.getHeight())+");\r\n"
			+current.getVar()+".setText(\""+current.getContents()+"\");\r\n"
			+"pane.add("+current.getVar()+");\r\n";
			
			
			current = current.next;
		}
		
		String finalSource = "this.add(pane, BorderLayout.CENTER);\r\n"
		+"Border border = BorderFactory.createLineBorder(Color.black);\r\n"
		+"pane.setBorder(border);\r\n"
		+"setVisible(true);\r\n"
		+"}\r\n"
		+"}\r\n";
		
		source = startSource+componentSource+finalSource;
	}

	public void SaveJavaFile() {
		JFileChooser fs = new JFileChooser();
		
		fs.setDialogTitle("Java 파일 저장");
		fs.setFileFilter(new FileTypeFilter(".java", "Java File"));
		int result = fs.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File fi = fs.getSelectedFile();
			FileWriter fw;
			try {
				fw = new FileWriter(fi.getPath());
				CreateJavaSource(fi.getName());
				fw.write(source);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
}
