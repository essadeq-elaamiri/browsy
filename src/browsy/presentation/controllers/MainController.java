package browsy.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Parent;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@FXML
	private TabPane tabPaneId;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {


		//engine = webView.getEngine();
		//homePage = "www.google.com";
		// textField.setText(homePage);
		//webZoom = 1;
		//loadPage();

	}
	public void initialiser() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
		///Browsy_0/src/browsy/presentation/views/webView.fxml

		AnchorPane root = fxmlLoader.load();

		//Parent root = FXMLLoader.load(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
		/*
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
		Parent root = fxmlLoader.load();
		 */
		Tab tab=new Tab();
		
		FontAwesomeIcon closeIcon = new FontAwesomeIcon();
        	closeIcon.setGlyphName("TIMES");
        	Button closebutton = new Button();
        	closebutton.getStyleClass().add("button");
        	closebutton.setText("");
        	closebutton.setGraphic(closeIcon);
        	closebutton.setOnAction(actionEvent -> {
                //TODO close Tab
       	 	});
        	tab.getStyleClass().addAll("primary-color", "tab");
		tab.setGraphic(closebutton);
		tab.setText("Home");
		tab.setContent(root);
		tabPaneId.getTabs().add(tabPaneId.getTabs().size()-1,tab);
		tabPaneId.getSelectionModel().select(tabPaneId.getTabs().size()-2);
		((WebViewController)fxmlLoader.getController()).setTab(tabPaneId.getTabs().get(tabPaneId.getTabs().size()-2));
		((WebViewController)fxmlLoader.getController()).setTabPane(tabPaneId);
		root.setUserData(tabPaneId.getTabs().get(tabPaneId.getTabs().size()-2));
	}

	public void onAjoutTab(ActionEvent actionEvent) {
		try {
			initialiser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
