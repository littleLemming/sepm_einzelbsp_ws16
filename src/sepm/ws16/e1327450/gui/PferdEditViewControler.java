package sepm.ws16.e1327450.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.PferdID;
import sepm.ws16.e1327450.service.Service;
import sepm.ws16.e1327450.service.ServiceException;

public class PferdEditViewControler {

    @FXML
    private TextField rasseFeld;
    @FXML
    private TextField bildFeld;
    @FXML
    private TextField minGeschwFeld;
    @FXML
    private TextField maxGeschwFeld;
    @FXML
    private TextField nameFeld;
    @FXML
    private TextField alterFeld;
    @FXML
    private TextField chipNrFeld;

    private Stage dialogStage;
    private Pferd pferd;
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

    public void setPferd(Pferd pferd) {
        this.pferd = pferd;
        if(pferd != null) {
            chipNrFeld.setText(pferd.getChip_nr()+"");
            nameFeld.setText(pferd.getName());
            bildFeld.setText(pferd.getBild());
            alterFeld.setText(pferd.getAlter_jahre()+"");
            rasseFeld.setText(pferd.getRasse());
            minGeschwFeld.setText(pferd.getMin_gesw()+"");
            maxGeschwFeld.setText(pferd.getMax_gesw()+"");
            chipNrFeld.setDisable(true);
        }
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void handleOk() {
        if (isInputValid()) {
            if(pferd == null) {
                Pferd newPferd = new Pferd(Integer.parseInt(chipNrFeld.getText()),nameFeld.getText(),rasseFeld.getText(),Integer.parseInt(alterFeld.getText()),bildFeld.getText(),Double.parseDouble(minGeschwFeld.getText()),Double.parseDouble(maxGeschwFeld.getText()));
                try {
                    service.savePferd(newPferd);
                } catch (ServiceException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Pferd konnte nicht gespeichert werden!");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            } else {
                pferd.setChip_nr(Integer.parseInt(chipNrFeld.getText()));
                pferd.setName(nameFeld.getText());
                pferd.setRasse(rasseFeld.getText());
                pferd.setAlter_jahre(Integer.parseInt(alterFeld.getText()));
                pferd.setBild(bildFeld.getText());
                pferd.setMin_gesw(Double.parseDouble(minGeschwFeld.getText()));
                pferd.setMax_gesw(Double.parseDouble(maxGeschwFeld.getText()));
                try {
                    service.updatePferd(pferd);
                } catch (ServiceException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Pferd konnte nicht geupdatet werden!");
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
        if(chipNrFeld.getText()==null||chipNrFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Keine chip-nr angegeben!");
            alert.setContentText("Bitte geben Sie eine chip-nr an!");
            alert.showAndWait();
            return false;
        }
        try {
            Integer.parseInt(chipNrFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Chip-nr muss Integer sein!");
            alert.setContentText("Bitte geben Sie einen Integer als chip-nr an an!");
            alert.showAndWait();
            return false;
        }
        try {
            int chip_nr = Integer.parseInt(chipNrFeld.getText());
            if(chip_nr<0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Chip-Nr muss größer 0 sein!");
                alert.setContentText("Bitte geben Sie eine positive Chip-Nr an!");
                alert.showAndWait();
                return false;
            }
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Svnr muss Integer sein!");
            alert.setContentText("Bitte geben Sie einen Integer als svnr an an!");
            alert.showAndWait();
            return false;
        }
        try {
            if (pferd == null && !service.isFreePferdID(new PferdID(Integer.parseInt(chipNrFeld.getText())))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Belegte Chip-Nr!");
                alert.setContentText("Bitte geben eine nicht benutze Chip-Nr an!");
                alert.showAndWait();
                return false;
            }
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Fehler beim überprüfen der Chip-Nr!");
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
        if(bildFeld.getText()==null || bildFeld.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Bild angegeben!");
            alert.setContentText("Bitte geben Sie eine Bild an!");
            alert.showAndWait();
            return false;
        }
        if(alterFeld.getText()==null||alterFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Kein Alter angegeben!");
            alert.setContentText("Bitte geben Sie ein Alter an!");
            alert.showAndWait();
            return false;
        }
        try {
            Integer.parseInt(alterFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Alter muss Integer sein!");
            alert.setContentText("Bitte geben Sie einen Integer als Alter an an!");
            alert.showAndWait();
            return false;
        }
        if(rasseFeld.getText()==null||rasseFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Keine Rasse angegeben!");
            alert.setContentText("Bitte geben Sie eine Rasse an!");
            alert.showAndWait();
            return false;
        }
        if(minGeschwFeld.getText()==null||minGeschwFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Keine minimum-Geschwindigkeit angegeben!");
            alert.setContentText("Bitte geben Sie eine minimum-Geschwindigkeit an!");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(minGeschwFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Minimum-Geschwindikeit muss Double sein!");
            alert.setContentText("Bitte geben Sie einen Double als Minimum-Geschwindigkeit an an!");
            alert.showAndWait();
            return false;
        }
        if(maxGeschwFeld.getText()==null||maxGeschwFeld.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Keine maximum-Geschwindigkeit angegeben!");
            alert.setContentText("Bitte geben Sie eine maximum-Geschwindigkeit an!");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(maxGeschwFeld.getText());
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Maximum-Geschwindikeit muss Double sein!");
            alert.setContentText("Bitte geben Sie einen Double als Maximum-Geschwindigkeit an an!");
            alert.showAndWait();
            return false;
        }
        try {
            double minGeschw = Double.parseDouble(minGeschwFeld.getText());
            double maxGeschw = Double.parseDouble(maxGeschwFeld.getText());
            if(minGeschw < 40.0 || maxGeschw > 60.0 || minGeschw > maxGeschw) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(dialogStage);
                alert.setTitle("Fehler bei Geschwindigkeitswerten!");
                alert.setContentText("Minimum-Geschwindikeit mindestens 40.0, Maximum-Geschwindigkeit höchstens 60.0, Minimum-Geschwindigkeit kleiner Maximum-Geschwindikeit!");
                alert.showAndWait();
                return false;
            }
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Minimum-Geschwindikeit und Maximum-Geschwindikeit muüssen Double sein!");
            alert.setContentText("Bitte geben Sie einen Double als Minimum-Geschwindigkeit und Maximum-Geschwindikeit an!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
