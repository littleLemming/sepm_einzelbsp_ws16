package sepm.ws16.e1327450.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewControler {

    private MainApp mainApp;

    @FXML
    private Button searchJockeyButton;
    @FXML
    private TableColumn<?, ?> rennergebnisPlatzColumn;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchPferdButton;
    @FXML
    private TableColumn<?, ?> pferdNameColumn;
    @FXML
    private TableView<?> rennergebnisTable;
    @FXML
    private TableColumn<?, ?> pferdChipNrColumn;
    @FXML
    private TableColumn<?, ?> jockeyNameColumn;
    @FXML
    private TableColumn<?, ?> rennergebnisChipNrColumn;
    @FXML
    private TableColumn<?, ?> rennergebnisGeschwColumn;
    @FXML
    private TableColumn<?, ?> rennergebnisRennIdColumn;
    @FXML
    private Button statistikButton;
    @FXML
    private TableView<?> jockeyTable;
    @FXML
    private Button editButton;
    @FXML
    private TableColumn<?, ?> jockeySvnrColumn;
    @FXML
    private TableView<?> pferdTable;
    @FXML
    private Button viewButton;
    @FXML
    private Button rennsimulationButton;
    @FXML
    private TableColumn<?, ?> rennergebnisSvnrColumn;
    @FXML
    private Button searchRennergebnisButton;

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
