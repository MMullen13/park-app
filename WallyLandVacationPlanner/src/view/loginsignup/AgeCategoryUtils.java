/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.loginsignup;
/**
 * An utility class for age category string
 *
 * @author Ana
 */
public class AgeCategoryUtils {

    private int id;
    private String text;

    public AgeCategoryUtils(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return text;
    }
}
