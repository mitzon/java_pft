package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        saveAsJson(contacts, new File(file));
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("Firstname %s", i))
                    .withLastName(String.format("Lastname %s", i))
                    .withHomePhone(String.format("+752568528%s", i))
                    .withMobilePhone(String.format("77-25-%s", i))
                    .withWorkPhone(String.format("789 521 23%s", i))
                    .withEmail(String.format("%s@ya.ru", i))
                    .withEmail2(String.format("testemail%s", i))
                    .withEmail3(String.format("+3mvf23%s", i))
                    .withAddress(String.format("Matrosova %s", i))
                    .withAddress2(String.format("prospekt Gruzovoi %s", i))
                    .withGroup(String.format("test group %s", i)));
        }
        return contacts;
    }
}
