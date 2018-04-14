package com.nestis.interview.tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * MongoDB Config class.
 * @author nestis
 *
 */
@Configuration
public class MongoConfiguration {

	/**
	 * Returns a MongoTemplate that ignores the field _class when inserting new values in db.
	 * @param mongoDbFactory MongoDbFactoryInstance.
	 * @return MongoTemplate.
	 * @throws Exception
	 */
	@Bean
	 public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) throws Exception {
		MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
		
		DbRefResolver dbRef = new DefaultDbRefResolver(mongoDbFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRef, new MongoMappingContext());
        converter.setTypeMapper(typeMapper);
			
		return new MongoTemplate(mongoDbFactory, converter);
	  }
}
