package com.uc.mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    private List<Cast> cast;

    public List<Cast> getCasts() {
        return cast;
    }

    public void setCasts(List<Cast> cast) {
        this.cast = cast;
    }
}