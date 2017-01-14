package com.madinfotech.orderbox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.model.Order;
import com.madinfotech.orderbox.ui.screens.fragment.DashBoardFragment;
import com.madinfotech.orderbox.ui.screens.view.DayLeftView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by prathameshkesarkar on 23/07/16.
 */
public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder> {

    LayoutInflater inflater;
    List<Order> orderList;

    public DashBoardAdapter(LayoutInflater inflater, List<Order> orderList) {
        this.inflater = inflater;
        this.orderList = orderList;
    }

    @Override
    public DashBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DashBoardViewHolder(inflater.inflate(R.layout.dashboard_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DashBoardViewHolder holder, int position) {
        holder.bind(orderList.get(position));
    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class DashBoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Unbinder unbinder;

        @BindView(R.id.customer_name_dash_item)
        TextView customerNameTxtView;

        @BindView(R.id.delivery_date_dash_item)
        TextView deliveryDateTxtView;

        @BindView(R.id.order_id_dash_item)
        TextView orderIdTxtView;

        @BindView(R.id.order_desc_dash_item)
        TextView orderDescriptionTxtView;

        @BindView(R.id.dash_item_action)
        LinearLayout dashActionLinearLayout;

        @BindView(R.id.day_left_view)
        DayLeftView dayLeftView;

        public DashBoardViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }


        public void bind(Order order) {
            customerNameTxtView.setText(order.getCustomer().getCustomerName());
            orderIdTxtView.setText(String.valueOf(order.getOrderId()).concat(" | "));
            orderDescriptionTxtView.setText(order.getDescription());

            Calendar cal = Calendar.getInstance();
            Date todaysDate = cal.getTime();
            Date deliverDate = order.getDeliveryDate();
            cal.setTime(deliverDate);

            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);

            String date = day + "/" + (month + 1) + "/" + year;
            deliveryDateTxtView.setText(date);

            long diffDate = (deliverDate.getTime() - todaysDate.getTime()) / (24 * 60 * 60 * 1000);

            dayLeftView.setDayLeftDate(diffDate);
            dayLeftView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.day_left_view) {
                dayLeftView.setDayLeftDate(0);

            }
            else {
                if (dashActionLinearLayout.getVisibility() == View.GONE)
                    dashActionLinearLayout.setVisibility(View.VISIBLE);
                else
                    dashActionLinearLayout.setVisibility(View.GONE);

            }
        }
    }

}
