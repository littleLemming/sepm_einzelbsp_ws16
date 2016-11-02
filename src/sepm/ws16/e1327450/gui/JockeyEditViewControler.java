package sepm.ws16.e1327450.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.service.Service;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sepm.ws16.e1327450.service.ServiceException;

public class JockeyEditViewControler {

    final static Logger logger = LoggerFactory.getLogger(JockeyEditViewControler.class);

    @FXML
    private TextField koennenFeld;
    @FXML
    private DatePicker geburtsdatumFeld;
    @FXML
    private TextField gewichtFeld;
    @FXML
    private TextField nameFeld;
    @FXML
    private TextField svnrFeld;

    private Stage dialogStage;
    private Jockey jockey;
    private Service service;
    private boolean okClicked = false;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setJockey(Jockey jockey) {
        if(jockey != null) {
            svnrFeld.setText(""+jockey.getSvnr());
            nameFeld.setText(jockey.getName());
            //geburtsdatumFeld.setValue(jockey.getGeburtsdatum().toLocalDate());
            //gewichtFeld.setText(""+jockey.getGewicht());
            //koennenFeld.setText(""+jockey.getKoennen());
        } else {
            try {
                svnrFeld.setText(service.getFreeRennID().getRenn_id()+"");
            } catch (ServiceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Es konnte keine svnr geladen werden!");
                alert.setContentText("Es ist ein Fehler beim Laden der svnr aufgetreten!");
            }
        }
        svnrFeld.setDisable(true);
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleOk() {
        /*if (isInputValid()) {
            article.setId(Integer.parseInt(idField.getText()));
            article.setName(nameField.getText());
            article.setPicture(pictureField.getText());
            article.setDescription(descriptionField.getText());
            article.setPrice(Double.parseDouble(priceField.getText()));
            try {
                service.updateArticle(article);
            } catch (ServiceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Could not update!");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
            okClicked = true;
            dialogStage.close();
        }*/
        dialogStage.close();
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    boolean isInputValid() {
        return false;
    }

}
