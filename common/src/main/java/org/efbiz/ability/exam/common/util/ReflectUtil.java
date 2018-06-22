package org.efbiz.ability.exam.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReflectUtil {

  public static Field[] getFields(Object target) {
    if (target == null) {
      return null;
    }
    Class<? extends Object> clazz = target.getClass();
    Field[] fields = clazz.getDeclaredFields();

    return fields;
  }

  public static void setFieldValue(Object target, String name, Object value) {
    if (target == null || name == null || "".equals(name) || (value == null)) {
      return;
    }
    Class<? extends Object> clazz = target.getClass();

    try {
      Method method = clazz
          .getDeclaredMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1));

      if (!Modifier.isPublic(method.getModifiers())) {
        method.setAccessible(true);
      }
      method.invoke(target, value);

    } catch (Exception e) {
      if (log.isDebugEnabled()) {
        log.debug(e);
      }
      try {
        Field field = clazz.getDeclaredField(name);
        if (!Modifier.isPublic(field.getModifiers())) {
          field.setAccessible(true);
        }
        field.set(target, value);
      } catch (Exception fe) {
        if (log.isDebugEnabled()) {
          log.debug(fe);
        }
      }
    }
  }

  public static Object getFieldValue(Object target, String name) {

    if (target == null || name == null || ".".equals(name)) {
      return null;
    }
    Class<? extends Object> clazz = target.getClass();
    try {
      String methodName = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
      Method method = clazz.getDeclaredMethod(methodName);

      if (!Modifier.isPublic(method.getModifiers())) {
        method.setAccessible(true);
      }

      return method.invoke(target);
    } catch (Exception e) {
      try {
        Field field = null;
        while (field == null) {

          try {
            field = clazz.getDeclaredField(name);
          } catch (Exception em) {

          }
          if (field == null) {
            clazz = clazz.getSuperclass();
          }
        }
        if (!Modifier.isPublic(field.getModifiers())) {
          field.setAccessible(true);
        }
        return field.get(target);
      } catch (Exception ex) {
      }
    }
    return null;
  }
}
