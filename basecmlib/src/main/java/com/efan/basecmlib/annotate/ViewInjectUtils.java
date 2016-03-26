package com.efan.basecmlib.annotate;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 一帆 on 2016/3/20.
 */
public class ViewInjectUtils {

    private static void injectViews(Object object, View sourceView){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if(viewInject != null){
                int viewId = viewInject.id();
                if(viewId != -1){
                    try {
                        field.setAccessible(true);
                        field.set(object, sourceView.findViewById(viewId));
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
                method.invoke(activity, layoutId);
               // method.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void inJectClickEvent(Object object, View sourceView){
        Method[] methods = object.getClass().getDeclaredMethods();
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
                        DynamicHandler handler = new DynamicHandler(object);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[] { listenerType }, handler);
                        for (int viewId : value)
                        {
                            sourceView.findViewById(viewId)
                                    .setOnClickListener((View.OnClickListener) object);
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
        injectViews(activity, activity.getWindow().getDecorView());
        inJectClickEvent(activity, activity.getWindow().getDecorView());
    }

    public static void inJect(Fragment frag,View sourceView) {
        injectViews(frag, sourceView);
        inJectClickEvent(frag, sourceView);
    }
}
