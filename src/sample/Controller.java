package sample;

/**
 * @versio 1.0
 * class untuk mengatur load tiap scene fxml
 */

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Tiket;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private BorderPane border;

    /**
     * Method untuk load scene yang ada pada menu user
     * @param scene untuk mendapatkan nama file dari scene dengan format fxml
     */
    public void loadScene(String scene){
        Parent root = null;
        try{
            root= FXMLLoader.load(getClass().getResource(scene + ".fxml"));
        }catch(Exception e) {
            System.out.println(e);
        }
        border.setCenter(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //showOptionMaskapai();
        //showTiket();
    }

    @FXML
    private Button tiket;

    @FXML
    private Button historybtn;

    @FXML
    private Button helpbtn;

    @FXML
    private Button aboutbtn;







    /**
     * Method untuk load scene buy ticket
     * @param actionEvent
     */
    public void buyticket(javafx.event.ActionEvent actionEvent) {
        loadScene("buyTicket");

    }

    /**
     * Method untuk close aplikasi ketika menekan icon X
     * @param mouseEvent
     */
    public void close(MouseEvent mouseEvent) {
        Stage stage = (Stage) border.getScene().getWindow();
        stage.close();

    }

    /**
     * Method untuk load scene history
     * @param actionEvent
     */
    public void history(ActionEvent actionEvent) {
       loadScene("history");
    }

    /**
     * Method untuk load scene help
     * @param actionEvent
     */
    public void help(ActionEvent actionEvent) {
        loadScene("help");
    }

    /**
     * Method untuk load scene about yang berisi data pengembang aplikasi
     * @param actionEvent
     */
    public void about(ActionEvent actionEvent) {
        loadScene("about");
    }

    /**
     * Method untuk menampilkan detail informasi tentang Fikri
     * @param actionEvent
     */
    public void detailsFikri(ActionEvent actionEvent) {
        Parent root = null;
        Stage stage = new Stage();

        try{
            root= FXMLLoader.load(getClass().getResource("aboutFikri.fxml"));
        }catch(Exception e) {
            System.out.println(e);
        }
        Scene sceneFikri = new Scene(root);
        sceneFikri.setFill(Color.TRANSPARENT);
        stage.setScene(sceneFikri);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * Method untuk menampilkan detail informasi tentang Syahrul
     * @param actionEvent
     */
    public void detailsSyahrul(ActionEvent actionEvent) {
        Parent root = null;
        Stage stage = new Stage();

        try{
            root= FXMLLoader.load(getClass().getResource("aboutSyahrul.fxml"));
        }catch(Exception e) {
            System.out.println(e);
        }
        Scene sceneFikri = new Scene(root);
        sceneFikri.setFill(Color.TRANSPARENT);
        stage.setScene(sceneFikri);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    /**
     * Method untuk menampilkan detail informasi tentang Riyan
     * @param actionEvent
     */
    public void detailsRiyan(ActionEvent actionEvent) {
        Parent root = null;
        Stage stage = new Stage();

        try{
            root= FXMLLoader.load(getClass().getResource("aboutRiyan.fxml"));
        }catch(Exception e) {
            System.out.println(e);
        }
        Scene sceneFikri = new Scene(root);
        sceneFikri.setFill(Color.TRANSPARENT);
        stage.setScene(sceneFikri);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    /**
     * Method untuk mengatur hover ketika cursor menyentuh button
     * @param mouseEvent
     */
    public void hover(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == tiket){
            tiket.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: #bdc3c7;");
        }else if (mouseEvent.getSource() == historybtn){
            historybtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: #bdc3c7;");
        }else if (mouseEvent.getSource() == helpbtn){
            helpbtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: #bdc3c7;");
        }else if (mouseEvent.getSource() == aboutbtn){
            aboutbtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: #bdc3c7;");
        }
    }


    /**
     * Method untuk menghilangkan hover ketika cursor menjauh dari button
     * @param mouseEvent
     */
    public void unhover(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == tiket){
            tiket.setStyle("-fx-background-color: transparent;");
        }else if (mouseEvent.getSource() == historybtn){
            historybtn.setStyle("-fx-background-color: transparent;");
        }else if (mouseEvent.getSource() == helpbtn){
            helpbtn.setStyle("-fx-background-color: transparent;");
        }else if (mouseEvent.getSource() == aboutbtn){
            aboutbtn.setStyle("-fx-background-color: transparent;");
        }
    }



    @FXML
    private ComboBox<String> listMaskapai;

    @FXML
    private TableView<Tiket> tableTiket;

    @FXML
    private TableColumn<Tiket, Integer> colId;

    @FXML
    private TableColumn<Tiket, String> colMaskapai;

    @FXML
    private TableColumn<Tiket, String> colTujuan;

    @FXML
    private TableColumn<Tiket, String> colWaktu;

    @FXML
    private TableColumn<Tiket, String> colKelas;

    @FXML
    private TableColumn<Tiket, Integer> colStok;

    String valueMaskapai;

    /*private void showOptionMaskapai() {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM maskapai");
            while (rs.next()){
                list.add(rs.getString("namaMaskapai"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //this.listMaskapai.setItems(null);
        this.listMaskapai.setItems(list);


    }*/

    public ObservableList<Tiket> getTiketList(){
        ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection =connectionClass.getConnection();
        String sql = "SELECT * FROM tiket";
        Statement st;
        ResultSet rs;
        try {

            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Tiket tiket;
            while (rs.next()){
                tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"),  rs.getString("kelas"), rs.getString("namaMaskapai"));
                tiketList.add(tiket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tiketList;
    }

   /* public void showTiket(){
        ObservableList<Tiket> list = getTiketList();
        this.colId.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("idTiket"));
        this.colMaskapai.setCellValueFactory(new PropertyValueFactory<Tiket, String>("namaMaskapai"));
        this.colTujuan.setCellValueFactory(new PropertyValueFactory<Tiket, String>("tujuan"));
        this.colWaktu.setCellValueFactory(new PropertyValueFactory<Tiket, String>("waktu"));
        this.colStok.setCellValueFactory(new PropertyValueFactory<Tiket, Integer>("stok"));
        this.colKelas.setCellValueFactory(new PropertyValueFactory<Tiket, String>("kelas"));
        this.tableTiket.setItems(list);
    }*/


    @FXML
    void order(ActionEvent event) {

    }


    @FXML
    void selectMaskapai(ActionEvent event) {
        valueMaskapai = listMaskapai.getSelectionModel().getSelectedItem().toString();
    }



    @FXML
    void search(ActionEvent event) {
    String k = colMaskapai.getCellData(2);
    if (valueMaskapai == null){
        ObservableList<Tiket> list = getTiketList();
        this.tableTiket.setItems(list);
        System.out.println(k);
    }else {
        ObservableList<Tiket> tiketList = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sql = "Select * FROM tiket where namaMaskapai LIKE '%"+valueMaskapai+"%'";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            Tiket tiket;
            while (rs.next()){
                tiket = new Tiket(rs.getInt("idTiket"), rs.getString("tujuan"), rs.getString("waktu"), rs.getInt("stok"),  rs.getString("kelas"), rs.getString("namaMaskapai"));
                tiketList.add(tiket);
            }
            this.tableTiket.setItems(tiketList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(valueMaskapai);
    }


    }





















}


