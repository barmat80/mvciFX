package com.maemlab.mvcifx.examples.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NirvanaModel {

    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleIntegerProperty age;

    private static NirvanaModel createFromPOJO(String name, String surname, int age) {
        var m = new NirvanaModel();
        m.load(name, surname, age);
        return m;
    }

    private void load(String name, String surname, int age) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.age = new SimpleIntegerProperty(age);
    }

    public static NirvanaModel toModel(Nirvana nirvana) {
        return createFromPOJO(nirvana.getName(), nirvana.getSurname(), nirvana.getAge());
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }
}
