package sepm.ws16.e1327450.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.*;
import sepm.ws16.e1327450.service.ServiceException;

import java.io.File;
import java.sql.Date;
import java.util.*;

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
    @FXML
    private TableView<Pferd> pferdAddToStatistikTable;
    @FXML
    private TableView<Jockey> jockeyAddToStatistikTable;
    @FXML
    private TableColumn<Pferd, Integer> pferdAddToStatistikChipNrColumn;
    @FXML
    private TableColumn<Pferd, String> pferdAddToStatistikNameColumn;
    @FXML
    private TableColumn<Jockey, Integer> jockeyAddToStatistikSvnrColumn;
    @FXML
    private TableColumn<Jockey, String> jockeyAddToStatistikNameColumn;


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
    private ObservableList<Pferd> pferdAddToStatistikList = FXCollections.observableArrayList();
    private ObservableList<Jockey> jockeyAddToStatistikList = FXCollections.observableArrayList();

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
            pferdAddToStatistikList.addAll(mainApp.getService().loadAllPferd());
            jockeyAddToStatistikList.addAll(mainApp.getService().loadAllJockey());
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
        pferdAddToStatistikTable.setItems(pferdAddToStatistikList);
        jockeyAddToStatistikTable.setItems(jockeyAddToStatistikList);
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
        rennergebnisseViewChipNrColumn.setCellValueFactory(cellData -> (cellData.getValue().getPferd() == null) ? new ReadOnlyObjectWrapper("unknown") : new ReadOnlyObjectWrapper(cellData.getValue().getPferd().getChip_nr()));
        rennergebnisseViewSvnrColumn.setCellValueFactory(cellData -> (cellData.getValue().getJockey() == null) ? new ReadOnlyObjectWrapper("unknown") : new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getSvnr()));
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
        pferdJockeyPaarRennsimulationSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getSvnr()));
        rennergebnisRennsimulationRennIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRenn_id()));
        rennergebnisRennsimulationChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPferd().getChip_nr()));
        rennergebnisRennsimulationSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getJockey().getSvnr()));
        rennergebnisRennsimulationDurchnittlicheGeschwindigkeitColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getDgeschw()));
        rennergebnisRennsimulationPlatzColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPlatz()));
        pferdAddToStatistikChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getChip_nr()));
        pferdAddToStatistikNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
        jockeyAddToStatistikSvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getSvnr()));
        jockeyAddToStatistikNameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getName()));
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
            return;
        }
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
        logger.info("handlePferdViewBild");
        Pferd selectedPferd = pferdViewTable.getSelectionModel().getSelectedItem();
        if (selectedPferd != null) {
            String basePath = new File("").getAbsolutePath();
            String picture = selectedPferd.getBild();
            basePath = basePath + "/images/" + picture;
            logger.info(basePath);
            boolean okClicked = mainApp.showBild(basePath);
        }
    }

    @FXML
    void handleStatistikCreate() {
        logger.info("handleStatistikCreate");
    }

    @FXML
    void hanldeResetSelection() {
        logger.info("hanldeResetSelection");
    }

    @FXML
    void handlePferdEdit() {
        logger.info("handlePferdEdit");
        Pferd selectedPferd = pferdViewTable.getSelectionModel().getSelectedItem();
        if (selectedPferd != null) {
            boolean okClicked = mainApp.showPferdEdit(selectedPferd);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Pferd gewählt!");
            alert.setHeaderText("Wählen Sie ein Pferd das Sie bearbeiten wollen!");
            alert.showAndWait();
        }
        try {
            List<Pferd> removelist1 = new ArrayList<>();
            List<Pferd> removelist2 = new ArrayList<>();
            List<Pferd> removelist3 = new ArrayList<>();
            List<Rennergebnis> removelist4 = new ArrayList<>();
            for(Pferd p : pferdViewList) {
                removelist1.add(p);
            }
            for(Pferd p : pferdRennergebnisFilterList) {
                removelist2.add(p);
            }
            for(Pferd p : pferdAddToRennsimulationList) {
                removelist3.add(p);
            }
            for(Rennergebnis r : rennergebnisViewList) {
                removelist4.add(r);
            }
            pferdViewList.removeAll(removelist1);
            pferdRennergebnisFilterList.removeAll(removelist2);
            pferdAddToRennsimulationList.removeAll(removelist3);
            rennergebnisViewList.removeAll(removelist4);
            pferdViewList.addAll(mainApp.getService().loadAllPferd());
            pferdRennergebnisFilterList.addAll(mainApp.getService().loadAllPferd());
            pferdAddToRennsimulationList.addAll(mainApp.getService().loadAllPferd());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
        } catch (ServiceException e) {
            Alert eAlert = new Alert(Alert.AlertType.ERROR);
            eAlert.initOwner(mainApp.getPrimaryStage());
            eAlert.setTitle("Fehler beim updaten der Pferde-Listen!");
            eAlert.showAndWait();
            return;
        }
    }

    @FXML
    void handlePferdDelete() {
        logger.info("handlePferdDelete");
        Pferd selectedPferd = pferdViewTable.getSelectionModel().getSelectedItem();
        if(selectedPferd == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Pferd gewählt!");
            alert.setHeaderText("Bitte wählen Sie ein Pferd das Sie löschen wollen!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zustimmung zum Löschen");
        alert.setHeaderText("Wenn Sie das Pferd löschen, werden die Daten des Pferdes nicht mehr abrufbar sein und es kann an keinen Rennsimulationen mehr teilnehmen!");
        alert.setContentText("Wollen Sie das Pferd wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                mainApp.getService().deletePferde(selectedPferd);
                List<Pferd> removelist1 = new ArrayList<>();
                List<Pferd> removelist2 = new ArrayList<>();
                List<Pferd> removelist3 = new ArrayList<>();
                List<Rennergebnis> removelist4 = new ArrayList<>();
                for(Pferd p : pferdViewList) {
                    removelist1.add(p);
                }
                for(Pferd p : pferdRennergebnisFilterList) {
                    removelist2.add(p);
                }
                for(Pferd p : pferdAddToRennsimulationList) {
                    removelist3.add(p);
                }
                for(Rennergebnis r : rennergebnisViewList) {
                    removelist4.add(r);
                }
                pferdViewList.removeAll(removelist1);
                pferdRennergebnisFilterList.removeAll(removelist2);
                pferdAddToRennsimulationList.removeAll(removelist3);
                rennergebnisViewList.removeAll(removelist4);
                pferdViewList.addAll(mainApp.getService().loadAllPferd());
                pferdRennergebnisFilterList.addAll(mainApp.getService().loadAllPferd());
                pferdAddToRennsimulationList.addAll(mainApp.getService().loadAllPferd());
                rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
            } catch (ServiceException e) {
                Alert eAlert = new Alert(Alert.AlertType.ERROR);
                eAlert.initOwner(mainApp.getPrimaryStage());
                eAlert.setTitle("Pferd konnte nicht gelöscht werden!");
                eAlert.setHeaderText("Während dem Löschen ist ein Fehler aufgetreten!");
                eAlert.showAndWait();
                return;
            }
        }
    }

    @FXML
    void handlePferdNew() {
        logger.info("handlePferdNew");
        boolean okClicked = mainApp.showPferdEdit(null);
        try {
            List<Pferd> removelist1 = new ArrayList<>();
            List<Pferd> removelist2 = new ArrayList<>();
            List<Pferd> removelist3 = new ArrayList<>();
            List<Rennergebnis> removelist4 = new ArrayList<>();
            for(Pferd p : pferdViewList) {
                removelist1.add(p);
            }
            for(Pferd p : pferdRennergebnisFilterList) {
                removelist2.add(p);
            }
            for(Pferd p : pferdAddToRennsimulationList) {
                removelist3.add(p);
            }
            for(Rennergebnis r : rennergebnisViewList) {
                removelist4.add(r);
            }
            pferdViewList.removeAll(removelist1);
            pferdRennergebnisFilterList.removeAll(removelist2);
            pferdAddToRennsimulationList.removeAll(removelist3);
            rennergebnisViewList.removeAll(removelist4);
            pferdViewList.addAll(mainApp.getService().loadAllPferd());
            pferdRennergebnisFilterList.addAll(mainApp.getService().loadAllPferd());
            pferdAddToRennsimulationList.addAll(mainApp.getService().loadAllPferd());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
        } catch (ServiceException e) {
            Alert eAlert = new Alert(Alert.AlertType.ERROR);
            eAlert.initOwner(mainApp.getPrimaryStage());
            eAlert.setTitle("Fehler beim updaten der Pferde-Listen!");
            eAlert.showAndWait();
            return;
        }
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
            return;
        }
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
        logger.info("handleJockeyEdit");
        Jockey selectedJockey = jockeyViewTable.getSelectionModel().getSelectedItem();
        if (selectedJockey != null) {
            boolean okClicked = mainApp.showJockeyEdit(selectedJockey);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Jockey gewählt!");
            alert.setHeaderText("Wählen Sie einen Jockey den Sie bearbeiten wollen!");
            alert.showAndWait();
        }
        try {
            List<Jockey> removelist1 = new ArrayList<>();
            List<Jockey> removelist2 = new ArrayList<>();
            List<Jockey> removelist3 = new ArrayList<>();
            List<Rennergebnis> removelist4 = new ArrayList<>();
            for(Jockey j : jockeyViewList) {
                removelist1.add(j);
            }
            for(Jockey j : jockeyRennergebnisFilterList) {
                removelist2.add(j);
            }
            for(Jockey j : jockeyAddToRennsimulationList) {
                removelist3.add(j);
            }
            for(Rennergebnis r : rennergebnisViewList) {
                removelist4.add(r);
            }
            jockeyViewList.removeAll(removelist1);
            jockeyRennergebnisFilterList.removeAll(removelist2);
            jockeyAddToRennsimulationList.removeAll(removelist3);
            rennergebnisViewList.removeAll(removelist4);
            jockeyViewList.addAll(mainApp.getService().loadAllJockey());
            jockeyRennergebnisFilterList.addAll(mainApp.getService().loadAllJockey());
            jockeyAddToRennsimulationList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
        } catch (ServiceException e) {
            Alert eAlert = new Alert(Alert.AlertType.ERROR);
            eAlert.initOwner(mainApp.getPrimaryStage());
            eAlert.setTitle("Fehler beim updaten der Jockey-Listen!");
            eAlert.showAndWait();
            return;
        }
    }

    @FXML
    void handleJockeyDelete() {
        logger.info("handleJockeyDelete");
        Jockey selectedJockey = jockeyViewTable.getSelectionModel().getSelectedItem();
        if(selectedJockey == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Jockey gewählt!");
            alert.setHeaderText("Bitte wählen Sie einen Jockey den Sie löschen wollen!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zustimmung zum Löschen");
        alert.setHeaderText("Wenn Sie den Jockey löschen, werden die Daten des Jockey nicht mehr abrufbar sein und er kann an keinen Rennsimulationen mehr teilnehmen!");
        alert.setContentText("Wollen Sie den Jockey wirklich löschen?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                mainApp.getService().deleteJockey(selectedJockey);
                List<Jockey> removelist1 = new ArrayList<>();
                List<Jockey> removelist2 = new ArrayList<>();
                List<Jockey> removelist3 = new ArrayList<>();
                List<Rennergebnis> removelist4 = new ArrayList<>();
                for(Jockey j : jockeyViewList) {
                    removelist1.add(j);
                }
                for(Jockey j : jockeyRennergebnisFilterList) {
                    removelist2.add(j);
                }
                for(Jockey j : jockeyAddToRennsimulationList) {
                    removelist3.add(j);
                }
                for(Rennergebnis r : rennergebnisViewList) {
                    removelist4.add(r);
                }
                jockeyViewList.removeAll(removelist1);
                jockeyRennergebnisFilterList.removeAll(removelist2);
                jockeyAddToRennsimulationList.removeAll(removelist3);
                rennergebnisViewList.removeAll(removelist4);
                jockeyViewList.addAll(mainApp.getService().loadAllJockey());
                jockeyRennergebnisFilterList.addAll(mainApp.getService().loadAllJockey());
                jockeyAddToRennsimulationList.addAll(mainApp.getService().loadAllJockey());
                rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
            } catch (ServiceException e) {
                Alert eAlert = new Alert(Alert.AlertType.ERROR);
                eAlert.initOwner(mainApp.getPrimaryStage());
                eAlert.setTitle("Jockey konnte nicht gelöscht werden!");
                eAlert.setHeaderText("Während dem Löschen ist ein Fehler aufgetreten!");
                eAlert.showAndWait();
                return;
            }
        }
    }

    @FXML
    void handleJockeyNew() {
        logger.info("handleJockeyNew");
        boolean okClicked = mainApp.showJockeyEdit(null);
        try {
            List<Jockey> removelist1 = new ArrayList<>();
            List<Jockey> removelist2 = new ArrayList<>();
            List<Jockey> removelist3 = new ArrayList<>();
            List<Rennergebnis> removelist4 = new ArrayList<>();
            for(Jockey j : jockeyViewList) {
                removelist1.add(j);
            }
            for(Jockey j : jockeyRennergebnisFilterList) {
                removelist2.add(j);
            }
            for(Jockey j : jockeyAddToRennsimulationList) {
                removelist3.add(j);
            }
            for(Rennergebnis r : rennergebnisViewList) {
                removelist4.add(r);
            }
            jockeyViewList.removeAll(removelist1);
            jockeyRennergebnisFilterList.removeAll(removelist2);
            jockeyAddToRennsimulationList.removeAll(removelist3);
            rennergebnisViewList.removeAll(removelist4);
            jockeyViewList.addAll(mainApp.getService().loadAllJockey());
            jockeyRennergebnisFilterList.addAll(mainApp.getService().loadAllJockey());
            jockeyAddToRennsimulationList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
        } catch (ServiceException e) {
            Alert eAlert = new Alert(Alert.AlertType.ERROR);
            eAlert.initOwner(mainApp.getPrimaryStage());
            eAlert.setTitle("Fehler beim updaten der Jockey-Listen!");
            eAlert.showAndWait();
            return;
        }
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
            return;
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
            rennergebnisFilterRennergebnisTable.getSelectionModel().select(null);
            pferdFilterRennergebnisTable.getSelectionModel().select(null);
            jockeyFilterRennergebnisTable.getSelectionModel().select(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Es konnte nicht nach der angegebnen Kombination an Parametern gesucht werden!");
            alert.showAndWait();
            return;
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
        logger.info("handleRennersimulationAddPair");
        Pferd selectedPferd = pferdAddToRennsimulationTable.getSelectionModel().getSelectedItem();
        Jockey selectedJockey = jockeyAddToRennsimulationTable.getSelectionModel().getSelectedItem();
        if(selectedPferd == null || selectedJockey == null) {
            pferdAddToRennsimulationTable.getSelectionModel().select(null);
            jockeyAddToRennsimulationTable.getSelectionModel().select(null);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Kein Pferd & Jockey ausgewählt!");
            alert.setHeaderText("Bitte wählen Sie sowhol ein Pferd als auch einen Jockey!");
            alert.showAndWait();
            return;
        }
        for(PferdJockeyPair pair : pferdJockeyPaarRennsimulationList) {
            if(pair.getJockey().equals(selectedJockey) || pair.getPferd().equals(selectedPferd)) {
                pferdAddToRennsimulationTable.getSelectionModel().select(null);
                jockeyAddToRennsimulationTable.getSelectionModel().select(null);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Keine doppelten Antritte!");
                alert.setHeaderText("Bitte wählen Sie so aus, dass jeder Jockey und jedes Pferd maximal einmal in einer Rennsimulation teilnehmen!");
                alert.showAndWait();
                return;
            }
        }
        pferdJockeyPaarRennsimulationList.add(new PferdJockeyPair(selectedPferd,selectedJockey));
        pferdAddToRennsimulationTable.getSelectionModel().select(null);
        jockeyAddToRennsimulationTable.getSelectionModel().select(null);
    }

    @FXML
    void handleRennsimulationReset() {
        logger.info("handleRennsimulationReset");
        List<PferdJockeyPair> removeList1 = new ArrayList<>();
        List<Rennergebnis> removeList2 = new ArrayList<>();
        for(PferdJockeyPair pair : pferdJockeyPaarRennsimulationList) {
            removeList1.add(pair);
        }
        for(Rennergebnis rennergebnis : rennergebnisRennsimulationList) {
            removeList2.add(rennergebnis);
        }
        pferdJockeyPaarRennsimulationList.removeAll(removeList1);
        rennergebnisRennsimulationList.removeAll(removeList2);
    }

    @FXML
    void handleRennsimulationDurchfuehren() {
        logger.info("handleRennsimulationDurchfuehren");
        if(pferdJockeyPaarRennsimulationList == null || pferdJockeyPaarRennsimulationList.size()==0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Keine Paare gewählt!");
            alert.setHeaderText("Bitte wählen Sie aus welche Pferd-Jockey-Paare an der Rennsimulation teilnehmen sollen!");
            alert.showAndWait();
            return;
        }
        for(PferdJockeyPair pair : pferdJockeyPaarRennsimulationList) {
            try {
                Jockey jockey = mainApp.getService().loadJockey(new JockeyID(pair.getJockey().getSvnr()));
                Pferd pferd = mainApp.getService().loadPferd(new PferdID(pair.getPferd().getChip_nr()));
                if(jockey == null || pferd == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Pferd oder Jockey gelöscht!");
                    alert.setHeaderText("Ein Pferd oder Jockey die an der Rennsimulation hätten teilnehmen sollen wurde gelöscht! Bitte führen Sie einen Reset durch und beginnen Sie von vorne!");
                    alert.showAndWait();
                    return;
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        int renn_id = -1;
        try {
            renn_id = mainApp.getService().getFreeRennID().getRenn_id();
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Konnte nicht eine freie Renn-ID finden!");
            alert.setHeaderText("Ein Fehler ist aufgetreten beim suchen einer freien Renn-ID!");
            alert.showAndWait();
            return;
        }
        Map<Pferd, Jockey> participants = new HashMap<>();
        for(PferdJockeyPair pair : pferdJockeyPaarRennsimulationList) {
            participants.put(pair.getPferd(),pair.getJockey());
        }
        RennsimulationData data = new RennsimulationData(renn_id,participants);
        List<Rennergebnis> ergebnisse = null;
        try {
            ergebnisse = mainApp.getService().doRennsimulation(data);
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Konnte nicht durchführen!");
            alert.setHeaderText("Ein Fehler ist aufgetreten beim Durchführen der Rennsimulation!");
            alert.showAndWait();
        }
        rennergebnisRennsimulationList.addAll(ergebnisse);
        try {
            List<Rennergebnis> removelist1 = new ArrayList<>();
            List<RennID> removelist2 = new ArrayList<>();
            for(Rennergebnis r : rennergebnisViewList) {
                removelist1.add(r);
            }
            for(RennID r : rennergebnisRennergebnisFilterList) {
                removelist2.add(r);
            }
            rennergebnisViewList.removeAll(removelist1);
            rennergebnisRennergebnisFilterList.removeAll(removelist2);
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
            rennergebnisRennergebnisFilterList.addAll(mainApp.getService().getAllRennIDs());
        } catch (ServiceException e) {
            logger.error("reload of rennergebnis-tables failse");
            e.printStackTrace();
        }
    }

}
