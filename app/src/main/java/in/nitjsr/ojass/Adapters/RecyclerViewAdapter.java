package in.nitjsr.ojass.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nitjsr.ojass.Activities.GuruGyanActivity;
import in.nitjsr.ojass.Activities.TeamActivity;
import in.nitjsr.ojass.EventsFolding.SubEventsActivity;
import in.nitjsr.ojass.Modals.Modal;
import in.nitjsr.ojass.R;
import in.nitjsr.ojass.Utils.Constants;
import in.nitjsr.ojass.Utils.Utilities;

import static in.nitjsr.ojass.Activities.GuruGyanActivity.POSITION_PARAM;
import static in.nitjsr.ojass.Utils.Constants.EVENT_FLAG;
import static in.nitjsr.ojass.Utils.Constants.GURU_GYAN_FLAG;
import static in.nitjsr.ojass.Utils.Constants.SPONSORS_FLAG;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Modal> dataset;
    private Context context;
    private int FLAG;
    public RecyclerViewAdapter(ArrayList<Modal> dataset, Context context, int FLAG) {
        this.dataset = dataset;
        this.context=context;
        this.FLAG = FLAG;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view;
        if (FLAG != GURU_GYAN_FLAG) view = inflater.inflate(R.layout.item_recyclerview, parent,false);
        else view = inflater.inflate(R.layout.item_guru_gyan_home, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Modal current = dataset.get(position);
        if (current.getImage() != null) Utilities.setPicassoImage(context, current.getImage(), holder.eventImg, Constants.SQUA_PLACEHOLDER);
        else Picasso.with(context).load(current.getDrawableImage()).fit().into(holder.eventImg);

        holder.evenTitle.setText(current.getEventName());
        if (FLAG != GURU_GYAN_FLAG) holder.eventDesc.setText(current.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (FLAG){
                    case EVENT_FLAG:
                        Intent subEvent =new Intent(context,SubEventsActivity.class);
                        subEvent.putExtra("title",current.getEventName());
                        context.startActivity(subEvent);
                        break;
                    case GURU_GYAN_FLAG:
                        Intent intent = new Intent(context, GuruGyanActivity.class);
                        intent.putExtra(POSITION_PARAM, position);
                        context.startActivity(intent);
                        break;
                    case SPONSORS_FLAG:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView evenTitle, eventDesc;
        public ImageView eventImg;
        public ViewHolder(View itemView) {
            super(itemView);
            evenTitle = itemView.findViewById(R.id.event_title);
            eventImg = itemView.findViewById(R.id.eventImg);
            if (FLAG != GURU_GYAN_FLAG) eventDesc = itemView.findViewById(R.id.event_description);
        }
    }
}
