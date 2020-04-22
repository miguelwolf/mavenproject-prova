/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.miguelwolf.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Miguel Wolf
 */
@ApplicationPath("webresources")
public class RestConfig extends ResourceConfig{

	public RestConfig() {
		packages("br.com.miguelwolf.webresources");
	}
}
