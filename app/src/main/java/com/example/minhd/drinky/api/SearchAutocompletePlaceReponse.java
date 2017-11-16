package com.example.minhd.drinky.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SearchAutocompletePlaceReponse {
    @SerializedName("predictions")
    private List<Prediction> predictionses;

    public List<Prediction> getPredictionses() {
        return predictionses;
    }

    public static class Prediction {
        @SerializedName("description")
        private String description;

        @SerializedName("id")
        private String id;

        @SerializedName("place_id")
        private String placeId;

        public String getDescription() {
            return description;
        }

        public String getId() {
            return id;
        }

        public String getPlaceId() {
            return placeId;
        }
    }
}
