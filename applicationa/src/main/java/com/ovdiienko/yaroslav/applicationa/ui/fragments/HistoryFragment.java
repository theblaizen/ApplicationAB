package com.ovdiienko.yaroslav.applicationa.ui.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ovdiienko.yaroslav.applicationa.R;
import com.ovdiienko.yaroslav.applicationa.adapters.recyclerviews.HistoryListAdapter;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.ImageEntity;
import com.ovdiienko.yaroslav.applicationa.dto.db.model.Status;
import com.ovdiienko.yaroslav.applicationa.utils.ActivityUtils;
import com.ovdiienko.yaroslav.applicationa.utils.AppException;
import com.ovdiienko.yaroslav.applicationa.utils.Constants;
import com.ovdiienko.yaroslav.applicationa.view_models.HistoryViewModel;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment implements HistoryListAdapter.HistoryItemClickListener {

    private HistoryListAdapter mHistoryAdapter;
    private RecyclerView mHistoryList;
    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        int container = R.layout.fragment_history;

        Bundle bundle = new Bundle();
        bundle.putInt(CONTAINER_LAYOUT, container);

        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    protected View initItems(View view) {

        mHistoryList = view.findViewById(R.id.rv_history_links_container);

        return view;
    }

    @Override
    protected void setupItems() {
        mViewModel.getImagesLiveData().observe(this, this::observeLiveDataChanges);
        mViewModel.getImages();
    }

    private void observeLiveDataChanges(List<ImageEntity> imageEntities) {
        setupHistoryList(imageEntities);
    }

    private void setupHistoryList(List<ImageEntity> imageEntities) {
        //use mock data while content provider is not written
        mHistoryAdapter = new HistoryListAdapter(getActivity(), mockList(), this);

        mHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHistoryList.setAdapter(mHistoryAdapter);
    }

    private List<ImageEntity> mockList() {
        List<ImageEntity> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageEntity entity = new ImageEntity();
            entity.setId(i + 1);
            entity.setDateTime(OffsetDateTime.now());
            if (i % 2 == 0) {
                entity.setLink("https://cdn.pixabay.com/photo/2016/06/18/17/42/image-1465348_960_720.jpg");
                entity.setStatus(Status.RED.getStatus());
            } else {
                entity.setLink("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTvQHHvrKG-Kq6ZQSZJJclpae-4_nCjIi5jJgI4uRd8OBLwDC7");
                entity.setStatus(Status.GREEN.getStatus());
            }
            items.add(entity);
        }
        return items;
    }

    @Override
    public void onItemClicked(View view, String url, long id) {
        if (!TextUtils.isEmpty(url)) {
            openAppB(url, id);
        }
    }

    private void openAppB(String url, long id) {
        try {
            ActivityUtils.openOtherApp(getActivity(), Constants.PACKAGE_PATH, Constants.ACTIVITY_PATH, new ActivityUtils.ExtrasSetter() {
                @Override
                public List<String> putExtrasString() {
                    List<String> stringExtras = new ArrayList<>();
                    stringExtras.add(url);
                    stringExtras.add(String.valueOf(id));
                    return stringExtras;
                }

                @Override
                public List<String> putExtrasName() {
                    List<String> nameExtras = new ArrayList<>();
                    nameExtras.add(Constants.IMAGE_EXTRAS_NAME);
                    nameExtras.add(Constants.OPENED_FROM_HISTORY_EXTRAS_NAME);
                    return nameExtras;
                }
            });
        } catch (AppException extrasException) {
            extrasException.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_history, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_option_date:
                mViewModel.getImagesSortedByDate();
                break;
            case R.id.menu_item_option_status:
                mViewModel.getImagesSortedByStatus();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
