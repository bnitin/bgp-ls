/**
 *  Copyright 2013 Nitin Bahadur (nitinb@gmail.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */
package org.topology.bgp_ls.netty;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nitinb
 *
 */
public class SingletonRegistry {
   private static HashMap<String, Object> map = new HashMap<String, Object>();
   private static final Logger log = LoggerFactory.getLogger(SingletonRegistry.class);

   protected SingletonRegistry() {
	   // prevent instantiation of class
   }
   
   public static synchronized Object getInstance(String classname) {
      Object singleton = map.get(classname);
      if(singleton != null) {
         return singleton;
      }
      try {
         singleton = Class.forName(classname).newInstance();
         log.info("Created singleton: " + singleton);
      }
      catch(ClassNotFoundException cnf) {
         log.error("Couldn't find class " + classname);     
      }
      catch(InstantiationException ie) {
         log.error("Couldn't instantiate an object of type " + 
                       classname);    
      }
      catch(IllegalAccessException ia) {
         log.error("Couldn't access class " + classname);
      }
      map.put(classname, singleton);
      return singleton;
   }
   
   public static synchronized void addInstance(String classname, Object classObject) {
	   Object singleton = map.get(classname);
	   if (singleton != null) {
		   log.error("Cannot add new instance for " + classname + ", instance already exists");
	   }
	   map.put(classname,  classObject);
   }
   
   public static synchronized void deleteInstance(String classname) {
	   Object singleton = map.get(classname);
	   if (singleton != null) {
		   map.remove(singleton);
	   }
   }
}
