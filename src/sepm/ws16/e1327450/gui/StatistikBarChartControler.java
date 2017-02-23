package sepm.ws16.e1327450.gui;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Statistik;
import sepm.ws16.e1327450.domain.StatistikData;

public class StatistikBarChartControler {

    final static Logger logger = LoggerFactory.getLogger(StatistikBarChartControler.class);

    @FXML
    BarChart<String, Number> barChart;

    private Statistik statistik;
    private Stage dialogStage;
    private boolean okClicked = false;
    private StatistikData statistikData;

    public void setStatistikData(StatistikData statistikData) {
        this.statistikData = statistikData;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setStatistik(Statistik statistik) {
        logger.info("setStatistik");
        this.statistik = statistik;

        String title = "Statistik für: ";
        if (statistikData.getChip_nr() != -1) {
            title += "Pferd mit Chip-Nummer " + statistikData.getChip_nr();
        }
        if (statistikData.getChip_nr() != -1 && statistikData.getSvnr() != -1) {
            title += " und ";
        }
        if (statistikData.getSvnr() != -1) {
            title += "Jockey mit SVNR " + statistikData.getSvnr();
        }

        barChart.setTitle(title);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Plätze");
        for (int i : statistik.getStatistik().keySet()) {
            series1.getData().add(new XYChart.Data<>(""+i, statistik.getStatistik().get(i)));
        }

        barChart.getData().addAll(series1);

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
    }

}