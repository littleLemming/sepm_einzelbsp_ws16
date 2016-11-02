package sepm.ws16.e1327450.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.*;
import sepm.ws16.e1327450.service.ServiceException;

import java.sql.Date;

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
    private ObservableList<Jockey> jockeyRennergebnisFilterList = FXCollections.observableArrayList();
    private ObservableList<RennID> rennergebnisRennergebnisFilterList = FXCollections.observableArrayList();


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        try {
            pferdViewList.addAll(mainApp.getService().loadAllPferd());
            jockeyViewList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisViewList.addAll(mainApp.getService().loadAllRennergebnis());
            pferdRennergebnisFilterList.addAll(mainApp.getService().loadAllPferd());
            jockeyRennergebnisFilterList.addAll(mainApp.getService().loadAllJockey());
            rennergebnisRennergebnisFilterList.addAll(mainApp.getService().getAllRennIDs());
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
    }

    @FXML
    private void initialize() {
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
    }

    @FXML
    void handlePferdSearchByName() {

    }

    @FXML
    void handlePferdShowAll() {

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

    }

    @FXML
    void handleJockeyShowAll() {

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

    }

    @FXML
    void handleRennergebnisSearchByPlatz() {

    }

    @FXML
    void handleRennergebnisShowAll() {

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
