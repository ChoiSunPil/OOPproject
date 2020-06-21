package controller;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.MyComponent;

class CreateJSONObject {
	private JSONObject jsonObject, componentInfo;
	private JSONArray componentArray;

	CreateJSONObject(EditorEventHandler edihandler) {
		MyComponent current;
		jsonObject = new JSONObject();
		componentArray = new JSONArray();
		current = edihandler.first.next;
		while (current != null) {
			int num = 0;
			componentInfo = new JSONObject();
			componentInfo.put("posx", current.getX());
			componentInfo.put("posy", current.getY());
			componentInfo.put("width", current.getWidth());
			componentInfo.put("height", current.getHeight());
			componentInfo.put("value", current.getContents());
			componentInfo.put("type", current.getType());
			componentInfo.put("var", current.getVar());
			componentArray.add(componentInfo);
			current = current.next;
			num++;
		}
		jsonObject.put("components", componentArray);
		String jsonInfo = jsonObject.toJSONString();
	}

	public String getJSONInfo() {
		return jsonObject.toJSONString();
	}
}

class ParseJSONFile {
	private JSONParser jsonParser;
	private JSONObject jsonObject, componentObject;
	private JSONArray componentInfoArray;
	private MyComponent first, current;

	ParseJSONFile(EditorEventHandler edihandler, String jsonInfo) {
		this.first = edihandler.first;
		try {
			jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			componentInfoArray = (JSONArray) jsonObject.get("components");
			for (int i = 0; i < componentInfoArray.size(); i++) {
				componentObject = (JSONObject) componentInfoArray.get(i);
				MyComponent node = new MyComponent(edihandler.first, edihandler.attributePane);
				node.setLocation((int) ((long) componentObject.get("posx")),
						(int) ((long) componentObject.get("posy")));
				node.setSize((int) ((long) componentObject.get("width")), (int) ((long) componentObject.get("height")));
				node.setContents((String) componentObject.get("value"));
				node.setType((String) componentObject.get("type"));
				node.setVar((String) componentObject.get("var"));
				node.setText("(" + node.getType() + ") " + node.getVar());
				MyComponent.num++;
				ComponentMouseHandler cmh = new ComponentMouseHandler(edihandler.attributePane, edihandler.first);
				node.addMouseListener(cmh);
				node.addMouseMotionListener(cmh);
				edihandler.first.getParent().add(node);
				if (first.next == null) {
					first.next = node;
				} else {
					current = first.next;
					while (current.next != null) {
						current = current.next;
					}
					current.next = node;
				}
				edihandler.first.getParent().repaint();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}
}
