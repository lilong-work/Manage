package com.zb.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    /**
     * 通用的增删改方法
     *
     * @return 返回修改的行数
     * @throws Exception
     */
    public static int exeUpdate(String sql, Object... values) {
        Connection conn = DButils.getConnection();
        int result = 0;
        try {
            ps = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    ps.setObject(i + 1, values[i]);
                }
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 通用的查询方法
     * @param <T>
     *
     * @return 返回resultset结果集，注意接收以后要关闭资源
     * @throws Exception
     */
    // ...代表多个参数，类似于数组，但是可以代表0-n个 动态参数
    public static <T> T exeQueryone(String sql, RowMapper<T> rowMapper, Object... values) {
        T t =null;
        Connection conn = DButils.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    ps.setObject(i + 1, values[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                t=rowMapper.makeRow(rs);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 返回rs的时候，这里不要关闭connection，关闭了rs里面就没有了
        return t;
    }
    /**
     * 通用的查询方法
     * @param <T>
     *
     * @return 返回resultset结果集，注意接收以后要关闭资源
     * @throws Exception
     */
    // ...代表多个参数，类似于数组，但是可以代表0-n个 动态参数
    public static <T> List<T>  exeQuery(String sql, RowMapper<T> rowMapper, Object... values) {
        List<T> list =new ArrayList<>();
        Connection conn = DButils.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    ps.setObject(i + 1, values[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(rowMapper.makeRow(rs));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 返回rs的时候，这里不要关闭connection，关闭了rs里面就没有了
        return  list;
    }
    /**
     * 通用的查询方法
     * @param
     *
     * @return 返回resultset结果集，注意接收以后要关闭资源
     * @throws Exception
     */
    // ...代表多个参数，类似于数组，但是可以代表0-n个 动态参数
    public static ResultSet  exeQuery(String sql,Object... values) {
        ResultSet rs=null;
        Connection conn = DButils.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    ps.setObject(i + 1, values[i]);
                    System.out.println("BaseDao:"+values[i].toString());
                }
            }
            rs = ps.executeQuery();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 返回rs的时候，这里不要关闭connection，关闭了rs里面就没有了
        return  rs;
    }
}
