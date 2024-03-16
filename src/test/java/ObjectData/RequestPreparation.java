package ObjectData;

import java.util.HashMap;

public interface RequestPreparation {
    //aceasta interfata are ca scop sa serializeze un specific request body
    void prepareObject(HashMap<String,String> testData);
    //toate clasele care le faci in request body va trebui sa implementeze
    //aceasta interfata ca sa ne asiguram ca va fi serializata



}
