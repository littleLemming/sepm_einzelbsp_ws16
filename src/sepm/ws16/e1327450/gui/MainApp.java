package sepm.ws16.e1327450.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sepm.ws16.e1327450.domain.Jockey;
import sepm.ws16.e1327450.domain.Pferd;
import sepm.ws16.e1327450.domain.Rennergebnis;
import sepm.ws16.e1327450.service.ImlService;
import sepm.ws16.e1327450.service.Service;
import sepm.ws16.e1327450.service.ServiceException;

import java.io.IOException;

public class MainApp extends Application {

    final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Service service;

    private ObservableList<Pferd> pferdList = FXCollections.observableArrayList();
    private ObservableList<Jockey> jockeyList = FXCollections.observableArrayList();
    private ObservableList<Rennergebnis> rennergebnisList = FXCollections.observableArrayList();

    public MainApp() {
        logger.info("MainApp()");
        try {
            service = new ImlService();
            pferdList.addAll(service.loadAllPferd());
            jockeyList.addAll(service.loadAllJockey());
            rennergebnisList.addAll(service.loadAllRennergebnis());
        } catch (ServiceException e) {
            logger.error("SETUP OF MAINAPP FAILED");
            e.printStackTrace();
        }

    }

    public Service getService() {
        return service;
    }

    public ObservableList<Pferd> getPferdList() {
        return pferdList;
    }

    public ObservableList<Jockey> getJockeyList() {
        return jockeyList;
    }

    public ObservableList<Rennergebnis> getRennergebnisList() {
        return rennergebnisList;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("start()");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Wendy’s Rennpferde");

        initRootLayout();

        showOverview();
    }

    public void initRootLayout() {
        logger.info("initRootLayout");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOverview() {
        logger.info("showOverview()");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Overview.fxml"));
            AnchorPane overview = (AnchorPane) loader.load();

            rootLayout.setCenter(overview);

            OverviewControler controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
