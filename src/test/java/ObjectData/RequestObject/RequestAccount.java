package ObjectData.RequestObject;

import ObjectData.RequestPreparation;

import java.util.HashMap;

public class RequestAccount implements RequestPreparation {
    private String userName;
    private String password;

    public RequestAccount(HashMap<String, String> testData)
    {
    prepareObject(testData);
    }
    @Override
    public void prepareObject(HashMap<String, String> testData) {
        //returneaza toate cheiile care sunt unice si intr-un hashmap toate cheiile suntunice
        for(String key: testData.keySet()){
            switch(key){
                case "userName":
                    setUsername(testData.get(key));
                    break;
                case "password":
                    setPassword(testData.get(key));
                    break;
            }
        }
        adjustObjectVariable();
    }
    private void adjustObjectVariable()
    {
    userName=userName+System.currentTimeMillis();
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
