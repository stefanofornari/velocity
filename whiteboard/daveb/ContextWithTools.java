/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

import java.util.*;

import org.apache.velocity.context.AbstractContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.Runtime;
import org.apache.velocity.runtime.configuration.*;

/**
 * Just a demo context that loads it's own tools.
 *
 * @author <a href="mailto:daveb@miceda-data.com">Dave Bryson</a>
 * @version $Id: ContextWithTools.java 463298 2006-10-12 16:10:32Z henning $
 */
public class ContextWithTools extends AbstractContext
{

    public ContextWithTools()
    {
        super();
        loadTools();
    }

    /**
     * Load the tools specified in the Properties file
     * This can use a lot of work, but it works for testing.
     */
    private void loadTools()
    {
        String toolPackages = VelocityResources.getString("ContextTools");
        Enumeration tools = new StringTokenizer( toolPackages );
        try
        {
            while ( tools.hasMoreElements() )
            {
                String toolName = (String)tools.nextElement();
                Object o = Class.forName( toolName ).newInstance();
                ContextTool ct = (ContextTool)o;
                ct.setContext( this );
                put( ct.getName(), ct);
            }
        }
        catch ( Exception cnf )
        {
            Runtime.error( "Not loaded: " + cnf );
        }
    }

    // Below: Implements the AbstractContext stuff

     /** storage for key/value pairs */
    private HashMap context = new HashMap();


    /** chaining CTOR */
    public ContextWithTools( Context context )
    {
        super( context );
        loadTools();
    }

    public Object internalGet( String key )
    {
        return context.get( key );
    }

    public Object internalPut( String key, Object value )
    {
        return context.put( key, value );
    }

    public  boolean internalContainsKey(Object key)
    {
        return context.containsKey( key );
    }

    public  Object[] internalGetKeys()
    {
        return context.keySet().toArray();
    }

    public  Object internalRemove(Object key)
    {
        return context.remove( key );
    }

    /**
     * Clones this context object
     * @return Object an instance of this Context
     */
    public Object clone()
    {
        ContextWithTools clone = null;

        try
        {
            clone = (ContextWithTools) super.clone();
            clone.context = (HashMap) context.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
        }
        return clone;
    }
}





