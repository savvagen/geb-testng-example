package com.example.ApiTests

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.testng.annotations.Test
import static groovyx.net.http.Method.*
import static groovyx.net.http.ContentType.*

class DemoTests {


    @Test
    void googleTest(){
        def http = new HTTPBuilder('http://www.google.com')

        def html = http.get( path : '/search', query : [q:'Groovy'] )
        assert html.HEAD.size() == 1
        assert html.BODY.size() == 1
    }

    @Test
    void googleTest2(){
        def http = new HTTPBuilder('http://www.google.com')

        http.get( path : '/search',
                contentType : TEXT,
                query : [q:'Groovy'] ) { resp, reader ->

            println "response status: ${resp.statusLine}"
            println 'Headers: -----------'
            resp.headers.each { h ->
                println " ${h.name} : ${h.value}"
            }
            println 'Response data: -----'
            System.out << reader
            println '\n--------------------'
        }
    }


    @Test
    void googleApiTest(){
        def http = new HTTPBuilder()

        http.request( 'http://ajax.googleapis.com', GET, TEXT ) { req ->
            uri.path = '/ajax/services/search/web'
            uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]
            headers.'User-Agent' = "Mozilla/5.0 Firefox/3.0.4"
            headers.Accept = 'application/json'

            response.success = { resp, reader ->
                assert resp.statusLine.statusCode == 200
                println "Got response: ${resp.statusLine}"
                println "Content-Type: ${resp.headers.'Content-Type'}"
                println reader.text
            }

            response.'404' = {
                println 'Not found'
            }
        }
    }


}
