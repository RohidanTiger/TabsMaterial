package nextop.asia.pratise.util;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by QuyDV on 4/20/17.
 */

public class NewsRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;

    public NewsRequest(Context context, String url) {
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
        String url = "https://lienquan.garena.vn/tin-tuc";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();

            Elements levels = doc.getElementsByClass("newsList");
            int id = 0;
            Log.d("Data",levels.size()+"");
            //writeToFile(new Gson().toJson(items));
            //return new Gson().toJson(items);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "haha";
    }
}
