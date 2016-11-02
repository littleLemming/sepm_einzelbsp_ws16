package sepm.ws16.e1327450.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.JockeyID;
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
        this.jockey = jockey;
        if(jockey != null) {
            svnrFeld.setText(""+jockey.getSvnr());
            nameFeld.setText(jockey.getName());
            geburtsdatumFeld.setValue(jockey.getGeburtsdatum().toLocalDate());
            gewichtFeld.setText(""+jockey.getGewicht());
            koennenFeld.setText(""+jockey.getKoennen());
            svnrFeld.setDisable(true);
        }
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleOk() {
        if (isInputValid()) {
            if(jockey==null) {
                logger.info("HERE");
                Jockey newJockey = new Jockey(Integer.parseInt(svnrFeld.getText()),Double.parseDouble(koennenFeld.getText()),nameFeld.getText(),java.sql.Date.valueOf(geburtsdatumFeld.getValue()),Integer.parseInt(gewichtFeld.getText()));
                try {
                    service.saveJockey(newJockey);
                } catch (ServiceException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Jockey konnte nicht gespeichert werden!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                jockey.setSvnr(Integer.parseInt(svnrFeld.getText()));
                jockey.setKoennen(Double.parseDouble(koennenFeld.getText()));
                jockey.setName(nameFeld.getText());
                jockey.setGeburtsdatum(java.sql.Date.valueOf(geburtsdatumFeld.getValue()));
                jockey.setGewicht(Integer.parseInt(gewichtFeld.getText()));
                try {
                    service.updateJockey(jockey);
                } catch (ServiceException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Jockey konnte nicht geupdatet werden!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    boolean isInputValid() {
        if(svnrFeld.getText()==null||svnrFeld.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Keine svnr angegeben!");
            alert.setContentText("Bitte geben Sie eine svnr an!");
            alert.showAndWait();
            return false;
        }
        try {
            Integer.parseInt(svnrFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Svnr muss Integer sein!");
            alert.setContentText("Bitte geben Sie einen Integer als svnr an an!");
            alert.showAndWait();
            return false;
        }
        try {
            if (jockey == null && !service.isFreeJockeyID(new JockeyID(Integer.parseInt(svnrFeld.getText())))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Belegte Svnr!");
                alert.setContentText("Bitte geben eine nicht benutze Svnr an!");
                alert.showAndWait();
                return false;
            }
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Fehler beim überprüfen der Svnr!");
            alert.showAndWait();
            return false;
        }
        if(nameFeld.getText()==null || nameFeld.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Name angegeben!");
            alert.setContentText("Bitte geben Sie einen Namen an!");
            alert.showAndWait();
            return false;
        }
        if(geburtsdatumFeld.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Geburtsdatum angegeben!");
            alert.setContentText("Bitte geben Sie einen Geburtsdatum an!");
            alert.showAndWait();
            return false;
        }
        if(gewichtFeld.getText()==null||gewichtFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Gewicht angegeben!");
            alert.setContentText("Bitte geben Sie eine Gewicht an!");
            alert.showAndWait();
            return false;
        }
        if(!gewichtFeld.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Gewicht muss Integer sein!");
            alert.setContentText("Bitte geben Sie einen Integer als Gewicht an!");
            alert.showAndWait();
            return false;
        }
        if(koennenFeld.getText()==null||koennenFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Können angegeben!");
            alert.setContentText("Bitte geben Sie ein Können an!");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(koennenFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Können muss Double sein!");
            alert.setContentText("Bitte geben Sie einen Double als Können an an!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
