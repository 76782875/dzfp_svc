package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.utils.StringUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlm on 2017/7/5.
 */
public class Transferdata {

    //数据库连接
    private static final String URL = "jdbc:mysql://invoice.datarj.com:3306/taxeasy";
    private static final String NAME = "root";
    private static final String PASS = "Rjxx1234";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String[] colnames; // 列名数组

    public static void main(String[] args) {
       List<Object> Objectlist=getdata("t_kpls","wljqr",0,0);
       String ClassName=generateClassName("t_kpls");
       for(int i=0;i<Objectlist.size();i++){
           if(ClassName.equals("Kpls")){
               Kpls kpls=(Kpls)Objectlist.get(i);
               System.out.println(kpls.getKplsh());
               for (int j = i + 1; j < Objectlist.size(); j++)
               {
                   Kpls kplsj=(Kpls)Objectlist.get(j);
                   if (kpls.getKplsh()==(kplsj.getKplsh()))
                   {
                       System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + kpls.getKplsh());
                   }
               }
           }else if(ClassName.equals("Jyls")){
               Jyls jyls=(Jyls)Objectlist.get(i);
               System.out.println(jyls.getDjh());
               for (int j = i + 1; j < Objectlist.size(); j++)
               {
                   Jyls jylsj=(Jyls)Objectlist.get(j);
                   if (jyls.getDjh()==(jylsj.getDjh()))
                   {
                       System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + jyls.getDjh());
                   }
               }
           }else if(ClassName.equals("Kpspmx")){
               Kpspmx kpspmx=(Kpspmx)Objectlist.get(i);
               System.out.println(kpspmx.getId());
               for (int j = i + 1; j < Objectlist.size(); j++)
               {
                   Kpspmx kpspmxj=(Kpspmx)Objectlist.get(j);
                   if (kpspmx.getId()==(kpspmxj.getId()))
                   {
                       System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + kpspmx.getId());
                   }
               }
           }else if(ClassName.equals("Jyspmx")){
               Jyspmx jyspmx=(Jyspmx)Objectlist.get(i);
               System.out.println(jyspmx.getId());
               for (int j = i + 1; j < Objectlist.size(); j++)
               {
                   Jyspmx jyspmxj=(Jyspmx)Objectlist.get(j);
                   if (jyspmx.getId()==(jyspmxj.getId()))
                   {
                       System.out.println("第" + (i + 1) + "个跟第" + (j + 1) + "个重复，值是：" + jyspmx.getId());
                   }
               }
           }

       }
        //System.out.println(JSON.toJSONString(Objectlist));
        System.out.println(Objectlist.size());
    }
    public static String generateClassName(String tablename) {
        String className = tablename.toLowerCase();
        if (className.startsWith("t_")) {
            className = className.substring(2);
        }
        return initcap(StringUtils.underlineToCamel2(className));
    }
    /**
     * 将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {
        //第2个字母就是大写字母,第1个字母是小写字母，则直接返回原字符串
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }


    public  static List<Object> getdata(String tableName,String gsdm,int djh,int kplsh){
        List<Object> list=new ArrayList<Object>();
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName(DRIVER);
            System.out.println("成功加载MySQL驱动！");
            Connection conn;
            conn = DriverManager.getConnection(URL,NAME,PASS);
            System.out.println("成功连接到数据库！");
            String model=generateClassName(tableName);
            String sql=null;
            if(model.equals("Kpls")){
                sql = "select * from "+tableName+" t where t.gsdm='"+gsdm+"' and t.fpztdm  not in('05','04') and t.djh = "+djh;    //要执行的SQL
            }else if(model.equals("Kpspmx")){
                sql = "select * from "+tableName+" t where t.gsdm='"+gsdm+"' and t.kplsh ="+kplsh;    //要执行的SQL
            }else if(model.equals("Jyspmx")){
                sql = "select * from "+tableName+" t where t.gsdm='"+gsdm+"' and t.djh = "+djh;    //要执行的SQL
            }else{
                sql = "select * from "+tableName+" t where t.gsdm='"+gsdm+"'";    //要执行的SQL
            }

            //查询数据的代码
            PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            ResultSetMetaData rsmd = pst.getMetaData();
            int size = rsmd.getColumnCount();    //统计列
            colnames = new String[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i+1).toLowerCase();//小写
            }
            if(model.equals("Kpls")){
                Kpls kpls=new Kpls();
                Field[] field = kpls.getClass().getDeclaredFields();
                while (rs.next()) {
                    Kpls kplsi=new Kpls();
                    for (int i = 0; i < colnames.length; i++) {
                        Field f = field[i];
                        f.setAccessible(true);
                        f.set(kplsi, rs.getObject(i + 1));
                    }
                    list.add(kplsi);
                }
            }else if(model.equals("Jyls")){
                Jyls jyls=new Jyls();
                Field[] field = jyls.getClass().getDeclaredFields();
                while (rs.next()) {
                    Jyls jylsi=new Jyls();
                    for (int i = 0; i < colnames.length; i++) {
                        Field f = field[i];
                        f.setAccessible(true);
                        f.set(jylsi, rs.getObject(i + 1));
                    }
                    list.add(jylsi);
                }
            }else if(model.equals("Jyspmx")){
                Jyspmx jyspmx=new Jyspmx();
                Field[] field = jyspmx.getClass().getDeclaredFields();
                while (rs.next()) {
                    Jyspmx jyspmxi=new Jyspmx();
                    for (int i = 0; i < colnames.length; i++) {
                        Field f = field[i];
                        f.setAccessible(true);
                        f.set(jyspmxi, rs.getObject(i + 1));
                    }
                    list.add(jyspmxi);
                }
            }else if(model.equals("Kpspmx")){
                Kpspmx kpspmx=new Kpspmx();
                Field[] field = kpspmx.getClass().getDeclaredFields();
                while (rs.next()) {
                    Kpspmx kpspmxi=new Kpspmx();
                    for (int i = 0; i < colnames.length; i++) {
                        Field f = field[i];
                        f.setAccessible(true);
                        f.set(kpspmxi, rs.getObject(i + 1));
                    }
                    list.add(kpspmxi);
                }
            }

            rs.close();
            conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
