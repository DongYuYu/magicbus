package com.magicbus.search.routeid;

import com.magicbus.data.RouteID;

import java.util.List;

import retrofit2.Response;

public interface RouteIDContract {

    interface View {
        void getResponse(Response<List<RouteID>> response);

    }

    interface Presenter {
        void sendRequest(String startpoint_latitude, String startpoint_longitude,
                         String endpoint_latitude, String endpoint_longitude);

    }
}