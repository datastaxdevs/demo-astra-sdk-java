package com.datastax.demo;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.astra.sdk.databases.domain.Database;
import com.datastax.astra.sdk.databases.domain.DatabaseInfo;
import com.datastax.astra.sdk.iam.domain.User;
import com.datastax.astra.sdk.streaming.domain.Tenant;

    
public class Sdk101 {
    
    
    
    public static void main(String[] args) {
        AstraClient client = AstraClient.builder()
                .databaseId("d31f5029-a6fc-40b1-8617-eb6d0e7b8980")           // Unique identifier for your instance
                .cloudProviderRegion("australiaeast")   // Cloud Provider region picked for you instance
                .keyspace("ks1")                          // (optional) Set your keyspace
                .appToken("AstraCS:vgFQsYhyZuNqiKLERYXnUMMD:b38b1f7811e5576533ba29e5f883954f8871a318527bff2772bde1f0cf470850")               // App Token will be used as ApiKey for Devops, Docs and REST Api.
                .clientId("vgFQsYhyZuNqiKLERYXnUMMD")     // Will be used as your username
                .clientSecret("85M-xAFcgD9JRAUD9FIy.wpHdcUFNhCeF-mPt,GZ2WuP+1YnPXU1Jc,kOfJ2KRz0xljlf0-Qt,n8oKM5MkM28ZfTjNlrJXFf3FQZE7ean0C5RiA6LMUyxP2Kp9BlHbMj")     // Will be used as your password
                .build();
        
        displayDevops(client);
        
       
        
        
    }

    
    public static void displayDevops(AstraClient client) {
           // ------------------------------
           // DEVOPS APIS: DATABASE 
           // ------------------------------
           System.out.println("What are running DB ?\n----");
           client.apiDevopsDatabases()
                      .databasesNonTerminated()
                      .map(Database::getInfo)
                      .map(DatabaseInfo::getName)
                      .forEach(System.out::println);
           
           // ------------------------------
           // DEVOPS APIS: IAM 
           // ------------------------------
           System.out.println("\nList of users:\n----");
           client.apiDevopsIam()
                      .users()
                      .map(User::getEmail)
                      .forEach(System.out::println);
           
           // ------------------------------
           // DEVOPS APIS: STREAMING 
           // ------------------------------
           System.out.println("\nList of Tenants:\n----");
           client.apiDevopsStreaming()
                      .tenants()
                      .map(Tenant::getTenantName)
                      .forEach(System.out::println);
           
       }
}
