package sepm.ws16.e1327450.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.*;
import sepm.ws16.e1327450.service.ServiceException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OverviewControler {

    final static Logger logger = LoggerFactory.getLogger(OverviewControler.class);

    private MainApp mainApp;

    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennsimulationRennIdColumn;
    @FXML
    private TableView<Jockey> jockeyAddToRennsimulationTable;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisseViewPlatzColumn;
    @FXML
    private TableColumn<Pferd, Double> pferdViewMaxGeschwColumn;
    @FXML
    private TableView<PferdJockeyPair> pferdJockeyPaarRennsimulationTable;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisseViewKoennenBerechnetColumn;
    @FXML
    private TableView<Rennergebnis> rennergebnisseViewTable;
    @FXML
    private TableColumn<Jockey, Integer> jockeyAddToRennsimulationSvnrColumn;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisseViewPferdGeschwColumn;
    @FXML
    private TableView<Rennergebnis> rennergebnisRennsimulationTable;
    @FXML
    private TableView<Pferd> pferdFilterRennergebnisTable;
    @FXML
    private TableColumn<Pferd, Integer> pferdAddToRennsimulationChipNrColumn;
    @FXML
    private TableColumn<Pferd, Double> pferdViewMinGeschwColumn;
    @FXML
    private TableColumn<RennID, Integer> rennergebnisFilterRennergebnisRennIdTable;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisRennsimulationDurchnittlicheGeschwindigkeitColumn;
    @FXML
    private TableColumn<Jockey, Double> jockeyViewKoennenColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisseViewSvnrColumn;
    @FXML
    private TableView<Pferd> pferdAddToRennsimulationTable;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisseViewChipNrColumn;
    @FXML
    private TableColumn<Pferd, String> pferdAddToRennsimulationNameColumn;
    @FXML
    private TableColumn<Jockey, Integer> jockeyViewSvnrColumn;
    @FXML
    private TableColumn<Pferd, Integer> pferdFilterRennergebnisChipNrColumn;
    @FXML
    private TableColumn<Jockey, String> jockeyViewNameColumn;
    @FXML
    private TableColumn<PferdJockeyPair, Integer> pferdJockeyPaarRennsimulationSvnrColumn;
    @FXML
    private TableColumn<Pferd, String> pferdViewNameColumn;
    @FXML
    private TableColumn<Jockey, Integer> jockeyViewGewichtColumn;
    @FXML
    private TableColumn<Pferd, String> pferdViewRasseColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisseViewRennIdColumn;
    @FXML
    private TableView<Jockey> jockeyViewTable;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisseViewDurchnittGeschwColumn;
    @FXML
    private TableView<Jockey> jockeyFilterRennergebnisTable;
    @FXML
    private TableColumn<Jockey, Integer> jockeyFilterRennergebnisSvnrColumn;
    @FXML
    private TableColumn<Pferd, Integer> pferdViewAlterColumn;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisseViewGlueckColumn;
    @FXML
    private TextField jockeySearchNameTextField;
    @FXML
    private TableView<Pferd> pferdViewTable;
    @FXML
    private TableColumn<Jockey, Date> jockeyViewGeburtsdatum;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennsimulationPlatzColumn;
    @FXML
    private TableColumn<Jockey, String> jockeyAddToRennsimulationNameColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennsimulationChipNrColumn;
    @FXML
    private TableColumn<Pferd, Integer> pferdViewChipNrColumn;
    @FXML
    private TableView<RennID> rennergebnisFilterRennergebnisTable;
    @FXML
    private TextField rennergebnisSearchPlatzTextField;
    @FXML
    private TextField pferdSearchNameTextField;
    @FXML
    private TableColumn<PferdJockeyPair, Integer> pferdJockeyPaarRennsimulationChipNrColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennsimulationSvnrColumn;

    private ObservableList<Pferd> pferdViewList = FXCollections.observableArrayList();
    private ObservableList<Jockey> jockeyViewList = FXCollections.observableArrayList();
    private ObservableList<Rennergebnis> rennergebnisViewList = FXCollections.observableArrayList();
    private ObservableList<Pferd> pferdRennergebnisFilterList = FXCollections.observableArrayList();
    private ObservableList<Jockey> jockeyAddToRennsimulationList = FXCollections.observableArrayList();
    private ObservableList<RennID> rennergebnisRennergebnisFilterList = FXCollections.observableArrayList();
    private ObservableList<Jockey> jockeyRennergebnisFilterList = FXCollections.observableArrayList();
    private ObservableList<Pferd> pferdAddToRennsimulationList = FXCollections.observableArrayList();
    private ObservableList<PferdJockeyPair> pferdJockeyPaarRennsimulationList = FXCollections.observableArrayList();
    private ObservableList<Rennergebnis> rennergebnisRennsimulationList = FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        logger.info("setMainApp");
        this.mainApp = mainApp;
        try {
            pferdViewList.addAll(mainApp.getService().loadAllPferd());
            jockeyViewList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
            pferdRennergebnisFilterList.addAll(mainApp.getService().loadAllPferd());
            jockeyRennergebnisFilterList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisRennergebnisFilterList.addAll(mainApp.getService().getAllRennIDs());
            jockeyAddToRennsimulationList.addAll(mainApp.getService().loadAllJockey());
            pferdAddToRennsimulationList.addAll(mainApp.getService().loadAllPferd());
        } catch (ServiceException e) {
            logger.error("SETUP OF OVERVIEWCONTROLER FAILED");
            e.printStackTrace();
        }
        pferdViewTable.setItems(pferdViewList);
        jockeyViewTable.setItems(jockeyViewList);
        rennergebnisseViewTable.setItems(rennergebnisViewList);
        pferdFilterRennergebnisTable.setItems(pferdRennergebnisFilterList);
        jockeyFilterRennergebnisTable.setItems(jockeyRennergebnisFilterList);
        rennergebnisFilterRennergebnisTable.setItems(rennergebnisRennergebnisFilterList);
        jockeyAddToRennsimulationTable.setItems(jockeyAddToRennsimulationList);
        pferdAddToRennsimulationTable.setItems(pferdAddToRennsimulationList);
        pferdJockeyPaarRennsimulationTable.setItems(pferdJockeyPaarRennsimulationList);
        rennergebnisRennsimulationTable.setItems(rennergebnisRennsimulationList);
    }

    @FXML
    private void initialize() {
        logger.info("initialize");
        pferdViewChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getChip_nr()));
        pferdViewNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        pferdViewRasseColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRasse()));
        pferdViewAlterColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getAlter_jahre()));
        pferdViewMinGeschwColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getMin_gesw()));
        pferdViewMaxGeschwColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getMax_gesw()));
        jockeyViewSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getSvnr()));
        jockeyViewNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        jockeyViewGeburtsdatum.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getGeburtsdatum()));
        jockeyViewKoennenColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getKoennen()));
        jockeyViewGewichtColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getGewicht()));
        rennergebnisseViewRennIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRenn_id()));
        rennergebnisseViewChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPferd().getChip_nr()));
        rennergebnisseViewSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getSvnr()));
        rennergebnisseViewDurchnittGeschwColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getDgeschw()));
        rennergebnisseViewPlatzColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPlatz()));
        rennergebnisseViewPferdGeschwColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPgeschw()));
        rennergebnisseViewGlueckColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getGlueck()));
        rennergebnisseViewKoennenBerechnetColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getKoennen_b()));
        pferdFilterRennergebnisChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getChip_nr()));
        jockeyFilterRennergebnisSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getSvnr()));
        rennergebnisFilterRennergebnisRennIdTable.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRenn_id()));
        jockeyAddToRennsimulationSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getSvnr()));
        jockeyAddToRennsimulationNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        pferdAddToRennsimulationChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getChip_nr()));
        pferdAddToRennsimulationNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        pferdJockeyPaarRennsimulationChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPferd().getChip_nr()));
        pferdJockeyPaarRennsimulationSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getName()));
        rennergebnisRennsimulationRennIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRenn_id()));
        rennergebnisRennsimulationChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPferd().getChip_nr()));
        rennergebnisRennsimulationSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getSvnr()));
        rennergebnisRennsimulationDurchnittlicheGeschwindigkeitColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getDgeschw()));
        rennergebnisRennsimulationPlatzColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPlatz()));
    }

    @FXML
    void handlePferdSearchByName() {
        logger.info("handlePferdSearchByName");
        String name = pferdSearchNameTextField.getText();
        if(name == null || name.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Name angegeben!");
            alert.setHeaderText("Bitte geben Sie einen Namen an nach dem gesucht werden soll!");
            alert.showAndWait();
        } else {
            try {
                List<Pferd> removeList = new ArrayList<>();
                for(Pferd p : pferdViewList) {
                    removeList.add(p);
                }
                pferdViewList.removeAll(removeList);
                pferdViewList.addAll(mainApp.getService().searchPferd(new PferdCondition(name,-1,-1,-1,-1,-1,-1)));
            } catch (ServiceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Es konnte nicht nach dem Namen gesucht werden!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handlePferdShowAll() {
        logger.info("handlePferdShowAll");
        try {
            List<Pferd> removeList = new ArrayList<>();
            for(Pferd p : pferdViewList) {
                removeList.add(p);
            }
            pferdViewList.removeAll(removeList);
            pferdViewList.addAll(mainApp.getService().loadAllPferd());
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnten nicht alle Pferde geladen werden!");
            alert.showAndWait();
        }
    }

    @FXML
    void handlePferdViewBild() {

    }

    @FXML
    void handlePferdEdit() {

    }

    @FXML
    void handlePferdDelete() {

    }

    @FXML
    void handlePferdNew() {

    }

    @FXML
    void handleJockeySearchByName() {
        logger.info("handleJockeySearchByName");
        String name = jockeySearchNameTextField.getText();
        if(name == null || name.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Name angegeben!");
            alert.setHeaderText("Bitte geben Sie einen Namen an nach dem gesucht werden soll!");
            alert.showAndWait();
        } else {
            try {
                List<Jockey> removeList = new ArrayList<>();
                for(Jockey j : jockeyViewList) {
                    removeList.add(j);
                }
                jockeyViewList.removeAll(removeList);
                jockeyViewList.addAll(mainApp.getService().searchJockey(new JockeyCondition(-1,-1,name,null,-1,-1)));
            } catch (ServiceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Es konnte nicht nach dem Namen gesucht werden!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void handleJockeyShowAll() {
        logger.info("handleJockeyShowAll");
        try {
            List<Jockey> removeList = new ArrayList<>();
            for(Jockey j : jockeyViewList) {
                removeList.add(j);
            }
            jockeyViewList.removeAll(removeList);
            jockeyViewList.addAll(mainApp.getService().loadAllJockey());
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnten nicht alle Jockeys geladen werden!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleJockeyEdit() {

    }

    @FXML
    void handleJockeyDelete() {

    }

    @FXML
    void handleJockeyNew() {

    }

    @FXML
    void handleRennergebnisFilterBy() {
        logger.info("handleRennergebnisFilterBy");
        RennID selectedRennID = rennergebnisFilterRennergebnisTable.getSelectionModel().getSelectedItem();
        Pferd selectedPferd = pferdFilterRennergebnisTable.getSelectionModel().getSelectedItem();
        Jockey selectedJockey = jockeyFilterRennergebnisTable.getSelectionModel().getSelectedItem();
        if(selectedJockey == null && selectedPferd == null && selectedRennID == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nichts ausgewählt!");
            alert.setHeaderText("Bitte wählen sie zumindest eine Renn-ID oder ein Pferd oder einen Jockey oder eine beliebige Kombination dieser!");
            alert.showAndWait();
        }
        int renn_id = -1;
        if(selectedRennID != null) {
            renn_id = selectedRennID.getRenn_id();
        }
        int chip_nr = -1;
        if(selectedPferd != null) {
            chip_nr = selectedPferd.getChip_nr();
        }
        int svnr = -1;
        if(selectedJockey != null) {
            svnr = selectedJockey.getSvnr();
        }
        try {
            List<Rennergebnis> removeList = new ArrayList<>();
            for(Rennergebnis r : rennergebnisViewList) {
                removeList.add(r);
            }
            rennergebnisViewList.removeAll(removeList);
            rennergebnisViewList.addAll(mainApp.getService().searchRennergebnis(new RennergebnisCondition(renn_id,chip_nr,svnr,-1,-1,-1,-1)));
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnte nicht nach der angegebnen Kombination an Parametern gesucht werden!");
            alert.showAndWait();
        }
        rennergebnisFilterRennergebnisTable.getSelectionModel().select(null);
        pferdFilterRennergebnisTable.getSelectionModel().select(null);
        jockeyFilterRennergebnisTable.getSelectionModel().select(null);
    }

    @FXML
    void handleRennergebnisSearchByPlatz() {
        logger.info("handleRennergebnisSearchByPlatz");
        String platz = rennergebnisSearchPlatzTextField.getText();
        if(platz == null || platz.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Platz angegeben!");
            alert.setHeaderText("Bitte geben Sie einen Platz an nach dem gesucht werden soll!");
            alert.showAndWait();
            return;
        }
        if (!platz.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Platz kann nur aus Zahlen bestehen!");
            alert.setHeaderText("Bitte geben sie in dem Eingabefeld ausschließlich Zahlen an!");
            alert.showAndWait();
            return;
        }
        int platz_i = Integer.parseInt(platz);
        try {
            List<Rennergebnis> removeList = new ArrayList<>();
            for(Rennergebnis r : rennergebnisViewList) {
                removeList.add(r);
            }
            rennergebnisViewList.removeAll(removeList);
            rennergebnisViewList.addAll(mainApp.getService().searchRennergebnis(new RennergebnisCondition(-1,-1,-1,-1,-1,platz_i,platz_i)));
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnte nicht nach dem Platz gesucht werden!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleRennergebnisShowAll() {
        logger.info("handleRennergebnisShowAll");
        try {
            List<Rennergebnis> removeList = new ArrayList<>();
            for(Rennergebnis r : rennergebnisViewList) {
                removeList.add(r);
            }
            rennergebnisViewList.removeAll(removeList);
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnten nicht alle Rennergebnisse geladen werden!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleRennersimulationAddPair() {

    }

    @FXML
    void handleRennsimulationRest() {

    }

    @FXML
    void handleRennsimulationDurchfuehren() {

    }


}
