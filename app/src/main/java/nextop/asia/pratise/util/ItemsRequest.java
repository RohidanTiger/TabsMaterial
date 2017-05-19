package nextop.asia.pratise.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import nextop.asia.pratise.model.Item;

/**
 * Created by QuyDV on 4/10/17.
 */

public class ItemsRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;

    public ItemsRequest(Context context,String url) {
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
        Document doc = null;
        ArrayList<Item> items = new ArrayList<>();
        try {
            doc = Jsoup.connect(mUrl).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();
            Element es = doc.getElementsByClass("list-item").first();

//            Elements images = es.getElementsByClass("img-skill");
//            int idM = 0;
//            for(Element element : images){
//                String url = element.getElementsByAttribute("src").attr("src");
//                downloadImagesToSdCard(url,String.valueOf(idM).concat(".jpg"));
//                idM++;
//            }

            Elements tags = es.getElementsByClass("tags");

            Elements levels = es.getElementsByClass("in-skill");
            int id = 0;
            for(Element element : levels){
                String name = element.getElementsByClass("name").html();
                String desc = element.getElementsByClass("txt").html();
                String tag = tags.get(id).html();
                int type = 0;
                if(tag.contains("cong")){
                    type = 0;
                }else if(tag.contains("phep")){
                    type = 1;
                }else if(tag.contains("thu")){
                    type = 2;
                }else if(tag.contains("toc-do")){
                    type = 3;
                }else type = 4;


                if(tag.contains("cap-1")){
                    Item item = new Item(id,name,desc,1,type);
                    items.add(item);
                }else if(tag.contains("cap-2")){
                    Item item = new Item(id,name,desc,2,type);
                    items.add(item);
                }else {
                    Item item = new Item(id,name,desc,3,type);
                    items.add(item);
                }

                id++;
            }
            writeToFile(new Gson().toJson(items));
            return new Gson().toJson(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "haha";
    }

    //save image
    public static void imageDownload(Context ctx, String url){
        Picasso.with(ctx)
                .load(url)
                .into(getTarget(url));
    }

    //target to save
    private static Target getTarget(final String url){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 256, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    private void downloadImagesToSdCard(String downloadUrl,String imageName) {
        try{
            URL url = new URL(downloadUrl); //you can write here any link

            File myDir =  new File("/sdcard"+"/"+"LienQuan");
            //Something like ("/sdcard/file.mp3")


            if(!myDir.exists()){
                myDir.mkdir();
                Log.v("", "inside mkdir");

            }

            String fname = imageName;
            File file = new File (myDir, fname);
            if (file.exists ()) file.delete ();

             /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();
            InputStream inputStream = null;
            HttpURLConnection httpConn = (HttpURLConnection)ucon;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }

            /*
             * Define InputStreams to read from the URLConnection.
             */
            // InputStream is = ucon.getInputStream();
            /*
             * Read bytes to the Buffer until there is nothing more to read(-1).
             */

            FileOutputStream fos = new FileOutputStream(file);
            int size = 1024*1024;
            byte[] buf = new byte[size];
            int byteRead;
            long bytesDownloaded = 0;
            while (((byteRead = inputStream.read(buf)) != -1)) {
                fos.write(buf, 0, byteRead);
                bytesDownloaded += byteRead;
            }
            /* Convert the Bytes read to a String. */

            fos.close();

        }catch(Exception io) {
        }
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();
                URL url = new URL(f_url[0]);

                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root+"/downloadedfile.jpg");
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);

                }
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }
        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
        }
    }

    public void writeToFile(String data) {
        // Get the directory for the user's public pictures directory.
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/LienQuan/");
        //File path =  new File("/sdcard"+"/"+"LienQuan");
        // Make sure the path directory exists.
        if(!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "items.txt");

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
