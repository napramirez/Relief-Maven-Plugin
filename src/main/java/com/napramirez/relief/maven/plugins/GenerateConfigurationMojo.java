package com.napramirez.relief.maven.plugins;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.napramirez.relief.model.config.Configuration;
import com.napramirez.relief.model.config.Jre;
import com.napramirez.relief.model.config.Library;
import com.napramirez.relief.model.config.Project;

/**
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 * @description Generates a <a href="http://workingfrog.org">Relief</a> configuration file
 * @goal generate-config
 * @requiresDependencyResolution compile
 * @aggregator
 */
public class GenerateConfigurationMojo
    extends AbstractMojo
{
    private static final String MAVEN_PROJECT_PACKAGING_POM = "pom";

    private static final String ENV_VAR_JAVA_HOME = "JAVA_HOME";

    /**
     * Directory containing the generated JAR.
     * 
     * @parameter expression="${outputDirectory}" default-value="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    /**
     * Filename of the generated <a href="http://workingfrog.org">Relief</a> configuration.
     * 
     * @parameter expression="${outputFilename}" default-value="projects.xml"
     * @required
     */
    private String outputFilename;

    /**
     * @parameter expression="${reactorProjects}"
     * @read-only
     */
    private List<MavenProject> reactorProjects;

    public void execute()
        throws MojoExecutionException
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance( Configuration.class );
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

            Configuration configuration = new Configuration();

            List<Project> projects = new ArrayList<Project>();
            for ( MavenProject mavenProject : reactorProjects )
            {
                if ( !MAVEN_PROJECT_PACKAGING_POM.equals( mavenProject.getPackaging() ) )
                {
                    Project project = new Project();
                    project.setName( mavenProject.getName() );

                    Jre jre = new Jre();
                    String jrePath = System.getenv( ENV_VAR_JAVA_HOME );
                    if ( jrePath != null && !jrePath.trim().isEmpty() )
                    {
                        jre.setPath( jrePath );
                    }
                    project.setJre( jre );

                    project.setBuildDirectory( mavenProject.getBuild().getOutputDirectory() );
                    project.setSources( mavenProject.getCompileSourceRoots() );

                    Library library = new Library();
                    library.setFullPath( mavenProject.getCompileClasspathElements() );

                    project.setLibrary( library );
                    projects.add( project );
                }
            }

            configuration.setProjects( projects );

            if ( !outputDirectory.exists() || !outputDirectory.isDirectory() )
            {
                if ( !outputDirectory.mkdirs() )
                {
                    throw new IOException( "Failed to create directory " + outputDirectory.getAbsolutePath() );
                }
            }

            File outputFile = new File( outputDirectory, outputFilename );

            marshaller.marshal( configuration, outputFile );

            getLog().info( "Successfully generated configuration file " + outputFilename );
        }
        catch ( JAXBException e )
        {
            throw new MojoExecutionException( e.getMessage(), e );
        }
        catch ( DependencyResolutionRequiredException e )
        {
            throw new MojoExecutionException( e.getMessage(), e );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( e.getMessage(), e );
        }
    }
}
