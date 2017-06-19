package nextop.asia.pratise.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nextop.asia.pratise.util.HeroesRequest;
import nextop.asia.pratise.util.ItemsRequest;
import nextop.asia.pratise.util.LoreRequest;
import nextop.asia.pratise.util.NewsRequest;
import nextop.asia.pratise.util.SkillRequest;
import nextop.asia.pratise.util.SupportSkillRequest;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by QuyDV on 4/10/17.
 */

public class FragmentItem extends Fragment {
    TextView txtContent;
    private LoaderManager.LoaderCallbacks<String> itemsListener = new LoaderManager.LoaderCallbacks<String>() {
        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            return new SkillRequest(getContext(),"https://lienquan.garena.vn/trang-bi");
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            txtContent.setText(data);
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item,container,false);
        txtContent = (TextView) rootView.findViewById(R.id.txt_content);
        getActivity().getSupportLoaderManager().initLoader(1, null, itemsListener).forceLoad();
        return rootView;
    }
}

