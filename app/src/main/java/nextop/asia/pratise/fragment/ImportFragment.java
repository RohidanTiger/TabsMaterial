package nextop.asia.pratise.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import nextop.asia.pratise.demo.TestDemo;
import pratice.asia.nextop.tabsmaterial.R;

/**
 * Created by billymobile on 11/17/15.
 */
public class ImportFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    public ImportFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), "ONCREATE",Toast.LENGTH_SHORT).show();
        //TestDemo sort = new TestDemo();
        //sort.sort(new int[]{10, 7, 8, 9, 1, 5, 12, -1});
        //Log.d("Xau doi xung:",sort.xauDoiXung("alevelb"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {


        View rootView = inflater.inflate(R.layout.import_fragment, container, false);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OneFragment(), "New Movie");
        adapter.addFragment(new TwoFragment(),"New Feeds");
        adapter.addFragment(new ThreeFragment(), "New Songs");


        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);


        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        Toast.makeText(getContext(), "ONCREATEVIEW",Toast.LENGTH_SHORT).show();

        viewPager.setOffscreenPageLimit(3);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();


    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new OneFragment();
            }

            if(position==1){
                return new TwoFragment();
            }

            if(position==2){
                return new ThreeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}