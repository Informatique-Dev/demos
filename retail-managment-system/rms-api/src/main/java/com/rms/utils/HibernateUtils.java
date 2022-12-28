package com.rms.utils;

import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class HibernateUtils {

    public static boolean isConvertable(Set<?> set) {
        if (set == null)
            return false;
        return Hibernate.isInitialized(set);
    }

    public static boolean isConvertable(List<?> list) {
        return Hibernate.isInitialized(list);
    }

    public static boolean isConvertable(Object obj) {
        return Hibernate.isInitialized(obj);
    }

}
