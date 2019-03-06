package geekbrains.androidlevel1;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static geekbrains.androidlevel1.ResultFragment.PARCEL;


public class CitiesFragment extends android.support.v4.app.Fragment{
    private ListView listView;
    private Parcel newParcel;
    private View view;
    private boolean isResultExist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list,container,false);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                addCity();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.preferences:
                preferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void addCity() {
        Snackbar.make(view,"city added", Snackbar.LENGTH_LONG).show();
    }

    private void preferences() {
        Snackbar.make(view, "preferences", Snackbar.LENGTH_LONG).show();
    }

    private void about() {
        Snackbar.make(view, "about", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] data = getResources().getStringArray(R.array.cities);

        newParcel = new Parcel("",0,false,false, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DataSourceBuilder builder = new DataSourceBuilder(getResources());

        SocnetAdapter adapter = new SocnetAdapter(builder.build());
        recyclerView.setAdapter(adapter);
        final Activity that = getActivity();

        final Switch sw1 = view.findViewById(R.id.switch1);
        final Switch sw2 = view.findViewById(R.id.switch2);
        final Switch sw3 = view.findViewById(R.id.switch3);

        sw1.setText(R.string.mainHumidity);
        sw2.setText(R.string.mainWindSpeed);
        sw3.setText(R.string.mainPicture);

        Switch humidity = view.findViewById(R.id.switch1);
        Switch windSpeed = view.findViewById(R.id.switch2);
        Switch picture = view.findViewById(R.id.switch3);
        humidity.setChecked(false);
        windSpeed.setChecked(false);
        picture.setChecked(false);

        View detailsFrame = getActivity().findViewById(R.id.result);
        isResultExist = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        if (isResultExist){
            showResult(newParcel);
        }

        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView cityName = (TextView) view;
                newParcel = new Parcel(cityName.getText().toString(), position, sw1.isChecked(), sw2.isChecked(), sw3.isChecked());
                Log.d("PARCEL", Integer.toString(position));
                String city = cityName.getText().toString();
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                Randomiser randomiser = new Randomiser();

                showResult(newParcel);
            }
        });
    }

    private void showResult(Parcel parcel) {
        if (isResultExist) {
            ResultFragment result = ResultFragment.create(parcel);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.result, result);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ResultActivity.class);
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }
    }
}
