package com.efan.basecmlib.annotate;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 一帆 on 2016/3/20.
 */
public class ViewInjectUtils {

    private static void injectViews(Activity activity){
        Class<? extends Activity> activityClass =  activity.getClass();
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if(viewInject != null){
                int viewId = viewInject.id();
                if(viewId != -1){
                    try {
                        Method method = activityClass.getMethod("findViewById", int.class);
                        Object obj = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void inJectContentView(Activity activity){
        Class<? extends Activity> activityClass =  activity.getClass();
        ContentView contentView = activityClass.getAnnotation(ContentView.class);
        if(contentView != null){
            int layoutId = contentView.id();
            try {
                Method method = activityClass.getMethod("setContentView", int.class);
                Object obj = method.invoke(activity, layoutId);
                method.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void inJectClickEvent(Activity activity){
        Class<? extends Activity> activityClass =  activity.getClass();
        Method[] methods = activityClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations){
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase != null){
                    String listenerSetter = eventBase.listenerSetter();
                    Class<?> listenerType = eventBase.listenerType();
                    String methodName = eventBase.methodName();
                    try {
                        Method clickMethod = annotationType.getDeclaredMethod("value");
                        int[] value = (int[]) clickMethod.invoke(annotation, null);
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[] { listenerType }, handler);
                        for (int viewId : value)
                        {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerMethod = view.getClass()
                                    .getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void inJect(Activity activity){
        inJectContentView(activity);
        injectViews(activity);
        inJectClickEvent(activity);
    }
}
