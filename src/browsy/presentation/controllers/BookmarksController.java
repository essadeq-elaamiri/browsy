package browsy.presentation.controllers;

import browsy.dataAccess.BookmarkDA;
import browsy.dataAccess.PageDA;
import browsy.entities.Bookmark;
import browsy.entities.History;
import browsy.entities.Page;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookmarksController implements Initializable {


    private WebViewInitializer webViewInitializer=new WebViewInitializer();

    public void setWebViewInitializer(Tab tab, TabPane tabPane) {
        this.webViewInitializer.setTab(tab);
        this.webViewInitializer.setTabPane(tabPane);
    }

    @FXML
    private GridPane bookmarksItemsPane;

    @FXML
    private ScrollPane bookmarksListPane;

    @FXML
    private TextField searchField;

    @FXML
    private Button sortButton;

    private BookmarkDA bookmarkDA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookmarkDA = new BookmarkDA();
        try {
            loadBookmarks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadSpecificBookmark(List<Bookmark> Bookmarks) throws IOException {

        this.bookmarksItemsPane.getChildren().removeIf(node -> 1==1);
        int row = 1;
        if (!Bookmarks.isEmpty()){
            for (Bookmark bookmark: Bookmarks){
                GridPane newItem = constructBookmarkItem(bookmark);
                //append items to the VBox
                //this.bookmarksItemsPane.getChildren().add(newItem);
                this.bookmarksItemsPane.addRow(row, newItem);
                row++;

            }
        }
    }

    List<Bookmark> listSearch=new ArrayList<>();
    @FXML
    void onSearchByName(ActionEvent event) throws IOException {
        List<Page> pages=new PageDA().getAllByName(searchField.getText());
        System.out.println("search : " +searchField.getText());
        System.out.println("size ="+pages.size());
        //System.out.println(pages.size()>0?pages.get(0):0);
        pages.forEach(p->{
            listSearch.addAll(bookmarkDA.getAllByPageId(p.getId()));


            //  System.out.println(listSearch.size()>0?listSearch.get(0).getPage().getName():0);
        });
        loadSpecificBookmark(listSearch);
        listSearch=new ArrayList<>();
    }

    @FXML
    void onSortBtnClicked(ActionEvent event) {

    }


    /**
     * Loading data from database, create items, append them to the VBox>ScrollPane
     * @param
     */
    private void loadBookmarks() throws IOException {
        //retrieve data
       loadSpecificBookmark( bookmarkDA.getAll());


    }

    private GridPane constructBookmarkItem(Bookmark bookmark) throws IOException {
        //get template
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/bookmarkItem.fxml"));
        GridPane item = fxmlLoader.load();
        //calling controller
        BookmarkItemController itemController = fxmlLoader.getController();
        itemController.setItemData(bookmark);
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
