package geekbrains.androidlevel1;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultFragment extends android.support.v4.app.Fragment{

    public static final String PARCEL = "parcel";
    public static ResultFragment create(Parcel parcel){
        ResultFragment f = new ResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }

    private Parcel getParcel(){
        return (Parcel) getArguments().getSerializable(PARCEL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result,container,false);
        Parcel parcel = getParcel();
        TextView cityText = view.findViewById(R.id.city);
        TextView temperatureText = view.findViewById(R.id.temperature);
        TextView humidityText = view.findViewById(R.id.humidity);
        TextView windText = view.findViewById(R.id.windSpeed);
        ImageView picture = view.findViewById(R.id.icon);
        Button helpButton = view.findViewById(R.id.helpButton);

        String s = parcel.city;
        final Randomiser randomiser = new Randomiser();
        helpButton.setText(R.string.helpButton);

        cityText.setText(s.toUpperCase());
        temperatureText.setText(getResources().getText(R.string.temperature) + " " + Integer.toString(randomiser.getRandom()));
        if (parcel.getHumidity()) {
            humidityText.setText(getResources().getText(R.string.humidity) + " " + Integer.toString(randomiser.getRandom()));
        }
        if (parcel.getWindSpeed()) {
            windText.setText(getResources().getText(R.string.windspeed) + " " + Integer.toString(randomiser.getRandom()));
        }
        if (parcel.getPicture()) {
            TypedArray imgs = getResources().obtainTypedArray(R.array.cities_imgs);
            picture.setImageResource(imgs.getResourceId(parcel.getIndex(), -1));
            imgs.recycle();
        }

        return view;
    }

}
