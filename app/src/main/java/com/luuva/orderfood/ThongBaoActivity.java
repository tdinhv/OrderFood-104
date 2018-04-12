package com.luuva.orderfood;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.luuva.adapter.NewAdapter;

import java.util.ArrayList;

public class ThongBaoActivity extends AppCompatActivity {
    private ListView lvNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        lvNew = findViewById(R.id.lv_New);
        ArrayList<com.luuva.model.New> arrNew = new ArrayList<>();

        com.luuva.model.New objnew1 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew2 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew3 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew4 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew5 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew6 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);
        com.luuva.model.New objnew7 = new com.luuva.model.New("[HN]-Giải nhiệt cực đã với TRÀ SỮA ROYAL TREA giảm 30% giao tận cửa","Chon ngay cho mình một hương vị trà của Royaltea để tiếp thêm năng luwongjcho buổi chiều làm việc ngày thứ 2 nhé.Rủ ngay cạ trà sữa cùng order về để được giảm 30% thôi nào.Áp dụng 8-22h hết 31/3", R.drawable.anh1);

        arrNew.add(objnew1);
        arrNew.add(objnew2);
        arrNew.add(objnew3);
        arrNew.add(objnew4);
        arrNew.add(objnew5);
        arrNew.add(objnew6);
        arrNew.add(objnew7);

        NewAdapter customAdaper = new NewAdapter(this, R.layout.list_new,arrNew);
        lvNew.setAdapter(customAdaper);

    }
}
