package sepm.ws16.e1327450.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.PferdJockeyPair;
import sepm.ws16.e1327450.domain.Rennergebnis;

import java.sql.Date;

public class OverviewControler {

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
    private TableColumn<Rennergebnis, Integer> rennergebnisFilterRennergebnisRennIdTable;
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
    private TableColumn<Rennergebnis, Double> rennergebnisseViewGluesColumn;
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
    private TableView<Rennergebnis> rennergebnisFilterRennergebnisTable;
    @FXML
    private TextField rennergebnisSearchPlatzTextField;
    @FXML
    private TextField pferdSearchNameTextField;
    @FXML
    private TableColumn<PferdJockeyPair, Integer> pferdJockeyPaarRennsimulationChipNrColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennsimulationSvnrColumn;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {

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
