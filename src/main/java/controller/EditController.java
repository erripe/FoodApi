package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Recipe;
import service.MainService;

public class EditController {

	Stage stage;

	MainService service = new MainService();

	ListController parentController;

	@FXML
	private TextField urlTf;

	@FXML
	private Button backBtn;

	@FXML
	private TextField publisherTf;

	@FXML
	private ImageView imageView;

	@FXML
	private TextField titleTf;

	@FXML
	private Button saveBtn;

	private Long codRecipe;

	private String imgUrl;

	@FXML
	void initialize() {
		assert urlTf != null : "fx:id=\"urlTf\" was not injected: check your FXML file 'Edit.fxml'.";
		assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Edit.fxml'.";
		assert publisherTf != null : "fx:id=\"publisherTf\" was not injected: check your FXML file 'Edit.fxml'.";
		assert imageView != null : "fx:id=\"urlImg\" was not injected: check your FXML file 'Edit.fxml'.";
		assert titleTf != null : "fx:id=\"titleTf\" was not injected: check your FXML file 'Edit.fxml'.";
		assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'Edit.fxml'.";

	}

	void open(Recipe r, Stage stage, ListController parentController) throws Exception {
		this.stage = stage;
		this.parentController = parentController;
		codRecipe = r.getCodRecipe();
		titleTf.setText(r.getTitle());
		publisherTf.setText(r.getPublisher());
		urlTf.setText(r.getUrl());
		imgUrl = r.getImageUrl();
		Image image = new Image(r.getImageUrl());
		imageView.setImage(image);
		stage.getIcons().add(image);
	}

	@FXML
	void saveBtn(ActionEvent event) throws Exception {
		Recipe r = new Recipe();
		r.setCodRecipe(codRecipe);
		r.setTitle(titleTf.getText());
		r.setPublisher(publisherTf.getText());
		r.setUrl(urlTf.getText());
		r.setImageUrl(imgUrl);
		if (msgConfirmation())
			if (service.updateLine(r)) {
				msgSucess();
				parentController.btnFindBd(event);
				stage.close();
			}
	}

	@FXML
	void backBtn(ActionEvent event) throws Exception {
		parentController.btnFindBd(event);
		stage.close();
	}

	void msgSucess() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("Operação executada com sucesso!");
		alert.showAndWait();
	}

	void msgErro(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	boolean msgConfirmation() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setContentText("Deseja realizar esta operação?");

		return alert.showAndWait().get() == ButtonType.OK;
	}
}
