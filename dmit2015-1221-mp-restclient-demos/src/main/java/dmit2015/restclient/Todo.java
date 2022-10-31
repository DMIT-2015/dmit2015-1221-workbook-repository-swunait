package dmit2015.restclient;

import lombok.Data;

@Data
public class Todo {

    private String key;
    private String task;
    private Boolean completed;
    private Boolean important;

}
