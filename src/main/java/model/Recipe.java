package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

import javafx.scene.control.Hyperlink;
import model.dao.RecipeDAO;
import util.Util;

@Entity
@Table(appliesTo = "Recipe")
public class Recipe extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long codRecipe;

	@Column
	private String title;

	@Column
	private String publisher;

	@Column
	private String url;

	@Column
	private String imageUrl;

	public Recipe() {
		super();
	}

	// ------------------------------------------------

	public static List<Recipe> findList(String[] args) throws Exception {
		return RecipeDAO.findList(args);
	}

	public static void updateList(String[] textField) throws Exception {
		Util.updateList(textField);
	}

	public static Recipe findById(Long codRecipe) throws Exception {
		return RecipeDAO.findById(codRecipe);
	}

	public static List<Recipe> findListTitle(String title) throws Exception {
		return RecipeDAO.findListTitle(title);
	}

	public boolean insert() throws Exception {
		return RecipeDAO.insert(this);
	}

	public boolean update() throws Exception {
		return RecipeDAO.update(this);
	}

	public boolean delete() throws Exception {
		return RecipeDAO.delete(this);
	}

	// ------------------------------------------------

	public Long getCodRecipe() {
		return codRecipe;
	}

	public void setCodRecipe(Long codRecipe) {
		this.codRecipe = codRecipe;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		Hyperlink link = new Hyperlink();
		link.setText(url);
		String out = String.format("Title: %s \nPublisher: %s \n%s", title, publisher, url);

		return out;
	}

}
