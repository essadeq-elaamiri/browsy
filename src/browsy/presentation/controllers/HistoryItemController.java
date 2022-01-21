package browsy.presentation.controllers;

import browsy.entities.History;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class HistoryItemController {


    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab,TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }

    @FXML
    private Button deleteHistory;

    @FXML
    private Label pageDateTime;

    @FXML
    private Label pageTitle;

    @FXML
    private Button visitPageBtn;

    private History history;
    @FXML
    void onDeleteHistoryBtnClicked(ActionEvent event)  {

    }

    @FXML
    void onVisitPageBtnClicked(ActionEvent event) throws IOException {
        webViewInitializer.initializePages(history.getPage().getLink());
    }

    public void setItemData(History history){
        this.history = history;
        this.pageTitle.setText(history.getPage().getName());
        this.pageDateTime.setText(history.getCreatedAt().toString());

    }
}
