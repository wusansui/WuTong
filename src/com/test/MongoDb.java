/**
 * 
 */
package com.test;

import java.util.HashMap;
import java.util.Map;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


/**
 *
 *<p>Title：MongoDb.java</p>
 * @author 吴彤
 * 何以解忧，唯有暴富。 
 * 2017年12月5日下午3:20:00
 */
public class MongoDb {
	
	//查询所有信息
	@Test
	public  void   textFindAll() throws  Exception{
    //创立连接		
	MongoClient client=new MongoClient("localhost",27017);
	//获取数据库对象
	MongoDatabase db = client.getDatabase("student");
	//获取数据库中表的信息
	MongoCollection<Document> collection = db.getCollection("user");
	//具体操作
	FindIterable<Document> iterable = collection.find();
	//获取游标对象
	MongoCursor<Document> it = iterable.iterator();
	while(it.hasNext()){
		//取出每一个对象
		Document next = it.next();
		String name = next.getString("name");
		Double age = next.getDouble("age");
		System.out.println("name"+name+",age"+age);
	}
	
	it.close();
	client.close();
		
	}
	//查询单个信息
	@Test
	public  void   textFindOne() throws  Exception{
		 //创立连接		
		MongoClient client=new MongoClient("localhost",27017);
		//获取数据库对象
		MongoDatabase db = client.getDatabase("student");
		//获取数据库中表的信息
		MongoCollection<Document> collection = db.getCollection("user");
		//具体操作
		Bson filter=new BasicDBObject("name","李四");
		FindIterable<Document> iterable = collection.find(filter);
		//获取游标对象
		MongoCursor<Document> it = iterable.iterator();
		while(it.hasNext()){
			//取出每一个对象
			Document next = it.next();
			String name = next.getString("name");
			Double age = next.getDouble("age");
			System.out.println("name"+name+",age"+age);
		}
		
		it.close();
		client.close();
			
	}
	

	//增加操作
	@Test
	public  void   textInsert() throws  Exception{
	//创立连接		
	MongoClient client=new MongoClient("localhost",27017);
	//获取数据库对象
	MongoDatabase db = client.getDatabase("student");
    //获取数据库中表的信息
	MongoCollection<Document> collection = db.getCollection("user");
      Map<String,Object>  map=new HashMap<>();
      map.put("name", "小可爱");
      map.put("age", 12);
      map.put("sex", "男");
      map.put("email", "222@qq.com");
      Map<String,Object> hobby=new HashMap<>();
      hobby.put("love", "小丑");
      
      Document document=new Document(map);
	  map.put("hobby", hobby);   
		   
      collection.insertOne(document);
      
      client.close();
			
	}

	

	//更改操作
	@Test
	public  void   textUpdate() throws  Exception{
		//创立连接		
		MongoClient client=new MongoClient("localhost",27017);
		//获取数据库对象
		MongoDatabase db = client.getDatabase("student");
	    //获取数据库中表的信息
		MongoCollection<Document> collection = db.getCollection("user");
		Bson filter=new BasicDBObject("_id",new ObjectId("5a2637be6712b64bf1fbe0f5"));
		Map<String, Object> map=new HashMap<>();
		map.put("name", "武大郎");
		map.put("age", 17);
		Bson update=new BasicDBObject(map);
		collection.updateOne(filter, new BasicDBObject("$set",update));
		client.close();
		
		
			
	}
	
	//删除操作
	@Test
	public  void   textRemove() throws  Exception{
		MongoClient client=new MongoClient("localhost",27017);
		//获取数据库对象
		MongoDatabase db = client.getDatabase("student");
	    //获取数据库中表的信息
		MongoCollection<Document> collection = db.getCollection("user");
	    collection.deleteOne(new BasicDBObject("_id",new ObjectId("5a2637be6712b64bf1fbe0f5")));
	    client.close();		
	}
	
	
}
