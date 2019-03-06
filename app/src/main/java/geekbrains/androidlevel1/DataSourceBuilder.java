package geekbrains.androidlevel1;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class DataSourceBuilder {
    private List<Soc> dataSource;
    private Resources resources;

    public DataSourceBuilder(Resources resources) {
        dataSource = new ArrayList<>(6);
        this.resources = resources;
    }

    public List<Soc> build(){
        String[] descriptions = resources.getStringArray(R.array.cities);
        int[] pictures = getImageArray();
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new Soc(descriptions[i], pictures[i]));
        }
        return dataSource;
     }

     private int[] getImageArray(){
         TypedArray pictures = resources.obtainTypedArray(R.array.cities_imgs);
         int lenght = pictures.length();
         int[] answer = new int[lenght];
         for (int i = 0; i < lenght; i++) {
             answer[i] = pictures.getResourceId(i,0);
         }
         return answer;
     }
}
