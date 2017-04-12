package nextop.asia.pratise.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

import nextop.asia.pratise.adapter.NewSongAdapter;
import nextop.asia.pratise.model.NewSong;
import nextop.asia.pratise.util.XmlParser;
import pratice.asia.nextop.tabsmaterial.MyApplication;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/17/15.
 */
public class ThreeFragment extends Fragment {

    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    private ListView listViewNewSong;
    private ArrayList<NewSong> lisNewSong;
    private NewSongAdapter songAdapter;
    XmlParser xmlParser = new XmlParser();

    public ThreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        listViewNewSong = (ListView)rootView.findViewById(R.id.listNewSong);
        lisNewSong = new ArrayList<NewSong>();
        new LoadSong().execute(URL);
        return rootView;
    }

    class LoadSong extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];

            Document doc = xmlParser.getDomElement(URL); // getting DOM element

            NodeList nl = doc.getElementsByTagName(KEY_SONG);
            for(int i=0;i<nl.getLength();i++){
                Element element = (Element)nl.item(i);
                String id = xmlParser.getValue(element, KEY_ID);
                String title = xmlParser.getValue(element,KEY_TITLE);
                String artist = xmlParser.getValue(element,KEY_ARTIST);
                String urlThumb = xmlParser.getValue(element,KEY_THUMB_URL);
                String duration = xmlParser.getValue(element,KEY_DURATION);
                NewSong newSong = new NewSong();
                newSong.setmId(Integer.parseInt(id));
                newSong.setmImageUrl(urlThumb);
                newSong.setmTitle(title);
                newSong.setmDuration(duration);
                newSong.setmArtist(artist);

                lisNewSong.add(newSong);

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    songAdapter = new NewSongAdapter(getContext(),lisNewSong);
                    listViewNewSong.setAdapter(songAdapter);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
