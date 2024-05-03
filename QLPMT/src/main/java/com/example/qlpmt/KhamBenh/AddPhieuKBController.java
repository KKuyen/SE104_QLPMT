package com.example.qlpmt.KhamBenh;

import Model.PhieuKhamBenh;
import com.example.qlpmt.HelloApplication;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class AddPhieuKBController implements Initializable {
    @FXML
    private VBox pkb_layout;

    @FXML
    private MFXButton HuyBtn;

    @FXML
    private MFXButton XongBtn;
    @FXML
    private MFXButton add_butt;
    @FXML
    private ImageView close;

    @FXML
    private MFXTableView<ThuocPKB> table_thuoc;
    private ObservableList<ThuocPKB> list;
    double x,y=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        close.setOnMouseClicked(event -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
        HuyBtn.setOnAction((ActionEvent event) -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
        XongBtn.setOnAction((ActionEvent event) -> {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        });
        add_butt.setOnAction((ActionEvent event) -> {
            try {
                AddThuoc(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        setupContextMenu();
        setupPaginated();

        //chia deu kich thuoc cac cot de vua voi chieu rong cua tableview
//        double tableViewWidth = table_thuoc.getPrefWidth();
//        int numberOfColumns = table_thuoc.getTableColumns().size();
//        for (MFXTableColumn column : table_thuoc.getTableColumns()) {
//            column.setPrefWidth(tableViewWidth / numberOfColumns);
//        }

       table_thuoc.autosizeColumnsOnInitialization();
        table_thuoc.getTableColumns().get(4).setPrefWidth(200);
        //Tu dong dieu chinh kich thuoc cac cot de phu hop voi noi dung
       //When.onChanged(table_thuoc.currentPageProperty()).then((oldValue, newValue) -> table_thuoc.autosizeColumns()).listen();


    }
    private void setupPaginated() {

        //Tao cac cot cua tableview
        MFXTableColumn<ThuocPKB> stt = new MFXTableColumn<>("STT", false, Comparator.comparing(ThuocPKB::getStt));
        MFXTableColumn<ThuocPKB> tenthuoc = new MFXTableColumn<>("Thuốc", false, Comparator.comparing(ThuocPKB::getTenThuoc));
        MFXTableColumn<ThuocPKB> donvi = new MFXTableColumn<>("Đơn vị", false, Comparator.comparing(ThuocPKB::getDonVi));
        MFXTableColumn<ThuocPKB> soluong = new MFXTableColumn<>("Số lượng", false, Comparator.comparing(ThuocPKB::getSoLuong));
        MFXTableColumn<ThuocPKB> cachdung = new MFXTableColumn<>("Cách dùng", false, Comparator.comparing(ThuocPKB::getCachDung));

        stt.setRowCellFactory(khambenh -> new MFXTableRowCell<>(ThuocPKB::getStt));
        tenthuoc.setRowCellFactory(khambenh -> new MFXTableRowCell<>(ThuocPKB::getTenThuoc));
        donvi.setRowCellFactory(khambenh -> new MFXTableRowCell<>(ThuocPKB::getDonVi));
        soluong.setRowCellFactory(khambenh -> new MFXTableRowCell<>(ThuocPKB::getSoLuong));
        cachdung.setRowCellFactory(khambenh -> new MFXTableRowCell<>(ThuocPKB::getCachDung));

        //Them cac cot vao tableview
        table_thuoc.getTableColumns().addAll(stt, tenthuoc, donvi, soluong, cachdung);

        //Them cac filter cho tableview
        table_thuoc.getFilters().addAll(
                //new StringFilter<>("STT", (ThuocPKB::getStt) ),
                new StringFilter<>("Thuốc", ThuocPKB::getTenThuoc),
                new StringFilter<>("Đơn vị", ThuocPKB::getDonVi),
                //new StringFilter<>("Số lượng", ThuocPKB::getSoLuong),
                new StringFilter<>("Cách dùng", ThuocPKB::getCachDung)
        );

        //Them du lieu vao tableview
        setData();
        table_thuoc.setItems(list);


    }

    public void SuaThuoc(ActionEvent events) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("sua_thuoc_pkb.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        Scene scene = new Scene(root, 320, 340);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void AddThuoc(ActionEvent events) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add_thuoc_pkb.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        Scene scene = new Scene(root, 320, 340);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void setupContextMenu(){
        MFXContextMenu contextMenu = new MFXContextMenu(table_thuoc);
        MFXContextMenuItem edit = new MFXContextMenuItem("Chỉnh sửa");
        MFXContextMenuItem delete = new MFXContextMenuItem("Xóa");
        contextMenu.getItems().addAll(edit, delete);
        edit.setStyle("-fx-text-fill: #2264D1; -fx-font-size: 16px; -fx-font-family: 'Times New Roman'");
        delete.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-family: 'Times New Roman'");
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Handle the click event here
                try {
                    SuaThuoc(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Handle the click event here
                System.out.println("Xóa clicked");
            }
        });
        table_thuoc.setTableRowFactory(phieukhambenh -> {
            MFXTableRow<ThuocPKB> row = new MFXTableRow<>(table_thuoc, new ThuocPKB(0, "", "", 0, ""));
            row.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
                contextMenu.show(row, event.getScreenX(), event.getScreenY());
                event.consume();
            });
            row.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    contextMenu.hide();
                }
            });
            return row;
        });
    }
    public void setData(){
        list = FXCollections.observableArrayList(
               new ThuocPKB(1, "Thuốc A", "Viên", 10, "Uống 3 lần/ngày"),
                    new ThuocPKB(2, "Thuốc B", "Viên", 20, "Uống 2 lần/ngày"),
                    new ThuocPKB(3, "Thuốc C", "Viên", 30, "Uống 1 lần/ngày"),
                    new ThuocPKB(4, "Thuốc D", "Viên", 40, "Uống 4 lần/ngày"),
                    new ThuocPKB(5, "Thuốc E", "Viên", 50, "Uống 5 lần/ngày"),
                    new ThuocPKB(6, "Thuốc F", "Viên", 60, "Uống 6 lần/ngày"),
                    new ThuocPKB(7, "Thuốc G", "Viên", 70, "Uống 7 lần/ngày"),
                    new ThuocPKB(8, "Thuốc H", "Viên", 80, "Uống 8 lần/ngày"),
                    new ThuocPKB(9, "Thuốc I", "Viên", 90, "Uống 9 lần/ngày"),
                    new ThuocPKB(10, "Thuốc K", "Viên", 100, "Uống 10 lần/ngày"),
                    new ThuocPKB(11, "Thuốc L", "Viên", 110, "Uống 11 lần/ngày"),
                    new ThuocPKB(12, "Thuốc M", "Viên", 120, "Uống 12 lần/ngày"),
                    new ThuocPKB(13, "Thuốc N", "Viên", 130, "Uống 13 lần/ngày"),
                    new ThuocPKB(14, "Thuốc O", "Viên", 140, "Uống 14 lần/ngày")
        );
    }

}
