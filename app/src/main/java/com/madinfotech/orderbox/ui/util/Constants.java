package com.madinfotech.orderbox.ui.util;

/**
 * Created by PrathamK on 7/9/2016.
 */
public final class Constants {
    public Constants() {
    }

    public final class Injection {
        public Injection() {
        }

        public final class Named {
            public Named() {
            }

            public static final String EMAIL_PATTERN = "email_pattern";
            public static final String API_USER_PATH = "user_path";
            public static final String LOGIN_HEADER = "header";
        }
    }

    public final class Status {
        public Status() {
        }

        public final class Code {
            public Code(){}
            public static final int NO_STATUS=0;
            public static final int READY=1;
            public static final int DELIVERED=2;
            public static final int DELAYED=3;

        }
    }
    public final class FILTER{
        public static final int CUSTOMER_NAME=0;
        public static final int PHONE_NUMBER=1;
    }
    public final class Customer{
        public  static  final int NEW_CUSTOMER=-1;
    }
}
