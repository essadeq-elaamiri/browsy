package browsy.presentation.controllers;


import browsy.dataAccess.HistoryDA;
import browsy.dataAccess.PageDA;
import browsy.entities.History;
import browsy.entities.Page;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {


    private Tab tab;

    public void setTab(Tab tab){
        this.tab=tab;
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

    @FXML
    void OnRefresh(ActionEvent event) {
        webView.getEngine().reload();
    }

    @FXML
    void onAddToFavorite(ActionEvent event) {

    }

    @FXML
    void onHome(ActionEvent event) {
        webView.getEngine().load("http://www.qwant.com");
        mainSearchField.setText(webView.getEngine().getLocation());
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

    }


    public void loadPage() {
        webView.getEngine().load("https://www.qwant.com");
        //engine.load("http://www.qwant.com");
        // engine.load("http://"+textField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage();
    }


    public void onSearchWebSite(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            webView.getEngine().load(mainSearchField.getText());
            tab.textProperty().bind(webView.getEngine().titleProperty());
            mainSearchField.setText(webView.getEngine().getLocation());
            //ajout dans l'history
            History history=new History(0, Date.valueOf(LocalDate.now()));
//complete here
            while(tab.getText()==null){
                System.out.println(webView.getEngine().getTitle());
                System.out.println(tab.getText());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Page page=new Page(0,webView.getEngine().getTitle(),webView.getEngine().getLocation());
            int i=new PageDA().save(page);
            Page pp=new PageDA().getOneById(i);
            System.out.println(pp);
            history.setPage(pp);
            new HistoryDA().save(history);

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

}
