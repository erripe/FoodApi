package service;

import java.util.List;

import model.Recipe;
import util.Util;

public class MainService {

	public void updateBd(String[] textField) throws Exception {
		Util.updateList(textField);
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
