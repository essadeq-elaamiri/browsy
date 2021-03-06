package browsy.presentation.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

   @FXML
    private TabPane tabPaneId;


    public void initialiser() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
        Tab tab=new Tab();
        FontAwesomeIcon closeIcon = new FontAwesomeIcon();
        closeIcon.setGlyphName("TIMES");
        Button closebutton = new Button();
        closebutton.getStyleClass().add("button");
        closebutton.setText("");
        closebutton.setGraphic(closeIcon);
        closebutton.setOnAction(actionEvent -> {
                tabPaneId.getTabs().remove(tab);
        });
        tab.getStyleClass().addAll("primary-color", "tab");
        tab.setText("Home");
        tab.setGraphic(closebutton);
        tab.setText("Home");
        tab.setContent(root);
        tabPaneId.getTabs().add(tabPaneId.getTabs().size()-1,tab);
        tabPaneId.getSelectionModel().select(tabPaneId.getTabs().size()-2);
        WebViewController webViewController=fxmlLoader.getController();
        webViewController.setWebViewInitializer(tabPaneId.getTabs().get(tabPaneId.getTabs().size()-2),tabPaneId);
    }

    public void onAjoutTab(ActionEvent actionEvent) {
        try {
            initialiser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
