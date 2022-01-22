package browsy.presentation.controllers;

import browsy.dataAccess.BookmarkDA;
import browsy.entities.Bookmark;
import browsy.presentation.utils.AlertMaker;
import browsy.presentation.utils.WebViewInitializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

public class BookmarkItemController { ////Browsy_0/src/browsy/presentation/controllers/BookmarkItemController.java

	private WebViewInitializer webViewInitializer=new WebViewInitializer();

	public void setWebViewInitializer(Tab tab, TabPane tabPane) {
		this.webViewInitializer.setTab(tab);
		this.webViewInitializer.setTabPane(tabPane);
	}

	@FXML
	private GridPane gridPaneId;

	@FXML
	private Button deleteBookmark;

	@FXML
	private Label pageDateTime;

	@FXML
	private Label pageTitle;

	@FXML
	private Button visitPageBtn;

	private Bookmark bookmark;

	@FXML
	void onDeleteBookmarkBtnClicked(ActionEvent event) {
		AlertMaker alert=new AlertMaker();
		AlertMaker.sendAlert(Alert.AlertType.CONFIRMATION,"Warning","deleing bookmark","are you sure ?")
				.ifPresent(buttonType -> {
					if(buttonType==ButtonType.OK){
						new BookmarkDA().delete(bookmark.getId());
						GridPane parent = (GridPane) gridPaneId.getParent();
						parent.getChildren().remove(gridPaneId);
					}
					else if(buttonType==ButtonType.CANCEL);
				});
	}

	@FXML
	void onVisitPageBtnClicked(ActionEvent event) throws IOException {
		webViewInitializer.initializePages(bookmark.getPage().getLink());

	}

	public void setItemData(Bookmark bookmark){
		this.bookmark = bookmark;
		this.pageTitle.setText(bookmark.getPage().getName());
		//this.pageDateTime.setText(bookmark.getCreatedAt().toString());
	}
}
