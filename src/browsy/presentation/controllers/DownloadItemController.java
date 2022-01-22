package browsy.presentation.controllers;

import browsy.dataAccess.DownloadDA;
import browsy.dataAccess.HistoryDA;
import browsy.entities.Download;
import browsy.entities.History;
import browsy.presentation.utils.AlertMaker;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class DownloadItemController {


    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab, TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }


    @FXML
    private Button deleteBookmark;

    @FXML
    private GridPane gridPaneId;

    @FXML
    private Label pageDateTime;

    @FXML
    private Label pageLocation;

    @FXML
    private Label pageTitle;

    @FXML
    private Button visitPageBtn;

    private Download download;

    @FXML
    void onDeleteDownloadBtnClicked(ActionEvent event)  {
        AlertMaker.sendAlert(Alert.AlertType.CONFIRMATION,"Warning","deleing Download","are you sure ?")
                .ifPresent(buttonType -> {
                    if(buttonType==ButtonType.OK){
                        new DownloadDA().delete(download.getId());
                        GridPane parent = (GridPane) gridPaneId.getParent();
                        parent.getChildren().remove(gridPaneId);
                    }
                    else if(buttonType==ButtonType.CANCEL);
                });

    }

    @FXML
    void onVisitPageBtnClicked(ActionEvent event) {
        try {
            Runtime.getRuntime().exec("explorer.exe  /select," + download.getLocationInSystem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setItemData(Download download){
        this.download = download;
        this.pageTitle.setText(download.getName());
        this.pageLocation.setText(download.getLocationInSystem());
        this.pageDateTime.setText(download.getDownloadedAt().toString());

    }

}
