package uno.unisal.com.uno;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import uno.unisal.com.uno.classes.Carta;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private List <Integer> cardsPictures = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(List<Integer> cardsPictures, Context mContext) {
        this.cardsPictures = cardsPictures;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listview, parent, false);
        return new ViewHolder((cardView));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(cardsPictures.get(position))
                .into(holder.imageViewCarta);

        holder.imageViewCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on an image: " + cardsPictures.get(position));
                Toast.makeText(mContext, cardsPictures.get(position), Toast.LENGTH_SHORT).show();
                //remover carta teste
                //cardsPictures.remove(cardsPictures.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardsPictures.size();
    }

    public void removeItem (int position){
        cardsPictures.remove(position);
        notifyItemRemoved(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCarta;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCarta = itemView.findViewById(R.id.imageViewCardId);
        }
    }
}