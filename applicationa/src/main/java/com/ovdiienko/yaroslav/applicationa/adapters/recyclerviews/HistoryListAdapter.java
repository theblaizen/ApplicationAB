package com.ovdiienko.yaroslav.applicationa.adapters.recyclerviews;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ovdiienko.yaroslav.applicationa.R;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.ImageEntity;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.Status;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    // Better to separate ImageEntity from LinkEntity
    List<ImageEntity> mLinks;
    private HistoryItemClickListener mLinkClickListener;

    public HistoryListAdapter(Context context, List<ImageEntity> links, HistoryItemClickListener listener) {
        mContext = context;
        mLinks = links;
        mLinkClickListener = listener;
    }

    public void setHistoryLinks(List<ImageEntity> mLinks) {
        this.mLinks = mLinks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_recycleview_image_links, parent, false);
        return new LinksViewHolder(rootView, mLinkClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        LinksViewHolder holder = (LinksViewHolder) viewHolder;
        ImageEntity link = mLinks.get(position);

        if (link != null) {
            holder.setLinkText(link.getLink());
            holder.changeLinkColor(link.getStatus());
            holder.itemView.setTag(link.getId());
        }
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface HistoryItemClickListener {
        void onItemClicked(View view, String utl, long id);
    }

    class LinksViewHolder extends RecyclerView.ViewHolder {
        TextView mLink;
        HistoryItemClickListener mListener;

        public LinksViewHolder(View itemView, HistoryItemClickListener listener) {
            super(itemView);

            mLink = itemView.findViewById(R.id.tv_item_link);
            mLink.setOnClickListener(this::onClick);
            mListener = listener;
        }

        public void changeLinkColor(int statusId) {
            Status status = Status.findByStatusId(statusId);
            if (status != null) {
                int color = status.getColorRes();
                mLink.setBackgroundColor(ContextCompat.getColor(mContext, color));
            }
        }

        public void setLinkText(String linkText) {
            if (!TextUtils.isEmpty(linkText)) {
                mLink.setText(linkText);
            }
        }

        public void onClick(View view) {
            mListener.onItemClicked(view, mLink.getText().toString(), (long) itemView.getTag());
        }
    }
}
