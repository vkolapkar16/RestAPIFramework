package Utility;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
				"system:env",	
				"file:${user.dir}/src/test/resources/Dev.properties",
				"file:${user.dir}/src/test/resources/Uat.properties"
})

public interface FrameworkConfig extends Config
{
	@Key("${enviroement}.dburl")
	String dburl();
	@Key("${enviroement}.url")
	String url();
	@Key("${enviroement}.username")
    String username();
	@Key("${enviroement}.password")
    String password();
	@Key("${enviroement}.loginuserID")
	String loginuserID();
	@Key("${enviroement}.adminmail")
    String adminmail();
	@Key("${enviroement}.adminpwd")
    String adminpwd();
	
	@Key("${enviroement}.buyerGst")
	String buyerGst();
	
	@Key("${enviroement}.sellerGst")
	String sellerGst();
	
	@Key("${enviroement}.RetailerName")
	String RetailerName();
	
	@Key("${enviroement}.RetailerNameCollection")
	String RetailerNameCollection();
	
	@Key("${enviroement}.NBFCname")
	String NBFCname();
}
