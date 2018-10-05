package com.procon.googlemaps;

import com.procon.googlemaps.models.AddressResponseModel;

public interface IGetAddressInfo {
    void onResponse(double latitude, double longitude);
}
