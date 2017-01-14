package com.madinfotech.orderbox.ui.screens.viewInterface;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public interface AddTaskView {

    void setDateError();

    void enableDateError();

    void disableDateError();

    void showDateLabel(String msg);

    String getParticular();

    String getAmount();

    String getDeliveryDate();

    String getOrderId();

    void showMessage(String msg);

    String getCustomerName();

    String getPhoneNumber();

    void showParticularErrorMessage();

    void showCustomerNameErrorMessage();

    void showPhoneNumberErrorMessage();

    void deliverDateErrorMessage();

}
