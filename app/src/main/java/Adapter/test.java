package Adapter;

import com.google.gson.Gson;

public class test {
    public test() {
        String test = "abcs";
        Gson builder = new Gson();
        System.out.println(builder.toJson(test));
    }
}
