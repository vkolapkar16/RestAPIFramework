package Utility;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.aeonbits.owner.ConfigFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import java.time.Year;
import java.time.Month;

public class Mongoutil
{
	FrameworkConfig cfg;

public String date()
{
	LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todaysDate = currentDate.format(formatter);
    System.out.println("Invoice Date: " + todaysDate);
    return todaysDate;
}

public String Year()
{
	Year currentYear = Year.now();
	String yearAsString = String.valueOf(currentYear.getValue());
    return yearAsString;
	
}

public String Month()
{
	LocalDate currentDate = LocalDate.now();
	Month currentMonth = currentDate.getMonth();
	String nowMonth = String.valueOf(currentMonth.getValue());
    return nowMonth;

	
}


public String AdminID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("users");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("name","kashif iqbal");
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
					.first();
		String Str = lastEntry.toJson();
		String Adminid = Str.substring(18,42);
		System.out.println(Adminid);	
		return Adminid;
}


public String UserPanelID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("users");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("email",cfg.username());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
					.first();
		String Str = lastEntry.toJson();
		String Adminid = Str.substring(18,42);
		System.out.println(Adminid);	
		return Adminid;
}


public String UserID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("entities");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("gstin",cfg.buyerGst());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
					.first();
		String Str = lastEntry.toJson();
		String UID = Str.substring(18,42);
		System.out.println(UID);	
		return UID;
}



public String BUserID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("entities");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("gstin",cfg.buyerGst());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
			
			    .first();
		String Str = lastEntry.toJson();
		String BID = Str.substring(18,42);
		System.out.println(BID);	
		return BID;
}


public String SUserID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("entities");
	 Bson filter=Filters.eq("gstin",cfg.sellerGst());
	 System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter).first();
		String Str = lastEntry.toJson();
		String SID = Str.substring(18,42);
		System.out.println(SID);	
		return SID;
}


public String Collect_BuyerID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("collections");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("buyer_name",cfg.RetailerNameCollection());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("buyer");	
		Document lastEntry = collection.find(filter)
			    .projection(projection)
			    .first();
		String Str = lastEntry.toJson();
		String CID = Str.substring(18,42);
		System.out.println(CID);	
		return CID;
	}

public String Company_UserID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("entities");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("company_name",cfg.RetailerName());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
			    .first();
		String Str = lastEntry.toJson();
		String CUID = Str.substring(18,42);
		System.out.println(CUID);	
		return CUID;
}


public String NBFC_ID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("nbfcmodels");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("nbfc_name",cfg.NBFCname());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
			    .first();
		String Str = lastEntry.toJson();
		String InvoiceID = Str.substring(18,42);
		System.out.println(InvoiceID);	
		return InvoiceID;
	}


public String buyerRandomInvoiceID() throws IOException
{
	 cfg = ConfigFactory.create(FrameworkConfig.class, System.getenv(), System.getProperties());
	
	 ObjectId Id = new ObjectId("6425365e11f6443ab8b137b2");
	 String db="xuritidb";
		
	 MongoDB cl = new MongoDB();
	 MongoClient mongoclient=cl.getAllCollection();
	 MongoDatabase database= mongoclient.getDatabase(db);
	 MongoCollection<Document> collection=database.getCollection("invoices");
//	 Bson filter =Filters.and(Filters.eq("record_type", "SALESINVOICE"),Filters.eq("_id", new org.bson.types.ObjectId(objectIdString)));
	//Bson filter = Filters.eq("account", new org.bson.types.ObjectId(objectIdString));
	 Bson filter=Filters.eq("buyer_gst",cfg.buyerGst());
		
		System.out.println(filter);
		
		Bson projection = Projections.include("_id");	
		Document lastEntry = collection.find(filter)
				.sort(Sorts.descending("timestamp"))
			    .projection(projection)
			    .limit(1)
			    .first();
		String Str = lastEntry.toJson();
		String BINVOICEID = Str.substring(18,42);
		System.out.println(BINVOICEID);	
		return BINVOICEID;
}
}



