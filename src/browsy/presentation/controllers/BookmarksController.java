package browsy.presentation.controllers;

import browsy.dataAccess.BookmarkDA;
import browsy.entities.Bookmark;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookmarksController implements Initializable {

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


    @FXML
    void onSearchFieldAction(ActionEvent event) {

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
        List<Bookmark> bookmarks = bookmarkDA.getAll();
        Label label = new Label("hello books");
        int row = 1;
        if (!bookmarks.isEmpty()){
            for (Bookmark bookmark: bookmarks){
                GridPane newItem = constructBookmarkItem(bookmark);
                //append items to the VBox
                //this.bookmarksItemsPane.getChildren().add(newItem);
                this.bookmarksItemsPane.addRow(row, newItem);
                row++;

            }
        }

    }

    private GridPane constructBookmarkItem(Bookmark bookmark) throws IOException {
        //get template
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/browsy/presentation/views/bookmarkItem.fxml"));
        GridPane item = fxmlLoader.load();
        //calling controller
        BookmarkItemController itemController = fxmlLoader.getController();
        itemController.setItemData(bookmark);

        return item;
    }


}
