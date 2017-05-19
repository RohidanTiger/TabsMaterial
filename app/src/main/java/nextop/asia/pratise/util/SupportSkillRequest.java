package nextop.asia.pratise.util;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import nextop.asia.pratise.model.SupportSkill;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkillRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;

    public SupportSkillRequest(Context context, String url) {
        super(context);
        this.mContext = context;
        mUrl = url;
    }
    @Override
    public String loadInBackground() {
        String url = "https://lienquan.garena.vn/doc-chieu";
        Document doc = null;
        ArrayList<SupportSkill> items = new ArrayList<>();
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

            Elements levels = doc.getElementsByClass("title");
            int id = 0;
            for(Element element : levels){
                String name = element.html();
                SupportSkill skill = new SupportSkill();
                skill.setId(String.valueOf(id));
                skill.setName(name);
                items.add(skill);
                id++;
//                Elements info = element.getElementsByClass("txt");
//                String mana = info.get(1).html();
//                String desc = info.get(2).html();
//                String video = element.getElementsByTag("a").first().getElementsByAttribute("href").attr("href");
            }

            Elements videos = doc.getElementsByClass("playvideo");
            for(int i = 0; i< videos.size(); i++){
                Element e = videos.get(i);
                String video = e.getElementsByAttribute("data-video").attr("data-video");
                items.get(i).setYoutube(video);
            }

            Elements content = doc.getElementsByClass("txtcript");
            for(int i = 0; i< videos.size(); i++){
                Element e = content.get(i);
                String desc = e.html();
                items.get(i).setDescription(desc);
            }

            Log.i("Data",new Gson().toJson(items));
            //writeToFile(new Gson().toJson(items));
            //return new Gson().toJson(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Haha";
    }
}
