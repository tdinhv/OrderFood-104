package com.luuva.orderfood;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luuva.adapter.UserAdapter;
import com.luuva.background.UserSession;
import com.luuva.model.User;
import com.luuva.model.UserOption;

import java.util.ArrayList;
public class UserFragment extends Fragment {
    ListView lvItem;
    ArrayList<UserOption> dsUser;
    UserAdapter userAdapter;
    TextView tvTT,tvFullname;
    Button btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tvTT = view.findViewById(R.id.btnInformation);
        tvFullname = view.findViewById(R.id.fullNameUser);
        btnLogout = view.findViewById(R.id.btnlogout);
        final UserSession session = new UserSession(view.getContext());
        final User userLogin = session.getUserLogin();
        if(userLogin!=null) tvFullname.setText(userLogin.getFullname());
        lvItem = view.findViewById(R.id.lvItem);

        dsUser = new ArrayList<>();

        dsUser.add(new UserOption("Lịch sử", R.drawable.icon_1));
        dsUser.add(new UserOption("Yêu thích", R.drawable.icon_2));
        dsUser.add(new UserOption("PayNow", R.drawable.icon_3));
        dsUser.add(new UserOption("Điạ chỉ", R.drawable.icon_4));
        dsUser.add(new UserOption("Hoá đơn", R.drawable.icon_5));
        dsUser.add(new UserOption("Mời bạn bè", R.drawable.icon_6));
        dsUser.add(new UserOption("Góp ý", R.drawable.icon_7));
        dsUser.add(new UserOption("Chính sách quy định", R.drawable.icon_8));
        dsUser.add(new UserOption("Cài đặt", R.drawable.icon_9));

        userAdapter = new UserAdapter(getActivity(),R.layout.user_item,dsUser);
        lvItem.setAdapter(userAdapter);
        tvTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(view.getContext(),"Test",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),UserInformation.class) ;
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userLogin!=null){
                    if(session.delUserLoginSession(view.getContext())){
                        Toast.makeText(view.getContext(),"Đã đăng xuất",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(view.getContext(),MainLoginActivity.class));
                    }
                }
            }
        });
        return view;
    }
    public void thongTin(View view){
        Intent intent = new Intent(view.getContext(),UserInformation.class) ;
        startActivity(intent);
    }
}
