package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names =  "-f", description = "Target file")
  public String file;

  @Parameter(names =  "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
    }
    generator.run();
  }
  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCSV(contacts, new File(file));
    } else if  (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }
  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    try(Writer writer = new FileWriter(file)){
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getLastname(), contact.getFirstname(),
                contact.getPhone(), contact.getAddress(), contact.getEmail(), contact.getGroup()));
      }
    }
  }
  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withLastname(String.format("lastname %s", i))
              .withFirstname(String.format("firstname %s", i)).withPhone(String.format("02 %s", i))
              .withAddress(String.format("Spb %s", i)).withEmail(String.format("email@mail.ru %s", i))
              .withGroup(String.format("test 1")));
    }
    return contacts;
  }
  }
