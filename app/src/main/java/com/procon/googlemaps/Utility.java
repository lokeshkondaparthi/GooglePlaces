package com.procon.googlemaps;

import android.content.Context;
import android.widget.Toast;

import com.procon.googlemaps.models.AddressResponseModel;
import com.procon.googlemaps.network.RetroHelper;
import com.procon.googlemaps.network.ServiceOperations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utility {
    public static ServiceOperations getService(Context ctx, String url){
        return RetroHelper.getAdapter(ctx,url).create(ServiceOperations.class);
    }
    public static void getAddressResponseModel(final Context context, String address, final IGetAddressInfo getAddressInfo){
        final Call<AddressResponseModel> responseModelCall = getService(context, "")
                .getAddressResponse(address, true, IAppConstants.API_KEY);
        responseModelCall.enqueue(new Callback<AddressResponseModel>() {
            @Override
            public void onResponse(Call<AddressResponseModel> call, Response<AddressResponseModel> response) {
                if (response.isSuccessful()) {
                    AddressResponseModel addressResponseModel = response.body();
                    if (addressResponseModel != null && addressResponseModel.getResults() != null) {

                        if (addressResponseModel.getResults().size() > 0) {
                            double latitude = addressResponseModel.getResults().get(0).getGeometry().getLocation().getLat();
                            double longitude = addressResponseModel.getResults().get(0).getGeometry().getLocation().getLng();
                            getAddressInfo.onResponse(latitude, longitude);
                        } else {
                            getAddressInfo.onResponse(0, 0);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressResponseModel> call, Throwable t) {
                Toast.makeText(context, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                getAddressInfo.onResponse(0, 0);
            }
        });
    }
}
