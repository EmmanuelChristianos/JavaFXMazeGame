package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class StartController {
	
	private Stage stage;
	
	@FXML
    private Button startButton;
	
	@FXML
	private ChoiceBox<String> mapSelect;
	
	@FXML
	private Label mapError;

    @FXML
    public void handleStartBtn(ActionEvent event) throws IOException {
    	if (mapSelect.getValue() == null) {
    		mapError.setVisible(true);
    	} else {
    		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(mapSelect.getValue());
    		DungeonController controller = dungeonLoader.loadController();
    		controller.setStartScreen(new StartScreen(stage));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.requestFocus();
            stage.setScene(scene);
            stage.show();
    	}
    }
    
    @FXML
    public void initialize() {
    	File[] files = new File("dungeons/").listFiles();
    	
    	for (File file: files) {
    		mapSelect.getItems().add(file.getName());
    	}
    }
    
    public StartController(Stage stage) {
    	this.stage = stage;
    }


}
