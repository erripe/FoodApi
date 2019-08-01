package service;

import java.util.List;

import model.Recipe;
import util.WebApiThread;

public class MainService {

	public void updateBd(String[] textField) throws Exception {
		WebApiThread t = new WebApiThread(textField);
		t.start();
	}

	public List<Recipe> findList(String[] textField) throws Exception {
		return Recipe.findList(textField);
	}

	public boolean deleteLine(Recipe r) throws Exception {
		return r.delete();
	}

	public boolean updateLine(Recipe r) throws Exception {
		return r.update();
	}
}
