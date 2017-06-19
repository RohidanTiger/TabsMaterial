package nextop.asia.pratise.util;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nextop.asia.pratise.model.Hero;
import nextop.asia.pratise.model.HeroDetail;
import nextop.asia.pratise.model.Item;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by QuyDV on 4/12/17.
 */

public class LoreRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;
    private ArrayList<String> listItem = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public LoreRequest(Context context, String url) {
        super(context);
        this.mContext = context;
        mUrl = url;
    }

    @Override
    public void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public String loadInBackground() {
        String url = "http://www.sok.ninja/heroes/Aleister";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();
            Elements lore = doc.getElementsByClass("lore");
            Log.d("Lore:",lore.outerHtml());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "haha";
    }

    private String requestIndex(){
        String data = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("items.txt")));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                data = data.concat(mLine);
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return data;
    }

    public String getRole(int r){
        String role = "";
        switch (r){
            case 1:{
                role = "Đấu Sĩ";
                break;
            }
            case 2:{
                role = "Pháp Sư";
                break;
            }
            case 3:{
                role = "Trợ Thủ";
                break;
            }
            case 4:{
                role = "Đỡ Đòn";
                break;
            }
            case 5:{
                role = "Sát Thủ";
                break;
            }
            case 6:{
                role = "Xạ Thủ";
                break;
            }
        }
        return role;
    }

    public void writeToFile(String data) {
        // Get the directory for the user's public pictures directory.
        final File path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/LienQuan/");

        // Make sure the path directory exists.
        if(!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "Heroes.txt");

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void loadAllSkillDetailFromFile(){
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("items.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            items = new Gson().fromJson(json,new TypeToken<List<Item>>(){}.getType());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestQuestionList(){
        final ArrayList listQues = new ArrayList();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("Item.txt")));

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                listItem.add(mLine);
            }
        } catch (IOException e) {
        }
    }

    private int findItemByName(String name){
        for(int i = 0; i < items.size(); i++){
            String ss = (items.get(i).getName().toLowerCase());
            if(ss.equals(name)) return items.get(i).getId();
        }
        return -1;
    }

    public static final String UTF8_BOM = "\uFEFF";

    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }
}
