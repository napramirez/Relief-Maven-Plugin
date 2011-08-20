package com.napramirez.relief.model.config;

/*
 * Copyright 2007 Nap Ramirez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "parser" )
@XmlAccessorType( XmlAccessType.FIELD )
public class Configuration
{
    public static final String DEFAULT_PARSER_NAME = "JavaParser";

    public static final String DEFAULT_DRIVER = "org.workingfrog.relief.plugins.java.JavaConfigHandler";

    @XmlAttribute
    private String name = DEFAULT_PARSER_NAME;

    @XmlElement
    private String driver = DEFAULT_DRIVER;

    @XmlElementWrapper( name = "projects", required = true )
    @XmlElement( name = "project" )
    private List<Project> projects = new ArrayList<Project>();

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDriver()
    {
        return driver;
    }

    public void setDriver( String driver )
    {
        this.driver = driver;
    }

    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects( List<Project> projects )
    {
        this.projects = projects;
    }
}
