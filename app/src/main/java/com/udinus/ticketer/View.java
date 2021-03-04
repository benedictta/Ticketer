package com.udinus.ticketer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

public class View extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private tiketAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageButton back;

    DatabaseHelper bantuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        bantuDB = new DatabaseHelper(this);
        back = (ImageButton)findViewById(R.id.kembali4);

        back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                onBackPressed();
            }
        });
        Cursor get =bantuDB.getAllData();
        final ArrayList<ticket_item> ticketList = new ArrayList<>();
        get.moveToFirst();
        if (get.getCount() == 0) {
            ticketList.add(new ticket_item("NO TICKET(S) FOUND",""));
        }
        else
        {
            for (int count = 0; count < get.getCount(); count++)
            {
                get.moveToPosition(count);
                ticketList.add(new ticket_item(get.getString(1),get.getString(0)));
            }
        }

        mRecyclerView = findViewById(R.id.tiket_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new tiketAdapter(ticketList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new tiketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                String idx = ticketList.get(position).getNoktp();
                Cursor getData = bantuDB.getAllData();

                StringBuffer buffer = new StringBuffer();
                while (getData.moveToNext())
                {
                    if(getData.getString(0).matches(idx))
                    {
                        buffer.append("Nama:\n" + getData.getString(1) + "\n\n");
                        buffer.append("Kereta:\n" + getData.getString(2) + "\n\n");
                        buffer.append("Kelas:\n" + getData.getString(3) + "\n\n");
                        buffer.append("Asal:\n" + getData.getString(4) + "\n\n");
                        buffer.append("Tujuan:\n" + getData.getString(5) + "\n\n");
                        buffer.append("Tanggal Keberangkatan:\n" + getData.getString(6) + "\n\n");
                        buffer.append("Keberangkatan:\n" + getData.getString(7) + "\n\n");
                        buffer.append("Kedatangan:\n" + getData.getString(8) + "\n\n");
                        buffer.append("Harga:\n Rp." + getData.getString(9));
                    }
                }
                if(!idx.matches(""))
                {
                    showMessage("Detail Tiket", buffer.toString());
                }

            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(com.udinus.ticketer.View.this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
