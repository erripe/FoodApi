package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Recipe;

public class WebApiThread extends Thread {
	private String[] textField;

	public WebApiThread(String[] textField) {
		this.textField = textField;
	}

	public void run() {
		try {
			final String KEY = "5e639b55f80d8d6e0dbae529ebd8c6ae";

			String ws;

			if (textField.length == 0) {
				ws = "https://www.food2fork.com/api/search?key=" + KEY;
			} else {
				ws = "https://www.food2fork.com/api/search?key=" + KEY + "&q=";
				for (int i = 0; i < textField.length; i++) {
					if (i == 0) {
						ws += textField[i];
					} else {
						ws += "%20" + textField[i];
					}
				}
			}

			URLConnection url = new URL(ws).openConnection();
			url.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.10; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

			InputStreamReader in = new InputStreamReader(url.getInputStream());
			BufferedReader br = new BufferedReader(in);

			String data = "";
			while (br.ready()) {
				data += br.readLine();

			}

			JSONObject js = new JSONObject(data);
			JSONArray jsonList = new JSONArray(js.get("recipes").toString());

			List<Recipe> list;

			Recipe r;
			for (int i = 0; i < jsonList.length(); i++) {
				r = new Recipe();
				JSONObject obj = new JSONObject(jsonList.get(i).toString());
				r.setPublisher(obj.getString("publisher"));
				String a = obj.getString("title");
				if (a != null && a.length() > 0) {
					a = a.replace("\\", "");
					a = a.replace("'", "");
					a = a.replace("\0", "");
					a = a.replace("\n", "");
					a = a.replace("\r", "");
					a = a.replace("\"", "");
					a = a.replace("\\x1a", "");
				}
				r.setTitle(a);
				r.setUrl(obj.getString("source_url"));
				r.setImageUrl(obj.getString("image_url"));
				list = Recipe.findListTitle(r.getTitle());
				if (!(list.size() > 0))
					r.insert();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
