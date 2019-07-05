package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Recipe;
import service.MainService;

public class ListController {

	MainService service;

	@FXML
	private ListView<Recipe> resultView;

	@FXML
	private Text textCount;

	@FXML
	private TextField tfBanco;

	@FXML
	private Button btnFindBd;

	@FXML
	ContextMenu contextMenu;

	@FXML
	private MenuItem btnExcluir;

	@FXML
	private MenuItem btnEditar;

	@FXML
	void initialize() {
		assert btnFindBd != null : "fx:id=\"btnFindBd\" was not injected: check your FXML file 'List.fxml'.";
		assert contextMenu != null : "fx:id=\"contextMenu\" was not injected: check your FXML file 'List.fxml'.";
		assert resultView != null : "fx:id=\"resultView\" was not injected: check your FXML file 'List.fxml'.";
		assert textCount != null : "fx:id=\"textCount\" was not injected: check your FXML file 'List.fxml'.";
		assert tfBanco != null : "fx:id=\"tfBanco\" was not injected: check your FXML file 'List.fxml'.";
		assert btnExcluir != null : "fx:id=\"btnExcluir\" was not injected: check your FXML file 'List.fxml'.";
		assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'List.fxml'.";

		service = new MainService();
	}

	public void findBd(ActionEvent event, TextField textField) throws Exception {
		String crua = textField.getText();
		String[] list = crua.split(" ");
		tfBanco.setText(textField.getText());
		resultView.getItems().clear();
		resultView.getItems().addAll(service.findList(list));
		textCount.setText("Aproximadamente " + resultView.getItems().size() + " resultados.");
	}

	@FXML
	public void btnFindBd(ActionEvent event) throws Exception {
		findBd(event, tfBanco);
	}

	@FXML
	void btnEditar(ActionEvent event) throws Exception {
		if (resultView.getItems().size() != 0) {
			if (resultView.getSelectionModel().isEmpty()) {
				msgErro("Escolha uma linha para ser excluída.");
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Edit.fxml"));
				AnchorPane root = loader.load();
				EditController c2 = loader.getController();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				c2.open(resultView.getSelectionModel().getSelectedItem(), stage, this);
				stage.show();
			}
		} else {
			msgErro("Realize uma busca!");
		}
	}

	@FXML
	void btnExcluir(ActionEvent event) throws Exception {
		if (resultView.getItems().size() != 0) {
			if (resultView.getSelectionModel().isEmpty()) {
				msgErro("Escolha uma linha para ser excluída.");
			} else {
				if (msgConfirmation())
					if (service.deleteLine(resultView.getSelectionModel().getSelectedItem()))
						msgSucess();
				btnFindBd(event);
			}
		} else {
			msgErro("Realize uma busca!");
		}
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
