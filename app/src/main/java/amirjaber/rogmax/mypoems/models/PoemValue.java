package amirjaber.rogmax.mypoems.models;

import java.util.List;

public class PoemValue {

    private String value, message;
    private List<PoemModel> result;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PoemModel> getResult() {
        return result;
    }

    public void setResult(List<PoemModel> result) {
        this.result = result;
    }
}
