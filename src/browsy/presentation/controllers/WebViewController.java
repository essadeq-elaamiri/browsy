package browsy.presentation.controllers;


import browsy.dataAccess.BookmarkDA;
import browsy.dataAccess.FolderDA;
import browsy.dataAccess.HistoryDA;
import browsy.dataAccess.PageDA;
import browsy.entities.Bookmark;
import browsy.entities.Folder;
import browsy.entities.History;
import browsy.entities.Page;
import browsy.presentation.downloader.MainDownload;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {


    private Tab tab;

    public void setTab(Tab tab){
        this.tab=tab;
    }

    private TabPane tabPane;

    public void setTabPane(TabPane tabPane){
        this.tabPane=tabPane;
    }

    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField mainSearchField;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button reloadButton;

    @FXML
    private MenuButton settingsButton;

    @FXML
    private Button switchModeButton;

    @FXML
    private WebView webView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiseSearch();
        initialiseEngine();
        loadPage();

    }





    @FXML
    void OnRefresh(ActionEvent event) {
        webView.getEngine().reload();

    }

    @FXML
    void onAddToFavorite(ActionEvent event) {
            new Thread(() -> {
                Folder folder=new FolderDA().getOneById(1);
                System.out.println(webView.getEngine().getLocation());
                Page pp=new PageDA().getAllByKeyword(webView.getEngine().getLocation()).get(0);
                 Bookmark book=new Bookmark(0,null);
                 book.setPage(pp);
                 book.setFolder(folder);
                 new BookmarkDA().save(book);
            }).start();
        }

    @FXML
    void onHome(ActionEvent event) {
        webView.getEngine().load("http://www.qwant.com");
    }

    @FXML
    void onNext(ActionEvent event) {
        WebHistory history = webView.getEngine().getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        mainSearchField.setText(entries.get(history.getCurrentIndex()).getUrl());

    }

    @FXML
    void onPrevious(ActionEvent event) {
        WebHistory history = webView.getEngine().getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);

        mainSearchField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    @FXML
    void onSwitchMode(ActionEvent event) {
        Parent mainParent=webView.getParent().getParent().getParent().getParent().getParent().getParent();
        String cssLink="/browsy/presentation/assets/css/";
        String darkThemeName="darkMode.css";
        String whiteThemeName="lightMode.css";
        if(mainParent.getStylesheets().
                get(0).
                split("/")[mainParent.getStylesheets().get(0).split("/").length-1].
                equals(whiteThemeName))
        {
            mainParent.getStylesheets().set(0,cssLink+darkThemeName);
        }
        else if(mainParent.getStylesheets().
                get(0).
                split("/")[mainParent.getStylesheets().get(0).split("/").length-1].
                equals(darkThemeName))
        {
            mainParent.getStylesheets().set(0,cssLink+whiteThemeName);
        }
    }

    public void loadPage() {
        webView.getEngine().load("https://www.qwant.com");
    }

    public void initialiseSearch(){
        mainSearchField.focusedProperty().addListener((obs, oldVal, newVal) ->
        {
            if(newVal) mainSearchField.textProperty().unbind();
            else mainSearchField.textProperty().bind(webView.getEngine().locationProperty());
        });
    }

    public void initialiseEngine(){
        webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("loading state: "+newValue);
            if (Worker.State.SUCCEEDED.equals(newValue)) {
                if(tab!=null && !tab.textProperty().isBound()){
                    tab.textProperty().bind(webView.getEngine().titleProperty());
                    mainSearchField.textProperty().bind(webView.getEngine().locationProperty());
                }
            }
        });

        webView.getEngine().locationProperty().addListener((observableValue, s, t1) -> {
            new MainDownload().startDownload(t1,webView.getEngine().getTitle());
            new Thread(() -> {
                while(!webView.getEngine().getLoadWorker().getMessage().equals("Loading complete")){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("adding to history :"+t1);
                addToHistory();
            }).start();
        });
    }



    public void addToHistory(){
        History history=new History(0, Date.valueOf(LocalDate.now()));
        Page page=new Page(0,webView.getEngine().getTitle(),webView.getEngine().getLocation());
        int i=new PageDA().save(page);
        System.out.println(i);
        Page pp=new PageDA().getOneById(i);
        System.out.println(pp);
        history.setPage(pp);
        new HistoryDA().save(history);
    }

    public void onSearchWebSite(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            webView.getEngine().load(mainSearchField.getText());
        }

    }

    private String getTitle(WebEngine webEngine) {
        Document doc = webEngine.getDocument();
        NodeList heads = doc.getElementsByTagName("head");
        String titleText = webEngine.getLocation() ; // use location if page does not define a title
        if (heads.getLength() > 0) {
            Element head = (Element)heads.item(0);
            NodeList titles = head.getElementsByTagName("title");
            if (titles.getLength() > 0) {
                Node title = titles.item(0);
                titleText = title.getTextContent();
            }
        }
        return titleText ;
    }
    @FXML
    public void onHistory(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/history.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
        Tab tab=new Tab();
        tab.setText("History");
        tab.setContent(root);
        tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
        //((WebViewController)fxmlLoader.getController()).setTab(tabPane.getTabs().get(tabPane.getTabs().size()-2));
        //root.setUserData(tabPaneId.getTabs().get(tabPaneId.getTabs().size()-2));
    }
    @FXML
    public void onBookmarks(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/bookmarks.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
        Tab tab=new Tab();
        tab.setText("Bookmarks");
        tab.setContent(root);
        tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);

    }
    @FXML
    public void onDownloads(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/downloads.fxml"));
        Parent root = fxmlLoader.load();
        //Parent root = FXMLLoader.load(this.getClass().getResource("/browsy/presentation/views/webView.fxml"));
        Tab tab=new Tab();
        tab.setText("Downloads");
        tab.setContent(root);
        tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
        //((WebViewController)fxmlLoader.getController()).setTab(tabPane.getTabs().get(tabPane.getTabs().size()-2));
        //root.setUserData(tabPaneId.getTabs().get(tabPaneId.getTabs().size()-2));

    }
}
