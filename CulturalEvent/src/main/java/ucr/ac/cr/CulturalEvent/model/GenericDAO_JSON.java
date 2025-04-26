/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Johan Brenes
 */
public class GenericDAO_JSON<T> {

    //atributos
    private String fileName;
    private Gson gson;
    private Type type;

    //constructor
    public GenericDAO_JSON(String fileName, Type type) {
        this.fileName = fileName;
        this.type = type;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }//endContructor

    public ArrayList<T> getAll() {
        try (FileReader reader = new FileReader(fileName)) {
            T[] elements = gson.fromJson(reader, type);
            if (elements == null) {
                return new ArrayList<>();
            } else {
                return new ArrayList<>(Arrays.asList(elements));
            }
        } catch (IOException ioe) {
            return new ArrayList<>();
        }
    }//endGetAll

    private void addElements(ArrayList<T> elements) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(elements.toArray(), writer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }//endAddElement

    public boolean save(T element) {
        ArrayList<T> elements = this.getAll();
        for (int i = 0; i < elements.size(); i++) {
            T e = elements.get(i);
            if (findById(element) == this.findById(e)) {
                return false;
            }//endIf
        }//endFor
        if (elements.add(element)) {
            addElements(elements);
            return true;
        }
        return false;
    }//endSave

    public boolean update(T element) {
        ArrayList<T> elements = this.getAll();
        for (int i = 0; i < elements.size(); i++) {
            T e = elements.get(i);
            if (findById(element) == findById(e)) {
                elements.set(i, element);
                addElements(elements);
                return true;
            }//endIf
        }//endFor
        return false;
    }//endUpDate

    public boolean remove(int id) {
        ArrayList<T> elements = this.getAll();
        for (int i = 0; i < elements.size(); i++) {
            T e = elements.get(i);
            if (findById(e) == id) {
                elements.remove(i);
                addElements(elements);
                return true;
            }//endIf
        }//endFor
        return false;
    }//endRemove

    public int findById(T element) {
        ArrayList<T> elements = this.getAll();
        if (elements.size() >= 1) {
            if (element instanceof User) {
                return ((User) element).getId();
//            } else if (element instanceof UserCS) {
//                return ((UserCS) element).getID();
            }
        }//endIf
        return -1;
    }//endFindById

    public T getElement(int id) {
        ArrayList<T> elements = this.getAll();
        for (int i = 0; i < elements.size(); i++) {
            T e = elements.get(i);
            if (findById(e) == id) {
                return elements.get(i);
            }//endIf
        }//endFor
        return null;
    }//end
}//endClass
