package browsy.main;

import browsy.presentation.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private final String MAIN_FXML_PATH = "/browsy/presentation/views/main.fxml";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(MAIN_FXML_PATH));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/browsy/presentation/assets/img/icon.png"));
			MainController cont=fxmlLoader.getController();
			cont.initialiser();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
