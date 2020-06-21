package model;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class AttributePane extends JPanel {
	private JLabel posx, posy, width, height, value, type, var;
	private JTextField tposx, tposy, twidth, theight, tvalue, tvar;
	public JButton change;
	public JComboBox ctype;

	public AttributePane() {
		super();
		GridLayout grid = new GridLayout(8, 2, 5, 5);
		setLayout(grid);
		posx = new JLabel("x 좌표");
		posx.setHorizontalAlignment(SwingConstants.CENTER);
		tposx = new JTextField();
		add(posx);
		add(tposx);

		posy = new JLabel("y 좌표");
		posy.setHorizontalAlignment(SwingConstants.CENTER);
		tposy = new JTextField();
		add(posy);
		add(tposy);

		width = new JLabel("너비");
		width.setHorizontalAlignment(SwingConstants.CENTER);
		twidth = new JTextField();
		add(width);
		add(twidth);

		height = new JLabel("높이");
		height.setHorizontalAlignment(SwingConstants.CENTER);
		theight = new JTextField();
		add(height);
		add(theight);

		value = new JLabel("속성값");
		value.setHorizontalAlignment(SwingConstants.CENTER);
		tvalue = new JTextField();
		add(value);
		add(tvalue);

		type = new JLabel("타입");
		type.setHorizontalAlignment(SwingConstants.CENTER);
		ctype = new JComboBox();
		ctype.addItem("JLabel");
		ctype.addItem("JTextArea");
		ctype.addItem("JButton");
		ctype.addItem("JTextField");
		ctype.addItem("JRadioButton");
		ctype.setEditable(false);
		add(type);
		add(ctype);

		var = new JLabel("변수명");
		var.setHorizontalAlignment(SwingConstants.CENTER);
		tvar = new JTextField();
		add(var);
		add(tvar);

		change = new JButton("변경");
		add(new JLabel());
		add(change);

		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
	}

	public void setPosxText(String str) {
		tposx.setText(str);
	}

	public void setPosyText(String str) {
		tposy.setText(str);
	}

	public void setWidthText(String str) {
		twidth.setText(str);
	}

	public void setHeightText(String str) {
		theight.setText(str);
	}

	public void setVarText(String str) {
		tvar.setText(str);
	}

	public void setValueText(String str) {
		tvalue.setText(str);
	}

	public String getPosxText() {
		return tposx.getText();
	}

	public String getPosyText() {
		return tposy.getText();
	}

	public String getWidthText() {
		return twidth.getText();
	}

	public String getHeightText() {
		return theight.getText();
	}

	public String getVarText() {
		return tvar.getText();
	}

	public String getValueText() {
		return tvalue.getText();
	}
}
