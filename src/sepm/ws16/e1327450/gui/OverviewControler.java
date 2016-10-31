package sepm.ws16.e1327450.gui;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;

public class OverviewControler {

    private MainApp mainApp;

    @FXML
    private Button searchJockeyButton;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisPlatzColumn;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchPferdButton;
    @FXML
    private TableColumn<Pferd, String> pferdNameColumn;
    @FXML
    private TableView<Rennergebnis> rennergebnisTable;
    @FXML
    private TableColumn<Pferd, String> pferdChipNrColumn;
    @FXML
    private TableColumn<Jockey, String> jockeyNameColumn;
    @FXML
    private TableColumn<Rennergebnis, String> rennergebnisChipNrColumn;
    @FXML
    private TableColumn<Rennergebnis, Double> rennergebnisGeschwColumn;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisRennIdColumn;
    @FXML
    private Button statistikButton;
    @FXML
    private TableView<Jockey> jockeyTable;
    @FXML
    private Button editButton;
    @FXML
    private TableColumn<Jockey, Integer> jockeySvnrColumn;
    @FXML
    private TableView<Pferd> pferdTable;
    @FXML
    private Button viewButton;
    @FXML
    private Button rennsimulationButton;
    @FXML
    private TableColumn<Rennergebnis, Integer> rennergebnisSvnrColumn;
    @FXML
    private Button searchRennergebnisButton;

    @FXML
    private void initialize() {
        pferdChipNrColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getChip_nr()));
        pferdNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        jockeySvnrColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getSvnr()));
        jockeyNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        rennergebnisRennIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getRenn_id()));
        rennergebnisChipNrColumn.setCellValueFactory(cellData -> (cellData.getValue() == null) ? new ReadOnlyStringWrapper("unknown") : new ReadOnlyStringWrapper(cellData.getValue().getPferd().getChip_nr()));
        rennergebnisSvnrColumn.setCellValueFactory(cellData -> (cellData.getValue() == null) ? new SimpleIntegerProperty(-1).asObject() : new SimpleIntegerProperty(cellData.getValue().getJockey().getSvnr()).asObject());
        rennergebnisGeschwColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getGeschw()));
        rennergebnisPlatzColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPlatz()));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        pferdTable.setItems(mainApp.getPferdList());
        jockeyTable.setItems(mainApp.getJockeyList());
        rennergebnisTable.setItems(mainApp.getRennergebnisList());
    }

    @FXML
    void handleView() {

    }

    @FXML
    void handleEdit() {

    }

    @FXML
    void handleDelete() {

    }

    @FXML
    void handleRennsimulation() {

    }

    @FXML
    void handleStatistik() {

    }

    @FXML
    void handleSearchPferd() {

    }

    @FXML
    void handleSearchJockey() {

    }

    @FXML
    void handleSearchRennergebnis() {

    }

}
