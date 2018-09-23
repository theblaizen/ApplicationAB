package com.ovdiienko.yaroslav.applicationa.dto.db.model;


import com.ovdiienko.yaroslav.applicationa.R;

public enum Status {
    GREEN(R.color.colorLinkGreen, 1),
    RED(R.color.colorLinkRed, 2),
    GREY(R.color.colorLinkGrey, 3);

    private int colorResId;
    private int status;

    Status(int resId, int status) {
        this.colorResId = resId;
        this.status = status;
    }

    public static Status findByStatusId(int statusId) {
        for (Status item : Status.values()) {
            if (item.status == statusId) {
                return item;
            }
        }

        return null;
    }

    public int getColorRes() {
        return colorResId;
    }

    public int getStatus() {
        return status;
    }
}
