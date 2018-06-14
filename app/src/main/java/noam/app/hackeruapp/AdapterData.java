package noam.app.hackeruapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyViewHolder> {

    private List<Item> itemList;

    public AdapterData(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.fullName.setText(this.itemList.get(position).getFullName());

        // sets the color of the text according to pre-requisites
        if (this.itemList.get(position).getLevel() < 10)
            viewHolder.fullName.setTextColor(Color.RED);
        else if (this.itemList.get(position).getLevel() < 50)
            viewHolder.fullName.setTextColor(Color.YELLOW);
        else
            viewHolder.fullName.setTextColor(Color.GREEN);

        // sets the Typeface of the text according to pre-requisites
        if (this.itemList.get(position).isIdentified())
            viewHolder.fullName.setTypeface(viewHolder.fullName.getTypeface(), Typeface.BOLD);
        else
            viewHolder.fullName.setTypeface(viewHolder.fullName.getTypeface(), Typeface.NORMAL);
}

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;

        public MyViewHolder(View view) {
            super(view);
            fullName = view.findViewById(R.id.fullName);
        }
    }
}
