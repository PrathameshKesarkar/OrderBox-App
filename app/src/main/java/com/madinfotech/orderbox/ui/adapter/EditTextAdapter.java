package com.madinfotech.orderbox.ui.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.ui.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prathameshkesarkar on 21/07/16.
 */
public class EditTextAdapter extends ArrayAdapter<Customer> implements Filterable {

    List<Customer> customersList;
    List<Customer> mOriginalList;
    LayoutInflater inflater;

    int type;
    private NameFilter nameFilter;

    public EditTextAdapter(Context context, int resource, List<Customer> customersList, int type) {
        super(context, resource, customersList);
        this.customersList = customersList;
        mOriginalList = new ArrayList<>(customersList);
        this.inflater = LayoutInflater.from(context);
        this.type = type;
    }

    @Override
    public Filter getFilter() {
        if (nameFilter == null && type == Constants.FILTER.CUSTOMER_NAME) {
            nameFilter = new CustomerNameFilter();
        } else if (nameFilter == null & type == Constants.FILTER.PHONE_NUMBER) {
            nameFilter = new PhoneNameFilter();
        }
        return nameFilter;
    }

    @Override
    public int getCount() {
        if (customersList != null)
            return customersList.size();
        return 0;
    }

    @Override
    public Customer getItem(int i) {
        return customersList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Customer customer = customersList.get(i);
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.suggestion_adapter_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.customerNameTextView.setText(customer.getCustomerName());
        holder.phoneNumberTextView.setText(customer.getPhoneNo());
        return view;
    }

    private static class ViewHolder {
        TextView customerNameTextView;
        TextView phoneNumberTextView;

        ViewHolder(View view) {
            customerNameTextView = (TextView) view.findViewById(R.id.customer_name_edit_view_adapter);
            phoneNumberTextView = (TextView) view.findViewById(R.id.phone_number_edit_view_adapter);
        }

    }

    private abstract class NameFilter extends Filter {

        @Override
        abstract protected FilterResults performFiltering(CharSequence charSequence);

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.values != null) {
                customersList = (List<Customer>) filterResults.values;
                clear();
                notifyDataSetChanged();
            } else {
                customersList.clear();
                clear();
                notifyDataSetChanged();

            }
            notifyDataSetInvalidated();

        }
    }

    private class CustomerNameFilter extends NameFilter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            ArrayList<Customer> list;
            if (charSequence == null || charSequence.length() == 0) {
                list = new ArrayList<>(mOriginalList);
                results.values = list;
                results.count = list.size();

            } else {
                String prefixString = charSequence.toString().toLowerCase();
                list = new ArrayList<>(customersList.size());
                for (Customer customer : customersList) {
                    if (customer.getCustomerName() == null) {
                        continue;
                    }
                    if (customer.getCustomerName().toLowerCase().matches(".*" + prefixString + ".*")) {
                        list.add(customer);
                    }
                    results.values = list;
                    results.count = list.size();
                }
            }
            return results;
        }
    }

    private class PhoneNameFilter extends NameFilter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            ArrayList<Customer> list;
            if (charSequence == null || charSequence.length() == 0) {
                list = new ArrayList<>(mOriginalList);
                results.values = list;
                results.count = list.size();

            } else {
                String prefixString = charSequence.toString();
                list = new ArrayList<>(customersList.size());
                for (Customer customer : customersList) {
                    if (customer.getPhoneNo()==null){
                        continue;
                    }
                    if (customer.getPhoneNo().matches(prefixString + ".*")) {
                        list.add(customer);
                    }
                    results.values = list;
                    results.count = list.size();
                }
            }
            return results;
        }
    }
}
