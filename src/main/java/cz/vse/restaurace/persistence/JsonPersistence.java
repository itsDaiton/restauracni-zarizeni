package cz.vse.restaurace.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.vse.restaurace.model.*;

public class JsonPersistence {

    private static final String SAVE_FILE_NAME = "usersdata.json";

    private Gson gson = new Gson();

    public List<User> loadData() throws PersistenceException {
        checkOrCreateFile("usersdata.json");
        try {
            List<String> lines = Files.readAllLines(Paths.get(SAVE_FILE_NAME));
            String jsonRaw = String.join("\n", lines);
            Type listOfUsersType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(jsonRaw, listOfUsersType);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Order> loadUserData(User user) throws PersistenceException {
        checkOrCreateDirectory("accountData");
        String fileName = "accountData\\" + user.getUserName() + ".json";
        checkOrCreateFile(fileName);
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            String jsonRaw = String.join("\n", lines);
            Type listOfUsersOrdersType = new TypeToken<List<Order>>() {}.getType();
            return gson.fromJson(jsonRaw, listOfUsersOrdersType);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Food> loadFoodData()  throws PersistenceException {
        checkOrCreateDirectory("appData");
        String fileName = "appData\\" + "food.json";
        checkOrCreateFile(fileName);
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            String jsonRaw = String.join("\n", lines);
            Type listOfFood = new TypeToken<List<Food>>() {}.getType();
            return gson.fromJson(jsonRaw, listOfFood);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Drink> loadDrinksData()  throws PersistenceException {
        checkOrCreateDirectory("appData");
        String fileName = "appData\\" + "drinks.json";
        checkOrCreateFile(fileName);
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            String jsonRaw = String.join("\n", lines);
            Type listOfDrinks = new TypeToken<List<Drink>>() {}.getType();
            return gson.fromJson(jsonRaw, listOfDrinks);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Table> loadTableData() throws PersistenceException {
        checkOrCreateFile("appData");
        String fileName = "appData\\" + "tables.json";
        checkOrCreateFile(fileName);
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            String jsonRaw = String.join("\n", lines);
            Type listOfTables = new TypeToken<List<Table>>() {}.getType();
            return gson.fromJson(jsonRaw, listOfTables);
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public void saveUserData(List<Order> orders, User user) throws PersistenceException {
        checkOrCreateDirectory("accountData");
        String fileName = "accountData\\" + user.getUserName() + ".json";
        checkOrCreateFile(fileName);
        String json = gson.toJson(orders);
        try {
            Files.write(Paths.get(fileName), json.getBytes());
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public void saveData(List<User> users) throws PersistenceException {
        checkOrCreateFile("usersdata.json");
        String json = gson.toJson(users);
        try {
            Files.write(Paths.get(SAVE_FILE_NAME), json.getBytes());
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    public void checkOrCreateFile(String fileName) throws PersistenceException {
        File file = new File(fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new PersistenceException(e);
            }
        }
    }

    public void checkOrCreateDirectory(String directoryName) throws PersistenceException {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        path += "\\" + directoryName;

        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }
}
