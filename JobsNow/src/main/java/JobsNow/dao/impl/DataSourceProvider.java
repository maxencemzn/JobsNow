package JobsNow.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource(){
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("pc1atezkhb77w07e");
            dataSource.setUser("xlx7ndytk9io8r8g");
            dataSource.setPassword("nnrpagoie3tys5ae");
        }
        return dataSource;
    }
}
