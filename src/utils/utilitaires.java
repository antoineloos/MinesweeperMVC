/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Epulapp
 */
public class utilitaires {
    public static String toHex(double r, double g, double b) {
        return "#" + toBrowserHexValue((int) (r * 255)) + toBrowserHexValue((int) (g * 255)) + toBrowserHexValue((int) (b * 255));
    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }
}
