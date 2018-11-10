package com.nibl.api.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Comparator;

public class PackComparator implements Comparator<Object> {

    final String sortBy;
    final String sortOrder;

    public PackComparator(String sortBy, String sortOrder) {
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Object o1, Object o2) {

        try {
            Field field1 = o1.getClass().getDeclaredField(sortBy);
            Field field2 = o2.getClass().getDeclaredField(sortBy);

            field1.setAccessible(true); // because the fields in Impianto entity has "private"
            field2.setAccessible(true);

            if (o1.getClass().getDeclaredField(sortBy).getType() == Integer.class) {
                Integer d1 = (Integer) field1.get(o1);
                Integer d2 = (Integer) field2.get(o2);
                return (sortOrder.toLowerCase().equals("asc")) ? d1.compareTo(d2) : d2.compareTo(d1);
            } else if (o1.getClass().getDeclaredField(sortBy).getType() == Long.class) {
                Long d1 = (Long) field1.get(o1);
                Long d2 = (Long) field2.get(o2);
                return (sortOrder.toLowerCase().equals("asc")) ? d1.compareTo(d2) : d2.compareTo(d1);
            } else if (o1.getClass().getDeclaredField(sortBy).getType() == Timestamp.class) {
                Timestamp d1 = (Timestamp) field1.get(o1);
                Timestamp d2 = (Timestamp) field2.get(o2);
                return (sortOrder.toLowerCase().equals("asc")) ? d1.compareTo(d2) : d2.compareTo(d1);
            } else {
                String d1 = (String) field1.get(o1);
                String d2 = (String) field2.get(o2);
                return (sortOrder.toLowerCase().equals("asc")) ? d1.compareTo(d2) : d2.compareTo(d1);
            }
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Missing variable sortBy");
        } catch (ClassCastException e) {
            throw new RuntimeException("sortBy is not found in class list");
        } catch (IllegalArgumentException e) {
            // shoud not happen
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}