package com.omg.xbase.hibernate;

import java.beans.*;
import java.util.*;
import java.util.Map.*;
import java.lang.reflect.*;
import java.text.*;

/**
 * ����ת��������ֻҪ�����������������ͬ(��Ȼ��Сд)��������ʲô���ͣ�����ת��������¶������ת��
 * ���ӣ�
 * //Ϊ�����Ч�ʣ����þ�̬����
    private static final ObjectConvert convertor = new ObjectConvert(DCMMDADownloadApp.class,MDADownloadAppVO.class);
    MDADownloadAppVO obj = (MDADownloadAppVO) convertor.convert(vo);//voΪDCMMDADownloadApp��ʵ��
 * @author 
 * @version 1.0
 */
public class ObjectConvert {
    private Class clazzA;
    private Class clazzB;
    private Map properMapA;
    private Map properMapB;
    private boolean isNeedConvert;
    private static Object[] getParams = new Object[0];
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ObjectConvert() {
    }

    public ObjectConvert(Class clazzA, Class clazzB) {
        this.clazzA = clazzA;
        this.clazzB = clazzB;
        if (clazzA.equals(clazzB)) {
            isNeedConvert = false;
        } else {
            isNeedConvert = true;
        }
        init();
    }
    /**
     * ������ת������һ������
     * @param obj Object
     * @return Object
     * @throws AppException
     */
    public Object convert(Object obj) {
        if (obj == null) {
            return null;
        }
        Object result = null;
        try {
//            if (!isNeedConvert) {
//                //������ͬ��ֱ�ӷ���
//                return obj;
//            }
            Class src = obj.getClass();
            if (src.equals(clazzA)) {
                result = clazzB.newInstance();
                convertProp(obj, result, properMapA, properMapB);
            } else {
                result = clazzA.newInstance();
                convertProp(obj, result, properMapB, properMapA);
            }
        } catch (Exception ex) {
        }
        return result;
    }

    /**
     * ��һ�������lstת������һ�ֶ��������
     * @param objlst List
     * @return Object[]
     * @throws AppException
     */
    public Object[] convert(List objlst) {
        if (objlst == null || objlst.size()==0) {
            return null;
        }
        int j=objlst.size();
        Class desClass = clazzA;
        Class srcClass  = objlst.get(0).getClass();
        if(srcClass.equals(clazzA))
        {
        	desClass = clazzB;
        }
        Object[] result = (Object[])Array.newInstance(desClass, j);
        for(int i=0;i<j;i++){
            Object srcObj = objlst.get(i);
            Object des = convert(srcObj);
            result[i]=des;
        }
        return result;
    }

    /**
        * ��һ�ֶ��������ת������һ�ֶ��������
        * @param objlst List
        * @return Object[]
        * @throws AppException
        */
    public Object[] convert(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return null;
        }
        int j = objs.length;
        Class desClass = clazzA;
        Class srcClass  = objs[0].getClass();
        if(srcClass.equals(clazzA))
        {
        	desClass = clazzB;
        }
        Object[] result = (Object[])Array.newInstance(desClass, j);
        for (int i = 0; i < j; i++) {
            Object des = convert(objs[i]);
            result[i] = des;
        }
        return result;
    }

    private void convertProp(Object obj, Object result, Map srcMap, Map descMap) throws
            Exception {
        String propName = null;
        PropertyDescriptor prop = null;
        Class propSrcType = null;
        Class propDestType = null;
        Iterator its = srcMap.entrySet().iterator();
        java.util.Map.Entry entry = null;
        while (its.hasNext()) {
            entry = (java.util.Map.Entry) its.next();
            propName = (String) entry.getKey();
            prop = (PropertyDescriptor) entry.getValue();
            Object propValues = prop.getReadMethod().invoke(obj, getParams);
            if (propValues == null) {
                //ֵΪ�գ�Ŀ������Ӧ����ֵҲΪ��
                continue;
            }
            String value = convert2String(propValues);
            PropertyDescriptor target = (PropertyDescriptor) descMap.get(propName.toUpperCase());
            if (target == null) {
                continue;
            }
            propSrcType = prop.getPropertyType();
            propDestType = target.getPropertyType();
            if (propSrcType.equals(propDestType) ||
                propSrcType.isAssignableFrom(propDestType) ||
                propDestType.isAssignableFrom(propSrcType)) {
                //�������һ�£�ֱ�ӿ���ֵ
                target.getWriteMethod().invoke(result, new Object[] {propValues});
                continue;
            }
            if (Boolean.TYPE.equals(propDestType) ||
                Boolean.class.isAssignableFrom(propDestType)) {
                //Ŀ����boolean/Boolean
                Object vv = new Boolean(false);
                if (value.equalsIgnoreCase("1") ||
                    value.equalsIgnoreCase("true") ||
                    value.equalsIgnoreCase("��") || value.equalsIgnoreCase("OK")) {
                    vv = new Boolean(true);
                }
                target.getWriteMethod().invoke(result, new Object[] {vv});
                continue;
            }
            if (Date.class.isAssignableFrom(propDestType)) {
                //Ŀ����Date
                target.getWriteMethod().invoke(result,new Object[] {getDateByStrYMDHMS(value)});
                continue;
            }
            //�����ʽ
            target.getWriteMethod().invoke(result,new Object[] {parseBasicType(propDestType,value)});
            continue;
        }
    }

    /**
     * yyyy-MM-dd hh:mm:ss��ʽ���ַ�ת����Date��ʽ
     * @param str String yyyy-MM-dd hh:mm:ss��ʽ���ַ�
     * @return Date
     */
    private Date getDateByStrYMDHMS(String str) {
        Date t = null;
        try {
            t = formatter.parse(str);
        } catch (Exception ex) {
        }
        return t;
    }

    private String getDateStrYMDHMS(Date date) {
        String str = null;
        if (date != null) {
            try {
                str = formatter.format(date);
            } catch (Exception ex) {
            }
        }
        return str;
    }

    private Object parseBasicType(Class type, String str) {
         try {
             if (str == null) {
                 return null;
             }
             if (type.equals(Integer.TYPE) ||
                 Integer.class.isAssignableFrom(type)) {
                 if (str.trim().length() == 0) {
                     return null;
                 }
                 return new Integer(str);
             } else if (type.equals(Long.TYPE) ||
                        Long.class.isAssignableFrom(type)) {
                 if (str.trim().length() == 0) {
                     return null;
                 }
                 return new Long(str);
             } else if (type.equals(Short.TYPE) ||
                        Short.class.isAssignableFrom(type)) {
                 if (str.trim().length() == 0) {
                     return null;
                 }
                 return new Short(str);
             } else if (type.equals(Byte.TYPE) ||
                        Byte.class.isAssignableFrom(type)) {
                 return new Byte(str);
             } else if (type.equals(Float.TYPE) ||
                        Float.class.isAssignableFrom(type)) {
                 if (str.trim().length() == 0) {
                     return null;
                 }
                 return new Float(str);
             } else if (type.equals(Double.TYPE) ||
                        Double.class.isAssignableFrom(type)) {
                 if (str.trim().length() == 0) {
                     return null;
                 }
                 return new Double(str);
             } else if (type.equals(Character.TYPE) ||
                        Character.class.isAssignableFrom(type)) {
                 if (str.length() >= 1) {
                     return new Character(str.charAt(0));
                 } else {
                     return new Character((char) 0);
                 }
             } else if (String.class.isAssignableFrom((type))) {
                 return str;
             }
         } catch (Exception ex) {
         }
        return null;
    }

    private String convert2String(Object value) {
        if (Boolean.TYPE.equals(value.getClass()) ||
            Boolean.class.isAssignableFrom(value.getClass())) {
            Boolean b = (Boolean) value;
            if (b.booleanValue()) {
                return "1";
            } else {
                return "0";
            }
        }
        if (Date.class.isAssignableFrom(value.getClass())) {
            return this.getDateStrYMDHMS((Date)value);
        }

        return value.toString().trim();
    }

    private void init() {
        if (properMapA != null && properMapB != null) {
            //�Ѿ���ʼ��
            return;
        }
        properMapA = new HashMap();
        properMapB = new HashMap();
        BeanInfo voinfo = null;
        try {
            voinfo = Introspector.getBeanInfo(clazzA,
                                              Introspector.USE_ALL_BEANINFO);
            PropertyDescriptor[] props = voinfo.getPropertyDescriptors();
            for (int i = 0; i < props.length; i++) {
                if (props[i].getWriteMethod() == null || props[i].getReadMethod() == null) {
                    continue;
                }
                String propName = props[i].getName();
                properMapA.put(propName.toUpperCase(), props[i]);
            }

            voinfo = Introspector.getBeanInfo(clazzB,
                                              Introspector.USE_ALL_BEANINFO);
            PropertyDescriptor[] prop = voinfo.getPropertyDescriptors();
            for (int i = 0; i < prop.length; i++) {
                if (prop[i].getWriteMethod() == null || prop[i].getReadMethod() == null) {
                    continue;
                }
                String propName = prop[i].getName();
                properMapB.put(propName.toUpperCase(), prop[i]);
            }
        } catch (Exception ex) {
        }
    }
}
