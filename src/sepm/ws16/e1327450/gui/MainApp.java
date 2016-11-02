package sepm.ws16.e1327450.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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

    public MainApp() {
        logger.info("MainApp()");
        try {
            service = new ImlService();
        } catch (ServiceException e) {
            logger.error("SETUP OF MAINAPP FAILED");
            e.printStackTrace();
        }

    }

    public Service getService() {
        return service;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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

    boolean showJockeyEdit(Jockey jockey) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("JockeyEditView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            if(jockey != null) {
                dialogStage.setTitle("Jockey bearbeiten");
            } else {
                dialogStage.setTitle("Jockey erstellen");
            }
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            JockeyEditViewControler controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setService(service);
            controller.setJockey(jockey);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
