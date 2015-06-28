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

import java.io.Serializable;
import org.apache.velocity.context.Context;

/**
 *
 * @author <a href="mailto:daveb@miceda-data.com">Dave Bryson</a>
 * @version $Id: ContextTool.java 463298 2006-10-12 16:10:32Z henning $
 */
public abstract class ContextTool implements Serializable
{
    /** Owner */
    protected Context context;

    /**
     * keep a pointer to my context
     * in case there's other stuff in there
     * that I might want to use in the Tool
     */
    public void setContext( Context c )
    {
        this.context = c;
    }

    /**
     * Provides the name for the context
     */
    public abstract String getName();
}


