package geekbrains.androidlevel1;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SocnetAdapter  extends RecyclerView.Adapter<SocnetAdapter.ViewHolder>{
    private List<Soc> dataSource;

    private OnItemClickListener itemClickListener;

    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.d("SocnetAdapter", "onCreateViewHolder");
        return vh;
    }

    public SocnetAdapter(List<Soc> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Soc item = dataSource.get(position);
        holder.description.setText(item.getDescription());
        holder.picture.setImageResource(item.getPicture());


        Log.d("SocnetAdapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public ImageView picture;
        public TextView temperature;


        public ViewHolder(View v) {
            super(v);
            description = v.findViewById(R.id.description);
            picture = v.findViewById(R.id.picture);
            temperature = v.findViewById(R.id.card_temperature);

            description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


}
