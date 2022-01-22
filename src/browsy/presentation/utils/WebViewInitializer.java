package browsy.presentation.utils;

import browsy.presentation.controllers.BookmarksController;
import browsy.presentation.controllers.HistoryController;
import browsy.presentation.controllers.WebViewController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class WebViewInitializer {

    private Tab tab;

    public void setTab(Tab tab){
        this.tab=tab;
    }

    private TabPane tabPane;

    public void setTabPane(TabPane tabPane){
        this.tabPane=tabPane;
    }

    public Tab getTab() {
        return tab;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public void initializeSettingsPages(String setting){

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/"+setting+".fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tab tab=new Tab();
        FontAwesomeIcon closeIcon = new FontAwesomeIcon();
        closeIcon.setGlyphName("TIMES");
        Button closebutton = new Button();
        closebutton.getStyleClass().add("button");
        closebutton.setText("");
        closebutton.setGraphic(closeIcon);
        closebutton.setOnAction(actionEvent -> {
            tabPane.getTabs().remove(tab);
        });
        tab.getStyleClass().addAll("primary-color", "tab");
        tab.setGraphic(closebutton);

        tab.setText(setting);
        tab.setContent(root);
        tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);

        if(setting.equals("history")) {
            HistoryController historyController = fxmlLoader.getController();
            historyController.setWebViewInitializer(tabPane.getTabs().get(tabPane.getTabs().size() - 2), tabPane);
        }

        if(setting.equals("bookmarks")) {
            BookmarksController bookmarksController = fxmlLoader.getController();
            bookmarksController.setWebViewInitializer(tabPane.getTabs().get(tabPane.getTabs().size() - 2), tabPane);
        }
        /*
        if(setting.equals("downloads")) {
            DownloadsController downloadsController = fxmlLoader.getController();
            downloadsController.setWebViewInitializer(tabPane.getTabs().get(tabPane.getTabs().size() - 2), tabPane);
        }*/
    }
    public void initializePages(String link) throws IOException {

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
            tabPane.getTabs().remove(tab);
        });
        tab.getStyleClass().addAll("primary-color", "tab");
        tab.setText("Page");
        tab.setGraphic(closebutton);
        tab.setContent(root);
        tabPane.getTabs().add(tabPane.getTabs().size()-1,tab);
        tabPane.getSelectionModel().select(tabPane.getTabs().size()-2);
        WebViewController viewController=fxmlLoader.getController();
        viewController.setWebViewInitializer(tabPane.getTabs().get(tabPane.getTabs().size()-2),tabPane);
        //viewController.setTabPane(tabPane);
        viewController.loadPage(link);
    }
}
