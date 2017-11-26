package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TableView<Person> tableView = new TableView<>();
    @FXML
    TableColumn<Person, String> tableColumnName;
    @FXML
    TableColumn<Person, String> tableColumnSurname;
    @FXML
    Button button;
    @FXML
    ImageView imageView = new ImageView();
    @FXML
    ListView<String> listView;
    ObservableList<Person> listPerson = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList();
    private int newValueIndex;
    private String newValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.setEditable(true);
        listView.setEditable(true);
        initPersonList();
        tableView.setItems(listPerson);
        initCulumns();
        initDetails();

    }

    public void showPersonDetails(Person person) throws Exception {
        if (list.contains(person.getName() + " " + person.getSurname())) {
            return;
        }

        list.add(person.getName() + " " + person.getSurname());
        listView.setStyle("-fx-font-size: 20px");
        listView.setItems(list);
    }

    public void initPersonList() {
        listPerson.addAll(new Person("Martin", "Knyazyan"),
                new Person("Java", "Oracle"),
                new Person("Tim", "Cook"),
                new Person("Steve", "Jobs"));
    }

    private void changedList() {
        list.remove(newValueIndex);
        list.add(newValueIndex, newValue);
        listView.setItems(list);
        listView.refresh();
    }

    public void initCulumns() {
        tableColumnName.setCellValueFactory(param -> param.getValue().nameProperty());
        tableColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnName.setOnEditCommit(event -> {
            (event.getTableView().getItems().get(
                    event.getTablePosition().getRow())).setName(event.getNewValue());
            newValue = event.getNewValue();
            newValueIndex = event.getTablePosition().getRow();
            System.out.println(listView.getItems().get(newValueIndex));
            System.out.println(newValueIndex);
            changedList();
        });
        tableColumnSurname.setCellValueFactory(param -> param.getValue().surnameProperty());
        tableColumnSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnSurname.setOnEditCommit(event -> {
            (event.getTableView().getItems().get(
                    event.getTablePosition().getRow())).setSurname(event.getNewValue());
        });

    }

    public void initDetails() {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                showPersonDetails(newValue);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
