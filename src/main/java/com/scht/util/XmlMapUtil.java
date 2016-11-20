package com.scht.util; /**
 * @(#) ResultMapUtil.java 2014/12/08 16:48
 * <p/>
 * 版权所有 (c) 北京银软网络技术有限公司
 * 北京市海淀区上地国际创业园西区1号
 * 保留所有权利
 */



import java.lang.reflect.Field;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author 孙鹏鹏
 * @version 1.0
 */
public class XmlMapUtil {
    private static String TABLE_PREFIX = "t";
    private static String Mapper_PreFix = "my";

    public static void main(String[] args) {
        /*Class clazz = LpPic.class;
        System.out.println(createTableSql(clazz));
        System.out.println(createResultMapper(clazz));
        System.out.println(createSaveSql(clazz));
        System.out.println(createUpdateSql(clazz));
        System.out.println(createFindSql(clazz));
        System.out.println(createListSql(clazz));*/
    }

    private static String getSQLName(String name, String pre) {
        char[] nameArray = name.toCharArray();
        StringBuffer buffer = new StringBuffer(pre);
        buffer.append("_");
        int i;
        for (i = 0; i < nameArray.length; i++) {
            if (nameArray[i] <= 'Z' && nameArray[i] >= 'A') {
                if (i != 0) {
                    buffer.append("_");
                }
                buffer.append(String.valueOf(nameArray[i]).toLowerCase());
            } else {
                buffer.append(nameArray[i]);
            }
        }
        String result = buffer.toString();
        buffer.setLength(0);
        return result;
    }

    public static String getTableName(String className) {
        return getSQLName(className, Mapper_PreFix);
    }


    public static String getColumnName(String fieldName) {
        return getSQLName(fieldName, "f");
    }


    private static String createResultMapper(Class clazz) {
        String tableName = getTableName(clazz.getSimpleName());
        StringBuffer buffer = new StringBuffer();
        buffer.append("<resultMap id=\"").append(tableName).append("\" type=\"").append(clazz.getName()).append("\">").append("\n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (name.equals("id")) {
                buffer.append(" <id column=\"").append(getColumnName(name)).append("\" property=\"").append(name).append("\"/>");
            } else {
                buffer.append(" <result column=\"").append(getColumnName(name)).append("\" property=\"").append(name).append("\"/>");
            }
            buffer.append("\n");
        }
        buffer.append("</resultMap>");
        return buffer.toString();
    }


    /**
     * <select id="save" parameterType="com.scht.wd.model.Role">
     * insert into wd_role(f_id,f_name,f_remark,f_status,f_create_time)
     * values (#{id},#{name,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR},#{status,jdbcType=VARCHAR},#{createTime,jdbcType=VARCHAR})
     * </select>
     *
     * @param clazz
     * @return
     */
    private static String createSaveSql(Class clazz) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<insert id=\"insert\" parameterType=\"").append(clazz.getName()).append("\">\n");
        buffer.append("insert into ").append(getSQLName(clazz.getSimpleName(), TABLE_PREFIX));
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (Field field : fields) {
            String name = field.getName();

            String columnName = getColumnName(name);
            columns.append(columnName).append(",");

            String typeName = field.getType().getSimpleName();
            //if (typeName.equals("String")) {
             //   values.append("#{").append(name).append(",jdbcType=VARCHAR},");
            //} else {
                values.append("#{").append(name).append("},");
            //}
        }
        buffer.append("(").append(columns.substring(0, columns.length() - 1)).append(")\n values(").append(values.substring(0, values.length() - 1)).append(")\n");
        buffer.append("</insert>");
        return buffer.toString();
    }

    /**
     * <select id="update" parameterType="com.scht.wd.model.Role">
     * update wd_role set f_name=#{name,jdbcType=VARCHAR},f_remark=#{remark,jdbcType=VARCHAR},f_status=#{status,jdbcType=VARCHAR},
     * f_create_time=#{createTime,jdbcType=VARCHAR}
     * where f_id=#{id}
     * </select>
     *
     * @param clazz
     * @return
     */
    private static String createUpdateSql(Class clazz) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<update id=\"update\" parameterType=\"").append(clazz.getName()).append("\">\n");
        buffer.append("update ").append(getSQLName(clazz.getSimpleName(), TABLE_PREFIX)).append(" \n <set> ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();

            String columnName = getColumnName(name);

            String typeName = field.getType().getSimpleName();
           // if (typeName.equals("String")) {
           //     buffer.append(columnName).append("=#{").append(name).append(",jdbcType=VARCHAR},");
           // } else {
            buffer.append("\n <if test=\""+name+"!=null and "+name+"!=''\"> \n");
                buffer.append(columnName).append(" = #{").append(name).append("}, \n");
            buffer.append("</if> ");
          //  }
        }
        String str = buffer.substring(0, buffer.length() - 1);
        str += "\n </set> \n where f_id=#{id}\n</update>";
        buffer.append("</update>");
        return str;
    }

    //    <select id="find" resultMap="my_role_bo">
//    select * from wd_role where f_id=#{id}
//    </select>
    private static String createFindSql(Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<select id=\"findById\" resultMap=\"").append(getSQLName(simpleName, Mapper_PreFix)).append("\">\n");
        buffer.append("select * from ").append(getSQLName(simpleName, TABLE_PREFIX)).append(" where f_id=#{id}\n</select>");
        return buffer.toString();
    }

    /**
     * <select id="list" resultMap="my_user_bo">
     * select * from wd_user where f_status='OPEN'
     * order by f_create_time desc
     * limit #{index},#{size}
     * </select>
     *
     * @param clazz
     * @return
     */
    private static String createListSql(Class clazz) {
        String simpleName = clazz.getSimpleName();
        StringBuffer buffer = new StringBuffer();
        buffer.append(" <select id=\"list\" resultMap=\"").append(getSQLName(simpleName, Mapper_PreFix)).append("\">\n");
        buffer.append("select * from ").append(getSQLName(simpleName, TABLE_PREFIX)).append(" where f_status='OPEN'").append("order by f_create_time desc limit #{index},#{size}\n");
        buffer.append("</select>");
        return buffer.toString();
    }

    private static String createTableSql(Class<?> clazz) {
        String tableName = getSQLName(clazz.getSimpleName(), TABLE_PREFIX);
        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE ").append(tableName).append("( \n");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            String columnName = getColumnName(name);
            String type = field.getType().getSimpleName();
//            buffer.append("\n");
            if ("f_id".equals(columnName)) {
                buffer.append(columnName).append(" varchar(32) primary key,");
            } else if ("f_remark".equals(columnName)) {
                buffer.append(columnName).append(" varchar(1000) default '',");
            } else if ("f_status".equals(columnName)) {
                buffer.append(columnName).append(" varchar(100) not null,");
            } else if (columnName.indexOf("money") > 0) {
                buffer.append(columnName).append(" varchar(100) default '0',");
            } else if (columnName.indexOf("time") >= 0) {
                buffer.append(columnName).append(" bigint default 0,");
            } else if ("f_name".equals(columnName)) {
                buffer.append(columnName).append(" varchar(100) not null,");
            } else if (type.equals("long")) {
                buffer.append(columnName).append(" bigint default 0,");
            } else if (type.equals("int")) {
                buffer.append(columnName).append(" int default 0,");
            } else if (type.equals("boolean")) {
                buffer.append(columnName).append(" char(1) default '0',");
            } else {
                buffer.append(columnName).append(" varchar(100) default '',");
            }
            buffer.append("\n");
        }
        String result = buffer.toString().substring(0, buffer.length() - 2);
        buffer.setLength(0);
        return result + "\n);";
    }

}