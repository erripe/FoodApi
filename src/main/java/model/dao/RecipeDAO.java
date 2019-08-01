package model.dao;

import java.util.List;

import model.Recipe;

public class RecipeDAO extends ModelDAO {

	public RecipeDAO() {
	}

	@SuppressWarnings("unchecked")
	public static List<Recipe> findList(String[] args) throws Exception {
		String sql = "";

		if (!(args.length == 0)) {
			for (int i = 0; i < args.length; i++) {
				sql += " AND title LIKE '%" + args[i] + "%'";
			}
		}

		RecipeDAO dao = new RecipeDAO();
		return ModelDAO.cast(dao.executeFindList(sql, new Recipe()));
	}

	public static Recipe findById(Long codComanda) throws Exception {
		RecipeDAO dao = new RecipeDAO();
		return (Recipe) dao.executeFindById(Recipe.class, codComanda);
	}

	@SuppressWarnings("unchecked")
	public static List<Recipe> findListTitle(String title) throws Exception {
		RecipeDAO dao = new RecipeDAO();
		final String cmdSqlTitle;

		cmdSqlTitle = " AND title LIKE ('%" + title + "%')";
		return ModelDAO.cast(dao.executeFindList(cmdSqlTitle, new Recipe()));
	}

	public static boolean insert(Recipe obj) throws Exception {
		RecipeDAO dao = new RecipeDAO();
		return dao.executeInsert(obj);
	}

	public static boolean update(Recipe obj) throws Exception {
		RecipeDAO dao = new RecipeDAO();
		return dao.executeUpdate(obj);
	}

	public static boolean delete(Recipe obj) throws Exception {
		RecipeDAO dao = new RecipeDAO();
		return dao.executeDelete(obj);
	}

}
