package com.example.azure.demo;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class DemoApplication {
    public static final String serviceName = "";
    public static final String adminKey = "";
    public static final String queryKey = "";
    public static final String indexName = "hotels-quickstart2";
    public static final String apiVersion = "2019-05-06";

    public static void main(String[] args) throws IOException {
        String url = "https://" + serviceName + ".search.windows.net/indexes/" + indexName + "?api-version=" + apiVersion;
        String indexDef = "{\n" +
                "  \"name\": \"hotels-quickstart2\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"name\": \"HotelId\",\n" +
                "      \"type\": \"Edm.String\",\n" +
                "      \"key\": true,\n" +
                "      \"filterable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"HotelName\",\n" +
                "      \"type\": \"Edm.String\",\n" +
                "      \"searchable\": true,\n" +
                "      \"filterable\": false,\n" +
                "      \"sortable\": true,\n" +
                "      \"facetable\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Description\",\n" +
                "      \"type\": \"Edm.String\",\n" +
                "      \"searchable\": true,\n" +
                "      \"filterable\": false,\n" +
                "      \"sortable\": false,\n" +
                "      \"facetable\": false,\n" +
                "      \"analyzer\": \"en.lucene\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Description_fr\",\n" +
                "      \"type\": \"Edm.String\",\n" +
                "      \"searchable\": true,\n" +
                "      \"filterable\": false,\n" +
                "      \"sortable\": false,\n" +
                "      \"facetable\": false,\n" +
                "      \"analyzer\": \"fr.lucene\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Category\",\n" +
                "      \"type\": \"Edm.String\",\n" +
                "      \"searchable\": true,\n" +
                "      \"filterable\": true,\n" +
                "      \"sortable\": true,\n" +
                "      \"facetable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Tags\",\n" +
                "      \"type\": \"Collection(Edm.String)\",\n" +
                "      \"searchable\": true,\n" +
                "      \"filterable\": true,\n" +
                "      \"sortable\": false,\n" +
                "      \"facetable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"ParkingIncluded\",\n" +
                "      \"type\": \"Edm.Boolean\",\n" +
                "      \"filterable\": true,\n" +
                "      \"sortable\": true,\n" +
                "      \"facetable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"LastRenovationDate\",\n" +
                "      \"type\": \"Edm.DateTimeOffset\",\n" +
                "      \"filterable\": true,\n" +
                "      \"sortable\": true,\n" +
                "      \"facetable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Rating\",\n" +
                "      \"type\": \"Edm.Double\",\n" +
                "      \"filterable\": true,\n" +
                "      \"sortable\": true,\n" +
                "      \"facetable\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Address\",\n" +
                "      \"type\": \"Edm.ComplexType\",\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"name\": \"StreetAddress\",\n" +
                "          \"type\": \"Edm.String\",\n" +
                "          \"filterable\": false,\n" +
                "          \"sortable\": false,\n" +
                "          \"facetable\": false,\n" +
                "          \"searchable\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"City\",\n" +
                "          \"type\": \"Edm.String\",\n" +
                "          \"searchable\": true,\n" +
                "          \"filterable\": true,\n" +
                "          \"sortable\": true,\n" +
                "          \"facetable\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"StateProvince\",\n" +
                "          \"type\": \"Edm.String\",\n" +
                "          \"searchable\": true,\n" +
                "          \"filterable\": true,\n" +
                "          \"sortable\": true,\n" +
                "          \"facetable\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"PostalCode\",\n" +
                "          \"type\": \"Edm.String\",\n" +
                "          \"searchable\": true,\n" +
                "          \"filterable\": true,\n" +
                "          \"sortable\": true,\n" +
                "          \"facetable\": true\n" +
                "        },\n" +
                "        {\n" +
                "          \"name\": \"Country\",\n" +
                "          \"type\": \"Edm.String\",\n" +
                "          \"searchable\": true,\n" +
                "          \"filterable\": true,\n" +
                "          \"sortable\": true,\n" +
                "          \"facetable\": true\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", adminKey);

        HttpEntity<String> entityReq = new HttpEntity<String>(indexDef, headers);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> respEntity = template
                .exchange(url, HttpMethod.PUT, entityReq, String.class);
        System.out.println(respEntity.toString());

    }
}
