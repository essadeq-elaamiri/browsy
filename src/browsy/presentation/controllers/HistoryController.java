package browsy.presentation.controllers;

import browsy.dataAccess.BookmarkDA;
import browsy.dataAccess.HistoryDA;
import browsy.entities.Bookmark;
import browsy.entities.History;
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
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {


    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab,TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }

    @FXML
    private Button clearButton;

    @FXML
    private Button findButton;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private GridPane historyItemsPane;

    @FXML
    private ScrollPane historyListPane;

    @FXML
    private Button olderHistoryButton;

    @FXML
    private TextField searchField;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Button todayHistoryButton;

    @FXML
    private Button yesterdayHistoryButton;

    private HistoryDA historyDA;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        historyDA = new HistoryDA();
        try {
            loadHistory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loading data from database, create items, append them to the VBox>ScrollPane
     * @param
     */
    private void loadHistory() throws IOException {
        //retrieve data
        List<History> histories = historyDA.getAll();
        int row = 1;
        if (!histories.isEmpty()){
            for (History history: histories){
                GridPane newItem = constructHistoryItem(history);
                //append items to the VBox
                //this.bookmarksItemsPane.getChildren().add(newItem);
                this.historyItemsPane.addRow(row, newItem);
                row++;

            }
        }
    }

    private GridPane constructHistoryItem(History history) throws IOException {
        //get template
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/historyItem.fxml"));
        GridPane item = fxmlLoader.load();
        //calling controller

        HistoryItemController itemController = fxmlLoader.getController();

        itemController.setItemData(history);
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


    @FXML
    void onClearHistory(ActionEvent event) {
    }

    @FXML
    void onFindHistory(ActionEvent event) {

    }

    @FXML
    void onSearchByName(KeyEvent event) {

    }

    @FXML
    void showOlderHistory(ActionEvent event) {

    }

    @FXML
    void showTodayHistory(ActionEvent event) {

    }

    @FXML
    void showYesterdayHistory(ActionEvent event) {

    }

}
