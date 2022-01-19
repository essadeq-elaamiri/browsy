package browsy.presentation.controllers;

import browsy.entities.Bookmark;
import browsy.presentation.utils.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Date;
import java.time.LocalDateTime;

public class BookmarkItemController { ////Browsy_0/src/browsy/presentation/controllers/BookmarkItemController.java
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
		AlertMaker.sendAlert(AlertType.CONFIRMATION, "delete", null, null);
	}

	@FXML
	void onVisitPageBtnClicked(ActionEvent event) {
		AlertMaker.sendAlert(AlertType.CONFIRMATION, "visit page", null, null);

	}

	public void setItemData(Bookmark bookmark){
		this.bookmark = bookmark;
		this.pageTitle.setText(bookmark.getPage().getName());
		//this.pageDateTime.setText(bookmark.getCreatedAt().toString());

	}
}
