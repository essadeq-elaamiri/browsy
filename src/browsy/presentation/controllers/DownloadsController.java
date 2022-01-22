package browsy.presentation.controllers;

import browsy.dataAccess.BookmarkDA;
import browsy.dataAccess.DownloadDA;
import browsy.dataAccess.PageDA;
import browsy.entities.Bookmark;
import browsy.entities.Download;
import browsy.entities.Page;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DownloadsController implements Initializable
{

    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab, TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }

    @FXML
    private GridPane downloadsItemsPane;

    @FXML
    private ScrollPane downloadListPane;

    @FXML
    private TextField searchField;

    @FXML
    private Button sortButton;

    private DownloadDA downloadDA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        downloadDA = new DownloadDA();
        try {
            loadDownloads();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadSpecificDownloads(List<Download> downloads) throws IOException {

        downloadsItemsPane.getChildren().removeIf(node -> 1==1);
        int row = 1;
        if (!downloads.isEmpty()){
            for (Download download: downloads){
                GridPane newItem = constructDownloadItem(download);
                //append items to the VBox
                //this.bookmarksItemsPane.getChildren().add(newItem);
                this.downloadsItemsPane.addRow(row, newItem);
                row++;

            }
        }
    }

    @FXML
    void onSearchByName(KeyEvent event) throws IOException {
        loadSpecificDownloads(downloadDA.getAllByKeyword(searchField.getText()));
    }

    @FXML
    void onSortBtnClicked(ActionEvent event) {

    }


    /**
     * Loading data from database, create items, append them to the VBox>ScrollPane
     * @param
     */
    private void loadDownloads() throws IOException {
        //retrieve data
        loadSpecificDownloads( downloadDA.getAll());


    }

    private GridPane constructDownloadItem(Download download) throws IOException {
        //get template
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/downloadItem.fxml"));
        GridPane item = fxmlLoader.load();
        //calling controller
        DownloadItemController itemController = fxmlLoader.getController();
        itemController.setItemData(download);
        new Thread(() -> {
            while(webViewInitializer.getTabPane()==null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            itemController.setWebViewInitializer(webViewInitializer.getTab(), webViewInitializer.getTabPane());
        }).start();
        return item;
    }


}
