package common.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;

@DataSourceDefinitions({

//	@DataSourceDefinition(
//		name="java:app/datasources/mssqlDS",
//		className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
//		url="jdbc:sqlserver://DMIT-Capstone1.ad.sast.ca;databaseName=DMIT2015_1213_E01_yourNaitUsername;TrustServerCertificate=true",   // Replace yourNaitUsername with your NAIT username
//		user="yourNaitUsername",  //  Replace yourNaitUsername with your NAIT username
//		password="RemotePassword.200012345"),    // Replace 200012345 with your NAIT StudentID


	@DataSourceDefinition(
		name="java:app/datasources/RemoteMssqlContosoDS",
		className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
		url="jdbc:sqlserver://DMIT-Capstone1.ad.sast.ca;databaseName=DMIT2015_1221_A01_swuUser2015;TrustServerCertificate=true",
		user="swuUser2015",
		password="RemotePassword.200012345"),


})

@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}