package com.alfredtechsystems.myapplication2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashbel on 10/8/2017.
 */

class ParseJSON {
    public static String  success;
    public static String message;

    public static final String JSON_ARRAY = "people";
    public static final String KEY_ID = "Name";
    public static final String KEY_NAME = "age";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected String parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                success = jo.getString(KEY_ID);
                message = jo.getString(KEY_NAME);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  message;
    }
}
