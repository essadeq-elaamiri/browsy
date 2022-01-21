package browsy.presentation.controllers;

import browsy.dataAccess.HistoryDA;
import browsy.entities.History;
import browsy.presentation.utils.AlertMaker;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HistoryItemController {


    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab,TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }

    private int row;
    private GridPane gridPane;
    public void setGrid(int row, GridPane gridPane){
        this.row=row;
        this.gridPane=gridPane;
    }
    @FXML
    private Button deleteHistory;


    @FXML
    private GridPane gridPaneId;

    @FXML
    private Label pageDateTime;

    @FXML
    private Label pageTitle;

    @FXML
    private Button visitPageBtn;

    private History history;
    @FXML
    void onDeleteHistoryBtnClicked(ActionEvent event)  {
        AlertMaker alert=new AlertMaker();
        AlertMaker.sendAlert(Alert.AlertType.CONFIRMATION,"Warning","deleing history","are you sure ?")
                .ifPresent(buttonType -> {
                    if(buttonType==ButtonType.OK){
                        new HistoryDA().delete(history.getId());
                        //gridPane.getChildren().remove(row-1);
                        gridPane.getChildren().remove(gridPaneId);
                    }
                    else if(buttonType==ButtonType.CANCEL);
                });

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
