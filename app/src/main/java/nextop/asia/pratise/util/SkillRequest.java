package nextop.asia.pratise.util;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import nextop.asia.pratise.model.Item;
import nextop.asia.pratise.model.Skill;

/**
 * Created by QuyDV on 4/12/17.
 */

public class SkillRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;

    public SkillRequest(Context context, String url) {
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
        for(int i = 1 ;i <= 42; i++){
            String url = "https://lienquan.garena.vn/tuong-chi-tiet/".concat(String.valueOf(i));
            Document doc = null;
            ArrayList<Skill> items = new ArrayList<>();
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();
                //Element es = doc.getElementsByClass("list-item").first();

//            Elements images = es.getElementsByClass("img-skill");
//            int idM = 0;
//            for(Element element : images){
//                String url = element.getElementsByAttribute("src").attr("src");
//                downloadImagesToSdCard(url,String.valueOf(idM).concat(".jpg"));
//                idM++;
//            }

                Elements levels = doc.getElementsByClass("in-skill");
                int id = 0;
                for(Element element : levels){
                    String name = element.getElementsByClass("name").html();
                    Elements info = element.getElementsByClass("txt");
                    String mana = info.get(1).html();
                    String desc = info.get(2).html();
                    String video = element.getElementsByTag("a").first().getElementsByAttribute("href").attr("href");

                    Skill skill = new Skill(String.valueOf(id), name,desc,mana,video);
                    id++;
                    items.add(skill);
                }
                Log.i("Data",new Gson().toJson(items));
                //writeToFile(new Gson().toJson(items));
                //return new Gson().toJson(items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "haha";
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

        final File file = new File(path, "skill.txt");

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
}
