package sepm.ws16.e1327450.gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class BildViewControler {

    final static Logger logger = LoggerFactory.getLogger(BildViewControler.class);

    private Stage dialogStage;
    private boolean okClicked = false;
    private String picture;

    @FXML
    private ImageView imageView;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPicture(String picture) {
        this.picture = picture;
        File file = new File(this.picture);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    public void handleOk() {}

}
