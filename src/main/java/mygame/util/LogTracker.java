package mygame.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogTracker {

    private List<String> logs = new ArrayList<>();

    public void addLog(String s){
        logs.add(s);
    }

    public JSONArray getLogs() {
        JSONArray array = (JSONArray) JSONObject.toJSON(logs);
        logs.clear();
        return array;
    }
}
