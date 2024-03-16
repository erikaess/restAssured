package PropertyUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Properties;

public class PropertyUtility {
    private Properties properties;

    public PropertyUtility(String fileName) {
        loadFile(fileName);
    }


    //facem o metoda care sa incarce un fisier
    private void loadFile(String fileName) {
        properties = new Properties();//initializam=instantiam
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/" + fileName + ".properties");
            properties.load(fileInputStream);//legam properties de fileinputstream
        } catch (Exception ignored) {
            //Exception e clasa parinte al exceptiilor
        }

    }

    //facem o metoda care returneaza toate datele dintr-un fisier
    public HashMap<String, String> getAllData() {
        HashMap<String, String> testData = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            //pentru fiecare cheie din lista de chei
            testData.put(key, properties.getProperty(key));
        }
        return testData;
    }


}
