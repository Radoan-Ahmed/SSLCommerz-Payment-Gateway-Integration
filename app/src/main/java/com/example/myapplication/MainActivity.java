package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCSdkMainResponseModel;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCBaseSuccessResponseListener;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class MainActivity extends AppCompatActivity implements SSLCTransactionResponseListener {


    TextView success,fail,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        success = findViewById(R.id.success);
        fail = findViewById(R.id.fail);
        cancel = findViewById(R.id.cancel);


        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization("daffo624ae963e28ce","daffo624ae963e28ce@ssl", 10, SSLCCurrencyType.BDT,"123456789098765",
                "yourProductType", SSLCSdkType.TESTBOX);


        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("customer name","customer email",
"address","dhaka","1214","Bangladesh","phoneNumber");

        final SSLCProductInitializer productInitializer = new SSLCProductInitializer ("food","food",
        new SSLCProductInitializer.ProductProfile.TravelVertical("Travel","10","A","12","Dhk-Syl"));


        final SSLCShipmentInfoInitializer shipmentInfoInitializer = new SSLCShipmentInfoInitializer("Courier", 2, new SSLCShipmentInfoInitializer.ShipmentDetails("AA","Address 1",
"Dhaka","1000","BD"));



        IntegrateSSLCommerz
                .getInstance(MainActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .addProductInitializer(productInitializer)
                .buildApiCall(this);



    }



    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        success.setText(sslcTransactionInfoModel.getAPIConnect()+"---"+sslcTransactionInfoModel.getStatus());
    }

    @Override
    public void transactionFail(String s) {
        fail.setText(s);
    }

    @Override
    public void merchantValidationError(String s) {
        cancel.setText(s);
    }
}

