package com.madinfotech.orderbox.ui.screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.madinfotech.orderbox.OrderBoxApplication;
import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.repository.OrderRealmRepository;
import com.madinfotech.orderbox.repository.specification.FindAllCustomerSpecification;
import com.madinfotech.orderbox.ui.adapter.EditTextAdapter;
import com.madinfotech.orderbox.ui.screens.presenter.AddTaskPresenter;
import com.madinfotech.orderbox.ui.screens.viewInterface.AddTaskView;
import com.madinfotech.orderbox.ui.util.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by prathameshkesarkar on 16/07/16.
 */
public class AddTaskActivity extends BaseActivity implements View.OnClickListener, AddTaskView, View.OnFocusChangeListener {

    @BindView(R.id.customer_name_text_layout)
    TextInputLayout customerNameTextInput;

    @BindView(R.id.date_image_button)
    ImageButton dateImageButton;

    @BindView(R.id.new_order_editText)
    EditText orderIdEditText;

    @BindView(R.id.particular_edit_text)
    EditText particularEditText;

    @BindView(R.id.particular_text_input)
    TextInputLayout particularTextLayout;

    @BindView(R.id.amount_text_input)
    TextInputLayout amountTextLayout;

    @BindView(R.id.delivery_date_text_layout)
    TextInputLayout dateTextLayout;

    @BindView(R.id.customer_name_sugges_edit_text)
    AutoCompleteTextView customerAutoCompleteEditText;

    @BindView(R.id.phone_number_sugges_edit_text)
    AutoCompleteTextView phoneAutoCompleteEditText;

    @BindView(R.id.phone_number_text_layout)
    TextInputLayout phoneNumberTextLayout;

    @BindView(R.id.add_task_button)
    Button doneButton;

    @BindView(R.id.phone_number_button)
    ImageButton phoneNumberButton;

    @Inject
    Calendar calendar;

    @BindView(R.id.customer_name_button)
    ImageButton custerNameButton;

    @Inject
    AddTaskPresenter addTaskPresenter;

    @Inject
    OrderRealmRepository realmRepository;

    EditTextAdapter customerAdapter;
    EditTextAdapter phoneAdapter;

    private int year, month, day;

    Unbinder unBinder;
    List<Customer> customerList;
    InputMethodManager imm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        //Binding ButterKnife
        unBinder = ButterKnife.bind(this);
        //Attaching Presenter
        addTaskPresenter.setView(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        //TODO initialze Adapter for phone and customer
        customerList = realmRepository.searchCustomer(new FindAllCustomerSpecification());
        customerAdapter = new EditTextAdapter(AddTaskActivity.this, 0, customerList, Constants.FILTER.CUSTOMER_NAME);
        phoneAdapter = new EditTextAdapter(AddTaskActivity.this, 0, customerList, Constants.FILTER.PHONE_NUMBER);

        //Set height
        customerAutoCompleteEditText.setDropDownHeight(220);
        phoneAutoCompleteEditText.setDropDownHeight(220);

        //setFocus Listener
        customerAutoCompleteEditText.setOnFocusChangeListener(this);
        phoneAutoCompleteEditText.setOnFocusChangeListener(this);
        particularEditText.setOnFocusChangeListener(this);

        //setItemClickListener
        customerAutoCompleteEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Customer customer;
                customer = customerAdapter.getItem(pos);
                listClickActions(customer);
            }
        });
        phoneAutoCompleteEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Customer customer;
                customer = phoneAdapter.getItem(pos);
                listClickActions(customer);
            }
        });

        //Clear button for Phone no and customer name
        custerNameButton.setOnClickListener(this);
        phoneNumberButton.setOnClickListener(this);


        customerAutoCompleteEditText.requestFocus();

        //disable editing on dateEditText
        dateTextLayout.getEditText().setKeyListener(null);
        dateImageButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }


    @Override
    public void setupActivityComponent() {
        ((OrderBoxApplication) getApplication()).createAddTaskComponent().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((OrderBoxApplication) getApplication()).releaseAddTaskComponent();
        addTaskPresenter.unBindView();

        unBinder.unbind();
    }

    @Override
    public void onClick(View view) {
        //Clicking on Calender view
        if (view == dateImageButton) {

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            view.requestFocus();
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonthOfYear, int selectedDayOfMonth) {
                    Date todaysDate = calendar.getTime();

                    calendar.set(Calendar.DAY_OF_MONTH, selectedDayOfMonth);
                    calendar.set(Calendar.MONTH, selectedMonthOfYear);
                    calendar.set(Calendar.YEAR, selectedYear);

                    Date selectedDate = calendar.getTime();

                    if (todaysDate.compareTo(selectedDate) > 0) {
                        enableDateError();
                        setDateError();
                    } else {
                        disableDateError();
                        String date = selectedDayOfMonth + "/" + (selectedMonthOfYear+1) + "/" + selectedYear;
                        addTaskPresenter.setDate(selectedDate);
                        showDateLabel(date);
                    }
                }
            }, year, month, day).show();

        }
        //Call Presenter to save the Order
        else if (view == doneButton) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            addTaskPresenter.saveOrder();
        }
        //Both Customer Name and Phone Number has similar functionality
        else if (view == custerNameButton || view == phoneNumberButton) {
            addTaskPresenter.removeCustomer();
            // If both CustomerEditText and PhoneEditText are disable
            if (!customerAutoCompleteEditText.isEnabled() || !phoneAutoCompleteEditText.isEnabled()) {

                //clear the textView
                customerAutoCompleteEditText.setText("");
                phoneAutoCompleteEditText.setText("");

                //allow editing
                customerAutoCompleteEditText.setEnabled(true);
                phoneAutoCompleteEditText.setEnabled(true);
            }
            //If editing is allowed clear respecting editText
            else if (customerAutoCompleteEditText.isEnabled())
                customerAutoCompleteEditText.setText("");
            else if (phoneAutoCompleteEditText.isEnabled()) {
                phoneAutoCompleteEditText.setText("");
            }
        }
    }

    @Override
    public void setDateError() {
        dateTextLayout.setError("Select Present or Future date");
    }

    @Override
    public void enableDateError() {
        dateTextLayout.setErrorEnabled(true);
    }

    @Override
    public void disableDateError() {
        dateTextLayout.setErrorEnabled(false);
    }

    @Override
    public void showDateLabel(String date) {
        dateTextLayout.getEditText().setText(date);
    }

    @Override
    public String getParticular() {
        return particularEditText.getText().toString();
    }

    @Override
    public String getAmount() {
        return amountTextLayout.getEditText().getText().toString();
    }

    @Override
    public String getDeliveryDate() {
        return dateTextLayout.getEditText().getText().toString();
    }

    @Override
    public String getOrderId() {
        return orderIdEditText.getText().toString();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getCustomerName() {
        return customerAutoCompleteEditText.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return phoneAutoCompleteEditText.getText().toString();
    }

    @Override
    public void showParticularErrorMessage() {
        particularTextLayout.setErrorEnabled(true);
        particularTextLayout.setError("Particular Cannot be blank");
    }

    @Override
    public void showCustomerNameErrorMessage() {
        customerNameTextInput.setErrorEnabled(true);
        customerNameTextInput.setError("Customer Name cannot be blank");
    }

    @Override
    public void showPhoneNumberErrorMessage() {
        phoneNumberTextLayout.setErrorEnabled(true);
        phoneNumberTextLayout.setError("Phone Number Cannot be blank");
    }

    @Override
    public void deliverDateErrorMessage() {
        dateTextLayout.setErrorEnabled(true);
        dateTextLayout.setError("Delivery date cannot be blank");
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (view == customerAutoCompleteEditText) {
            if (hasFocus) {
                //I added 0 cause I am not using the Layout passed here any ways
                customerAutoCompleteEditText.setAdapter(customerAdapter);
            } else {
                if (customerAutoCompleteEditText.getText().toString().isEmpty()) {
                    showCustomerNameErrorMessage();
                } else
                    customerNameTextInput.setErrorEnabled(false);
            }
        } else if (view == phoneAutoCompleteEditText) {
            if (hasFocus) {
                //I added 0 cause I am not using the Layout passed here any ways
                phoneAutoCompleteEditText.setAdapter(phoneAdapter);
            } else {
                if (phoneAutoCompleteEditText.getText().toString().isEmpty())
                    showPhoneNumberErrorMessage();
                else
                    phoneNumberTextLayout.setErrorEnabled(false);
            }
        } else if (view == particularEditText) {
            if (!hasFocus) {
                if (particularEditText.getText().toString().isEmpty())
                    showParticularErrorMessage();
                else
                    particularTextLayout.setErrorEnabled(false);
            }
        } else if (view == dateTextLayout.getEditText()) {
            if (!hasFocus) {
                if (dateTextLayout.getEditText().getText().toString().isEmpty()) {
                    deliverDateErrorMessage();
                } else
                    dateTextLayout.setErrorEnabled(false);
            }
        }
    }

    private void listClickActions(Customer customer) {
        addTaskPresenter.setCustomer(customer);
        customerAutoCompleteEditText.setText(customer.getCustomerName());
        phoneAutoCompleteEditText.setText(customer.getPhoneNo());
        customerAutoCompleteEditText.setEnabled(false);
        phoneAutoCompleteEditText.setEnabled(false);
        customerNameTextInput.setErrorEnabled(false);
        phoneNumberTextLayout.setErrorEnabled(false);
    }
}
