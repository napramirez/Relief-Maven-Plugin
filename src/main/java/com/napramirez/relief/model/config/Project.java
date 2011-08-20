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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class Project
{
    @XmlAttribute( name = "name", required = true )
    private String name;

    @XmlElement( name = "jrePath", required = true )
    private Jre jre;

    @XmlElement( name = "baseDir", required = true )
    private String baseDirectory;

    @XmlElement( name = "blackbox", required = true, nillable = true )
    private Blackbox blackbox = new Blackbox();

    @XmlElement( name = "build", required = true, nillable = false )
    private String buildDirectory;

    @XmlElementWrapper( name = "src", nillable = true )
    @XmlElement( name = "path" )
    private List<String> sources;

    @XmlElement( name = "lib" )
    private Library library;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Jre getJre()
    {
        return jre;
    }

    public void setJre( Jre jre )
    {
        this.jre = jre;
    }

    public String getBaseDirectory()
    {
        return baseDirectory;
    }

    public void setBaseDirectory( String baseDirectory )
    {
        this.baseDirectory = baseDirectory;
    }

    public Blackbox getBlackbox()
    {
        return blackbox;
    }

    public void setBlackbox( Blackbox blackbox )
    {
        this.blackbox = blackbox;
    }

    public String getBuildDirectory()
    {
        return buildDirectory;
    }

    public void setBuildDirectory( String buildDirectory )
    {
        this.buildDirectory = buildDirectory;
    }

    public List<String> getSources()
    {
        return sources;
    }

    public void setSources( List<String> sources )
    {
        this.sources = sources;
    }

    public Library getLibrary()
    {
        return library;
    }

    public void setLibrary( Library library )
    {
        this.library = library;
    }
}
