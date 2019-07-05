package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.MainService;

public class MainController {

	MainService service;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button luckyListBtn;

	@FXML
	private Button findListBtn;

	@FXML
	private TextField listTf;

	@FXML
	void initialize() throws IOException {
		assert luckyListBtn != null : "fx:id=\"luckyList\" was not injected: check your FXML file 'Main.fxml'.";
		assert findListBtn != null : "fx:id=\"findList\" was not injected: check your FXML file 'Main.fxml'.";
		assert listTf != null : "fx:id=\"textField\" was not injected: check your FXML file 'Main.fxml'.";

		service = new MainService();
	}

	@FXML
	public void findListBtn(ActionEvent event) throws Exception {
		// service.updateBd(list);
		System.out.println("API ACESSADA!!!!");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/List.fxml"));
		VBox root = loader.load();
		ListController c2 = loader.getController();
		c2.findBd(event, listTf);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	@FXML
	public void luckyListBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/List.fxml"));
		VBox root = loader.load();
		ListController c2 = loader.getController();
		c2.findBd(event, listTf);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
