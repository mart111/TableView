package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Person {

    private SimpleStringProperty name;
    private SimpleStringProperty surname;

    public Person(String name, String surname) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public static Image getImage() throws Exception {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        InputStream ip = new FileInputStream(selectedFile.getPath());
        return new Image(ip);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }
}

