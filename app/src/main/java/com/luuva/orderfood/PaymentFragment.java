package com.luuva.orderfood;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.luuva.adapter.PaymentAdapter;
import com.luuva.adapter.UserAdapter;
import com.luuva.model.Payment;
import com.luuva.model.User;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {
    ListView lvItem_pay;
    ArrayList<Payment> dsPayment;
    PaymentAdapter payAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        lvItem_pay = view.findViewById(R.id.lvItem_pay);
        dsPayment = new ArrayList<>();

        dsPayment.add(new Payment("Ví điện tử AirPay", R.drawable.pay1));
        dsPayment.add(new Payment("PayNow Credits", R.drawable.pay2));
        dsPayment.add(new Payment("Credit VISA/MASTER/JCB", R.drawable.pay31));
        dsPayment.add(new Payment("Tiền mặt", R.drawable.pay41));
        dsPayment.add(new Payment("Internet Banking", R.drawable.icon_5));

        payAdapter = new PaymentAdapter(getActivity(),R.layout.payment_item,dsPayment);
        lvItem_pay.setAdapter(payAdapter);
        return view;
    }
}

/*@Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_user,container,false);
            tvTT = view.findViewById(R.id.btnInformation);
            lvItem = view.findViewById(R.id.lvItem);

            items = new ArrayList<>();
            items.add("Lịch sử");
            items.add("Yêu thích");
            items.add("");
            items.add("Điạ chỉ");PayNow
            items.add("Hóa đơn");
            items.add("Mời bạn bè");
            items.add("Góp ý");
            items.add("Chính sách quy định");
            items.add("Cài đặt");

            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
            lvItem.setAdapter(adapter);
            tvTT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thongTin(view);
                }
            });
            return view;
        }

        public void thongTin(View view){
            Intent intent = new Intent(view.getContext(),UserInformation.class) ;
            startActivity(intent);
        }*/