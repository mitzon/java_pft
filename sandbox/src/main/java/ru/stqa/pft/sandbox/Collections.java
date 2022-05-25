package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String [] langs = {"Java", "C#", "Python", "PHP"};
        for (int i = 0; i < langs.length; i++);


        for (String l : langs) {
            System.out.println("Bla-bla " + l);
        }

        List<String> languages = new ArrayList<String>();

        List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

        languages.add("Java");
    }
}
