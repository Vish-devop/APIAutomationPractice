package utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


    public class JsonReader {
        public static String getTestData(String input) throws IOException, ParseException, org.json.simple.parser.ParseException {
            String testdata;
            return testdata = (String) getJsonData().get(input);//input is the key

        }
        public static JSONObject getJsonData() throws IOException, ParseException, org.json.simple.parser.ParseException {
            //        passing the path to testdata.json file
            File filename = new File("TestData/testdata.json");
            //        converting json file into string
            String json = FileUtils.readFileToString(filename,"UTF-8");
            //        parsing the string into object
            Object obj=new JSONParser().parse(json);
            //        giving json object so that I can return to function everytime it get called
            JSONObject jsonObject=(JSONObject) obj;
            return jsonObject;

        }
    }

