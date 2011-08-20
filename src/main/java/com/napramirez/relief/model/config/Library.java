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
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class Library
{
    @XmlAttribute( name = "show" )
    private boolean show;

    @XmlElement( name = "path" )
    private List<String> path = new ArrayList<String>();

    @XmlElement( name = "fullPath" )
    private List<String> fullPath = new ArrayList<String>();

    public boolean isShow()
    {
        return show;
    }

    public void setShow( boolean show )
    {
        this.show = show;
    }

    public List<String> getPath()
    {
        return path;
    }

    public void setPath( List<String> path )
    {
        this.path = path;
    }

    public List<String> getFullPath()
    {
        return fullPath;
    }

    public void setFullPath( List<String> fullPath )
    {
        this.fullPath = fullPath;
    }
}
